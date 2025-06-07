package com.example.demo.Controladores;


import com.example.demo.Servicios.DTO.EmailDTO;
import com.example.demo.Servicios.impl.EmailServicoImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin("http://127.0.0.1:5500/")
public class EmailControlador {
///
    @Autowired
    EmailServicoImpl emailServico;

    @PostMapping("/Registro-Exitoso")
    private ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) throws MessagingException {
        emailServico.sendMail(emailDTO);
        return new ResponseEntity<>("Correo envia exitosamente", HttpStatus.OK);
    }
}
