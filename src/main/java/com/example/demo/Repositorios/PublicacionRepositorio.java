package com.example.demo.Repositorios;

import com.example.demo.Entidades.Promocion;
import com.example.demo.Entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {

    List<Publicacion> findByestado(Boolean estado);

    /*    List<Publicacion> FindUsuarioB(Long usuarioId);

    List<Publicacion> findByTitulo(String titulo);

    Long countByUsuarioId (Long usuarioId);

    List<Publicacion> findByFechaCreacionBetween(LocalDate inicio, LocalDate fin);*/

}
