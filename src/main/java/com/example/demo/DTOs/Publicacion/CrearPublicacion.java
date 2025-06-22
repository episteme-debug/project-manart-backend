package com.example.demo.DTOs.Publicacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearPublicacion {
    private String titulo;
    private String contenido;
    private Boolean estado;
    private Long idUsuario;
}
