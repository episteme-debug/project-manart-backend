package com.example.demo.Servicios;

import com.example.demo.DTOs.CategoriaProductoDTO;
import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaProductoServicio {

    @Autowired
    CategoriaProductoRepositorio categoriaProductoRepositorio;

    //1. Crear una Categoria de Producto
    public CategoriaProducto crearCategoriaProducto(CategoriaProducto categoriaProducto){
        return categoriaProductoRepositorio.save(categoriaProducto);
    }

    //2. Obtener una categoría por su id
    public CategoriaProducto obtenerCategoriaPorId(Long idCategoria){
        return categoriaProductoRepositorio.findById(idCategoria).get();
    }

    //3. Obtener todas las categorias
    public List<CategoriaProducto> obtenerCategorias(){
        return categoriaProductoRepositorio.findAll();
    }

    //4. Obtener categorias por estado
    public List<CategoriaProducto> obtenerCategoriasPorEstado(Boolean estadoCategoria){
        return categoriaProductoRepositorio.findByEstadoCategoria(estadoCategoria);
    }

    //5. Actualizar uno o más datos de categoría
    public CategoriaProducto actualizarCategoria(Long idCategoria, CategoriaProductoDTO categoriaProductoDTO){

        CategoriaProducto categoriaProducto = categoriaProductoRepositorio.findById(idCategoria).get();

        if(categoriaProductoDTO.getNombreCategoria() != null){
            categoriaProducto.setNombreCategoria(categoriaProductoDTO.getNombreCategoria());
        }

        if(categoriaProductoDTO.getDescripcionCategoria() != null){
            categoriaProducto.setDescripcionCategoria(categoriaProductoDTO.getDescripcionCategoria());
        }

        if(categoriaProductoDTO.getEstadoCategoria() != null){
            categoriaProducto.setEstadoCategoria(categoriaProductoDTO.getEstadoCategoria());
        }

        if(categoriaProductoDTO.getImagenCategoria() != null){
            categoriaProducto.setImagenCategoria(categoriaProductoDTO.getImagenCategoria());
        }

        return categoriaProductoRepositorio.save(categoriaProducto);
    }

    //6. Eliminar categoría
    public List<?> eliminarCategoria(Long idCategoria){
        categoriaProductoRepositorio.deleteById(idCategoria);

        return obtenerCategorias();
    }

/*    // Contar cuántos productos hay por categoría.
    public int contarProductosPorCategoria(Long categoriaId) {
        return categoriaProductoRepositorio.contarProductosPorCategoria(categoriaId);
    }*/
}
