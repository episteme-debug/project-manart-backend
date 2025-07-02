package com.example.demo.DTOs.Publicacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearPublicacionDTO {
    private String titulo;
    private String contenido;
}
