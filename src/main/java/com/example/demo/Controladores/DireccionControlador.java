package com.example.demo.Controladores;

import com.example.demo.DTOs.DireccionDTO.RespuestaDireccion;
import com.example.demo.DTOs.DireccionDTO.EnviosDireccion;
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
    public ResponseEntity<?> crearDireccion(@RequestBody EnviosDireccion direccion) {
        try {
            RespuestaDireccion nueva = direccionServicio.crearDireccion(direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //. Obtener todas las direcciones asociadas a un usuario
    @GetMapping("private/listarporusuario/{idUsuario}")
    @PreAuthorize("@autorizacion.esPropietario(#idUsuario) or hasRole('ADMIN')")
    public ResponseEntity<?> direccionesPorUsuario(@PathVariable Long idUsuario) {
        try {
            List<RespuestaDireccion> direcciones = direccionServicio.obtenerDireccionPorUsuario(idUsuario);
            return ResponseEntity.ok(direcciones);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    //. Actualizar una dirección
    // Devuelve un 200 si esta bien y actualiza o 400 o 404 con los mensaje
    @PreAuthorize("@autorizacion.esPropietarioDireccion(#idDireccion) or hasRole('ADMIN') ")
    @PutMapping("private/actualizar/{idDireccion}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Long idDireccion, @RequestBody EnviosDireccion enviosDireccion) {
        try {
            RespuestaDireccion actualizada = direccionServicio.actualizarDireccion(idDireccion, enviosDireccion);
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
    @PreAuthorize("@autorizacion.esPropietarioDireccion(#idDireccion) or hasRole('ADMIN')")
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
    public List<RespuestaDireccion> listarDirecciones() {
        return direccionServicio.listarDirecciones();
    }
}