
package com.historiaclinicabackend.service.itf;

import com.historiaclinicabackend.entities.Usuarios;
import jakarta.json.JsonObject;


public interface IUsuarioService {
    
    Usuarios createUser (JsonObject usuarioJson) throws Exception;
    
    Usuarios getUserByCedula(JsonObject usuarioJson) throws Exception;
    
    String loginUser(JsonObject usuarioJson) throws Exception;
}
