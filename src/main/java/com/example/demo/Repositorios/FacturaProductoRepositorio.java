package com.example.demo.Repositorios;

import com.example.demo.Entidades.FacturaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaProductoRepositorio extends JpaRepository<FacturaProducto, Long> {
}
