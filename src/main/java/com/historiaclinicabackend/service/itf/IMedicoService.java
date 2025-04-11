
package com.historiaclinicabackend.service.itf;

import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Usuarios;
import jakarta.json.JsonObject;

/**
 *
 * @author andre
 */
public interface IMedicoService {
    
    Medicos registerMedico(JsonObject medicoJson) throws Exception;
    
    Medicos getMedByCedula(JsonObject medicoJson) throws Exception;
    
    String loginMed(JsonObject medicoJson) throws Exception;
    
    Usuarios getUserByCedula(JsonObject usuarioJson) throws Exception;
    
}
