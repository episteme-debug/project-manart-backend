package com.example.demo.Repositorios;

import com.example.demo.Entidades.ArchivoMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoMultimediaRepositorio extends JpaRepository<ArchivoMultimedia, Long> {

}
