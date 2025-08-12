
package com.historiaclinicabackend.dao.impl;

import com.historiaclinicabackend.dao.itf.ICitasEjb;
import com.historiaclinicabackend.entities.Citas;
import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Pacientes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

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

    @Override
    public void deleteCitaById(Citas cita) throws Exception {
        if(cita.getCitaId() != null){
            em.remove(cita);
        }else{
            throw new Exception("Cita no encontrada para eliminación.");
        }
    }

    @Override
    public List<Citas> getAllCitasByCed(Citas cita) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Citas> cq = cb.createQuery(Citas.class);
        Root<Citas> root = cq.from(Citas.class);
        
        cq.select(root).where(cb.equal(root.get("citaCedulaPaciente"), cita.getCitaCedulaPaciente()));
        
        return em.createQuery(cq).getResultList();
    }

}
