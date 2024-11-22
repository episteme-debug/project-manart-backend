package com.example.demo.Repositorios;

import com.example.demo.Entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
}
