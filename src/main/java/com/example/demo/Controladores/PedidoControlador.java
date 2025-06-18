package com.example.demo.Controladores;

import com.example.demo.Entidades.Pedido;
import com.example.demo.Servicios.PasarelaPago.FormularioPagoServicio;
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
    private final FormularioPagoServicio formularioPagoServicio;

    @PostMapping("private/comprar")
    public ResponseEntity<?> comprar() {
        try {
            Pedido pedido = pedidoServicio.comprar();

            Map<String, String> datosFormulario = formularioPagoServicio.prepararFormularioPago(pedido);
            return ResponseEntity.ok(datosFormulario);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }


}
