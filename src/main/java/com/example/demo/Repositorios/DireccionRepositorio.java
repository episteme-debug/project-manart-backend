package com.example.demo.Repositorios;

import com.example.demo.Entidades.Direccion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DireccionRepositorio extends JpaRepository<Direccion, Long> {

    List<Direccion> findByUsuario_IdUsuario(Long idUsuario);
    Optional<Direccion> findByUsuario_IdUsuarioAndEsPredeterminadaTrue(Long idUsuario);

}
