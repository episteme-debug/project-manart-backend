package com.example.demo.Servicios;

import com.example.demo.DTOs.Publicacion.CrearPublicacionDTO;
import com.example.demo.DTOs.Publicacion.PublicacionDTO;
import com.example.demo.Entidades.Publicacion;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.PublicacionRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicacionServicio {
    @Autowired
    PublicacionRepositorio publicacionRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public PublicacionDTO crearPublicacion(CrearPublicacionDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(dto.getTitulo());
        publicacion.setContenido(dto.getContenido());
        publicacion.setUsuario(usuario);

        Publicacion guardada = publicacionRepositorio.save(publicacion);

        return generarRespuesta(guardada);
    }



    public PublicacionDTO obtenerPublicacionById(Long id) {
        if(id == null || id <= 0){
            throw new IllegalArgumentException("El ID de la publicación no es válido.");
        }

        Publicacion publicacion = publicacionRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Publicación no encontrada"));

        return generarRespuesta(publicacion);
    }


    public List<PublicacionDTO> obtenerTodasPublicaciones(){
       List<Publicacion>publicacion = publicacionRepositorio.findAll();
       List<PublicacionDTO> publicacionDTOS = new ArrayList<>();
       for(Publicacion p: publicacion){
           PublicacionDTO respuesta = generarRespuesta(p);
           publicacionDTOS.add(respuesta);
       }
      return publicacionDTOS;
    }

    public List<PublicacionDTO> obtenerPorEstado(Boolean estado) {
        List<Publicacion> publicaciones = publicacionRepositorio.findByestado(estado);
        return publicaciones.stream()
                .map(this::generarRespuesta)
                .collect(Collectors.toList());
    }

    public PublicacionDTO actualizarPublicacion(Long id, PublicacionDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID de la publicación no es válido.");
        }

        Optional<Publicacion> publicacionOptional = publicacionRepositorio.findById(id);
        if (!publicacionOptional.isPresent()) {
            throw new NoSuchElementException("Publicación no encontrada");
        }

        Publicacion publicacion = publicacionOptional.get();

        if (dto.getTitulo() != null) {
            publicacion.setTitulo(dto.getTitulo());
        }

        if (dto.getContenido() != null) {
            publicacion.setContenido(dto.getContenido());
        }

        if (dto.getEstado() != null) {
            publicacion.setEstado(dto.getEstado());
        }

        Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
        return generarRespuesta(publicacionActualizada);
    }



    public void eliminarPorId (Long id){
        if(id == null || id <= 0){
            throw new IllegalArgumentException("El Id de la pulicacion no es valido");
        }
        if(!publicacionRepositorio.existsById(id)){
            throw new NoSuchElementException("Publicacion no existe");
        }
        publicacionRepositorio.deleteById(id);
    }

    public PublicacionDTO generarRespuesta(Publicacion publicacion){
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setContenido(publicacion.getContenido());
        publicacionDTO.setEstado(publicacion.getEstado());
        publicacionDTO.setFechaCreacion(publicacion.getFechaCreacion());
        publicacionDTO.setFechaUltimaActualizacion(publicacion.getFechaUltimaActualizacion());

        if (publicacion.getUsuario() != null) {
            publicacionDTO.setNombreUsuario(publicacion.getUsuario().getNombreUsuario());
        }

        return publicacionDTO;
    }

}
