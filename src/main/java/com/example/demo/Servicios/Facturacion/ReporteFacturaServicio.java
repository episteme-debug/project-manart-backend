package com.example.demo.Servicios.Facturacion;

import com.example.demo.Entidades.Factura;
import com.example.demo.Entidades.FacturaProducto;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Helper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ReporteFacturaServicio {

    private final Environment environment;
    private final FacturaServicio facturaServicio;
    private final JavaMailSender mailSender;

    public byte[] generarFacturaPDF(Long idFactura) throws Exception {
        Image imagen = ImageIO.read(new File("src/main/resources/static/empresarial/logo.png"));
        Factura factura = facturaServicio.obtenerFacturaPorId(idFactura);
        List<FacturaProducto> productos = factura.getFacturaProductos();

        JRBeanCollectionDataSource datosTabla = new JRBeanCollectionDataSource(productos);

        InputStream template = getClass().getResourceAsStream("/templates/Factura.jrxml");
        JasperReport reporte = JasperCompileManager.compileReport(template);

        Map<String, Object> params = new HashMap<>();
        params.put("nombreCliente", factura.getNombreCliente());
        params.put("nitCliente", factura.getNitCliente());
        params.put("emailCliente", factura.getEmailCliente());
        params.put("telefonoCliente", factura.getTelefonoCliente());
        params.put("ciudadCliente", factura.getCiudadCliente());
        params.put("direccionCliente", factura.getDireccionCliente());

        params.put("nombreEmpresa", environment.getProperty("empresa.nombre"));
        params.put("nitEmpresa", environment.getProperty("empresa.nit"));
        params.put("direccionEmpresa", environment.getProperty("empresa.direccion"));
        params.put("ciudadEmpresa", environment.getProperty("empresa.ciudad"));
        params.put("telefonoEmpresa", environment.getProperty("empresa.telefono"));
        params.put("emailEmpresa", environment.getProperty("empresa.email"));
        params.put("actividadEconomica", environment.getProperty("empresa.actividad"));
        params.put("logo", imagen);

        params.put("totalBruto", factura.getTotalBruto());
        params.put("iva", factura.getIVA());
        params.put("totalNeto", factura.getTotalNeto());
        params.put("totalItems", factura.getTotalItems());
        params.put("metodoPago", factura.getMetodoPago());
        params.put("valorLetras", factura.getValorLetras());
        params.put("observaciones", factura.getObservaciones());

        params.put("TableDataSource", datosTabla);

        JasperPrint print = JasperFillManager.fillReport(reporte, params, new JREmptyDataSource());

        return JasperExportManager.exportReportToPdf(print);
    }

    public void enviarPorEmail (Long idFactura) throws Exception {
        Factura factura = facturaServicio.obtenerFacturaPorId(idFactura);
        byte[] pdfFactura = generarFacturaPDF(idFactura);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        String asunto = "Factura ManArt # " + factura.getIdFactura() + " - " + factura.getNombreCliente();
        String cuerpoHtml = """
            <html>
            <body>
                <h2>Estimado/a %s,</h2>
                <p>Adjunto encontrará su factura electrónica #%s.</p>
                <p>Gracias por su preferencia.</p>
                <br>
                <p>Saludos cordiales,<br>
                Equipo de ManArt</p>
            </body>
            </html>
            """.formatted(factura.getNombreCliente(), factura.getIdFactura());
        String nombreArchivo = "Factura_" + factura.getIdFactura() + ".pdf";

        helper.setTo(factura.getEmailCliente());
        helper.setSubject("ManArt - Factura de compra");
        helper.setText(cuerpoHtml, true);

        ByteArrayResource pdfResource = new ByteArrayResource(pdfFactura);
        helper.addAttachment(nombreArchivo, pdfResource);

        mailSender.send(message);

    }





}
