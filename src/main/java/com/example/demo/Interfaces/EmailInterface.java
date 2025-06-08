package com.example.demo.Interfaces;

import com.example.demo.DTOs.EmailDTO;
import jakarta.mail.MessagingException;

public interface EmailInterface {
    public void  sendMail(EmailDTO emailDTO) throws MessagingException;
}
