package com.example.demo.Servicios.impl;

import com.example.demo.Interfaces.EmailInterface;
import com.example.demo.DTOs.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
public class EmailServicoImpl implements EmailInterface {
    private final JavaMailSender javaMailSender;
    private  final TemplateEngine templateEngine;

    public EmailServicoImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

        @Override
        public void sendMail (EmailDTO emailDTO) throws MessagingException {
            try {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(emailDTO.getDestinatario());
        helper.setSubject(emailDTO.getAsunto());

        Context context = new Context();
        context.setVariable("mensaje", emailDTO.getMensaje());
        context.setVariable("nombre", emailDTO.getNombre());
        context.setVariable("linkRedirecionPagina","http://localhost:3000");
        String contentHTML = templateEngine.process("email", context);

        helper.setText(contentHTML, true);
                FileSystemResource file = new FileSystemResource(new File("C:\\Users\\javier cardenas\\Downloads\\logo.png"));
                helper.addAttachment("Recido pdf",file);
        javaMailSender.send(message);
    }catch (Exception e){
        throw new RuntimeException("Error al envia le correo: "+e.getMessage(),e);
    }
    }
}
