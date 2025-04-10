
package com.historiaclinicabackend.dao.impl;

import com.historiaclinicabackend.dao.itf.IUsuarioEjb;
import com.historiaclinicabackend.entities.Usuarios;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless(name = "EjbUsuarios")
public class UsuarioEjb implements IUsuarioEjb{

    @PersistenceContext(name="historiPU")
    private EntityManager em;

    @Override
    public Usuarios createUser(Usuarios usuario) throws Exception {
        em.persist(usuario);
        return usuario;
    }

    @Override
    public Usuarios getUserByCedula(Usuarios usuario) throws Exception {
        return em.find(Usuarios.class, usuario.getUsuCedula());
    }
    
}
