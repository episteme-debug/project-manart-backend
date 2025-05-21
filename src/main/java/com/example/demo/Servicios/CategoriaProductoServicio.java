package com.example.demo.Servicios;

import com.example.demo.DTOs.CategoriasProductoDTO.ActualizacionCategoria;
import com.example.demo.DTOs.CategoriasProductoDTO.CreacionCategoria;
import com.example.demo.DTOs.CategoriasProductoDTO.RespuestaCategoria;
import com.example.demo.Entidades.ArchivoMultimedia;
import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Enums.EntidadesArchivoMultimediaEnum;
import com.example.demo.Repositorios.ArchivoMultimediaRepositorio;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CategoriaProductoServicio {

    private final CategoriaProductoRepositorio categoriaProductoRepositorio;
    private final ArchivoMultimediaRepositorio archivoMultimediaRepositorio;
    private final ProductoServicio productoServicio;

    //1. Crear una Categoria de Producto
    public RespuestaCategoria crearCategoriaProducto(CreacionCategoria creacionCategoria){
        CategoriaProducto categoriaProducto = new CategoriaProducto();

        categoriaProducto.setNombreCategoria(creacionCategoria.getNombreCategoria());
        categoriaProducto.setDescripcionCategoria(creacionCategoria.getDescripcionCategoria());

        CategoriaProducto nuevaCategoria = categoriaProductoRepositorio.save(categoriaProducto);

        return generarRespuesta(nuevaCategoria);
    }

    //2. Obtener una categoría por su id
    public RespuestaCategoria obtenerCategoriaPorId(Long idCategoria){
        CategoriaProducto categoriaProducto = categoriaProductoRepositorio.findById(idCategoria)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada"));

        return generarRespuesta(categoriaProducto);
    }

    //3. Obtener todas las categorias
    public List<RespuestaCategoria> obtenerCategorias(){
        List<CategoriaProducto> categoriasProductos = categoriaProductoRepositorio.findAll();
        List<RespuestaCategoria> respuestasCategorias = new ArrayList<>();

        for (CategoriaProducto categoria : categoriasProductos) {
            RespuestaCategoria respuesta = generarRespuesta(categoria);
            respuestasCategorias.add(respuesta);
        }

        return respuestasCategorias;
    }

    //4. Obtener categorias por estado
    public List<RespuestaCategoria> obtenerCategoriasPorEstado(Boolean estadoCategoria){
        List<CategoriaProducto> categoriasProductos = categoriaProductoRepositorio.findByEstadoCategoria(estadoCategoria);
        List<RespuestaCategoria> respuestasCategorias = new ArrayList<>();

        for (CategoriaProducto categoria : categoriasProductos) {
            RespuestaCategoria respuesta = generarRespuesta(categoria);
            respuestasCategorias.add(respuesta);
        }

        return respuestasCategorias;
    }

    //5. Actualizar uno o más datos de categoría
    public RespuestaCategoria actualizarCategoria(Long idCategoria, ActualizacionCategoria actualizacionCategoria){

        CategoriaProducto categoriaProducto = categoriaProductoRepositorio.findById(idCategoria)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada"));

        if(actualizacionCategoria.getNombreCategoria() != null){
            categoriaProducto.setNombreCategoria(actualizacionCategoria.getNombreCategoria());
        }

        if(actualizacionCategoria.getDescripcionCategoria() != null){
            categoriaProducto.setDescripcionCategoria(actualizacionCategoria.getDescripcionCategoria());
        }

        if(actualizacionCategoria.getEstadoCategoria() != null){
            categoriaProducto.setEstadoCategoria(actualizacionCategoria.getEstadoCategoria());
        }

        if(actualizacionCategoria.getImagenCategoria() != null){
            categoriaProducto.setImagenCategoria(actualizacionCategoria.getImagenCategoria());
        }

        CategoriaProducto nuevaCategoria = categoriaProductoRepositorio.save(categoriaProducto);

        return generarRespuesta(nuevaCategoria);
    }

    //6. Eliminar categoría
    public void eliminarCategoria(Long idCategoria){
        categoriaProductoRepositorio.deleteById(idCategoria);
    }

    public RespuestaCategoria generarRespuesta (CategoriaProducto categoriaProducto) {
        RespuestaCategoria respuestaCategoria = new RespuestaCategoria();

        respuestaCategoria.setIdCategoria(categoriaProducto.getIdCategoria());
        respuestaCategoria.setNombreCategoria(categoriaProducto.getNombreCategoria());
        respuestaCategoria.setDescripcionCategoria(categoriaProducto.getDescripcionCategoria());
        respuestaCategoria.setEstadoCategoria(categoriaProducto.getEstadoCategoria());

        List<ArchivoMultimedia> archivos = archivoMultimediaRepositorio.findByTipoEntidadAndIdObjetoEntidad(EntidadesArchivoMultimediaEnum.CategoriaProducto, categoriaProducto.getIdCategoria());

        respuestaCategoria.setArchivoMultimedia(archivos);

        return respuestaCategoria;
    }

/*    // Contar cuántos productos hay por categoría.
    public int contarProductosPorCategoria(Long categoriaId) {
        return categoriaProductoRepositorio.contarProductosPorCategoria(categoriaId);
    }*/
}
