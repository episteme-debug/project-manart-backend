package com.example.demo.Mensajes;
import com.example.demo.Entidades.Usuario;

public class MensajeLogIn {
    String mensaje;
    int error;
    Usuario usuario;

    public MensajeLogIn() {
    }

    public MensajeLogIn(String mensaje, int error, Usuario usuario) {
        this.mensaje = mensaje;
        this.error = error;
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "logInMessage{" +
                "mensaje='" + mensaje + '\'' +
                ", error=" + error +
                ", usuario=" + usuario +
                '}';
    }
}
