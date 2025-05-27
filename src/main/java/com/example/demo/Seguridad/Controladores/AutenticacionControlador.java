package com.example.demo.Seguridad.Controladores;

import com.example.demo.DTOs.ApiMensaje;
import com.example.demo.DTOs.AuthDTO.AutenticacionRespuesta;
import com.example.demo.DTOs.AuthDTO.LogIn;
import com.example.demo.DTOs.UsuarioDTO.CreacionUsuario;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Seguridad.Servicios.AutenticacionServicio;
import com.example.demo.Servicios.UsuarioServicio;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin("http://127.0.0.1:3000/")
@RequestMapping("/api/autenticacion/")
@RestController
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;
    private final UsuarioServicio usuarioServicio;

    @PostMapping("public/registro")
    public ResponseEntity<?> registro(@RequestBody CreacionUsuario request, HttpServletResponse response)
    {
        try {
            String rol = autenticacionServicio.registroUsuario(request, response);
            return ResponseEntity.ok(new ApiMensaje(rol));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiMensaje(e.getMessage()));
        }
    }

    @PostMapping("public/login")
    public ResponseEntity<ApiMensaje> login(@RequestBody LogIn request, HttpServletResponse response)
    {
        try {
            String rol = autenticacionServicio.login(request, response);
            return ResponseEntity.ok(new ApiMensaje(rol));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiMensaje(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiMensaje("Error inesperado: " + e.getMessage()));
        }
    }

    @GetMapping("public/detalleusuario")
    public ResponseEntity<Usuario> obtenerusuarioAutenticado() {
        Usuario usuario = usuarioServicio.obtenerDetalleUsuario();
        return ResponseEntity.ok(usuario);
    }
}
