package com.example.demo.Servicios;

import com.example.demo.Entidades.*;
import com.example.demo.Enums.MetodoPagoEnum;
import com.example.demo.Repositorios.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PedidoServicio {

    private final CarritoCompraRepositorio carritoCompraRepositorio;
    private final RelacionCarritoProductoRepositorio relacionCarritoProductoRepositorio;
    private final PedidoRepositorio pedidoRepositorio;
    private final RelacionPedidoProductoRepositorio relacionPedidoProductoRepositorio;
    private final ProductoServicio productoServicio;

    public Long obtenerIdUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No hay un usuario autenticado.");
        }

        Usuario usuarioAutenticado = (Usuario) auth.getPrincipal();
        Long idUsuario = usuarioAutenticado.getIdUsuario();

        return idUsuario;
    }

    //. Validar que el carrito exista y no esté vacío
    public CarritoCompra validarCarrito(Long idUsuario) {
        CarritoCompra carritoCompra = carritoCompraRepositorio.findByUsuarioIdUsuario(idUsuario);

        if (carritoCompra == null) {
            throw new NoSuchElementException("El usuario no cuenta con ningún carrito activo.");
        }

        List<RelacionCarritoProducto> listaProductos = carritoCompra.getRelacionCarritoProductos();

        if (listaProductos == null || listaProductos.isEmpty()) {
            throw new IllegalStateException("El carrito no puede estar vacío.");
        }

        return carritoCompra;
    }


    //. Tranferencia de datos de carrito a pedido (subtotal-total, usuario)
    public Pedido transferirDatosPedido(CarritoCompra carritoCompra, MetodoPagoEnum metodoPago) {
        Pedido pedido = new Pedido();

        pedido.setUsuario(carritoCompra.getUsuario());
        pedido.setMetodoPago(metodoPago);
        pedido.setSubtotal(carritoCompra.getTotal());

        BigDecimal descuento = carritoCompra.getTotal().multiply(BigDecimal.valueOf(0.10));
        pedido.setDescuento(descuento);
        pedido.setTotal(carritoCompra.getTotal().subtract(descuento));

        pedido = pedidoRepositorio.save(pedido);

        List<RelacionPedidoProducto> productosPedido = new ArrayList<>();
        List<RelacionCarritoProducto> productosCarrito = carritoCompra.getRelacionCarritoProductos();

        for (int i = 0; i < productosCarrito.size(); i++) {
            RelacionPedidoProducto productoPedido = new RelacionPedidoProducto();
            RelacionCarritoProducto productoCarrito = productosCarrito.get(i);

            productoPedido.setProducto(productoCarrito.getProducto());
            productoPedido.setPrecioUnitario(productoCarrito.getPrecioUnitario());
            productoPedido.setCantidad(productoCarrito.getCantidad());
            productoPedido.setSubtotal(productoCarrito.getSubtotal());
            productoPedido.setPedido(pedido);

            // Restar cantidad al stock
            productoServicio.actualizarStock(productoCarrito.getProducto(), productoCarrito.getCantidad(), false);

            productosPedido.add(productoPedido);
        }

        relacionPedidoProductoRepositorio.saveAll(productosPedido);
        pedido.setRelacionPedidoProductos(productosPedido);

        return pedido;
    }

    //. Guardar pedido en la base de datos
    public void guardarPedido(Pedido pedido){
        pedidoRepositorio.save(pedido);
    }

    //. Vaciar el carrito de productos
    public void vaciarCarrito(CarritoCompra carritoCompra) {
        List<RelacionCarritoProducto> relaciones = carritoCompra.getRelacionCarritoProductos();

        for (RelacionCarritoProducto relacion : relaciones) {
            relacionCarritoProductoRepositorio.delete(relacion);
        }

        relaciones.clear();
    }

    //. Comprar, ejecuta todos los métodos anteriores
    @Transactional
    public Pedido comprar(MetodoPagoEnum metodoPago) {
        Long idUsuario = obtenerIdUsuarioAutenticado();
        try {
            CarritoCompra carritoCompra = validarCarrito(idUsuario);
            Pedido pedido = transferirDatosPedido(carritoCompra, metodoPago);
            guardarPedido(pedido);
            vaciarCarrito(carritoCompra);

            return pedido;
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la compra: " + e.getMessage(), e);
        }
    }


}
