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
    protected boolean estadoUsuario;

    @Column(nullable = false)
    protected String imagenPerfilUsuario;

    @Column(nullable = false)
    protected int tipoUsuario;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    protected CredencialesAcceso credencialesAcceso;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    List<Publicacion> publicaciones;


/*    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Valoracion> valoraciones;*/

    public Usuario(Integer idUsuario, String nombreUsuario, String apellidoUsuario, String telefonoUsuario, boolean estadoUsuario, String imagenPerfilUsuario, int tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.estadoUsuario = estadoUsuario;
        this.imagenPerfilUsuario = imagenPerfilUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {
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

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public CredencialesAcceso getCredencialesAcceso() {
        return credencialesAcceso;
    }

    public void setCredencialesAcceso(CredencialesAcceso credencialesAcceso) {
        this.credencialesAcceso = credencialesAcceso;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", apellidoUsuario='" + apellidoUsuario + '\'' +
                ", telefonoUsuario='" + telefonoUsuario + '\'' +
                ", estadoUsuario=" + estadoUsuario +
                ", imagenPerfilUsuario='" + imagenPerfilUsuario + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", credencialesAcceso=" + credencialesAcceso +
                ", publicaciones=" + publicaciones +
                '}';
    }
}
