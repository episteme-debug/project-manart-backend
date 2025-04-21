package com.example.demo.Controladores;

import com.example.demo.DTOs.CompraDTO;
import com.example.demo.Enums.MetodoPagoEnum;
import com.example.demo.Servicios.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/pedido/")
@RestController
public class PedidoControlador {

    @Autowired
    PedidoServicio pedidoServicio;

    @PostMapping("/comprar")
    public ResponseEntity<Void> comprar(@RequestBody CompraDTO compraDTO) {
        MetodoPagoEnum metodoPagoEnum = MetodoPagoEnum.valueOf(compraDTO.getMetodoPago());
        pedidoServicio.comprar(compraDTO.getIdUsuario(), metodoPagoEnum);
        return ResponseEntity.ok().build();
    }


}
