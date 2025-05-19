package com.example.demo.Servicios;

import com.example.demo.DTOs.RelCarritoProductoDTO;
import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.RelacionCarritoProducto;
import com.example.demo.Repositorios.CarritoCompraRepositorio;
import com.example.demo.Repositorios.ProductoRepositorio;
import com.example.demo.Repositorios.RelacionCarritoProductoRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RelacionCarritoProductoServicio {

    @Autowired
    RelacionCarritoProductoRepositorio relacionCarritoProductoRepositorio;

    @Autowired
    ProductoRepositorio productoRepositorio;

    @Autowired
    CarritoCompraRepositorio carritoCompraRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    // Agrega un producto al carrito sin duplicarlo. Si ya existe, actualiza la cantidad.
    public RelacionCarritoProducto crearProducto(RelCarritoProductoDTO productoDTO) {
        Long idProducto = productoDTO.getProducto().getIdProducto();
        Long idUsuario = productoDTO.getUsuario().getIdUsuario();
        // Validacion de datos
        validarDTO(productoDTO, idProducto, idUsuario);

        // Verifica si el producto ya está en el carrito, de estar no lo vuelve a insertar, solamente modifica la cantidad
        RelacionCarritoProducto relacionExistente = relacionCarritoProductoRepositorio
                .findByProducto_IdProducto(idProducto);

        if (relacionExistente != null) {
            relacionExistente.setCantidad(productoDTO.getCantidad());
            return relacionCarritoProductoRepositorio.save(relacionExistente);
        }

        // De no estar agrega el producto
        Producto producto = productoRepositorio.findById(idProducto).get();

        CarritoCompra carrito = carritoCompraRepositorio.findByUsuarioIdUsuario(idUsuario);
        if (carrito == null) {
            throw new NoSuchElementException("Carrito no encontrado para el usuario con ID: " + idUsuario);
        }

        RelacionCarritoProducto nuevaRelacion = new RelacionCarritoProducto();
        nuevaRelacion.setProducto(producto);
        nuevaRelacion.setPrecioUnitario(producto.getPrecioProducto());
        nuevaRelacion.setCantidad(productoDTO.getCantidad());
        nuevaRelacion.setSubtotal(productoDTO.getSubtotal());
        nuevaRelacion.setCarritoCompra(carrito);

        return relacionCarritoProductoRepositorio.save(nuevaRelacion);
    }

    // Submetodo de crear, valida la informacion del dto
    public void validarDTO (RelCarritoProductoDTO productoDTO, Long idProducto, Long idUsuario) {
        if (productoDTO == null) {
            throw new IllegalArgumentException("El DTO del producto no puede ser nulo.");
        }

        if (productoDTO.getProducto() == null || productoDTO.getProducto().getIdProducto() == null) {
            throw new IllegalArgumentException("El producto o su ID no pueden ser nulos.");
        }

        if (productoDTO.getUsuario() == null || productoDTO.getUsuario().getIdUsuario() == null) {
            throw new IllegalArgumentException("El usuario o su ID no pueden ser nulos.");
        }

        if (productoDTO.getCantidad() == null || productoDTO.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }

        if (productoDTO.getSubtotal() == null || productoDTO.getSubtotal().doubleValue() <= 0) {
            throw new IllegalArgumentException("El subtotal debe ser mayor que cero.");
        }

        if (!productoRepositorio.existsById(idProducto)) {
            throw new NoSuchElementException("El producto con dicho Id no existe");
        }

        if(!usuarioRepositorio.existsById(idUsuario)) {
            throw new NoSuchElementException("El usuario con dicho Id no existe");
        }
    }

    //. Traer a todo los productos de carrito
    public List<RelacionCarritoProducto> listarProductos(){
        return relacionCarritoProductoRepositorio.findAll();
    }

    //. Actualizar la cantidad
    public void actualizarCantidad(Long idCarritoXProducto, Integer nuevaCantidad) {
        if (idCarritoXProducto == null || idCarritoXProducto <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
        if (!relacionCarritoProductoRepositorio.existsById(idCarritoXProducto)){
            throw new NoSuchElementException("No se encontro el id del producto.");
        }
        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException("La cantidad no puede ser 0 ni un número negativo.");
        }

        relacionCarritoProductoRepositorio.actualizarCantidad(idCarritoXProducto, nuevaCantidad);
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

}
