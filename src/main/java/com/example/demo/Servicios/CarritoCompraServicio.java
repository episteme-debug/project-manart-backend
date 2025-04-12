package com.example.demo.Servicios;

import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Interfaces.CarritoInterfaz;
import com.example.demo.Repositorios.CarritoCompraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoCompraServicio {
    @Autowired
    CarritoCompraRepositorio carritoCompraRepositorio;

    //. Crear carrito
    public CarritoCompra crearCarrito(CarritoCompra carritoCompra) {
        return carritoCompraRepositorio.save(carritoCompra);
    }

    //Obtener todos los carritos
    public List<CarritoInterfaz> obtenerTodosCarritoCompra(){
        return carritoCompraRepositorio.findAllCarritos();
    }

    //Obtener todos los carritos por usuario
    public List<CarritoInterfaz> listarPorUsuario(Long id){
        return carritoCompraRepositorio.findByUsuarioIdUsuario(id);
    }

    //Obtener Carrito por id
    public CarritoInterfaz ObtenerPorId(Long id){
        return carritoCompraRepositorio.findCarritoById(id);
    }

    //Obtener Total de carrito
    public Double obtenerTotal(Long idCarrito){
        return carritoCompraRepositorio.obtenerTotalPorCarrito(idCarrito);
    }

    //La actualizacion total ya esta esta tipo triggers donde con cualquier cambio se de dispara el trigger aunque suene redundaten
    //es esta en la clase DatabaseInitializer

    public List<?> eliminarCarritoCompraById(Long idCarrito){
        carritoCompraRepositorio.deleteById(idCarrito);
        return obtenerTodosCarritoCompra();
    }
}
