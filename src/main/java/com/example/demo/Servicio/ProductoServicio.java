package com.example.demo.Servicio;

import com.example.demo.Entidades.Producto;
import com.example.demo.Repositorios.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductoServicio {
    @Autowired
    ProductoRepositorio ProductoRepositorio;


    public Optional<Producto> getProductoById(Integer id){
        return ProductoRepositorio.findById(id);
    }
}
