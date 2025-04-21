package com.example.demo.Servicios;

import com.example.demo.DTOs.RelCarritoProductoDTO;
import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.RelacionCarritoProducto;
import com.example.demo.Repositorios.CarritoCompraRepositorio;
import com.example.demo.Repositorios.ProductoRepositorio;
import com.example.demo.Repositorios.RelacionCarritoProductoRepositorio;
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

    // Agrega un producto al carrito sin duplicarlo. Si ya existe, actualiza la cantidad.
    public RelacionCarritoProducto crearProducto(RelCarritoProductoDTO productoDTO) {
        Long idProducto = productoDTO.getProducto().getIdProducto();
        Long idUsuario = productoDTO.getUsuario().getIdUsuario();

        // Buscar relación existente del producto en el carrito del usuario
        RelacionCarritoProducto relacionExistente = relacionCarritoProductoRepositorio
                .findByProducto_IdProducto(idProducto);

        if (relacionExistente != null) {
            relacionExistente.setCantidad(productoDTO.getCantidad());
            return relacionCarritoProductoRepositorio.save(relacionExistente);
        }

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


    //. Traer a todo los productos de carrito
    public List<RelacionCarritoProducto> listarProductos(){
        return relacionCarritoProductoRepositorio.findAll();
    }

    //. Actualizar la cantidad
    public void actualizarCantidad(Long idCarritoXProducto, Integer nuevaCantidad) {
        relacionCarritoProductoRepositorio.actualizarCantidad(idCarritoXProducto, nuevaCantidad);
    }

    //. Eliminar producto
    public void eliminarProducto(Long idCarritoXProducto){
        relacionCarritoProductoRepositorio.deleteById(idCarritoXProducto);
    }

}
