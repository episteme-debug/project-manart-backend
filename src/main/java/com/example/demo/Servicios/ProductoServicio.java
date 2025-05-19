package com.example.demo.Servicios;

import com.example.demo.DTOs.ProductoDTO.Creacion;
import com.example.demo.DTOs.ProductoSDTO;
import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.ProductoRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoServicio {

    @Autowired
    ProductoRepositorio productoRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //. Crear nuevo producto
    public Producto crearProducto(Creacion productoDTO) throws BadRequestException {
        Producto producto = new Producto();
        Usuario usuario = usuarioRepositorio.findById(productoDTO.getIdUsuario())
                        .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        producto.setNombreProducto(productoDTO.getNombreProducto());
        producto.setDescripcionProducto(productoDTO.getDescripcionProducto());
        producto.setPrecioProducto(productoDTO.getPrecioProducto());
        producto.setStockProducto(producto.getStockProducto());
        producto.setUsuario(usuario);

        return productoRepositorio.save(producto);
    }

    //. Obtener producto por Id
    public Producto obtenerProductoPorId(Long id) throws BadRequestException {
        if (id == null || id <= 0) {
            throw new BadRequestException("ID inválido.");
        }
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto con ID " + id + " no existe."));
    }

    //. Obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        return productoRepositorio.findAll();
    }

    //. Obtener productos por nombre
    public List<Producto> obtenerProductosPorNombre(String nombre) throws BadRequestException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BadRequestException("El nombre para buscar es obligatorio.");
        }
        return productoRepositorio.findByNombreProducto(nombre);
    }

    // Actualizaar uno o más datos de un producto
    public Producto actualizarProducto(Long id, ProductoSDTO dto) throws BadRequestException {
        if (id == null || id <= 0 || !productoRepositorio.existsById(id)) {
            throw new BadRequestException("ID inválido");
        }

        Producto producto = productoRepositorio.findById(id).get();

        if (dto.getDescripcionProducto() != null) {
            producto.setDescripcionProducto(dto.getDescripcionProducto().trim());
        }

        if (dto.getPrecioProducto() != null) {
            producto.setPrecioProducto(dto.getPrecioProducto());
        }

        if (dto.getStockProducto() != null) {
            producto.setStockProducto(dto.getStockProducto());
        }

        if (dto.getImagenProducto() != null) {
            producto.setImagenProducto(dto.getImagenProducto().trim());
        }

        if (dto.getEstadoProducto() != null) {
            producto.setEstadoProducto(dto.getEstadoProducto());
        }

        return productoRepositorio.save(producto);
    }


    //. Actualizar Stock
    public void actualizarStock(Producto producto, int cantidad, boolean esAgregar) {
        if (!esAgregar) {
            cantidad = -cantidad;
        }
        producto.setStockProducto(producto.getStockProducto() + cantidad);
    }


    //. Eliminar producto
    public void eliminarProducto(Long id) throws BadRequestException {
        if (id == null || id <= 0) {
            throw new BadRequestException("ID inválido.");
        }
        if (!productoRepositorio.existsById(id)) {
            throw new NoSuchElementException("Producto con ID " + id + " no existe.");
        }
        productoRepositorio.deleteById(id);
    }


}
