package com.example.demo.Servicios;

import com.example.demo.DTOs.CarritoProductoDTO.AgregarItem;
import com.example.demo.DTOs.CarritoProductoDTO.RespuestaCarrito;
import com.example.demo.Entidades.*;
import com.example.demo.Enums.EntidadesArchivoMultimediaEnum;
import com.example.demo.Repositorios.CarritoCompraRepositorio;
import com.example.demo.Repositorios.ProductoRepositorio;
import com.example.demo.Repositorios.RelacionCarritoProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class RelacionCarritoProductoServicio {

    private final ArchivoMultimediaServicio archivoMultimediaServicio;
    private final CarritoCompraRepositorio carritoCompraRepositorio;
    private final RelacionCarritoProductoRepositorio relacionCarritoProductoRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public Long obtenerIdUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No hay un usuario autenticado.");
        }

        Usuario usuarioAutenticado = (Usuario) auth.getPrincipal();
        Long idUsuario = usuarioAutenticado.getIdUsuario();

        return idUsuario;
    }

    // Agrega un producto al carrito sin duplicarlo. Si ya existe, actualiza la cantidad.
    public RespuestaCarrito crearProducto(AgregarItem dto) throws BadRequestException {
        Long idProducto = dto.getIdProducto();
        Integer cantidad = dto.getCantidad();
        Long idUsuario = obtenerIdUsuarioAutenticado();

        Producto producto = productoRepositorio.findById(idProducto)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));

        // Buscar si ya existe una relación con ese producto en el carrito del usuario
        RelacionCarritoProducto relacion = relacionCarritoProductoRepositorio.findByProducto_IdProducto(idProducto);

        if (relacion != null) {
            return generarRespuesta(actualizarCantidadProductoExistente(relacion, cantidad));
        }

        return generarRespuesta(agregarNuevoProductoAlCarrito(producto, idUsuario, cantidad));
    }

    public RelacionCarritoProducto actualizarCantidad(Long idRelacion, Integer nuevaCantidad) {
        RelacionCarritoProducto relacion = relacionCarritoProductoRepositorio.findById(idRelacion)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        return actualizarCantidadProductoExistente(relacion, nuevaCantidad);
    }

    private RelacionCarritoProducto actualizarCantidadProductoExistente(RelacionCarritoProducto relacion, Integer cantidad) {
        relacion.setCantidad(cantidad);
        relacion.setSubtotal(relacion.getPrecioUnitario().multiply(BigDecimal.valueOf(cantidad)));
        return relacionCarritoProductoRepositorio.save(relacion);
    }

    private RelacionCarritoProducto agregarNuevoProductoAlCarrito(Producto producto, Long idUsuario, Integer cantidad) throws BadRequestException {

        CarritoCompra carrito = carritoCompraRepositorio.findByUsuarioIdUsuario(idUsuario);
        if (carrito == null) {
            throw new NoSuchElementException("Carrito no encontrado para el usuario con ID: " + idUsuario);
        }

        RelacionCarritoProducto nuevaRelacion = new RelacionCarritoProducto();
        nuevaRelacion.setProducto(producto);
        nuevaRelacion.setPrecioUnitario(producto.getPrecioProducto());
        nuevaRelacion.setCantidad(cantidad);
        nuevaRelacion.setSubtotal(producto.getPrecioProducto().multiply(BigDecimal.valueOf(cantidad)));
        nuevaRelacion.setCarritoCompra(carrito);

        actualizarStockProducto(producto, cantidad);

        return relacionCarritoProductoRepositorio.save(nuevaRelacion);
    }

    //. Traer a todo los productos de carrito
    public List<RespuestaCarrito> listarProductos(){
        List<RelacionCarritoProducto> items = relacionCarritoProductoRepositorio.findAll();
        List<RespuestaCarrito> listadoRespuesta = new ArrayList<>();

        for (RelacionCarritoProducto item : items) {
            RespuestaCarrito respuestaItem = generarRespuesta(item);
            listadoRespuesta.add(respuestaItem);
        }

        return listadoRespuesta;
    }

    //. Eliminar producto
    public void eliminarProducto(Long idCarritoXProducto){
        if (idCarritoXProducto == null || idCarritoXProducto <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
        if (!relacionCarritoProductoRepositorio.existsById(idCarritoXProducto)){
            throw new NoSuchElementException("Producto no existe");
        }
        relacionCarritoProductoRepositorio.deleteById(idCarritoXProducto);
    }

    public RespuestaCarrito generarRespuesta (RelacionCarritoProducto item) {
        RespuestaCarrito respuesta = new RespuestaCarrito();

        respuesta.setIdCarrito(item.getCarritoCompra().getIdCarrito());
        respuesta.setIdItem(item.getIdItem());
        respuesta.setIdProducto(item.getProducto().getIdProducto());
        respuesta.setNombreProducto(item.getProducto().getNombreProducto());
        respuesta.setCantidad(item.getCantidad());
        respuesta.setPrecioUnitario(item.getPrecioUnitario());
        respuesta.setSubtotal(item.getSubtotal());

        List<ArchivoMultimedia> archivos = archivoMultimediaServicio.listarArchivosPorEntidadYId(EntidadesArchivoMultimediaEnum.Producto, item.getProducto().getIdProducto());
        if(archivos.isEmpty()){
            respuesta.setImagenProducto("");
        } else {
            respuesta.setImagenProducto(archivos.getFirst().getRuta());
        }

        return respuesta;
    }

    public void actualizarStockProducto (Producto producto, Integer cantidad) throws BadRequestException {
        if (cantidad >= producto.getStockProducto() || cantidad <= 0)
            throw new IllegalArgumentException("Ingrese una cantidad válida. \nStock disponible: " + (producto.getStockProducto() - 1));

        if (producto.getStockProducto() == 0)
            throw new BadRequestException("No hay stock disponible para esta artesanía.");

        producto.setStockProducto(producto.getStockProducto() - cantidad);
    }


}
