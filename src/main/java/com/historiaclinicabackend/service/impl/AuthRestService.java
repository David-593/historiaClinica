package com.historiaclinicabackend.service.impl;

import com.historiaclinicabackend.security.itf.IJwtProvider;
import com.historiaclinicabackend.dao.itf.IAuthRestEjb;
import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Usuarios;
import com.historiaclinicabackend.service.itf.IAuthRestService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.persistence.EntityNotFoundException;

/**
 *
 * @author andre
 */

@RequestScoped
@Named(value = "AuthRestService")
public class AuthRestService implements IAuthRestService {
    
    @EJB
    private IAuthRestEjb AuthRestEjb;
    
    @Inject
    private IJwtProvider  jwtProvider;

    @Override
    public Medicos getMedByCedula(JsonObject medicoJson) throws Exception {
        Medicos medico = new Medicos();
        medico.setMedCedulaUsuario(medicoJson.getString("Cedula"));
        return AuthRestEjb.existMedByCedula(medico);
    }

    @Override
    public Usuarios getUserByCedula(JsonObject usuarioJson) throws Exception {
        Usuarios usuario = new Usuarios ();
        usuario.setUsuCedula(usuarioJson.getString("Cedula"));
        return AuthRestEjb.existUserByCedula(usuario);
    }

    @Override
    public String login(JsonObject authJson) throws Exception {
        
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(authJson.getString("Cedula"));
        usuario.setUsuClave(authJson.getString("Clave"));
        
        JsonObject cedulaJson = Json.createObjectBuilder()
                .add("Cedula", authJson.getString("Cedula"))
                .build();
        
        Usuarios user = getUserByCedula(cedulaJson);
        
        if(user != null){ 
            if(!user.getUsuClave().equals(user.getUsuClave())){
                throw new Exception("Contraseña incorrecta");
            }
            String token = jwtProvider.generateToken(user);
            return token;
        }else{
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }
    
}
