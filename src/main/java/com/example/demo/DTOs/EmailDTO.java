package com.example.demo.DTOs;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String destinatario;
    private String nombre;
    private String asunto;
    private String mensaje;
    private String linkRedirecionPagina;
}