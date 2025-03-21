package com.example.demo.DTOs;

public class ContraseñaUsuarioDTO {

    Long idUsuario;
    String contraseñaAntigua;
    String contraseñaNueva;

    public ContraseñaUsuarioDTO() {
    }

    public ContraseñaUsuarioDTO(Long idUsuario, String contraseñaAntigua, String contraseñaNueva) {
        this.idUsuario = idUsuario;
        this.contraseñaAntigua = contraseñaAntigua;
        this.contraseñaNueva = contraseñaNueva;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContraseñaAntigua() {
        return contraseñaAntigua;
    }

    public void setContraseñaAntigua(String contraseñaAntigua) {
        this.contraseñaAntigua = contraseñaAntigua;
    }

    public String getContraseñaNueva() {
        return contraseñaNueva;
    }

    public void setContraseñaNueva(String contraseñaNueva) {
        this.contraseñaNueva = contraseñaNueva;
    }
}
