package com.example.demo.Servicio;

import com.example.demo.Entidades.CategoriaProducto;
import com.example.demo.Repositorios.CategoriaProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class CategoriaProductoServicio {

    @Autowired
    CategoriaProductoRepositorio categoriaProductoRepositorio;

}
