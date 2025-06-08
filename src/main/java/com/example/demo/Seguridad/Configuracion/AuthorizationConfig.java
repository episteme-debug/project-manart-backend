package com.example.demo.Seguridad.Configuracion;

import com.example.demo.Entidades.Direccion;
import com.example.demo.Entidades.Factura;
import com.example.demo.Entidades.Producto;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.DireccionRepositorio;
import com.example.demo.Repositorios.FacturaRepositorio;
import com.example.demo.Repositorios.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component("autorizacion")
@RequiredArgsConstructor
public class AuthorizationConfig {

    public boolean esPropietario(Long userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal(); // Tu clase que implementa UserDetails
        return usuario.getIdUsuario().equals(userId);
    }

    private final DireccionRepositorio direccionRepositorio;

    public boolean esPropietarioDireccion(Long idDireccion) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        Direccion direccion = direccionRepositorio.findById(idDireccion)
                .orElseThrow(() -> new NoSuchElementException("Dirección no encontrada"));

        return direccion.getUsuario().getIdUsuario().equals(usuario.getIdUsuario());
    }

    private final ProductoRepositorio productoRepositorio;

    public boolean esPropietarioProducto(Long idProducto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        Producto producto = productoRepositorio.findById(idProducto)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));

        return producto.getUsuario().getIdUsuario().equals(usuario.getIdUsuario());
    }


    private final FacturaRepositorio facturaRepositorio;
    public boolean esPropietarioFactura(Long idFactura) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        Factura factura = facturaRepositorio.findById(idFactura)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));

        return factura.getPedido().getUsuario().getIdUsuario().equals(usuario.getIdUsuario());
    }
}
