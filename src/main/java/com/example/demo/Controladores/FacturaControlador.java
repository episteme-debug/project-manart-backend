package com.example.demo.Controladores;

import com.example.demo.Servicios.Facturacion.FacturaServicio;
import com.example.demo.Servicios.Facturacion.ReporteFacturaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/factura")
@RequiredArgsConstructor
@RestController
public class FacturaControlador {

    private final FacturaServicio facturaServicio;
    private final ReporteFacturaServicio reporteFacturaService;

    @GetMapping("private/obtenerporid/{idFactura}/pdf")
    @PreAuthorize("@autorizacion.esPropietarioFactura(#idFactura) or hasRole('ADMIN')")
    public ResponseEntity<byte[]> generarPDF(@PathVariable Long idFactura) throws Exception {
        byte[] pdf = reporteFacturaService.generarFacturaPDF(idFactura);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("factura_" + idFactura + ".pdf").build());

        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @PostMapping("private/crearfactura/{idPedido}/{metodo}")
    public ResponseEntity<?> crearFactura (@PathVariable Long idPedido, @PathVariable String metodo) {
        try {
            facturaServicio.crearFactura(idPedido, metodo);
            return ResponseEntity.ok("OK");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}
