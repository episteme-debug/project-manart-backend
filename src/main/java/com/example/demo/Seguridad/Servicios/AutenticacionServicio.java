package com.example.demo.Seguridad.Servicios;

import com.example.demo.DTOs.AuthDTO.AutenticacionRespuesta;
import com.example.demo.DTOs.AuthDTO.LogIn;
import com.example.demo.DTOs.UsuarioDTO.CreacionUsuario;
import com.example.demo.Entidades.CarritoCompra;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.UsuarioRepositorio;
import com.example.demo.Servicios.CarritoCompraServicio;
import com.example.demo.Servicios.UsuarioServicio;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionServicio {

    private final JWTServicio jwtService;
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioServicio usuarioServicio;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CarritoCompraServicio carritoCompraServicio;
    private final CookieServicio cookieServicio;

    public String registroUsuario(CreacionUsuario dto, HttpServletResponse response) {
        Usuario usuarioCreado = usuarioRepositorio.save(inicializarUsuario(dto));

        // Crear un carrito asociado a este usuario
        CarritoCompra carritoCompra = new CarritoCompra();
        carritoCompra.setUsuario(usuarioCreado);
        carritoCompraServicio.crearCarrito(carritoCompra);

        String token = jwtService.generarToken(usuarioCreado);
        cookieServicio.deleteCookie("token", response);
        cookieServicio.addHttpOnlyCookie("token", token, 7 * 24 * 60 * 60, response);

        return usuarioCreado.getRolUsuario().toString();
    }

    public String login(LogIn request, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getAlias(), request.getContraseña()
            ));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Alias o contraseña incorrectos");
        }

        Usuario usuario = usuarioServicio.obtenerPorAlias(request.getAlias());
        String token = jwtService.generarToken(usuario);

        cookieServicio.deleteCookie("token", response);
        cookieServicio.addHttpOnlyCookie("token", token, 7 * 24 * 60 * 60, response);

        return usuario.getRolUsuario().toString();
    }

    public Usuario inicializarUsuario(CreacionUsuario dto) {
        if (dto == null ||
                isNullOrEmpty(dto.getAlias()) ||
                isNullOrEmpty(dto.getNombreUsuario()) ||
                isNullOrEmpty(dto.getNumeroDocumentoUsuario()) ||
                isNullOrEmpty(dto.getApellidoUsuario()) ||
                isNullOrEmpty(dto.getEmailUsuario()) ||
                isNullOrEmpty(dto.getHashContrasenaUsuario()) ||
                isNullOrEmpty(dto.getTelefonoUsuario())) {

            throw new IllegalArgumentException("Todos los campos deben estar llenos.");
        }

        if (usuarioRepositorio.existsByAlias(dto.getAlias()))
            throw new IllegalArgumentException("Alias no disponible");

        if (usuarioRepositorio.existsByEmailUsuario(dto.getEmailUsuario()))
            throw new IllegalArgumentException("El email proporcionado ya existe");

        Usuario usuario = new Usuario();
        usuario.setAlias(dto.getAlias());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setNumeroDocumentoUsuario(dto.getNumeroDocumentoUsuario());
        usuario.setApellidoUsuario(dto.getApellidoUsuario());
        usuario.setEmailUsuario(dto.getEmailUsuario());
        usuario.setHashContrasenaUsuario(passwordEncoder.encode(dto.getHashContrasenaUsuario()));
        usuario.setTelefonoUsuario(dto.getTelefonoUsuario());
        usuario.setRolUsuario(dto.getRolUsuario());

        return usuario;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }


}
