package com.example.demo.Enums;

public enum UsuarioEnum {
    ADMIN, COMPRADOR, VENDEDOR, ORGANIZADOR;

    public static boolean existe(String valor) {
        for (UsuarioEnum valorEnum : UsuarioEnum.values()) {
            if (valorEnum.name().equals(valor)) {
                return true;
            }
        }
        return false;
    }

}
