package com.example.demo.DTOs.UsuarioDTO;

import com.example.demo.Enums.UsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreacionUsuario {
    private String alias;
    private String nombreUsuario;
    private String numeroDocumentoUsuario;
    private String apellidoUsuario;
    private String emailUsuario;
    private String hashContrasenaUsuario;
    private String telefonoUsuario;
    private UsuarioEnum rolUsuario;
}