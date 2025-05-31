package com.example.demo.Repositorios;

import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Enums.EntidadesArchivoMultimediaEnum;
import com.example.demo.Enums.TipoArchivoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoMultimediaRepositorio extends JpaRepository<ArchivoMultimedia, Long> {
    List<ArchivoMultimedia> findByTipoEntidadAndIdObjetoEntidad(EntidadesArchivoMultimediaEnum entidad, Long idObjeto);
    List<ArchivoMultimedia> findByTipoEntidadAndIdObjetoEntidadAndTipo(EntidadesArchivoMultimediaEnum entidad, Long idObjeto, TipoArchivoEnum tipo);

}
