package com.example.demo.Servicios.Facturacion;

import com.example.demo.Entidades.*;
import com.example.demo.Enums.EstadoPedidoEnum;
import com.example.demo.Repositorios.DireccionRepositorio;
import com.example.demo.Repositorios.FacturaProductoRepositorio;
import com.example.demo.Repositorios.FacturaRepositorio;
import com.example.demo.Repositorios.PedidoRepositorio;
import com.example.demo.Servicios.DireccionServicio;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Builder
@RequiredArgsConstructor
@Service
public class FacturaServicio {

    private final ConversorNumeroLetra conversor;
    private final DireccionRepositorio direccionRepositorio;
    private final DireccionServicio direccionServicio;
    private final FacturaProductoRepositorio facturaProductoRepositorio;
    private final FacturaRepositorio facturaRepositorio;
    private final PedidoRepositorio pedidoRepositorio;

    public void crearFactura (Long idPedido, String metodoPago) throws Exception {
        Factura factura = new Factura();
        Pedido pedido = verificarPedido(idPedido);
        verificarExistenciaFactura(idPedido);

        String nombreCompleto = pedido.getUsuario().getNombreUsuario() + " " + pedido.getUsuario().getApellidoUsuario();
        BigDecimal porcentajeIVA = BigDecimal.valueOf(0.19);
        BigDecimal totalBruto = pedido.getTotal();
        BigDecimal IVA = totalBruto.multiply(porcentajeIVA);
        BigDecimal totalNeto = totalBruto.add(IVA);
        List<Direccion> direcciones = direccionRepositorio.findByUsuario_IdUsuario(pedido.getUsuario().getIdUsuario());
        Direccion direccion;
        if (direcciones.isEmpty()) {
            direccion = new Direccion();
        } else {
            direccion = direcciones.getFirst();
        }


        factura.setTotalBruto(totalBruto);
        factura.setIVA(IVA);
        factura.setTotalNeto(totalNeto);
        factura.setMetodoPago(metodoPago);
        factura.setObservaciones("");
        factura.setValorLetras(conversor.convertir(totalNeto));

        factura.setNombreCliente(nombreCompleto);
        factura.setEmailCliente(pedido.getUsuario().getEmailUsuario());
        factura.setTelefonoCliente(pedido.getUsuario().getTelefonoUsuario());
        factura.setNitCliente(pedido.getUsuario().getNumeroDocumentoUsuario());
        factura.setDireccionCliente(direccionServicio.construirDireccionComoTexto(direccion));
        factura.setCiudadCliente(direccion.getCiudad() + " - " + direccion.getDepartamento());

        List<FacturaProducto> detalleFactura = crearDetalleFactura(pedido.getRelacionPedidoProductos(), factura);
        factura.setFacturaProductos(detalleFactura);
        factura.setTotalItems(detalleFactura.size());
        factura.setPedido(pedido);

        facturaRepositorio.save(factura);
    }

    public List<FacturaProducto> crearDetalleFactura (List<RelacionPedidoProducto> items, Factura factura) {
        List<FacturaProducto> itemsFactura = new ArrayList<>();

        for (RelacionPedidoProducto item : items) {
            FacturaProducto itemFactura = new FacturaProducto();

            itemFactura.setIdProducto(item.getProducto().getIdProducto());
            itemFactura.setDescripcion(item.getProducto().getNombreProducto());
            itemFactura.setCantidad(item.getCantidad());
            itemFactura.setPrecioUnitario(item.getPrecioUnitario());
            itemFactura.setPrecioTotal(item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad())));
            itemFactura.setFactura(factura);

            itemsFactura.add(itemFactura);
        }

        return itemsFactura;
    }

    public Factura obtenerFacturaPorId (Long idFactura) {
        return facturaRepositorio.findById(idFactura)
                .orElseThrow(() -> new NoSuchElementException("Factura no encontrada"));
    }

    public Factura obtenerFacturaPorPedido(Long idPedido) {
        Pedido pedido = pedidoRepositorio.findById(idPedido)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));

        Factura factura = facturaRepositorio.findByPedido_IdPedido(idPedido)
                .orElseThrow(() -> new NoSuchElementException("Factura no encontrada"));

        return factura;
    }

    public Pedido verificarPedido (Long idPedido) {
        Pedido pedido = pedidoRepositorio.findById(idPedido)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));

        if (pedido.getEstado() != EstadoPedidoEnum.COMPLETADO)
            throw new IllegalArgumentException("La factura no puede ser generada porque el pago del pedido no fue exitoso");

        return pedido;
    }

    public void verificarExistenciaFactura (Long idPedido) {
        Optional<Factura> factura = facturaRepositorio.findByPedido_IdPedido(idPedido);

        if (factura.isPresent()) {
            throw new IllegalArgumentException("Ya existe una factura asociada a este pedido con id " + factura.get().getIdFactura());
        }
    }

}
