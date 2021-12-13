package com.culturaweb.wearefive.controller;

import com.culturaweb.wearefive.dto.DetalleCompraDTO;
import com.culturaweb.wearefive.service.IComprasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DetalleCompraDTO> comprarUnEjemplar(@PathVariable(value = "idModelo") int idModelo, @PathVariable(value = "talla") String talla, HttpServletRequest request){
        DetalleCompraDTO r = this.comprasService.comprarUnEjemplar(idModelo,talla,request.getHeader("Authorization"));
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
}
