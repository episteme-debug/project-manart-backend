package com.example.demo.Servicios;

import com.example.demo.Entidades.PasswordResetToken;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Repositorios.PasswordResetTokenRepositorio;
import com.example.demo.Repositorios.UsuarioRepositorio;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordResetTokenRepositorio tokenRepositorio;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetTokenServicio(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void crearYEnviarToken(String email){
        Usuario usuario = usuarioRepositorio.findByEmailUsuario(email).
                orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUsuario(usuario);
        passwordResetToken.setToken(token);
        passwordResetToken.setExpirarData(LocalDateTime.now().plusHours(1));

        tokenRepositorio.save(passwordResetToken);

        String link = "http://localhost:3000/cambiar-contrasena/" + token;

        Context context = new Context();
        context.setVariable("recuperarLink", link);

        String html = templateEngine.process("cambiar-contraseña", context);

        eviarCorreo(usuario.getEmailUsuario(),"Recuperacion de Contraseña",html);
    }
    private  void eviarCorreo( String destino,String asunto, String contenidohtml){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(destino);
            helper.setSubject(asunto);
            helper.setText(contenidohtml,true);
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  boolean validadTokenYActualizarContrasena(String token ,String nuevaContraseña){
        PasswordResetToken resetToken = tokenRepositorio.findByToken(token).
                orElseThrow(()-> new RuntimeException( "Token no valido"));

        if(resetToken.getExpirarData().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Token expirado");
        }

        Usuario usuario = resetToken.getUsuario();
        usuario.setHashContrasenaUsuario(passwordEncoder.encode(nuevaContraseña));
        usuarioRepositorio.save(usuario);


        tokenRepositorio.delete(resetToken);

        return true;
    }
}
