package com.example.demo.Servicios;

import com.example.demo.DTOs.PromocionDTO;
import com.example.demo.Entidades.Promocion;
import com.example.demo.Repositorios.PromocionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PromocionServicio {

    @Autowired
    PromocionRepositorio promocionRepositorio;

    //. Crear una promocion !!
    // Valida si todos los datos sea diferentes de null para ingresar solo con los obligatorios o los datos minimos
    public Promocion crearPromocion(Promocion promocion) {
        if(promocion.getDetallesPromocion() == null || promocion.getEstadoPromocion() == null ||
                promocion.getFechaFinPromocion() == null || promocion.getFechaInicioPromocion() == null||
                promocion.getNombrePromocion() == null || promocion.getPorcentajeDescuentoPromocion() == null){
            throw new IllegalArgumentException("Faltas campos obligatorios.");
        }
        return promocionRepositorio.save(promocion);
    }


    //. Obtener promoción por el id
    // Valida que id exista para traer
    public Promocion obtenerPromocionById(Long idPromocion) {
        if (idPromocion == null || idPromocion <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
        if (!promocionRepositorio.existsById(idPromocion)){
            throw new NoSuchElementException("Promocion no encontrada.");
        }
        return promocionRepositorio.findById(idPromocion).get();
    }

    //. Obtener todas las promociones
    public List<PromocionDTO> getAllpromocion() {
        List<Promocion> promocion = promocionRepositorio.findAll();
        List<PromocionDTO> respuestaPromocion = new ArrayList<>();
        for(Promocion p: promocion){
            PromocionDTO respuesta = generarRespuesta(p);
            respuestaPromocion.add(respuesta);
        }
        return respuestaPromocion;
    }

    //. Obtener promociones por estado
    public List<Promocion> obtenerPorEstado(Boolean estadoPromocion) {
        return promocionRepositorio.findByEstadoPromocion(estadoPromocion);
    }

    //. Actualizacion de Promocion
    public Promocion actualizarPromocion(Long idPromocion, PromocionDTO promocionDTO) {
        // Valida si el id o lo datos esta
        if (promocionDTO == null || promocionDTO.getIdPromocion() == null || idPromocion == null ||idPromocion <= 0){
            throw new IllegalArgumentException("Los datos de la promocion son invalidos.");
        }

        // Vaida si el id existe
        Optional<Promocion> promocionOptional = promocionRepositorio.findById(promocionDTO.getIdPromocion());
        if(!promocionOptional.isPresent()){
            throw new NoSuchElementException("Pomocion no encontrada.");
        }

        Promocion promocion = promocionRepositorio.findById(idPromocion).get();

        if(promocionDTO.getNombrePromocion() != null){
            promocion.setNombrePromocion(promocionDTO.getNombrePromocion());
        }

        if(promocionDTO.getDetallesPromocion() != null){
            promocion.setDetallesPromocion(promocionDTO.getDetallesPromocion());
        }

        if(promocionDTO.getFechaInicioPromocion() != null){
            promocion.setFechaInicioPromocion(promocionDTO.getFechaInicioPromocion());
        }

        if(promocionDTO.getFechaFinPromocion() != null){
            promocion.setFechaFinPromocion(promocionDTO.getFechaFinPromocion());
        }

        if(promocionDTO.getPorcentajeDescuentoPromocion() != null){
            promocion.setPorcentajeDescuentoPromocion(promocionDTO.getPorcentajeDescuentoPromocion());
        }

        if(promocionDTO.getEstadoPromocion() != null){
            promocion.setEstadoPromocion(promocionDTO.getEstadoPromocion());
        }

        return promocionRepositorio.save(promocion);
    }

    //. Borrar promocion por Id
    // valida que el id exista para eliminar
    public void eliminarPorId (Long idPromocion){
        if (idPromocion == null || idPromocion <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
        if (!promocionRepositorio.existsById(idPromocion)){
            throw new NoSuchElementException("Promocion no existe.");
        }

        promocionRepositorio.deleteById(idPromocion);

    }
    public PromocionDTO generarRespuesta(Promocion promocion) {
        PromocionDTO dto = new PromocionDTO();
        dto.setIdPromocion(promocion.getIdPromocion());
        dto.setNombrePromocion(promocion.getNombrePromocion());
        dto.setDetallesPromocion(promocion.getDetallesPromocion());
        dto.setFechaInicioPromocion(promocion.getFechaInicioPromocion());
        dto.setFechaFinPromocion(promocion.getFechaFinPromocion());
        dto.setPorcentajeDescuentoPromocion(promocion.getPorcentajeDescuentoPromocion());
        dto.setEstadoPromocion(promocion.getEstadoPromocion());
        // Si tu DTO también incluye productos relacionados, debes mapearlos aquí
        return dto;
    }

/*
    //get por filtros
    public List<Promocion> buscarPromociones(String palabraClave, Integer descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        return PromocionRepositorio.buscarPromociones(palabraClave, descuento, fechaInicio, fechaFin);
    }
*/
}
