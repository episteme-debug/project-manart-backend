package com.example.demo.Servicios.Facturacion;

import com.example.demo.Entidades.Factura;
import com.example.demo.Entidades.FacturaProducto;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.env.Environment;
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
}
