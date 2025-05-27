package com.example.demo.Servicios;

import com.example.demo.Configuraciones.PayUConfig;
import com.example.demo.Entidades.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PayUServicio {

    private final PayUConfig config;

    public Map<String, String> prepararFormularioPago(Pedido pedido) {
        String referencia = "pedido-" + pedido.getIdPedido();
        String monto = pedido.getTotal().setScale(2, RoundingMode.HALF_UP).toPlainString();
        String firma = generarFirma(config.getApiKey(), config.getMerchantId(), referencia, monto, config.getCurrency());

        /* https://developers.payulatam.com/latam/es/docs/getting-started/test-your-solution.html */
        Map<String, String> datos = new HashMap<>();
        datos.put("merchantId", config.getMerchantId());
        datos.put("accountId", config.getAccountId());
        datos.put("description", "Compra de productos artesanales");
        datos.put("referenceCode", referencia);
        datos.put("amount", monto);
        datos.put("currency", config.getCurrency());
        datos.put("signature", firma);
        datos.put("test", config.isTest() ? "1" : "0");
        datos.put("buyerEmail", pedido.getUsuario().getEmailUsuario());
        datos.put("responseUrl", "https://tusitio.com/respuesta");
        datos.put("confirmationUrl", "https://tusitio.com/confirmacion");

        return datos;
    }

    private String generarFirma(String apiKey, String merchantId, String ref, String monto, String moneda) {
        String base = apiKey + "~" + merchantId + "~" + ref + "~" + monto + "~" + moneda;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generando la firma", e);
        }
    }
}

