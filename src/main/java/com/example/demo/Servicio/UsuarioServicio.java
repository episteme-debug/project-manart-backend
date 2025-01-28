package com.example.demo.Servicio;

import com.example.demo.DTO.logInDTO;
import com.example.demo.Entidades.Usuario;
import com.example.demo.Messages.logMessage;
import com.example.demo.Repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //Get All Usuarios
    public List<Usuario> getAllUsuarios(){
        return usuarioRepositorio.findAll();
    }

    //Get a Usuario By Id
    public Optional<Usuario> getUsuarioById(Integer id){
        return usuarioRepositorio.findById(id);
    }

    //Insert a Usuario in db
    public logMessage saveUsuario(Usuario objeto){
        logMessage dataMessage = new logMessage();
        if(verificarExistenciaUsuario(objeto) == false){
            dataMessage.setMensaje("Email no registrado");
            dataMessage.setError(0);
            dataMessage.setUsuario(objeto);
            usuarioRepositorio.save(objeto);
            return dataMessage;
        }
        dataMessage.setMensaje("Dicho email ya se encuentra registrado en nuestra base de datos.");
        dataMessage.setError(1);
        dataMessage.setUsuario(null);
        return dataMessage;
    }

    //Verificación de inexistencia del usuario en la base de datos
    public Boolean verificarExistenciaUsuario(Usuario objeto){
        Boolean existe = false;
        String emailObjeto = objeto.getEmailUsuario();
        List<Usuario> usuarios = getAllUsuarios();

        for(int i = 0; i < usuarios.size(); i++){
            Usuario usuario = usuarios.get(i);
            if(usuario.getEmailUsuario().equals(emailObjeto)){
                existe = true;
            }
        }

        return existe;
    }

    //LogIn Persona
    public logMessage logInPersona(logInDTO dataPersona){
        logMessage dataMessage = new logMessage();
        String emailUsuario = dataPersona.getEmailUsuario();
        String contrasenaUsuario = dataPersona.getContrasenaUsuario();
        Optional<Usuario> personaExiste = usuarioRepositorio.findByEmailUsuario(emailUsuario);

        if(personaExiste.isPresent()){
            Usuario usuario = personaExiste.get();
            if (usuario.getContrasenaUsuario().equals(contrasenaUsuario)){
                //Ingresos correctos
                dataMessage.setMensaje("Inicio de Sesión exitoso!");
                dataMessage.setError(2);
                dataMessage.setUsuario(usuario);
                return dataMessage;
            }else {
                //Password incorrecta
                dataMessage.setMensaje("Contraseña Incorrecta." + " Por favor verifique sus datos.");
                dataMessage.setError(1);
                dataMessage.setUsuario(null);
                return dataMessage;
            }
        }
        //Usuario no encontrado
        dataMessage.setMensaje("Email Incorrecto." + " Por favor verifique sus datos.");
        dataMessage.setError(0);
        dataMessage.setUsuario(null);
        return dataMessage;
    }
}
