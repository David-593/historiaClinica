package com.historiaclinicabackend.dao.impl;

import com.historiaclinicabackend.dao.itf.IAuthRestEjb;
import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Usuarios;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author andre
 */
@Stateless(name = "EjbAuth")
public class AuthRestEjb implements IAuthRestEjb{
    
    @PersistenceContext(name = "historiPU")
    private EntityManager em;     

    @Override
    public Medicos existMedByCedula(Medicos medico) throws Exception {
        return em.find(Medicos.class, medico.getMedCedulaUsuario());
    }

    @Override
    public Usuarios existUserByCedula(Usuarios usuario) throws Exception {
        return em.find(Usuarios.class, usuario.getUsuCedula());        
    }
    
}
