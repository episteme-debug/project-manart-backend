package com.example.demo.Controladores;

import com.example.demo.DTOs.CategoriasProductoDTO.ActualizacionCategoria;
import com.example.demo.DTOs.CategoriasProductoDTO.CreacionCategoria;
import com.example.demo.DTOs.CategoriasProductoDTO.RespuestaCategoria;
import com.example.demo.Servicios.CategoriaProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/categoriaproducto")
@RestController
public class CategoriaProductoControlador {

    @Autowired
    CategoriaProductoServicio categoriaProductoServicio;

    //1. Crear categoría de producto
    @PostMapping("private/crearcategoria")
    @PreAuthorize("hasRole('ADMIN')")
    public RespuestaCategoria crearCategoria(@RequestBody CreacionCategoria creacionCategoria){
        return categoriaProductoServicio.crearCategoriaProducto(creacionCategoria);
    }

    //2. Obtener una categoría por ID
    @GetMapping("public/obtenercategoriaporid/{id}")
    public RespuestaCategoria obtenerCategoriaPorId(@PathVariable Long id) {
        return categoriaProductoServicio.obtenerCategoriaPorId(id);
    }

    //3. Obtener todas las categorias
    @GetMapping("public/obtenercategorias")
    public List<RespuestaCategoria> obtenerCategorias(){
        return categoriaProductoServicio.obtenerCategorias();
    }

    //4. Obtener categorias por estado
    @GetMapping("private/obtenercategoriasporestado/{estadoCategoria}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RespuestaCategoria> obtenerCategoriasPorEstado(@PathVariable Boolean estadoCategoria){
        return categoriaProductoServicio.obtenerCategoriasPorEstado(estadoCategoria);
    }

    //5. Actualizar categoria
    @PatchMapping("private/actualizarcategoria/{idCategoria}")
    @PreAuthorize("hasRole('ADMIN')")
    public RespuestaCategoria actualizarCategoria(@PathVariable Long idCategoria, @RequestBody ActualizacionCategoria actualizacionCategoria){
        return categoriaProductoServicio.actualizarCategoria(idCategoria, actualizacionCategoria);
    }

    //6. Eliminar Categoría de producto
    @DeleteMapping("private/eliminarcategoria/{idCategoria}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarCategoria(@PathVariable Long idCategoria){
        categoriaProductoServicio.eliminarCategoria(idCategoria);
    }

//    //7. Contar productos en una categoría
//    @GetMapping("/contarProductos/{categoriaId}")
//    public int contarProductosPorCategoria(@PathVariable Long categoriaId) {
//        return categoriaProductoServicio.contarProductosPorCategoria(categoriaId);
//    }

}
