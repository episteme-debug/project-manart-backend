package com.example.demo.Seguridad.Servicios;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieServicio {

    public void addHttpOnlyCookie(String nombre, String valor, int maxAge, HttpServletResponse response) {
        String headerValue = String.format(
                "%s=%s; Max-Age=%d; Path=/; HttpOnly; SameSite=Lax",
                nombre, valor, maxAge
        );
        response.addHeader("Set-Cookie", headerValue);
    }

    public void deleteCookie(String nombre, HttpServletResponse response) {
        String headerValue = String.format(
                "%s=; Max-Age=0; Path=/; HttpOnly; SameSite=Lax",
                nombre
        );
        response.addHeader("Set-Cookie", headerValue);
    }


}
