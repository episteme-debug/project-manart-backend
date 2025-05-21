package com.example.demo.DTOs.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizacionUsuario {
    private String alias;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String emailUsuario;
    private String telefonoUsuario;
    private Boolean estadoUsuario;

}
