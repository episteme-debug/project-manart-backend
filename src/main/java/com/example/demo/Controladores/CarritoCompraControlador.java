package com.example.demo.Controladores;

import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Interfaces.CarritoInterfaz;
import com.example.demo.Servicios.CarritoCompraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/carrito")
@RestController
public class CarritoCompraControlador {

    @Autowired
    CarritoCompraServicio carritoCompraServicio;

    //Crea un carrito
    @PostMapping("/crearCarrito")
    public CarritoCompra crearCarrito(@RequestBody CarritoCompra carritoCompra){
        System.out.println(carritoCompra.getIdCarrito());
        return carritoCompraServicio.crearCarrito(carritoCompra);
    }

    //Obtener los carritos de la base de datos
    @GetMapping("/obtenerTodos")
    public List<CarritoInterfaz>obtenerTodos(){
        return carritoCompraServicio.obtenerTodosCarritoCompra();
    }

    //Obtener los carritos por usuario
    @GetMapping("/listarporusuario/{id}")
    public CarritoCompra<CarritoInterfaz> listarPorUsuario(@PathVariable Long id){
        return carritoCompraServicio.listarPorUsuario(id);
    }

    //Obtener carrito por id
    @GetMapping("/obtenerPorId/{id}")
    public CarritoInterfaz ObtenerPorId(@PathVariable Long id){
        return carritoCompraServicio.ObtenerPorId(id);
    }

    //Obtener el total del un carrito
    @GetMapping("/obtenerTotal/{id}")
    public Double obtenerTotalCarrito(@PathVariable Long id) {
        return carritoCompraServicio.obtenerTotal(id);
    }

    //Eliminar carrito y todas su relaciones
    @DeleteMapping("/eliminarById/{id}")
    public List<?> eliminarCarritoCompraById(@PathVariable Long id) {
        return carritoCompraServicio.eliminarCarritoCompraById(id);
    }

}
