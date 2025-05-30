
package com.historiaclinicabackend.dao.itf;

import com.historiaclinicabackend.entities.Citas;
import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Pacientes;

/**
 *
 * @author andre
 */
public interface ICitasEjb {
    
    Citas createCitas(Citas cita) throws Exception;
    
    Pacientes existPacByCed(Pacientes paciente) throws Exception;
    
    Medicos existMedByCed(Medicos medico) throws Exception;
    
    void deleteCitaById(Citas cita) throws Exception;
}
