package com.example.demo.Controladores;

import com.example.demo.DTOs.PromocionDTO;
import com.example.demo.Entidades.Promocion;
import com.example.demo.Servicios.PromocionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/promocion")
@RestController
public class PromocionControlador {

    @Autowired
    PromocionServicio promocionServicio;

    //1. Crear una promocion !!
    @PostMapping("/crearPromocion")
    public Promocion savePromocion(@RequestBody Promocion promocion) {
        return promocionServicio.crearPromocion(promocion);
    }

    //2. Obtener promocion segun el id
    @GetMapping("/obtenerPromocionById/{idPromocion}")
    public Promocion obtenerPromocionById(@PathVariable Long idPromocion) {
        return promocionServicio.obtenerPromocionById(idPromocion);

    }

    //3. Obtener todas las promociones
    @GetMapping("/obtenerPromociones")
    public List<Promocion> getAllPromocion() {
        return promocionServicio.getAllpromocion();
    }

    //4. Obtener promociones por estado
    @GetMapping("/obtenerPorEstado/{estadoPromocion}")
    public List<Promocion> getAllPromocionActivas(@PathVariable Boolean estadoPromocion) {
        return promocionServicio.obtenerPorEstado(estadoPromocion);
    }

    //5. Actualizar!!
    @PutMapping("/actualizarPromocion/{idPromocion}")
    public Promocion actualizarPromocion(@PathVariable Long idPromocion, @RequestBody PromocionDTO promocionDTO) {
        return promocionServicio.actualizarPromocion(idPromocion, promocionDTO);
    }

    //7. Eliminar promocion con id
    @DeleteMapping("/eliminarPromocion/{idPromocion}")
    public List<?> deletePromocionId(@PathVariable Long idPromocion) {
        return promocionServicio.eliminarPorId(idPromocion);
    }
/*
    //filtra por fechas o porcentaje de descuento
    @GetMapping("/buscar")
    public List<Promocion> buscarPromociones(
            @RequestParam(required = false) String palabraClave,
            @RequestParam(required = false) Integer descuento,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        return PromocionServicio.buscarPromociones(palabraClave, descuento, fechaInicio, fechaFin);
    }

    //eliminar promocion con id
    @DeleteMapping("/Delete")
    public List<?> deletePromocionId(@RequestBody Promocion promocion) {
        return PromocionServicio.deletePromocionId(promocion);
    }*/
}
