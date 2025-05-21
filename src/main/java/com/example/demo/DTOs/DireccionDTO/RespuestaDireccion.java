package com.example.demo.DTOs.DireccionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDireccion {
    private Long idDireccion;
    private Long idUsuario;
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
    private LocalDate fechaCreacion;
}
