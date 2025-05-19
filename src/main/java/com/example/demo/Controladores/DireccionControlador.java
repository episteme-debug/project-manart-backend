package com.example.demo.Controladores;

import com.example.demo.DTOs.DireccionDTO;
import com.example.demo.Entidades.Direccion;
import com.example.demo.Servicios.DireccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/direccion")
@CrossOrigin("http://127.0.0.1:5500/")
public class DireccionControlador {

    @Autowired
    DireccionServicio direccionServicio;

    //. Crear una dirección
    // Devuelve un status 201 de que fue creado o un 400 con mensaje que falta datos
    @PostMapping("private/crear")
    public ResponseEntity<?> crearDireccion(@RequestBody Direccion direccion) {
        try {
            Direccion nueva = direccionServicio.crearDireccion(direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //. Obtener todas las direcciones asociadas a un usuario
    @GetMapping("private/listarporusuario/{idUsuario}")
    @PreAuthorize("@autorizacion.esPropietario(#idUsuario)")
    public ResponseEntity<?> direccionesPorUsuario(@PathVariable Long idUsuario) {
        try {
            List<Direccion> direcciones = direccionServicio.obtenerDireccionPorUsuario(idUsuario);
            return ResponseEntity.ok(direcciones);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //. Actualizar una dirección
    // Devuelve un 200 si esta bien y actualiza o 400 o 404 con los mensaje
    @PreAuthorize("@autorizacion.esPropietarioDireccion(#idDireccion)")
    @PutMapping("private/actualizar/{idDireccion}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Long idDireccion, @RequestBody DireccionDTO direccionDTO) {
        try {
            Direccion actualizada = direccionServicio.actualizarDireccion(idDireccion, direccionDTO);
            return ResponseEntity.ok(actualizada);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

    //. Eliminar dirección por ID
    // Devuelve un 200 y elimina o 404 si no se encontró
    @DeleteMapping("private/eliminarporid/{idDireccion}")
    @PreAuthorize("@autorizacion.esPropietarioDireccion(#idDireccion)")
    public ResponseEntity<?> deleteDireccionId(@PathVariable Long idDireccion) {
        try {
            direccionServicio.eliminarDireccionId(idDireccion);
            return ResponseEntity.ok().build();

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //. Listar todas las direcciones
    @GetMapping("private/listardirecciones")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Direccion> listarDirecciones() {
        return direccionServicio.listarDirecciones();
    }
}