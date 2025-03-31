package com.example.demo.Servicios;

import com.example.demo.DTOs.DireccionDTO;
import com.example.demo.Entidades.Direccion;
import com.example.demo.Repositorios.DireccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionServicio {
    @Autowired
    DireccionRepositorio direccionRepositorio;

    //1. Crear direccion
    public Direccion crearDireccion(Direccion direccion) {
        return direccionRepositorio.save(direccion);
    }

    //2. Obtener todas las direcciones asociadas a un usuario
    public List<Direccion> obtenerDireccionPorUsuario(Long idUsuario) {
        return direccionRepositorio.findByUsuario_IdUsuario(idUsuario);
    }

    //3. Actulizacion de Direccion
    public Direccion actualizarDireccion(Long idDireccion, DireccionDTO direccionDTO) {

        Direccion direccion = direccionRepositorio.findById(idDireccion).get();

        if (direccionDTO.getTipoVia() != null) {
            direccion.setTipoVia(direccionDTO.getTipoVia());
        }

        if (direccionDTO.getNumeroViaPrincipal() != null) {
            direccion.setNumeroViaPrincipal(direccionDTO.getNumeroViaPrincipal());
        }

        if (direccionDTO.getLetraViaPrincipal() != null) {
            direccion.setLetraViaPrincipal(direccionDTO.getLetraViaPrincipal());
        }

        if (direccionDTO.getBisViaPrincipal() != null) {
            direccion.setBisViaPrincipal(direccionDTO.getBisViaPrincipal());
        }

        if (direccionDTO.getNumeroViaSecundaria() != null) {
            direccion.setNumeroViaSecundaria(direccionDTO.getNumeroViaSecundaria());
        }

        if (direccionDTO.getLetraViaSecundaria() != null) {
            direccion.setLetraViaSecundaria(direccionDTO.getLetraViaSecundaria());
        }

        if (direccionDTO.getBisViaSecundaria() != null) {
            direccion.setBisViaSecundaria(direccionDTO.getBisViaSecundaria());
        }

        if (direccionDTO.getNumeroPredio() != null) {
            direccion.setNumeroPredio(direccionDTO.getNumeroPredio());
        }

        if (direccionDTO.getComplemento() != null) {
            direccion.setComplemento(direccionDTO.getComplemento());
        }

        if (direccionDTO.getBarrio() != null) {
            direccion.setBarrio(direccionDTO.getBarrio());
        }

        if (direccionDTO.getCiudad() != null) {
            direccion.setCiudad(direccionDTO.getCiudad());
        }

        if (direccionDTO.getDepartamento() != null) {
            direccion.setDepartamento(direccionDTO.getDepartamento());
        }

        if (direccionDTO.getEsPredeterminada() != null) {
            direccion.setEsPredeterminada(direccionDTO.getEsPredeterminada());
        }

        return direccionRepositorio.save(direccion);
    }

    //4. Borrar direccion por el id
    public List<?> eliminarDireccionId(Long idDireccion) {
        direccionRepositorio.deleteById(idDireccion);
        return getAllDireccion();
    }

    // Get Todas las Direcciones
    public List<Direccion> getAllDireccion(){
        return direccionRepositorio.findAll();
    }

    //get id de la direccion
    public Direccion getDireccionId(Long id){
        return direccionRepositorio.findById(id).get();
    }

/*

    //Get todas las direciones permanetes
    public List<Direccion>getAllDireccionesPermanetes(Boolean esPredeterminada){
        return DireccionRepositorio.findByesPredeterminada(esPredeterminada);

    }
    }*/
}
