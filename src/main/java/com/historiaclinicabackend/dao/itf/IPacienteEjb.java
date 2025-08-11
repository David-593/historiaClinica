
package com.historiaclinicabackend.dao.itf;

import com.historiaclinicabackend.entities.Pacientes;
import com.historiaclinicabackend.entities.Usuarios;

/**
 *
 * @author andre
 */
public interface IPacienteEjb {
    
    Pacientes RegisterPaciente(Pacientes paciente) throws Exception;
    
    Pacientes existPacByCedula(Pacientes paciente) throws Exception;
    
    Usuarios existUserByCedula(Usuarios usuario) throws Exception;
    
}
