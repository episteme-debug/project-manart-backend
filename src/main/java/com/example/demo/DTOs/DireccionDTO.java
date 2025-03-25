package com.example.demo.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {

    private Integer idDireccion;
    private String tipoVia;
    private String numeroViaPrincipal;
    private String letraViaPrincipal;
    private Boolean bisViaPrincipal;
    private String numeroViaSecundaria;
    private String letraViaSecundaria;
    private Boolean bisViaSecundaria;
    private String numeroPredio;
    private String complemento;
    private String barrio;
    private String ciudad;
    private String departamento;
    private Boolean esPredeterminada;

}
