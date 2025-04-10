
package com.historiaclinicabackend.dao.impl;

import com.historiaclinicabackend.dao.itf.IPacienteEjb;
import com.historiaclinicabackend.entities.Pacientes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author andre
 */
@Stateless(name="EjbPacientes")
public class PacienteEjb implements IPacienteEjb{
    
    @PersistenceContext(name="historiPU")
    private EntityManager em;
    
    @Override
    public Pacientes RegisterPaciente(Pacientes paciente) throws Exception {
        em.persist(paciente);
        return paciente;
    }

    @Override
    public Pacientes existPacByCedula(Pacientes paciente) throws Exception {
        return em.find(Pacientes.class, paciente.getPacCedulaUsuario());
    }

    
}
