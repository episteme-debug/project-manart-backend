package com.example.demo.Repositorios;

import com.example.demo.Entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Long> {

    Optional<Factura> findByPedido_IdPedido(Long idPedido);
}
