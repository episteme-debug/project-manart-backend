package com.example.demo.Controladores;

import com.example.demo.DTOs.ProductoDTO;
import com.example.demo.Entidades.Producto;
import com.example.demo.Servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    //1. Crear nuevo producto
    @PostMapping("/crearProducto")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoServicio.crearProducto(producto);
    }

    //2. Obtener Producto por Id
    @GetMapping("/obtenerProductoPorId/{idProducto}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable Long idProducto) {
        return productoServicio.obtenerProductoPorId(idProducto);
    }

    //3. Obtener todos los productos
    @GetMapping("/obtenerTodosProductos")
    public List<Producto> get_all_productos() {
        return productoServicio.obtenerTodosProductos();
    }

    //4. Obtener lista de productos por nombre
    @GetMapping("/obtenerProductosPorNombre/{nombreProducto}")
    public List<Producto> obtenerProductosPorNombre(@PathVariable String nombreProducto){
        return productoServicio.obtenerProductosPorNombre(nombreProducto);
    }

 //5. Actualizar un producto existente
    @PatchMapping("/actualizarProducto/{id}")
    public Producto actualizarProducto(@PathVariable Long id , @RequestBody ProductoDTO productoActualizado){
        return  productoServicio.actualizarProducto(id, productoActualizado);
    }

    //6.  Eliminar un producto
    @DeleteMapping("/eliminarProducto/{idProducto}")
    public void eliminarProducto(@PathVariable Long idProducto) {
        productoServicio.eliminarProducto(idProducto);
    }
}

