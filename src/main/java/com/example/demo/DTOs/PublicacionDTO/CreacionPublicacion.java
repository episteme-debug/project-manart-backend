package com.example.demo.DTOs.PublicacionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreacionPublicacion {
    private String titulo;
    private String contenido;
    private Boolean estado;
    private Long idUsuario;
}