package com.example.demo.Repositorios;

import com.example.demo.Entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
}
