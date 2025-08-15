package com.historiaclinicabackend.service.itf;

import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Usuarios;
import jakarta.json.JsonObject;

/**
 *
 * @author andre
 */
public interface IAuthRestService {
    
    Medicos getMedByCedula(JsonObject medicoJson) throws Exception;
    
    Usuarios getUserByCedula(JsonObject usuarioJson) throws Exception;
    
    String login(JsonObject authJson) throws Exception;
    
}
