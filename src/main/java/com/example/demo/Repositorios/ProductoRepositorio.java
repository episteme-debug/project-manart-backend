package com.example.demo.Repositorios;

import com.example.demo.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
}