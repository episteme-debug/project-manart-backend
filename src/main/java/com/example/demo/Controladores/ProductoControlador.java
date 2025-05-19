package com.example.demo.Controladores;

import com.example.demo.DTOs.ProductoDTO.Creacion;
import com.example.demo.DTOs.ProductoSDTO;
import com.example.demo.Entidades.Producto;
import com.example.demo.Servicios.ProductoServicio;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductoControlador {

    @Autowired
    ProductoServicio productoServicio;

    //. Crear nuevo producto
    @PostMapping("private/crear")
    @PreAuthorize("hasRole('ADMIN') or hasRole('VENDEDOR')")
    public ResponseEntity<?> crearProducto(@RequestBody Creacion productoDTO) {
        try {
            Producto nuevo = productoServicio.crearProducto(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    //. Obtener Producto por Id
    @GetMapping("public/obtenerporid/{idProducto}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Producto producto = productoServicio.obtenerProductoPorId(id);
            return ResponseEntity.ok(producto);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

    //. Obtener todos los productos
    @GetMapping("public/listarproductos")
    public List<Producto> listarProductos() {
        return productoServicio.obtenerTodosProductos();
    }

    //. Obtener lista de productos por nombre
    @GetMapping("public/obtenerpornombre/{nombreProducto}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) {
        try {
            List<Producto> resultados = productoServicio.obtenerProductosPorNombre(nombre);
            return ResponseEntity.ok(resultados);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }


    //. Actualizar un producto existente
    @PatchMapping("private/actualizarproducto/{idProducto}")
    @PreAuthorize("@esPropietarioProducto(#idProducto)")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody ProductoSDTO dto) {
        try {
            Producto actualizado = productoServicio.actualizarProducto(id, dto);
            return ResponseEntity.ok(actualizado);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //.  Eliminar un producto
    @DeleteMapping("private/eliminarproducto/{idProducto}")
    @PreAuthorize("@esPropietarioProducto(#idProducto) or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            productoServicio.eliminarProducto(id);
            return ResponseEntity.ok().build();

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

}

