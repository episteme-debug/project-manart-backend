package com.example.demo.Entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idUsuario;

    @Column(nullable = false, length = 100)
    protected String nombreUsuario;

    @Column(nullable = false, length = 100)
    protected String apellidoUsuario;

    @Column(nullable = false, length = 20)
    protected String telefonoUsuario;

    @Column(nullable = false)
    protected boolean estadoUsuario = true;

    @Column(nullable = false)
    protected String imagenPerfilUsuario = "avatarGenerico.jpg";

    @Column(nullable = false)
    protected int tipoUsuario;

    @Column(nullable = false, length = 200)
    private String emailUsuario;

    @Column(nullable = false, length = 250)
    private String contrasenaUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Publicacion> publicaciones;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Direccion> direcciones;


/*    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Valoracion> valoraciones;*/

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String apellidoUsuario, String telefonoUsuario, boolean estadoUsuario, String imagenPerfilUsuario, int tipoUsuario, String emailUsuario, String contrasenaUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.estadoUsuario = estadoUsuario;
        this.imagenPerfilUsuario = imagenPerfilUsuario;
        this.tipoUsuario = tipoUsuario;
        this.emailUsuario = emailUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String apellidoUsuario, String telefonoUsuario, boolean estadoUsuario, String imagenPerfilUsuario, int tipoUsuario, String emailUsuario, String contrasenaUsuario, List<Publicacion> publicaciones, List<Direccion> direcciones) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.estadoUsuario = estadoUsuario;
        this.imagenPerfilUsuario = imagenPerfilUsuario;
        this.tipoUsuario = tipoUsuario;
        this.emailUsuario = emailUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.publicaciones = publicaciones;
        this.direcciones = direcciones;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public boolean isEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getImagenPerfilUsuario() {
        return imagenPerfilUsuario;
    }

    public void setImagenPerfilUsuario(String imagenPerfilUsuario) {
        this.imagenPerfilUsuario = imagenPerfilUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}
