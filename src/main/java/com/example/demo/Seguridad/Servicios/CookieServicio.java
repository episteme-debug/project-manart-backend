package com.example.demo.Seguridad.Servicios;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieServicio {

    public void addHttpOnlyCookie(String nombre, String valor, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(nombre, valor);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
        String headerValue = String.format("%s=%s; Max-Age=%d; Path=/; HttpOnly; SameSite=Lax",
                nombre, valor, maxAge);
        response.addHeader("Set-Cookie", headerValue);
    }

    public void deleteCookie(String nombre, HttpServletResponse response) {
        Cookie cookie = new Cookie(nombre, null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Se envia en solicitudes http, para true - https
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        String headerValue = String.format("%s=; Max-Age=0; Path=/; HttpOnly; SameSite=Lax");
        response.addHeader("Set-Cookie", headerValue);
    }

}
