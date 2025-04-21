package com.historiaclinicabackend.service.impl;

import com.historiaclinicabackend.dao.itf.IHistoriaClinicaEjb;
import com.historiaclinicabackend.entities.HistoriaClinica;
import com.historiaclinicabackend.entities.Usuarios;
import com.historiaclinicabackend.service.itf.IHistoriaClinicaService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.JsonObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andre
 */
@RequestScoped
@Named(value = "HistoriaMedicoService")
public class HistoriaClinicaService implements IHistoriaClinicaService {

    @EJB
    private IHistoriaClinicaEjb historiaClinicaEjb;

    @Override
    public HistoriaClinica registerMedico(JsonObject historyJson) throws Exception {

        //Instanciamos usuario para obtener la Cedula
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(historyJson.getString("Cedula"));

        HistoriaClinica medicalHistory = new HistoriaClinica();
        //setHistCedulaUsuario espera un objeto completo y solo asignamos su cedula
        medicalHistory.setHistCedulaUsuario(usuario);
        medicalHistory.setHistFecha(new Date());
        medicalHistory.setHistDiagnostico(historyJson.getString("Diagnostico"));
        medicalHistory.setHistTratamiento(historyJson.getString("Tratamiento"));
        medicalHistory.setHistReceta(historyJson.getString("Receta"));
        medicalHistory.setHistObservaciones(historyJson.getString("Observaciones"));

        return historiaClinicaEjb.createHistory(medicalHistory);
    }

    @Override
    public Usuarios getUserByCedula(JsonObject userJson) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(userJson.getString("Cedula"));

        return usuario;
    }

    @Override
    public List<HistoriaClinica> getMedicalHistoryfromUser(JsonObject historyJson) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(historyJson.getString("Cedula"));

        HistoriaClinica historiaClinica = new HistoriaClinica();
        historiaClinica.setHistCedulaUsuario(usuario);

        return historiaClinicaEjb.findHistoryByCedulaUsuario(usuario);
    }

    @Override
    public String deleteMedicalHistory(JsonObject historyJson) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(historyJson.getString("Cedula"));

        HistoriaClinica historiaClinica = new HistoriaClinica();
        historiaClinica.setHistCedulaUsuario(usuario);
        historiaClinicaEjb.findHistoryByCedulaUsuario(usuario);

        if (historiaClinica != null) {
            historiaClinicaEjb.deleteHistoriaCli(historiaClinica);
        }
        return "Historia ELiminada correctamente";
    }

}
