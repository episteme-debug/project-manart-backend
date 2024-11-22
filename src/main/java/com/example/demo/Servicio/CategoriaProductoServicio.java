package com.example.demo.Servicio;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class CategoriaProductoServicio {

    @Autowired
    CategoriaProductoRepositorio categoriaProductoRepositorio;

    public void saveCategoriasPrueba() {
        List<CategoriaProducto> categorias = Arrays.asList(
                new CategoriaProducto(1, "Artesanías de Barro", "Productos hechos a mano con barro", true, "barro.jpg", 1),
                new CategoriaProducto(2, "Tejidos y Fibras", "Productos elaborados con fique y otros tejidos", true, "tejidos.jpg", 2),
                new CategoriaProducto(3, "Joyería Artesanal", "Joyería hecha con materiales naturales", true, "joyeria.jpg", 3),
                new CategoriaProducto(4, "Cuero y Marroquinería", "Productos de cuero hechos por artesanos", true, "cuero.jpg", 4),
                new CategoriaProducto(5, "Decoración para el Hogar", "Artículos decorativos hechos a mano", true, "decoracion.jpg", 5),
                new CategoriaProducto(6, "Arte Indígena", "Obras de arte y productos hechos por comunidades indígenas", true, "arte_indigena.jpg", 6),
                new CategoriaProducto(7, "Cestería", "Canastos y cestas hechos con fibras naturales", true, "cesteria.jpg", 7),
                new CategoriaProducto(8, "Ropa Tradicional", "Vestimenta hecha con técnicas tradicionales", true, "ropa_tradicional.jpg", 8),
                new CategoriaProducto(9, "Instrumentos Musicales", "Instrumentos tradicionales artesanales", true, "instrumentos.jpg", 9),
                new CategoriaProducto(10, "Pinturas y Arte Visual", "Pinturas y arte visual hechas por artistas locales", true, "pinturas.jpg", 10)
        );

        categoriaProductoRepositorio.saveAll(categorias);
    }
}
