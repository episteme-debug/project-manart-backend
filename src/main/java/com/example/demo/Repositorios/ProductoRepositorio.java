package com.example.demo.Repositorios;

import com.example.demo.DTOs.ProductoDTO.RangoDePreciosDTO;
import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.RelacionCategoriaProducto;
import com.example.demo.Enums.RegionesDeColombiaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {


    @Query(value = """
    SELECT DISTINCT p.* FROM producto p
    JOIN relacion_categoria_producto rcp ON p.id_producto = rcp.id_producto
    WHERE rcp.id_categoria_producto IN (
        SELECT id_categoria_producto 
        FROM relacion_categoria_producto 
        WHERE id_producto = :idProducto
    )
    AND p.id_producto <> :idProducto
""", nativeQuery = true)
    List<Producto> findRelacionados(@Param("idProducto") Long idProducto);


    @Query(value = "SELECT p.*, c.nombre_categoria, o.nombre_promocion, o.porcentaje_descuento_promocion " +
            "FROM producto p " +
            "INNER JOIN relacion_categoria_producto rcp ON p.id_producto = rcp.id_producto " +
            "INNER JOIN categoria_producto c ON rcp.id_categoria_producto = c.id_categoria " +
            "LEFT JOIN promocion o ON p.id_promocion = o.id_promocion " +
            "WHERE (:nombreCategoria IS NULL OR c.nombre_categoria = :nombreCategoria) " +
            "AND (:porcentajeDescuento IS NULL OR o.porcentaje_descuento_promocion = :porcentajeDescuento) " +
            "AND (:precioMin IS NULL OR p.precio_producto >= :precioMin) " +
            "AND (:precioMax IS NULL OR p.precio_producto <= :precioMax)"+
            " AND (:region IS NULL OR p.region_producto =  :region)",
            nativeQuery = true)

    List<Producto> buscarProductosFiltrados(String nombreCategoria, Integer porcentajeDescuento, Double precioMin, Double precioMax , String region);

    @Query("SELECT new com.example.demo.DTOs.ProductoDTO.RangoDePreciosDTO(MIN(p.precioProducto), MAX(p.precioProducto)) FROM Producto p")
    RangoDePreciosDTO rango_precios();

    // Obtener productos por nombre
    @Query(value = "SELECT * FROM producto p\n" +
            "WHERE LOWER(REPLACE(p.nombre_producto, ' ', '')) LIKE LOWER(CONCAT('%', :nombreProducto, '%'))", nativeQuery = true)
    List<Producto> findByNombreProducto(@Param("nombreProducto") String nombreProducto);

    List<Producto> findByCategorias_IdCategoria(Long idCategoria);

    List<Producto> findByUsuario_IdUsuario(Long idUsuario);

    List<Producto> findByRegionProducto(RegionesDeColombiaEnum region);
}