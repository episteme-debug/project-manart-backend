package com.example.demo.Repositorios;

import com.example.demo.Entidades.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaProductoRepositorio extends JpaRepository<CategoriaProducto, Long> {
    // Obtener productos por nombre
    Optional<CategoriaProducto> findByNombreCategoria(String nombreCategoria);

    //Obtener categorias por estado
    List<CategoriaProducto> findByEstadoCategoria(Boolean estadoCategoria);

/*    @Query("SELECT COUNT(p) FROM Producto p WHERE p.categoria.id = :categoriaId")
    int contarProductosPorCategoria(@Param("categoriaId") Long categoriaId);*/
}
