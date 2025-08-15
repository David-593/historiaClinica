
package com.historiaclinicabackend.service.itf;

import com.historiaclinicabackend.entities.Citas;
import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Pacientes;
import jakarta.json.JsonObject;
import java.util.List;

/**
 *
 * @author andre
 */
public interface ICitaService {
    
    Citas createCita(JsonObject citaJson) throws Exception;
    
    Pacientes getCitaByCeduPac(JsonObject citaPacJson) throws Exception;
    
    Medicos getCitasByCeduMed(JsonObject citaMedJson) throws Exception;
    
    List<Citas> getAllCitasByCed(JsonObject citaJson) throws Exception;
    
    String deleteCitaById(JsonObject citaJson) throws Exception;
    
}
