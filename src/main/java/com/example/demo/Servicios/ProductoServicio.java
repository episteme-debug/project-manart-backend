package com.example.demo.Servicios;

import com.example.demo.DTOs.ProductoDTO;
import com.example.demo.Entidades.Producto;
import com.example.demo.Repositorios.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    @Autowired
    ProductoRepositorio productoRepositorio;

    //1. Crear nuevo producto
    public Producto crearProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    //2. Obtener producto por Id
    public Optional<Producto> obtenerProductoPorId(Long idProducto) {
        return productoRepositorio.findById(idProducto);
    }

    //3. Obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        return productoRepositorio.findAll();
    }

    //4. Obtener productos por nombre
    public List<Producto> obtenerProductosPorNombre(String nombreProducto) {
        return productoRepositorio.findByNombreProducto(nombreProducto);
    }

    //5. Actualizar producto
    public Producto actualizarProducto(Long idProducto, ProductoDTO productoDTO) {
        Producto producto = productoRepositorio.findById(idProducto).get();

        if (productoDTO.getNombreProducto() != null) {
            producto.setNombreProducto(productoDTO.getNombreProducto());
        }

        if (productoDTO.getDescripcionProducto() != null) {
            producto.setDescripcionProducto(productoDTO.getDescripcionProducto());
        }

        if (productoDTO.getStockProducto() != null) {
            producto.setStockProducto(productoDTO.getStockProducto());
        }

        if (productoDTO.getPrecioProducto() != null) {
            producto.setPrecioProducto(productoDTO.getPrecioProducto());
        }

        if (productoDTO.getImagenProducto() != null) {
            producto.setImagenProducto(productoDTO.getImagenProducto());
        }

        if (productoDTO.getEstadoProducto() != null) {
            producto.setEstadoProducto(productoDTO.getEstadoProducto());
        }

        return productoRepositorio.save(producto);
    }

    //6. Eliminar producto
    public void eliminarProducto(Long idProducto) {
        productoRepositorio.deleteById(idProducto);
    }

}
