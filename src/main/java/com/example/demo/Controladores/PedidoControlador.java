package com.example.demo.Controladores;

import com.example.demo.Entidades.Pedido;
import com.example.demo.Enums.MetodoPagoEnum;
import com.example.demo.Servicios.PayUServicio;
import com.example.demo.Servicios.PedidoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin("http://127.0.0.1:3000")
@RequestMapping("/api/pedido/")
@RequiredArgsConstructor
@RestController
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;
    private final PayUServicio payUServicio;

    @PostMapping("private/comprar/{metodoPago}")
    public ResponseEntity<?> comprar(@PathVariable String metodoPago) {
        try {
            MetodoPagoEnum metodoPagoEnum = MetodoPagoEnum.valueOf(metodoPago.toUpperCase());
            Pedido pedido = pedidoServicio.comprar(metodoPagoEnum);

            Map<String, String> datosFormulario = payUServicio.prepararFormularioPago(pedido);
            return ResponseEntity.ok(datosFormulario);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }


}
