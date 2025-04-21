
package com.historiaclinicabackend.dao.itf;

import com.historiaclinicabackend.entities.HistoriaClinica;
import com.historiaclinicabackend.entities.Usuarios;
import java.util.List;

/**
 *
 * @author andre
 */
public interface IHistoriaClinicaEjb {
    
    HistoriaClinica createHistory(HistoriaClinica medicalHistory) throws Exception;
    
    List<HistoriaClinica> findHistoryByCedulaUsuario(Usuarios usuario) throws Exception;

    Usuarios existUserByCedula(Usuarios usuario) throws Exception;
    
    void deleteHistoriaCli(HistoriaClinica medicalHistory) throws Exception;
    
}
