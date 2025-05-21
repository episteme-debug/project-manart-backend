package com.example.demo.Seguridad.Controladores;

import com.example.demo.DTOs.AuthDTO.AutenticacionRespuesta;
import com.example.demo.DTOs.AuthDTO.LogIn;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Seguridad.Servicios.AutenticacionServicio;
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

    @PostMapping("public/registro")
    public ResponseEntity<?> registro(@RequestBody Usuario request)
    {
        try {
            return ResponseEntity.ok(autenticacionServicio.registroUsuario(request));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("public/login")
    public ResponseEntity<?> login(@RequestBody LogIn request)
    {
        try {
            AutenticacionRespuesta respuesta = autenticacionServicio.login(request);
            return ResponseEntity.ok(respuesta);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + e.getMessage());
        }
    }
}
