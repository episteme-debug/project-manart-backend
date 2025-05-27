package com.example.demo.Repositorios;

import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.RelacionCategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    // Obtener productos por nombre
    @Query(value = "SELECT * FROM producto p\n" +
            "WHERE LOWER(REPLACE(p.nombre_producto, ' ', '')) LIKE LOWER(CONCAT('%', :nombreProducto, '%'))", nativeQuery = true)
    List<Producto> findByNombreProducto(@Param("nombreProducto") String nombreProducto);

    List<Producto> findByCategorias_IdCategoria(Long idCategoria);

    List<Producto> findByUsuario_IdUsuario(Long idUsuario);
}