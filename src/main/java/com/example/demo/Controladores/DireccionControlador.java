package com.example.demo.Controladores;

import com.example.demo.DTOs.DireccionDTO;
import com.example.demo.Entidades.Direccion;
import com.example.demo.Servicios.DireccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direccion")
@CrossOrigin("http://127.0.0.1:5500/")
public class DireccionControlador {

    @Autowired
    DireccionServicio direccionServicio;

    //1. Crear una dirección
    @PostMapping("/crearDireccion")
    public Direccion crearDireccion(@RequestBody Direccion direccion) {
        return direccionServicio.crearDireccion(direccion);
    }

    //2. Obtener todas las direcciones asociadas a un usuario
    @GetMapping("/direccionesPorUsuario/{idUsuario}")
    public List<Direccion> direccionesPorUsuario(@PathVariable Long idUsuario) {
        return direccionServicio.obtenerDireccionPorUsuario(idUsuario);
    }

    //3. Actualizar uno o más datos del producto
    @PutMapping("/actualizarDireccion/{idDireccion}")
    public Direccion actualizarDireccion(@PathVariable Long idDireccion, @RequestBody DireccionDTO direccionDTO) {
        return direccionServicio.actualizarDireccion(idDireccion, direccionDTO);
    }

    @DeleteMapping("/eliminarDireccionId/{idDireccion}")
    public List<?> deleteDireccionId(@PathVariable Long idDireccion) {
        return direccionServicio.eliminarDireccionId(idDireccion);

/*    @GetMapping("/getDireccionId")
    public ResponseEntity<?> getDireccionId(@RequestBody Direccion direccion) {
        Long idDireccion = direccion.getIdDireccion();
        Optional<Direccion> optionalDireccion = DireccionServicio.getDireccionId(idDireccion);

        return ResponseEntity.ok(optionalDireccion.get());
    }

    @GetMapping("/getAllDireccionesPermanetes")
    public List<Direccion> getAllDireccionesPermanetes(@RequestParam Boolean esPredeterminada) {
        return DireccionServicio.getAllDireccionesPermanetes(esPredeterminada);
    }


    //Traer todas
    @GetMapping("/getAllDireccion")
    public List<Direccion> getAllDireccion() {
        return DireccionServicio.getAllDireccion();
    }

    }*/
    }
}