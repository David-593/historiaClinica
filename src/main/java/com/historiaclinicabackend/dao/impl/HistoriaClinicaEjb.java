package com.historiaclinicabackend.dao.impl;

import com.historiaclinicabackend.dao.itf.IHistoriaClinicaEjb;
import com.historiaclinicabackend.entities.HistoriaClinica;
import com.historiaclinicabackend.entities.Usuarios;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author andre
 */
@Stateless(name = "EjbHistoriaClinica")
public class HistoriaClinicaEjb implements IHistoriaClinicaEjb {

    @PersistenceContext(name = "historiPU")
    private EntityManager em;

    @Override
    public HistoriaClinica createHistory(HistoriaClinica medicalHistory) throws Exception {
        em.persist(medicalHistory);
        return medicalHistory;
    }

    @Override
    public Usuarios existUserByCedula(Usuarios usuario) throws Exception {
        return em.find(Usuarios.class, usuario.getUsuCedula());
    }

    @Override
    public List<HistoriaClinica> findHistoryByCedulaUsuario(Usuarios usuario) throws Exception {
        TypedQuery<HistoriaClinica> query = em.createQuery(
                "SELECT h FROM HistoriaClinica h WHERE h.histCedulaUsuario.usuCedula = :cedula",
                HistoriaClinica.class
        );
        query.setParameter("cedula", usuario.getUsuCedula());
        return query.getResultList();
    }

    @Override
    public void deleteHistoriaCli(HistoriaClinica medicalHistory) throws Exception {
        if (medicalHistory != null) {
            em.remove(medicalHistory);
        } else {
            throw new Exception("Historia clínica no encontrada para eliminación.");
        }
    }

}
