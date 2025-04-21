package com.historiaclinicabackend.service.impl;

import com.historiaclinicabackend.dao.itf.IMedicosEjb;
import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.entities.Usuarios;
import com.historiaclinicabackend.service.itf.IMedicoService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.persistence.EntityNotFoundException;

/**
 *
 * @author andre
 */
@RequestScoped
@Named(value = "MedicoService")
public class MedicoService implements IMedicoService {

    @EJB
    private IMedicosEjb MedicosEjb;

    @Override
    public Medicos registerMedico(JsonObject medicoJson) throws Exception {
        Medicos medico = new Medicos();
        medico.setMedCedulaUsuario(medicoJson.getString("Cedula"));
        medico.setMedEspecialidad(medicoJson.getString("Especialidad"));
        medico.setMedTelefono(medicoJson.getString("Telefono"));

        return MedicosEjb.createMedico(medico);
    }

    @Override
    public Medicos getMedByCedula(JsonObject medicoJson) throws Exception {
        Medicos medico = new Medicos();
        medico.setMedCedulaUsuario(medicoJson.getString("Cedula"));

        return MedicosEjb.existMedByCedula(medico);

    }

    @Override
    public Usuarios getUserByCedula(JsonObject usuarioJson) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(usuarioJson.getString("Cedula"));

        return MedicosEjb.existUserByCedula(usuario);
    }

    @Override
    public String loginMed(JsonObject medicoJson) throws Exception {
        Medicos medico = new Medicos();
        medico.setMedCedulaUsuario(medicoJson.getString("Cedula"));

        Usuarios usuario = new Usuarios();
        usuario.setUsuClave(medicoJson.getString("Clave"));

        JsonObject cedulaJson = Json.createObjectBuilder()
                .add("Cedula", medicoJson.getString("Cedula"))
                .build();

        Medicos doctor = getMedByCedula(cedulaJson);
        Usuarios user = getUserByCedula(cedulaJson);

        if (user != null) {
            if (user.getUsuRol().equals("medico")) {
                if (doctor != null) {
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