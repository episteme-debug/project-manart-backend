package com.example.demo.Controladores;

import com.example.demo.DTOs.FlitroProductoDTO;
import com.example.demo.DTOs.ProductoDTO.*;
import com.example.demo.Entidades.Producto;
import com.example.demo.Repositorios.ProductoRepositorio;
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
//
    @Autowired
    ProductoServicio productoServicio;

    //. Crear nuevo producto
    @PostMapping("private/crear")
    @PreAuthorize("hasRole('ADMIN') or hasRole('VENDEDOR')")
    public ResponseEntity<?> crearProducto(@RequestBody CreacionProducto productoDTO) {
        try {
            RespuestaProducto nuevo = productoServicio.crearProducto(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    //. Obtener Producto por Id
    @GetMapping("public/obtenerporid/{idProducto}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long idProducto) {
        try {
            RespuestaProducto producto = productoServicio.obtenerProductoPorId(idProducto);
            return ResponseEntity.ok(producto);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

    //. Obtener todos los productos
    @GetMapping("public/listarproductos")
    public List<RespuestaProducto> listarProductos() {
        return productoServicio.listarProductos();
    }

    //. Obtener todos los productos
    @GetMapping("public/listarproductosporcategoria/{idCategoria}")
    public List<RespuestaProducto> listarProductosPorCategoria(@PathVariable Long idCategoria) {
        return productoServicio.listarProductosPorCategoria(idCategoria);
    }


    //. Obtener lista de productos por nombre
    @GetMapping("public/obtenerpornombre/{nombreProducto}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) {
        try {
            List<RespuestaProducto> resultados = productoServicio.obtenerProductosPorNombre(nombre);
            return ResponseEntity.ok(resultados);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }


    //. Actualizar un producto existente
    @PatchMapping("private/actualizarproducto/{idProducto}")
    @PreAuthorize("@autorizacion.esPropietarioProducto(#idProducto)")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long idProducto, @RequestBody ActualizacionProducto dto) {
        try {
            RespuestaProducto actualizado = productoServicio.actualizarProducto(idProducto, dto);
            return ResponseEntity.ok(actualizado);

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //.  Eliminar un producto
    @DeleteMapping("private/eliminarproducto/{idProducto}")
    @PreAuthorize("@autorizacion.esPropietarioProducto(#idProducto) or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto) {
        try {
            productoServicio.eliminarProducto(idProducto);
            return ResponseEntity.ok().build();

        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @PatchMapping("private/categorizarProducto/{idProducto}")
    @PreAuthorize("@autorizacion.esPropietarioProducto(#idProducto) or hasRole('ADMIN')")
    public ResponseEntity<?> categorizarProducto (@PathVariable Long idProducto, @RequestBody List<Long> idsCategorias) {
        try {
            productoServicio.categorizarProducto(idProducto, idsCategorias);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("public/filtar")
    public List<RespuestaFiltro> buscarProductosFiltrados(FlitroProductoDTO filtro) {
        return productoServicio.buscarProductosFiltrados(filtro);
    }

    @GetMapping("public/rango-precios")
    public ResponseEntity<RangoDePreciosDTO> obtenerRangoPrecios() {
        RangoDePreciosDTO rango = productoServicio.obtenerRangoDePrecios();
        return (rango != null) ? ResponseEntity.ok(rango) : ResponseEntity.noContent().build();
    }
}

