package com.example.demo.Controladores;

import com.example.demo.DTOs.CompraDTO;
import com.example.demo.Enums.MetodoPagoEnum;
import com.example.demo.Servicios.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/pedido/")
@RestController
public class PedidoControlador {

    @Autowired
    PedidoServicio pedidoServicio;

    @PostMapping("private/comprar")
    public ResponseEntity<?> comprar(@RequestBody CompraDTO compraDTO) {
        try {
            MetodoPagoEnum metodoPagoEnum = MetodoPagoEnum.valueOf(compraDTO.getMetodoPago().toUpperCase());
            pedidoServicio.comprar(compraDTO.getIdUsuario(), metodoPagoEnum);
            return ResponseEntity.ok().build();

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }


}
