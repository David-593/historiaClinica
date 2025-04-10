
package com.historiaclinicabackend.service.itf;

import com.historiaclinicabackend.entities.Pacientes;
import jakarta.json.JsonObject;

/**
 *
 * @author andre
 */
public interface IPacienteService {
    
    Pacientes registerPaciente (JsonObject pacienteJson) throws Exception;
    
    Pacientes getPacByCedula (JsonObject pacienteJson) throws Exception;
    
    String loginPaciente (JsonObject pacienteJson) throws Exception;
    
}
