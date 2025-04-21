package com.example.demo.Repositorios;

import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Interfaces.ArchivoMultimediaInterfaz;
import com.example.demo.Interfaces.CarritoInterfaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface ArchivoMultimediaRepositorio extends JpaRepository<ArchivoMultimedia, Long> {
    List<ArchivoMultimediaInterfaz> findByUsuario_IdUsuario(Long id);
    List<ArchivoMultimediaInterfaz> findByProducto_IdProducto(Long id);
    List<ArchivoMultimediaInterfaz> findByCategoriaProducto_IdCategoria(Long id);
    List<ArchivoMultimediaInterfaz> findByPublicacion_Id(Long id);


}
