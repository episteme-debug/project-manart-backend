package com.example.demo.Servicios;

import com.example.demo.DTOs.PromocionDTO;
import com.example.demo.Entidades.Promocion;
import com.example.demo.Repositorios.PromocionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionServicio {

    @Autowired
    PromocionRepositorio promocionRepositorio;

    //1. Crear una promocion !!
    public Promocion crearPromocion(Promocion promocion) {
        return promocionRepositorio.save(promocion);
    }

    //2. Obtener promoción por el id
    public Promocion obtenerPromocionById(Long Id) {
        return promocionRepositorio.findById(Id).get();
    }

    //3. Obtener todas las promociones
    public List<Promocion> getAllpromocion() {
        return promocionRepositorio.findAll();
    }

    //4. Obtener promociones por estado
    public List<Promocion> obtenerPorEstado(Boolean estadoPromocion) {
        return promocionRepositorio.findByEstadoPromocion(estadoPromocion);
    }

    //6. Actualizacion de Promocion !!
    public Promocion actualizarPromocion(Long idPromocion, PromocionDTO promocionDTO) {
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

    //7. Borrar promocion por Id
    public List<?> eliminarPorId (Long idPromocion){
        promocionRepositorio.deleteById(idPromocion);
        return getAllpromocion();
    }
/*
    //get por filtros
    public List<Promocion> buscarPromociones(String palabraClave, Integer descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        return PromocionRepositorio.buscarPromociones(palabraClave, descuento, fechaInicio, fechaFin);
    }
*/
}
