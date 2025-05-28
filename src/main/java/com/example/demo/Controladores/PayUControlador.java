package com.example.demo.Controladores;

import com.example.demo.Servicios.PasarelaPago.NotificacionPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/pago/")
@RequiredArgsConstructor
@RestController
public class PayUControlador {

    private final NotificacionPagoServicio notificacionPagoServicio;

    @PostMapping("public/notificacion")
    public ResponseEntity<String> recibirNotificacion(@RequestParam Map<String, String> payload) {
        System.out.println("Notificacion recibida");
        System.out.println("Firma esperada: " + notificacionPagoServicio.generarFirma(payload));
        System.out.println("Firma recibida: " + payload.get("sign"));
        System.out.println("value recibido: [" + payload.get("value") + "]");
        System.out.println(payload.get("merchant_id"));
        System.out.println(payload.get("reference_sale"));
        System.out.println(payload.get("value"));
        System.out.println(payload.get("currency"));
        System.out.println(payload.get("state_pol"));
        try {
            notificacionPagoServicio.procesarNotificacion(payload);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error procesando notificación");
        }
    }

}
