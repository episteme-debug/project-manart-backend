package com.example.demo.Repositorios;

import com.example.demo.Entidades.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromocionRepositorio extends JpaRepository<Promocion, Long> {

    List<Promocion> findByEstadoPromocion(Boolean estadoPromocion);

    @Query("SELECT p FROM Promocion p WHERE " +
            "(:palabraClave IS NULL OR p.nombrePromocion LIKE %:palabraClave%) AND " +
            "(:descuento IS NULL OR p.porcentajeDescuentoPromocion = :descuento) AND " +
            "(:fechaInicio IS NULL OR :fechaFin IS NULL OR p.fechaInicioPromocion BETWEEN :fechaInicio AND :fechaFin)")
    List<Promocion> buscarPromociones(@Param("palabraClave") String palabraClave,
                                      @Param("descuento") Integer descuento,
                                      @Param("fechaInicio") LocalDate fechaInicio,
                                      @Param("fechaFin") LocalDate fechaFin);

}
