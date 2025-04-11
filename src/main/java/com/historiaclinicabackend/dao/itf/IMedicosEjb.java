
package com.historiaclinicabackend.dao.itf;

import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Usuarios;

/**
 *
 * @author andre
 */
public interface IMedicosEjb {
    
    Medicos createMedico(Medicos medico) throws Exception;
    
    Medicos existMedByCedula(Medicos medico) throws Exception;
    
    Usuarios existUserByCedula(Usuarios usuario) throws Exception;
    
}
