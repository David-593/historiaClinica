
package com.historiaclinicabackend.service.itf;

import com.historiaclinicabackend.entities.HistoriaClinica;
import com.historiaclinicabackend.entities.Usuarios;
import jakarta.json.JsonObject;
import java.util.List;

/**
 *
 * @author andre
 */
public interface IHistoriaClinicaService {
    
    HistoriaClinica registerMedico(JsonObject historyJson) throws Exception;
    
    Usuarios getUserByCedula(JsonObject userJson) throws Exception;
    
    List<HistoriaClinica> getMedicalHistoryfromUser(JsonObject historyJson) throws Exception;
    
    String deleteMedicalHistory(JsonObject historyJson) throws Exception;
    
}
