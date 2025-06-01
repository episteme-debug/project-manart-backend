package com.example.demo.Controladores;

import com.example.demo.DTOs.CarritoProductoDTO.AgregarItem;
import com.example.demo.DTOs.CarritoProductoDTO.RespuestaCarrito;
import com.example.demo.Entidades.RelacionCarritoProducto;
import com.example.demo.Servicios.RelacionCarritoProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/relcarritoproducto")
@RestController
public class RelacionCarritoProductoControlador {

    @Autowired
    RelacionCarritoProductoServicio relacionCarritoProductoServicio;

    //. Agregar producto
    @PostMapping("private/agregarproducto")
    public ResponseEntity<?> agregarProducto(@RequestBody AgregarItem producto) {
        try {
            return ResponseEntity.ok(relacionCarritoProductoServicio.crearProducto(producto));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //. Obtener todos los productos
    @GetMapping("private/listarproductos")
    public List<RespuestaCarrito> listarProductos() {
        return relacionCarritoProductoServicio.listarProductos();
    }


/*    //. Actulizar cantidad
    @PutMapping("private/actualizarcantidad/{idCarritoProducto}/{cantidad}")
    public ResponseEntity<String> actualizarCantidad(@PathVariable Long idCarritoProducto, @PathVariable Integer cantidad) {
        try {
            relacionCarritoProductoServicio.actualizarCantidad(idCarritoProducto, cantidad);
            return ResponseEntity.ok("Cantidad actualizada correctamente.");

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }*/
@PutMapping("private/actualizar-cantidad/{idRelacion}")
public ResponseEntity<Map<String, String>> actualizarCantidad(
        @PathVariable Long idRelacion,
        @RequestParam Integer cantidad) {

    relacionCarritoProductoServicio.actualizarCantidad(idRelacion, cantidad);
    Map<String, String> respuesta = new HashMap<>();
    respuesta.put("mensaje", "Cantidad actualizada correctamente");

    return ResponseEntity.ok(respuesta);
}

    //. Eliminar producto
    @DeleteMapping("private/eliminarproducto/{idCarritoProducto}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long idCarritoProducto) {
        try {
            relacionCarritoProductoServicio.eliminarProducto(idCarritoProducto);
            return ResponseEntity.ok("Producto eliminado correctamente.");

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }
}
