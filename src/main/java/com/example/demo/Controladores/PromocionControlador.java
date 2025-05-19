package com.example.demo.Controladores;

import com.example.demo.DTOs.PromocionDTO;
import com.example.demo.Entidades.Promocion;
import com.example.demo.Servicios.PromocionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/promocion")
@RestController
public class PromocionControlador {

    @Autowired
    PromocionServicio promocionServicio;

    //. Crear una promocion !!
    // Devuelve un status 201 de que fue creado o un 400 con mensaje que falta datos
    @PostMapping("private/crear")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearPromocion(@RequestBody Promocion promocion) {
        try {
            Promocion nueva = promocionServicio.crearPromocion(promocion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //. Obtener promocion segun el id
    // Devuelve un 200 y la trae o un 404 si no existe
    @GetMapping("public/obtenerporid/{idPromocion}")
    public ResponseEntity<?> obtenerPromocionById(@PathVariable Long idPromocion) {
        try {
            Promocion promocion = promocionServicio.obtenerPromocionById(idPromocion);
            return ResponseEntity.ok(promocion);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //. Obtener todas las promociones
    @GetMapping("public/listarpromociones")
    public List<Promocion> listarpromociones() {
        return promocionServicio.getAllpromocion();
    }

    //. Obtener promociones por estado
    @GetMapping("private/listarporestado/{estadoPromocion}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Promocion> listarporestado(@PathVariable Boolean estadoPromocion) {
        return promocionServicio.obtenerPorEstado(estadoPromocion);
    }

    //. Actualizar
    // Devuelve un 200 si esta bien y actualiza o 400 o 404 con los mensaje
    @PatchMapping("private/actualizar/{idPromocion}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizarPromocion(@PathVariable Long idPromocion, @RequestBody PromocionDTO promocionDTO) {
        try {
            Promocion actulaizar = promocionServicio.actualizarPromocion(idPromocion, promocionDTO);
            return ResponseEntity.ok(actulaizar);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

    //. Eliminar promocion con id
    @DeleteMapping("private/eliminar/{idPromocion}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePromocionId(@PathVariable Long idPromocion) {
        try {
            promocionServicio.eliminarPorId(idPromocion);
            return ResponseEntity.ok().build();

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
