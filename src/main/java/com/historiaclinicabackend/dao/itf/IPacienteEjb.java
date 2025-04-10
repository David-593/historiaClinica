
package com.historiaclinicabackend.dao.itf;

import com.historiaclinicabackend.entities.Pacientes;

/**
 *
 * @author andre
 */
public interface IPacienteEjb {
    
    Pacientes RegisterPaciente(Pacientes paciente) throws Exception;
    
    Pacientes existPacByCedula(Pacientes paciente) throws Exception;
    
}
