package com.example.demo.Controladores;

import com.example.demo.DTOs.CategoriaProductoDTO;
import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Servicios.CategoriaProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/categoriasProductos")
@RestController
public class CategoriaProductoControlador {

    @Autowired
    CategoriaProductoServicio categoriaProductoServicio;

    //1. Crear categoría de producto
    @PostMapping("/crearCategoria")
    public CategoriaProducto crearCategoria(@RequestBody CategoriaProducto categoriaProducto){
        return categoriaProductoServicio.crearCategoriaProducto(categoriaProducto);
    }

    //2. Obtener una categoría por ID
    @GetMapping("/obtenerCategoriaPorId/{id}")
    public CategoriaProducto obtenerCategoriaPorId(@PathVariable Long id) {
        return categoriaProductoServicio.obtenerCategoriaPorId(id);
    }

    //3. Obtener todas las categorias
    @GetMapping("/obtenerCategorias")
    public List<CategoriaProducto> obtenerCategorias(){
        return categoriaProductoServicio.obtenerCategorias();
    }

    //4. Obtener categorias por estado
    @GetMapping("/obtenerCategoriasPorEstado/{estadoCategoria}")
    public List<CategoriaProducto> obtenerCategoriasPorEstado(@PathVariable Boolean estadoCategoria){
        return categoriaProductoServicio.obtenerCategoriasPorEstado(estadoCategoria);
    }

    //5. Actualizar categoria
    @PatchMapping("/actualizarCategoria/{idCategoriaProducto}")
    public CategoriaProducto actualizarCategoria(@PathVariable Long idCategoria, @RequestBody CategoriaProductoDTO categoriaProductoDTO){
        return categoriaProductoServicio.actualizarCategoria(idCategoria, categoriaProductoDTO);
    }

    //6. Eliminar Categoría de producto
    @DeleteMapping("/eliminarCategoria/{idCategoria}")
    public List<?> eliminarCategoria(Long idCategoria){
        return categoriaProductoServicio.eliminarCategoria(idCategoria);
    }

//    //7. Contar productos en una categoría
//    @GetMapping("/contarProductos/{categoriaId}")
//    public int contarProductosPorCategoria(@PathVariable Long categoriaId) {
//        return categoriaProductoServicio.contarProductosPorCategoria(categoriaId);
//    }

}
