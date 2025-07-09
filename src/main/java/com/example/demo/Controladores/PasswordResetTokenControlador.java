package com.example.demo.Controladores;

import com.example.demo.Repositorios.PasswordResetTokenRepositorio;
import com.example.demo.Servicios.PasswordResetTokenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/api/recuperar")
public class PasswordResetTokenControlador {
    @Autowired
    private PasswordResetTokenServicio passwordResetTokenServicio;

    @PostMapping("/recuperacion-contrasena/{email}")
    public ResponseEntity<String> solicitarRecuperacion(@PathVariable String email){
        System.out.println("Hola");
        passwordResetTokenServicio.crearYEnviarToken(email);
        return ResponseEntity.ok("Correo de recuperacion enviado");
    }

    @PostMapping("/actualizar-contrasena")
    public  ResponseEntity<String> cambiarContrasena(@RequestParam String token,@RequestParam String nuevaContrasena){
        System.out.println("hola");
        System.out.println(token);
        boolean actualizar = passwordResetTokenServicio.validadTokenYActualizarContrasena(token,nuevaContrasena);

        if(actualizar){
            return ResponseEntity.ok("Contraseña actulizada correctemente");
        }else {
            return  ResponseEntity.badRequest().body("No se puede actulizar la contraseña");
        }
    }

}