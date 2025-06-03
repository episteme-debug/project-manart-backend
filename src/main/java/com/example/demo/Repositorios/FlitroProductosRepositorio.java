package com.example.demo.Repositorios;

import com.example.demo.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlitroProductosRepositorio extends JpaRepository <Producto , Long>{


}
