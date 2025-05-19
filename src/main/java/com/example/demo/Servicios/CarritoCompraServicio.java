package com.example.demo.Servicios;

import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Interfaces.CarritoInterfaz;
import com.example.demo.Repositorios.CarritoCompraRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarritoCompraServicio {

    @Autowired
    CarritoCompraRepositorio carritoCompraRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //. Crear carrito
    public CarritoCompra crearCarrito(CarritoCompra carritoCompra) {
        if(carritoCompra.getUsuario() == null){
            throw new IllegalArgumentException("Falta los campos requeridos.");
        }
        return carritoCompraRepositorio.save(carritoCompra);
    }

    //Obtener todos los carritos
    public List<CarritoInterfaz> obtenerTodosCarritoCompra(){
        return carritoCompraRepositorio.findAllCarritos();
    }

    //Obtener el único carrito por usuario
    public CarritoCompra<CarritoInterfaz> obtenerPorUsuario(Long id){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }

        if (!usuarioRepositorio.existsById(id)) {
            throw new NoSuchElementException("Usuario no existe.");
        }

        CarritoCompra<CarritoInterfaz> carrito = carritoCompraRepositorio.findByUsuarioIdUsuario(id);

        if (carrito == null) {
            throw new NoSuchElementException("El usuario no tiene ningun carrito activo.");
        }

        return carrito;
    }

    //Obtener Carrito por id
    public CarritoInterfaz ObtenerPorId(Long id){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
        if(!carritoCompraRepositorio.existsById(id)){
            throw new NoSuchElementException("No se encontro ningun carrito.");
        }
        return carritoCompraRepositorio.findCarritoById(id);
    }

    //Obtener Total de carrito
    public Double obtenerTotal(Long idCarrito){
        if (idCarrito == null || idCarrito <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
        if(!carritoCompraRepositorio.existsById(idCarrito)){
            throw new NoSuchElementException("No se encontro ningun carrito.");
        }
        return carritoCompraRepositorio.obtenerTotalPorCarrito(idCarrito);
    }

    //La actualizacion total ya esta esta tipo triggers donde con cualquier cambio se de dispara el trigger aunque suene redundaten
    //es esta en la clase DatabaseInitializer6

    public void eliminarCarritoCompraById(Long idCarrito){
        if (idCarrito == null || idCarrito <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
        if(!carritoCompraRepositorio.existsById(idCarrito)){
            throw new NoSuchElementException("Carrito no existe.");
        }
        carritoCompraRepositorio.deleteById(idCarrito);
    }
}
