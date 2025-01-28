package com.example.demo.Repositorios;

import com.example.demo.Entidades.Artesano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtesanoRepositorio extends JpaRepository<Artesano, Integer> {

}
