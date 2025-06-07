package com.example.demo.Servicios.impl;

import com.example.demo.Interfaces.EmailInterface;
import com.example.demo.Servicios.DTO.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
        String contentHTML = templateEngine.process("email", context);

        helper.setText(contentHTML, true);
        javaMailSender.send(message);
    }catch (Exception e){
        throw new RuntimeException("Error al envia le correo: "+e.getMessage(),e);
    }
    }
}
