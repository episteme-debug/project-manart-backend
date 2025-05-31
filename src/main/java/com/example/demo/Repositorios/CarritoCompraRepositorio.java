package com.example.demo.Repositorios;

import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Interfaces.CarritoInterfaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoCompraRepositorio extends JpaRepository<CarritoCompra, Long> {
    // Retorna todos los carritos asociados a un usuario teniendo en cuenta las 'columnas' específicas de la interfaz
    CarritoCompra<CarritoInterfaz> findByUsuarioIdUsuario(Long id);

    //  Retorna todos los carritos de la tabla, hay que renombrar los nombres de las columnas para quecoincidan con los nombres de la interfaz
    @Query(value = "SELECT c.id_carrito AS idCarrito, c.total AS total, c.id_usuario AS usuarioIdUsuario FROM carrito_compra c\n", nativeQuery = true)
    List<CarritoInterfaz> findAllCarritos();

    // Retorna un carrito con un id específico
    @Query(value = "SELECT c.id_carrito AS idCarrito, c.total AS total, c.id_usuario AS usuarioIdUsuario FROM carrito_compra c WHERE c.id_carrito = :idCarrito", nativeQuery = true)
    CarritoInterfaz findCarritoById(@Param("idCarrito") Long idCarrito);

    //SUMA TODO LOS TOTALES DE CARRITO
    @Query(value = "SELECT SUM(r.subtotal) FROM carrito_compra AS c " +
            "JOIN relacion_carrito_producto AS r ON c.id_carrito = r.id_carrito " +
            "WHERE c.id_carrito = :idCarrito",
            nativeQuery = true)
    Double obtenerTotalPorCarrito(@Param("idCarrito") Long idCarrito);


}
