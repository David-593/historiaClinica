
package com.historiaclinicabackend.service.impl;

import com.historiaclinicabackend.dao.itf.IPacienteEjb;
import com.historiaclinicabackend.entities.Pacientes;
import com.historiaclinicabackend.service.itf.IPacienteService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.JsonObject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author andre
 */
@RequestScoped
@Named(value="PacienteService")
public class PacienteService implements IPacienteService{
    
    @EJB
    private IPacienteEjb PacienteEjb;

    @Override
    public Pacientes registerPaciente(JsonObject pacienteJson) throws Exception {
        
        //Transformar de date a string
        String fechaStr = pacienteJson.getString("Nacimiento");
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDate fechaNacimiento = LocalDate.parse(fechaStr, formatter);
        
        Date fechaDate = Date.from(fechaNacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        Pacientes paciente = new Pacientes();
        paciente.setPacCedulaUsuario(pacienteJson.getString("Cedula"));
        paciente.setPacTipoSangre(pacienteJson.getString("TipoSangre"));
        paciente.setPacDireccion(pacienteJson.getString("Residencia"));
        paciente.setPacTelefono(pacienteJson.getString("Telefono"));
        paciente.setPacFechaRegistro(new Date());
        paciente.setPacFechaNacimiento(fechaDate);
        paciente.setPacSexo(pacienteJson.getString("Sexo"));
        
        return PacienteEjb.RegisterPaciente(paciente);
    }

    @Override
    public Pacientes getPacByCedula(JsonObject pacienteJson) throws Exception {
        Pacientes paciente = new Pacientes();
        paciente.setPacCedulaUsuario(pacienteJson.getString("Cedula"));
        
        return PacienteEjb.existPacByCedula(paciente);
    }

    @Override
    public String loginPaciente(JsonObject pacienteJson) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
