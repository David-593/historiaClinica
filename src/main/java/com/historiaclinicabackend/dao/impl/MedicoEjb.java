
package com.historiaclinicabackend.dao.impl;

import com.historiaclinicabackend.dao.itf.IMedicosEjb;
import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Usuarios;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author andre
 */
@Stateless(name = "EjbMedico")
public class MedicoEjb implements IMedicosEjb{

    @PersistenceContext(name="historiPU")
    private EntityManager em;
    
    @Override
    public Medicos createMedico(Medicos medico) throws Exception {
        em.persist(medico);
        return medico;
    }

    @Override
    public Medicos existMedByCedula(Medicos medico) throws Exception {
         return em.find(Medicos.class, medico.getMedCedulaUsuario());
    }

    @Override
    public Usuarios existUserByCedula(Usuarios usuario) throws Exception {
        return em.find(Usuarios.class, usuario.getUsuCedula());
    }


    
}
