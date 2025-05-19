package com.example.demo.Servicios;

import com.example.demo.DTOs.DireccionDTO;
import com.example.demo.Entidades.Direccion;
import com.example.demo.Repositorios.DireccionRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DireccionServicio {

    @Autowired
    DireccionRepositorio direccionRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //. Crear direccion
    // Quité jsonignore porque no lo toma y hace que la validacion sea null
    // Valida si todos los datos sea diferentes de null para ingresar solo con los obigatorios o los datos minimos
    public Direccion crearDireccion(Direccion direccion) {
        if (direccion.getTipoVia() == null || direccion.getNumeroViaPrincipal() == null || direccion.getNumeroViaSecundaria() == null || direccion.getNumeroPredio() == null || direccion.getBarrio() == null || direccion.getCiudad() == null || direccion.getDepartamento() == null || direccion.getEsPredeterminada() == null || direccion.getUsuario() == null || direccion.getUsuario().getIdUsuario() == null) {

            throw new IllegalArgumentException("Faltan campos obligatorios para registrar la dirección.");
        }
        return direccionRepositorio.save(direccion);
    }

    //. Obtener todas las direcciones asociadas a un usuario
    public List<Direccion> obtenerDireccionPorUsuario(Long idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }

        if (!usuarioRepositorio.existsById(idUsuario)) {
            throw new NoSuchElementException("No existe este usuario");
        }

        return direccionRepositorio.findByUsuario_IdUsuario(idUsuario);
    }

    //. Actulizacion de Direccion
    public Direccion actualizarDireccion(Long idDireccion, DireccionDTO direccionDTO) {
        // Valida si el id o los datos están
        if (direccionDTO == null || direccionDTO.getIdDireccion() == null || idDireccion == null || idDireccion <= 0) {
            throw new IllegalArgumentException("El id o los datos a modificar son inválidos");
        }

        // Vaida si el id existe
        Optional<Direccion> direccionOptional = direccionRepositorio.findById(direccionDTO.getIdDireccion());
        if (!direccionOptional.isPresent()) {
            throw new NoSuchElementException("Direccion no encontrada");
        }

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

    //. Borrar direccion por el id
    // Valida que el id exista para eliminar
    public void eliminarDireccionId(Long idDireccion) {

        if (idDireccion == null || idDireccion <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }

        if (!direccionRepositorio.existsById(idDireccion)){
            throw new NoSuchElementException("La direccion no exite.");
        }

        direccionRepositorio.deleteById(idDireccion);
    }

    //. Listar todas las direcciones
    public List<Direccion> listarDirecciones() {
        return direccionRepositorio.findAll();
    }

}
