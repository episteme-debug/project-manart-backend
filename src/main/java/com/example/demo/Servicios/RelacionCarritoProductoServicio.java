package com.example.demo.Servicios;

import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.RelacionCarritoProducto;
import com.example.demo.Repositorios.ProductoRepositorio;
import com.example.demo.Repositorios.RelacionCarritoProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelacionCarritoProductoServicio {

    @Autowired
    RelacionCarritoProductoRepositorio relacionCarritoProductoRepositorio;

    @Autowired
    ProductoRepositorio productoRepositorio;

    //. Agregar un producto y no deja que se duplique
    public RelacionCarritoProducto crearProducto(RelacionCarritoProducto object) {
        Producto product = productoRepositorio.findById(object.getProducto().getIdProducto()).get();
        object.setPrecioUnitario(product.getPrecioProducto());

        return relacionCarritoProductoRepositorio.save(object);
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
