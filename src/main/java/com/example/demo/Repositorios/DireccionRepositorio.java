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
/*    List<Direccion> findByesPredeterminada(Boolean esPredeterminada);

    Integer countByUsuario_IdUsuario(Integer idUsuario);

    long countByEsPredeterminada(boolean esPredeterminada);

    Optional<Direccion> findByUsuario_IdUsuarioAndEsPredeterminada(Integer idUsuario, Boolean esPredeterminada);

    @Modifying
    @Transactional
    @Query("UPDATE Direccion d SET d.esPredeterminada = false WHERE d.esPredeterminada = true")
    void removerPredeterminadas();

    @Query("SELECT d FROM Direccion d ORDER BY d.fechaCreacion DESC LIMIT 1")
    Optional<Direccion> findUltimaCreada();

    // Obtener la última dirección actualizada
    @Query("SELECT d FROM Direccion d ORDER BY d.fechaActualizacion DESC LIMIT 1")
    Optional<Direccion> findUltimaActualizada();*/
}
