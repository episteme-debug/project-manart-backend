package com.example.demo.guardar_imagen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface imagenRepositorio extends JpaRepository<imagen, Integer> {
}
