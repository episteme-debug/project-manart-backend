package com.example.demo.Controladores;

import com.example.demo.DTOs.PublicacionDTO.CreacionPublicacion;
import com.example.demo.DTOs.PublicacionDTO.RespuestaPublicacion;
import com.example.demo.Entidades.Publicacion;
import com.example.demo.Servicios.PublicacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping("private/crear")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearPublicacion(@RequestBody CreacionPublicacion dto) {
        try {
            RespuestaPublicacion nueva = publicacionServicio.crearPublicacion(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @GetMapping("public/obtenerporid/{idPubicacion}")
    public  ResponseEntity<?> obtenerPublicacionById(@PathVariable Long idPubicacion){
        try {
            RespuestaPublicacion publicacion = publicacionServicio.obtenerPublicacionById(idPubicacion);
            return  ResponseEntity.ok(publicacion);
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("public/listarpublicaciones")
    public List<RespuestaPublicacion> listarpublicaciones(){
        return publicacionServicio.obtenerTodasPublicaciones();
    }

    @GetMapping("private/listarporestado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RespuestaPublicacion> listarporestado(@PathVariable Boolean estado){
        return  publicacionServicio.obtenerPorEstado(estado);
    }

    @PatchMapping("private/actualizar/{idPublicacion}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<?> actulizarPublicaion(@PathVariable Long idPublicacion, @RequestBody RespuestaPublicacion publicacionDTO){
        try{
            Publicacion actualizar = publicacionServicio.actulizarPublicacion(idPublicacion,publicacionDTO);
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