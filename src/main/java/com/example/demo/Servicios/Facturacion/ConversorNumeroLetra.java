package com.example.demo.Servicios.Facturacion;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConversorNumeroLetra {

    private final String[] unidades = {"", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
    private final String[] especiales = {"diez", "once", "doce", "trece", "catorce", "quince",
            "dieciséis", "diecisiete", "dieciocho", "diecinueve"};
    private final String[] decenas = {"", "", "veinte", "treinta", "cuarenta", "cincuenta",
            "sesenta", "setenta", "ochenta", "noventa"};
    private final String[] centenas = {"", "ciento", "doscientos", "trescientos", "cuatrocientos",
            "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"};

    public String convertir(BigDecimal valor) {
        valor = valor.setScale(2, BigDecimal.ROUND_DOWN); // dos decimales

        BigDecimal parteEntera = valor.setScale(0, BigDecimal.ROUND_DOWN);
        int centavos = valor.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)).intValue();

        String literal = convertirNumero(parteEntera.toBigInteger().toString());
        String centavosTexto = convertirNumero(String.valueOf(centavos));

        return (literal.trim() + " pesos" + " con " + centavosTexto + " centavos").toUpperCase();
    }

    private String convertirNumero(String numero) {
        int n = numero.length();

        if (n > 12) return "Número demasiado grande";

        while (numero.length() < 12)
            numero = "0" + numero;

        int milMillones = Integer.parseInt(numero.substring(0, 3));
        int millones = Integer.parseInt(numero.substring(3, 6));
        int miles = Integer.parseInt(numero.substring(6, 9));
        int cientos = Integer.parseInt(numero.substring(9, 12));

        StringBuilder resultado = new StringBuilder();

        if (milMillones > 0) {
            if (milMillones == 1) resultado.append("mil millones ");
            else resultado.append(convertirGrupo(milMillones)).append(" mil ");
        }

        if (millones > 0) {
            if (millones == 1) resultado.append("un millón ");
            else resultado.append(convertirGrupo(millones)).append(" millones ");
        }

        if (miles > 0) {
            if (miles == 1) resultado.append("mil ");
            else resultado.append(convertirGrupo(miles)).append(" mil ");
        }

        if (cientos > 0) {
            if (cientos == 1 && resultado.length() > 0)
                resultado.append("uno");
            else
                resultado.append(convertirGrupo(cientos));
        }

        if (resultado.toString().trim().equals("")) {
            return "cero";
        }

        return resultado.toString().trim();
    }

    private String convertirGrupo(int numero) {
        if (numero == 0) return "";

        int c = numero / 100;
        int d = (numero % 100) / 10;
        int u = numero % 10;

        StringBuilder resultado = new StringBuilder();

        if (numero == 100) {
            return "cien";
        }

        if (c > 0) {
            resultado.append(centenas[c]).append(" ");
        }

        if (d == 1) {
            resultado.append(especiales[u]);
        } else {
            if (d > 1) {
                resultado.append(decenas[d]);
                if (u > 0) {
                    if (d == 2) {
                        resultado.append("i").append(unidades[u]); // veintiuno, veintidós...
                    } else {
                        resultado.append(" y ").append(unidades[u]);
                    }
                }
            } else if (u > 0) {
                resultado.append(unidades[u]);
            }
        }

        return resultado.toString().trim();
    }
}
