package com.example.demo.Servicios.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String destinatario;
    private String nombre;
    private String asunto;
    private String mensaje;
}
