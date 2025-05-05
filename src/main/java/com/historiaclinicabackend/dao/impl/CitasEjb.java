
package com.historiaclinicabackend.dao.impl;

import com.historiaclinicabackend.dao.itf.ICitasEjb;
import com.historiaclinicabackend.entities.Citas;
import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Pacientes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author andre
 */
@Stateless(name = "EjbCita")
public class CitasEjb implements ICitasEjb {
    
    @PersistenceContext(name = "historiPU")
    private EntityManager em;

    @Override
    public Citas createCitas(Citas cita) throws Exception {
        em.persist(cita);
        return cita;
    }

    @Override
    public Pacientes existPacByCed(Pacientes paciente) throws Exception {
        return em.find(Pacientes.class, paciente.getPacCedulaUsuario());
    }

    @Override
    public Medicos existMedByCed(Medicos medico) throws Exception {
        return em.find(Medicos.class, medico.getMedCedulaUsuario());
    }
    
}
