package com.example.demo.Controladores;

import com.example.demo.DTOs.Publicacion.CrearPublicacionDTO;
import com.example.demo.DTOs.Publicacion.PublicacionDTO;
import com.example.demo.Entidades.Publicacion;
import com.example.demo.Servicios.PublicacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/publicacion")
@RestController
public class PublicacionControlador {

    @Autowired
    PublicacionServicio publicacionServicio;

    @PostMapping("/private/crear")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crear(@RequestBody CrearPublicacionDTO dto) {
        try {
            PublicacionDTO nueva = publicacionServicio.crearPublicacion(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("public/obtenerporId/{idPubicacion}")
    public  ResponseEntity<?> obtenerPublicacionById(@PathVariable Long idPubicacion){
        try {
            PublicacionDTO publicacion = publicacionServicio.obtenerPublicacionById(idPubicacion);
            return  ResponseEntity.ok(publicacion);
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("public/listarpublicaciones")
    public List<PublicacionDTO> listarpublicaciones(){
        return publicacionServicio.obtenerTodasPublicaciones();
    }

    @GetMapping("private/listarporestado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PublicacionDTO> listarporestado(@PathVariable Boolean estado){
        return  publicacionServicio.obtenerPorEstado(estado);
    }

    @PatchMapping("private/actualizar/{idPublicacion}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actulizarPublicaion(@PathVariable Long idPublicacion, @RequestBody PublicacionDTO publicacionDTO) {
        try {
            PublicacionDTO actualizar = publicacionServicio.actualizarPublicacion(idPublicacion, publicacionDTO);
            return ResponseEntity.ok(actualizar);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @DeleteMapping("private/eliminar/{idPublicacion}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<?> eliminarPublicaconId(@PathVariable Long idPublicacion){
        try{
            publicacionServicio.eliminarPorId(idPublicacion);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
