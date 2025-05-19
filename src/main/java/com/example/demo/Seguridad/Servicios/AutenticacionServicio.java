package com.example.demo.Seguridad.Servicios;

import com.example.demo.DTOs.AutenticacionRespuesta;
import com.example.demo.DTOs.LogInDTO;
import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.UsuarioRepositorio;
import com.example.demo.Servicios.CarritoCompraServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionServicio {

    private final JWTServicio jwtService;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CarritoCompraServicio carritoCompraServicio;

    public AutenticacionRespuesta registroUsuario(Usuario usuario)
    {
        if (usuario == null || usuario.getNombreUsuario() == null || usuario.getApellidoUsuario() == null || usuario.getEmailUsuario() == null || usuario.getHashContrasenaUsuario() == null || usuario.getTelefonoUsuario() == null || usuario.getEstadoUsuario() == null || usuario.getRolUsuario() == null || usuario.getAlias() == null) {
            throw new IllegalArgumentException("Hay ingresos vacíos.");
        }

        if (usuarioRepositorio.existsByAlias(usuario.getAlias())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }

        usuario.setHashContrasenaUsuario(passwordEncoder.encode(usuario.getPassword()));
        Usuario usuarioCreado = usuarioRepositorio.save(usuario);

        // Crear un carrito asociado a este usuario
        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setUsuario(usuarioCreado);
        carritoCompraServicio.crearCarrito(carritoCompra);

        return AutenticacionRespuesta.builder()
                .token(jwtService.generarToken(usuario))
                .build();
    }

    public AutenticacionRespuesta login(LogInDTO request)
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getAlias(), request.getContraseña()
            ));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Alias o contraseña incorrectos");
        }

        Usuario usuario = usuarioRepositorio.findByAlias(request.getAlias())
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        String token = jwtService.generarToken(usuario);

        return AutenticacionRespuesta.builder()
                .token(token)
                .build();
    }

}
