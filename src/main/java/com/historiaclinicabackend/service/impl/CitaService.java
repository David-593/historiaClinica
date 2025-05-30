
package com.historiaclinicabackend.service.impl;

import com.historiaclinicabackend.dao.itf.ICitasEjb;
import com.historiaclinicabackend.entities.Citas;
import com.historiaclinicabackend.entities.Pacientes;
import com.historiaclinicabackend.service.itf.ICitaService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.JsonObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author andre
 */
@RequestScoped
@Named(value = "CitaService")
public class CitaService implements ICitaService {

    @EJB
    private ICitasEjb citasEjb;
    
    @Override
    public Citas createCita(JsonObject citaJson) throws Exception {
        
        //Transformar de date y hora a string
        String fechaStr = citaJson.getString("FechaCita");
        String horaStr = citaJson.getString("HoraCita");
        
        DateTimeFormatter formatterFecha = DateTimeFormatter.ISO_DATE;
        LocalDate fechaNacimiento = LocalDate.parse(fechaStr, formatterFecha);
        
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horaCita = LocalTime.parse(horaStr, formatterHora);
        
        Date fechaDate = Date.from(fechaNacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        Citas cita = new Citas();
        cita.setCitaFecha(fechaDate);
        cita.setCitaHora(horaCita);
        cita.setCitaEstado(true);
        
        return citasEjb.createCitas(cita);
        
    }

    @Override
    public Pacientes getCitaByCeduPac(JsonObject citaPacJson) throws Exception {
        Pacientes paciente = new Pacientes();
        paciente.setPacCedulaUsuario(citaPacJson.getString("Cedula"));
        
        return citasEjb.existPacByCed(paciente);
    }

    @Override
    public String deleteCitaById(JsonObject citaJson) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
