package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.DetalleCompraDTO;
import com.culturaweb.wearefive.service.IComprasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/cart")
public class ComprasController {

    IComprasService comprasService;

    public ComprasController(IComprasService comprasService) {
        this.comprasService = comprasService;
    }

    @GetMapping("/{idModelo}/{talla}/buy")
    @Secured("ROLE_CLIENTE")
    public ResponseEntity<DetalleCompraDTO> comprarUnEjemplar(@PathVariable(value = "idModelo") int idModelo, @PathVariable(value = "talla") String talla, HttpServletRequest request){
        DetalleCompraDTO r = this.comprasService.comprarUnEjemplar(idModelo,talla,request.getHeader("Authorization"));
        return new ResponseEntity<>(r,HttpStatus.OK);
    }

    @GetMapping("/{idModelo}/{idEjemplar}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> confirmarVenta(@PathVariable(value = "idModelo") int idModelo, @PathVariable(value = "idEjemplar") int idEjemplar){
        this.comprasService.confirmarVenta(idModelo,idEjemplar);
        return new ResponseEntity<>("venta confirmada!",HttpStatus.OK);
    }
}
