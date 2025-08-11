
package com.historiaclinicabackend.service.impl;

import com.historiaclinicabackend.dao.itf.IPacienteEjb;
import com.historiaclinicabackend.entities.Pacientes;
import com.historiaclinicabackend.entities.Usuarios;
import com.historiaclinicabackend.service.itf.IPacienteService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.persistence.EntityNotFoundException;
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
    public Usuarios getUserByCedula(JsonObject usuarioJson) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(usuarioJson.getString("Cedula"));

        return PacienteEjb.existUserByCedula(usuario);
    }

    @Override
    public String loginPaciente(JsonObject pacienteJson) throws Exception {
        Pacientes paciente = new Pacientes();
        paciente.setPacCedulaUsuario(pacienteJson.getString("Cedula"));
        
        Usuarios usuario = new Usuarios();
        usuario.setUsuClave(pacienteJson.getString("Clave"));
        
        JsonObject cedulaJson = Json.createObjectBuilder()
                .add("Cedula", pacienteJson.getString("Cedula"))
                .build();
        
        Pacientes Pac = getPacByCedula(cedulaJson);
        Usuarios user = getUserByCedula(cedulaJson);
        
        if (user != null) {
            if (user.getUsuRol().equals("paciente")) {
                if (Pac != null) {
                    if (user.getUsuClave().equals(usuario.getUsuClave())) {
                        String message = "Succesuful";
                        return message;
                    } else {
                        throw new Exception("Clave incorrecta");
                    }
                } else {
                    String messsage = "Este medico no existe";
                    return messsage;
                }
            } else {
                String message = "Usted no es un medico";
                return message;
            }
        } else {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }
    
}
