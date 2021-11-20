package com.culturaweb.wearefive.service;

import com.culturaweb.wearefive.dto.ZapatoDTO;
import org.springframework.stereotype.Service;

@Service
public class ZapatosServiceImpl implements IZapatosService{

    @Override
    public void agregarZapato(ZapatoDTO payload) {
        System.out.println("Agregando zapato!!!! :D");
        System.out.println("nombre: " + payload.getNombre());
        System.out.println("modelo: " + payload.getModelo());
    }

}
