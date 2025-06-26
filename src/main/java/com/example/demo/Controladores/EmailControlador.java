package com.example.demo.Controladores;

import com.example.demo.DTOs.EmailDTO;
import com.example.demo.Interfaces.EmailInterface;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin("http://127.0.0.1:5500/")
public class EmailControlador {

    @Autowired
    private EmailInterface emailServico;
    @PostMapping("/Registro-Exitoso")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) {
        try {
            emailServico.sendMail(emailDTO);
            return new ResponseEntity<>("Correo enviado exitosamente", HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Error al enviar el correo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
