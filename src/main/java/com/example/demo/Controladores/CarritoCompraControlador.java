package com.example.demo.Controladores;

import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Interfaces.CarritoInterfaz;
import com.example.demo.Servicios.CarritoCompraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/carrito")
@RestController
public class CarritoCompraControlador {

    @Autowired
    CarritoCompraServicio carritoCompraServicio;

    //Crea un carrito
    @PostMapping("private/crearCarrito")
    public ResponseEntity<?> crearCarrito(@RequestBody CarritoCompra carritoCompra) {
        try {
            CarritoCompra nuevoCarritoCompra = carritoCompraServicio.crearCarrito(carritoCompra);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarritoCompra);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //Obtener los carritos de la base de datos
    @GetMapping("private/obtenerTodos")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CarritoInterfaz> obtenerTodos() {
        return carritoCompraServicio.obtenerTodosCarritoCompra();
    }

    //Obtener carrito por usuario
    @GetMapping("private/obtenerporusuario/{id}")
    @PreAuthorize("@autorizacion.esPropietario(#id) or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerPorUsuario(@PathVariable Long id) {
        try {
            CarritoCompra<CarritoInterfaz> carrito = carritoCompraServicio.obtenerPorUsuario(id);
            return ResponseEntity.ok(carrito);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //Obtener carrito por id
    @GetMapping("private/obtenerPorId/{id}")
    @PreAuthorize("@autorizacion.esPropietario(#id) or hasRole('ADMIN')")
    public ResponseEntity<?> ObtenerPorId(@PathVariable Long id) {
        try {
            CarritoInterfaz carritoCompra = carritoCompraServicio.ObtenerPorId(id);
            return ResponseEntity.ok(carritoCompra);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //Obtener el total del carrito según su id
    @GetMapping("private/obtenerTotal/{id}")
    @PreAuthorize("@autorizacion.esPropietario(#id) or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerTotalCarrito(@PathVariable Long id) {
        try {
            CarritoInterfaz carritoCompra = carritoCompraServicio.ObtenerPorId(id);
            return ResponseEntity.ok(carritoCompra);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //Eliminar carrito y todas su relaciones
    @DeleteMapping("private/eliminarById/{id}")
    @PreAuthorize("@autorizacion.esPropietario(#id) or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarCarritoCompraById(@PathVariable Long id) {
        try {
            carritoCompraServicio.eliminarCarritoCompraById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

}
