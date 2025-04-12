package com.example.demo.Repositorios;

import com.example.demo.Entidades.RelacionCarritoProducto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RelacionCarritoProductoRepositorio extends JpaRepository<RelacionCarritoProducto, Long> {
    //LE DICE A SPRING BOOT QUE NO ES UN SELECT
    @Modifying
    //LE DICE QUE ES UNA TRANSACION OSEA QUE SE TIENE QUE EJEUTAR TODA JUANTA PARA QUE NO DE ERROR
    @Transactional
    //ES UN QUERY UPDATE QUE TOMA COOMO PARAMETRO EL ID Y LA CANTIDAD DE LA URL Y LO ACTULIZA DE LA TABLA RelacionCarritoProducto
    @Query("UPDATE RelacionCarritoProducto r SET r.cantidad = :cantidad WHERE r.idCarritoXProducto = :id")
    void actualizarCantidad(@Param("id") Long id, @Param("cantidad") Integer cantidad);
}
