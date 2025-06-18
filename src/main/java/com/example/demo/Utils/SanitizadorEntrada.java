package com.example.demo.Utils;

public class SanitizadorEntrada {

    public static String sanitizar(String entrada) {
        if (entrada == null) return null;

        entrada = entrada.replaceAll("[<>%{}$]", "");

        entrada = entrada.trim();


        return entrada;
    }
}
