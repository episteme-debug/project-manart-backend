package com.example.demo.Servicios;

import com.example.demo.DTOs.DireccionDTO.RespuestaDireccion;
import com.example.demo.DTOs.DireccionDTO.EnviosDireccion;
import com.example.demo.Entidades.Direccion;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.DireccionRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DireccionServicio {

    private final DireccionRepositorio direccionRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    //. Crear direccion
    public RespuestaDireccion crearDireccion(EnviosDireccion direccion) {
        if (direccion.getTipoVia() == null || direccion.getNumeroViaPrincipal() == null || direccion.getNumeroViaSecundaria() == null || direccion.getNumeroPredio() == null || direccion.getBarrio() == null || direccion.getCiudad() == null || direccion.getDepartamento() == null || direccion.getEsPredeterminada() == null || direccion.getIdUsuario() == null) {
            throw new IllegalArgumentException("Faltan campos obligatorios para registrar la dirección.");
        }

        Usuario usuario = usuarioRepositorio.findById(direccion.getIdUsuario())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        Direccion nuevaDireccion = new Direccion();
        nuevaDireccion.setIdDireccion(direccion.getIdDireccion());
        nuevaDireccion.setUsuario(usuario);
        nuevaDireccion.setTipoVia(direccion.getTipoVia());
        nuevaDireccion.setNumeroViaPrincipal(direccion.getNumeroViaPrincipal());
        nuevaDireccion.setLetraViaPrincipal(direccion.getLetraViaPrincipal());
        nuevaDireccion.setBisViaPrincipal(direccion.getBisViaPrincipal());
        nuevaDireccion.setNumeroViaSecundaria(direccion.getNumeroViaSecundaria());
        nuevaDireccion.setLetraViaSecundaria(direccion.getLetraViaSecundaria());
        nuevaDireccion.setBisViaSecundaria(direccion.getBisViaSecundaria());
        nuevaDireccion.setNumeroPredio(direccion.getNumeroPredio());
        nuevaDireccion.setComplemento(direccion.getComplemento());
        nuevaDireccion.setBarrio(direccion.getBarrio());
        nuevaDireccion.setCiudad(direccion.getCiudad());
        nuevaDireccion.setDepartamento(direccion.getDepartamento());
        nuevaDireccion.setEsPredeterminada(direccion.getEsPredeterminada());

        Direccion dir = direccionRepositorio.save(nuevaDireccion);

        return generarRespuesta(dir);
    }

    //. Obtener todas las direcciones asociadas a un usuario
    public List<RespuestaDireccion> obtenerDireccionPorUsuario(Long idUsuario) {

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }

        Usuario usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("usuario no encontrado"));

        List<Direccion> direcciones = direccionRepositorio.findByUsuario_IdUsuario(idUsuario);
        List<RespuestaDireccion> direccionesRespuesta = new ArrayList<>();

        for (Direccion direccion : direcciones) {
            RespuestaDireccion respuesta = generarRespuesta(direccion);
            direccionesRespuesta.add(respuesta);
        }

        return direccionesRespuesta;
    }

    //. Actulizacion de Direccion
    public RespuestaDireccion actualizarDireccion(Long idDireccion, EnviosDireccion enviosDireccion) {
        // Valida si el id o los datos están
        if (enviosDireccion == null || enviosDireccion.getIdDireccion() == null || idDireccion == null || idDireccion <= 0) {
            throw new IllegalArgumentException("El id o los datos a modificar son inválidos");
        }

        Direccion direccion = direccionRepositorio.findById(idDireccion)
                .orElseThrow(() -> new NoSuchElementException("Direccion no encontrada"));

        if (enviosDireccion.getTipoVia() != null) {
            direccion.setTipoVia(enviosDireccion.getTipoVia());
        }

        if (enviosDireccion.getNumeroViaPrincipal() != null) {
            direccion.setNumeroViaPrincipal(enviosDireccion.getNumeroViaPrincipal());
        }

        if (enviosDireccion.getLetraViaPrincipal() != null) {
            direccion.setLetraViaPrincipal(enviosDireccion.getLetraViaPrincipal());
        }

        if (enviosDireccion.getBisViaPrincipal() != null) {
            direccion.setBisViaPrincipal(enviosDireccion.getBisViaPrincipal());
        }

        if (enviosDireccion.getNumeroViaSecundaria() != null) {
            direccion.setNumeroViaSecundaria(enviosDireccion.getNumeroViaSecundaria());
        }

        if (enviosDireccion.getLetraViaSecundaria() != null) {
            direccion.setLetraViaSecundaria(enviosDireccion.getLetraViaSecundaria());
        }

        if (enviosDireccion.getBisViaSecundaria() != null) {
            direccion.setBisViaSecundaria(enviosDireccion.getBisViaSecundaria());
        }

        if (enviosDireccion.getNumeroPredio() != null) {
            direccion.setNumeroPredio(enviosDireccion.getNumeroPredio());
        }

        if (enviosDireccion.getComplemento() != null) {
            direccion.setComplemento(enviosDireccion.getComplemento());
        }

        if (enviosDireccion.getBarrio() != null) {
            direccion.setBarrio(enviosDireccion.getBarrio());
        }

        if (enviosDireccion.getCiudad() != null) {
            direccion.setCiudad(enviosDireccion.getCiudad());
        }

        if (enviosDireccion.getDepartamento() != null) {
            direccion.setDepartamento(enviosDireccion.getDepartamento());
        }

        if (enviosDireccion.getEsPredeterminada() != null) {
            direccion.setEsPredeterminada(enviosDireccion.getEsPredeterminada());
        }

        Direccion direccionActualizada = direccionRepositorio.save(direccion);
        return generarRespuesta(direccionActualizada);
    }

    //. Borrar direccion por el id
    // Valida que el id exista para eliminar
    public String eliminarDireccionId(Long idDireccion) {

        if (idDireccion == null || idDireccion <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }

        if (!direccionRepositorio.existsById(idDireccion)){
            throw new NoSuchElementException("La direccion no exite.");
        }

        direccionRepositorio.deleteById(idDireccion);
        return "Dirección eliminada con éxito";
    }

    //. Listar todas las direcciones
    public List<RespuestaDireccion> listarDirecciones() {
        List<Direccion> direcciones = direccionRepositorio.findAll();
        List<RespuestaDireccion> direccionesRespuesta = new ArrayList<>();

        for (Direccion direccion : direcciones) {
            RespuestaDireccion respuesta = generarRespuesta(direccion);
            direccionesRespuesta.add(respuesta);
        }

        return direccionesRespuesta;
    }

    public String construirDireccionComoTexto (Direccion direccion) {
        String dir;
        if (direccion == null) {
            dir = "No hay direccion registrada";
        } else {
            dir = direccion.getTipoVia() + " " + direccion.getNumeroViaPrincipal() + " " + direccion.getLetraViaPrincipal() + " " + direccion.getNumeroViaSecundaria() + " " + direccion.getLetraViaSecundaria() + " " + direccion.getNumeroPredio() + " " + direccion.getComplemento();
        }
        return dir;
    }

    public RespuestaDireccion generarRespuesta (Direccion direccion) {
        RespuestaDireccion respuesta = new RespuestaDireccion();

        respuesta.setIdDireccion(direccion.getIdDireccion());
        respuesta.setIdUsuario(direccion.getUsuario().getIdUsuario());
        respuesta.setTipoVia(direccion.getTipoVia());
        respuesta.setNumeroViaPrincipal(direccion.getNumeroViaPrincipal());
        respuesta.setLetraViaPrincipal(direccion.getLetraViaPrincipal());
        respuesta.setBisViaPrincipal(direccion.getBisViaPrincipal());
        respuesta.setNumeroViaSecundaria(direccion.getNumeroViaSecundaria());
        respuesta.setLetraViaSecundaria(direccion.getLetraViaSecundaria());
        respuesta.setBisViaSecundaria(direccion.getBisViaSecundaria());
        respuesta.setNumeroPredio(direccion.getNumeroPredio());
        respuesta.setComplemento(direccion.getComplemento());
        respuesta.setBarrio(direccion.getBarrio());
        respuesta.setCiudad(direccion.getCiudad());
        respuesta.setDepartamento(direccion.getDepartamento());
        respuesta.setEsPredeterminada(direccion.getEsPredeterminada());
        respuesta.setFechaCreacion(direccion.getFechaCreacion());

        return respuesta;
    }

}
