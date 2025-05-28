package com.example.demo.Servicios.PasarelaPago;

import com.example.demo.Configuraciones.PayUConfig;
import com.example.demo.Entidades.Pedido;
import com.example.demo.Enums.EstadoPedidoEnum;
import com.example.demo.Repositorios.PedidoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NotificacionPagoServicio {

    private final PayUConfig payUConfig;
    private final PedidoRepositorio pedidoRepositorio;

    public void procesarNotificacion(Map<String, String> payload) {
        String referencia = payload.get("reference_sale");
        Long idPedido = Long.parseLong( referencia.replace("pedido-", ""));
        String estado = payload.get("state_pol");
        String firmaRecibida = payload.get("sign");

        Pedido pedido = pedidoRepositorio.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Verificar firma
        String firmaEsperada = generarFirma(payload);
        if (!firmaEsperada.equals(firmaRecibida)) {
            throw new SecurityException("Firma inválida");
        }

        // Cambiar estado del pedido
        cambiarEstadoPedido(estado, pedido);
    }


    public String generarFirma(Map<String, String> payload) {
        String apiKey = payUConfig.getApiKey();
        String merchantId = payload.get("merchant_id");
        String referenceSale = payload.get("reference_sale");

        String rawValue = payload.get("value");
        BigDecimal valor = new BigDecimal(rawValue);
        String value;
        if (valor.scale() > 1 && valor.remainder(BigDecimal.ONE).multiply(BigDecimal.TEN).stripTrailingZeros().scale() > 0) {
            value = valor.setScale(2, RoundingMode.HALF_UP).toPlainString();
        } else {
            value = valor.setScale(1, RoundingMode.HALF_UP).toPlainString();
        }

        String currency = payload.get("currency");
        String statePol = payload.get("state_pol");

        String base = apiKey + "~" + merchantId + "~" + referenceSale + "~" + value + "~" + currency + "~" + statePol;

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

    public void cambiarEstadoPedido (String estado, Pedido pedido) {
        switch (estado) {
            case "4": // Transacción aprobada
                pedido.setEstado(EstadoPedidoEnum.COMPLETADO);
                break;
            case "6": // Transacción Rechazada
                pedido.setEstado(EstadoPedidoEnum.DECLINADO);
                break;
            case "104":
                pedido.setEstado(EstadoPedidoEnum.ERROR);
                break;
            default:
                pedido.setEstado(EstadoPedidoEnum.PENDIENTE);
        }
        pedidoRepositorio.save(pedido);
    }
}
