
package com.historiaclinicabackend.service.itf;

import com.historiaclinicabackend.entities.Citas;
import jakarta.json.JsonObject;

/**
 *
 * @author andre
 */
public interface ICitaService {
    
    Citas createCita(JsonObject citaJson) throws Exception;
    
    Citas getCitaBy(JsonObject citaJson) throws Exception;
    
    String deleteCitaById(JsonObject citaJson) throws Exception;
    
}
