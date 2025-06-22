package com.example.demo.Servicios;

import com.example.demo.DTOs.Publicacion.CrearPublicacion;
import com.example.demo.DTOs.Publicacion.PublicacionDTO;
import com.example.demo.Entidades.Publicacion;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.PublicacionRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PublicacionDTO crearPublicacion(CrearPublicacion dto) {
        Usuario usuario = usuarioRepositorio.findById(dto.getIdUsuario())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(dto.getTitulo());
        publicacion.setContenido(dto.getContenido());
        publicacion.setEstado(dto.getEstado());
        publicacion.setUsuario(usuario);

        Publicacion nueva = publicacionRepositorio.save(publicacion);

        return generarRespuesta(nueva); // usa el DTO normal
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


    public Publicacion actulizarPublicacion(Long id,PublicacionDTO publicacionDTO){
        if(publicacionDTO == null || publicacionDTO.getId() == null || id == null || id <= 0){
            throw new IllegalArgumentException("Los datos de la publicacion no son validos");
        }

        Optional<Publicacion> publicacionOptional = publicacionRepositorio.findById(publicacionDTO.getId());
        if(!publicacionOptional.isPresent()){
            throw  new NoSuchElementException("Publicaion no encontrada");
        }

        Publicacion publicacion = publicacionRepositorio.findById(id).get();

        if(publicacionDTO.getTitulo() != null){
            publicacion.setTitulo(publicacionDTO.getTitulo());
        }

        if(publicacionDTO.getContenido() != null){
            publicacion.setContenido(publicacionDTO.getContenido());
        }

        if(publicacionDTO.getEstado() != null){
            publicacion.setEstado(publicacionDTO.getEstado());
        }

        if(publicacionDTO.getFechaUltimaActualizacion() != null){
            publicacion.setFechaUltimaActualizacion(publicacionDTO.getFechaUltimaActualizacion());
        }
        return publicacionRepositorio.save(publicacion);
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
        return publicacionDTO;
    }
}
