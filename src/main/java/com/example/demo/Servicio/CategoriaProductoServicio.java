package com.example.demo.Servicio;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoriaProductoServicio {

    @Autowired
    CategoriaProductoRepositorio categoriaProductoRepositorio;

    //Get All Categorias Producto Activas
    public List<CategoriaProducto> get_all_categorias_producto(){
        List<CategoriaProducto> allCategorias = categoriaProductoRepositorio.findAll();
        List<CategoriaProducto> categoriasActivas = new ArrayList<>();

        //Recorrer arreglo con todas las categorías
        for(int i = 0; i < allCategorias.size(); i++){
            CategoriaProducto categoriaProducto = allCategorias.get(i);
            //Filtrar categorias activas
            if(categoriaProducto.getEstadoCategoria() == true){
                categoriasActivas.add(categoriaProducto);
            }
        }

        return categoriasActivas;
    }

    //Insert a Categoria Producto
    public CategoriaProducto insert_categoria_producto(CategoriaProducto categoriaProducto){
        return categoriaProductoRepositorio.save(categoriaProducto);
    }

    // Guardar una categoría en la base de datos
    public CategoriaProducto saveCategoria(CategoriaProducto categoriaProducto) {
        return categoriaProductoRepositorio.save(categoriaProducto);
    }

}
