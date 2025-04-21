
package com.historiaclinicabackend.dao.itf;

import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Usuarios;

/**
 *
 * @author andre
 */
public interface IHistoriaClinicaEjb {
    
    Medicos createHistory(Medicos medico) throws Exception;
    
    Medicos existMedById(Medicos medico) throws Exception;
    
    Usuarios existUserById(Usuarios usuario) throws Exception;
    
}
