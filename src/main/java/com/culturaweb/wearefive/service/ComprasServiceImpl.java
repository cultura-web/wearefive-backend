package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.DetalleCompraDTO;
import com.culturaweb.wearefive.exceptions.EjemplarNoExisteException;
import com.culturaweb.wearefive.exceptions.EjemplarNoReservadoException;
import com.culturaweb.wearefive.exceptions.ModeloDeZapatoNoExisteException;
import com.culturaweb.wearefive.exceptions.SinStockException;
import com.culturaweb.wearefive.model.EjemplarZapato;
import com.culturaweb.wearefive.model.Factura;
import com.culturaweb.wearefive.model.ModeloZapato;
import com.culturaweb.wearefive.model.Usuario;
import com.culturaweb.wearefive.repository.IEjemplarZapatoRepository;
import com.culturaweb.wearefive.repository.IFacturaRepository;
import com.culturaweb.wearefive.repository.IModeloZapatoRepository;
import com.culturaweb.wearefive.repository.IUsuarioRepository;
import com.culturaweb.wearefive.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComprasServiceImpl implements IComprasService {

    IModeloZapatoRepository modeloRepository;
    IUsuarioRepository usuarioRepository;
    IEjemplarZapatoRepository ejemplarZapatoRepository;
    IFacturaRepository facturaRepository;
    EmailServiceImpl emailService;
    JwtUtil jwtUtil;

    public ComprasServiceImpl(IModeloZapatoRepository modeloRepository, IUsuarioRepository usuarioRepository, IEjemplarZapatoRepository ejemplarZapatoRepository, IFacturaRepository facturaRepository, EmailServiceImpl emailService, JwtUtil jwtUtil) {
        this.modeloRepository = modeloRepository;
        this.usuarioRepository = usuarioRepository;
        this.ejemplarZapatoRepository = ejemplarZapatoRepository;
        this.facturaRepository = facturaRepository;
        this.emailService = emailService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public DetalleCompraDTO comprarUnEjemplar(int idModelo, String talla, String token) {
        ModeloZapato modelo = getModeloZapato(idModelo);
        EjemplarZapato ejemplar = null;
        for (EjemplarZapato e : modelo.getEjemplares()) {
            if (e.getTalla().equals(talla) && e.getStatus().equals("libre")) {
                ejemplar = e;
                ejemplar.setStatus("reservado");
                this.ejemplarZapatoRepository.save(ejemplar);
                break;
            }
        }
        if (ejemplar == null)
            throw new SinStockException();

        Usuario usuario = getUsuarioByToken(token.substring(7));
        //this.modeloRepository.save(modelo);

        int total = (modelo.getPrecioUnitario() * (100 - modelo.getDescuento())) / 100;
        Factura f = new Factura(
                UUID.randomUUID().toString(),
                total,
                LocalDate.now(),
                "confirmación pendiente",
                ejemplar.getId(),
                usuario.getCliente(),
                modelo);
        this.facturaRepository.save(f);

        DetalleCompraDTO r = new DetalleCompraDTO(modelo.getNombre(),ejemplar.getTalla(),f.getFechaCompra(),total,modelo.getDescuento(),f.getStatus(),usuario.getCliente().getDireccion());//TODO

        this.emailService.enviarEmailDeCompra(usuario.getCorreo(),r.toString());
        this.emailService.enviarEmailDeVenta(f.toString());

        return r;
    }

    @Override
    @Transactional
    public String confirmarVenta(int idModelo, int idEjemplar) {
        ModeloZapato modelo = getModeloZapato(idModelo);
        Optional<EjemplarZapato> optional = this.ejemplarZapatoRepository.findById(idEjemplar);
        if(optional.isEmpty())
            throw new EjemplarNoExisteException();
        EjemplarZapato ejemplar = optional.get();

        if(!ejemplar.getStatus().equals("reservado")){
            throw new EjemplarNoReservadoException();
        }
        modelo.getEjemplares().remove(ejemplar);
        this.modeloRepository.save(modelo);
        this.ejemplarZapatoRepository.deleteByIdEquals(ejemplar.getId());
        return "OK";
    }

    private ModeloZapato getModeloZapato(int id) {
        Optional<ModeloZapato> optional = this.modeloRepository.findById(id);
        if (optional.isEmpty())
            throw new ModeloDeZapatoNoExisteException();
        return optional.get();
    }

    private Usuario getUsuarioByToken(String token){
        String username = jwtUtil.getUsername(token);
        return usuarioRepository.findByUsernameEquals(username);
    }
}
