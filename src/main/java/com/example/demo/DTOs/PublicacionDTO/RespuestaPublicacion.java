package com.example.demo.DTOs.PublicacionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaPublicacion {
    private Long id;
    private String titulo;
    private String contenido;
    private Boolean estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaUltimaActualizacion;
}