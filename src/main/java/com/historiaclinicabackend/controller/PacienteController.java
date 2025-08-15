package com.historiaclinicabackend.controller;

import com.historiaclinicabackend.entities.Pacientes;
import com.historiaclinicabackend.security.middleware.Secured;
import com.historiaclinicabackend.service.itf.IPacienteService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.text.SimpleDateFormat;

/**
 *
 * @author andre
 */
@Path("/paciente")
@RequestScoped
public class PacienteController {

    @Inject
    private IPacienteService PacienteService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/registerPac")
    @Secured({"paciente", "admin"})
    public Response registerPaciente(JsonObject paciente){
        try {
            if(!paciente.containsKey("Cedula")){
                String message = "Cedula no valida";
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
            }
            Pacientes pacienteResponse = PacienteService.registerPaciente(paciente);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = sdf.format(pacienteResponse.getPacFechaNacimiento());
            
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("Cedula", pacienteResponse.getPacCedulaUsuario())
                    .add("TipoSangre", pacienteResponse.getPacTipoSangre())
                    .add("Residencia", pacienteResponse.getPacDireccion())
                    .add("Telefono", pacienteResponse.getPacTelefono())
                    .add("Nacimiento", fechaStr)
                    .add("Sexo", pacienteResponse.getPacSexo())
                    .build();
            return Response.status(Response.Status.CREATED).entity(jsonResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPaciente")
    @Secured({"admin"})
    public Response getPacCedulaUser(JsonObject paciente) {
        try {
            if (!paciente.containsKey("Cedula")) {
                String message = "No hay un paciente con esta cedula";
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
            }

            Pacientes pacienteResponse = PacienteService.getPacByCedula(paciente);

            if (pacienteResponse == null) {
                String message = "Paciente no encontrado con la cédula proporcionada";
                return Response.status(Response.Status.NOT_FOUND).entity(message).build();
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = sdf.format(pacienteResponse.getPacFechaNacimiento());

            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("Cedula", pacienteResponse.getPacCedulaUsuario())
                    .add("TipoSangre", pacienteResponse.getPacTipoSangre())
                    .add("Residencia", pacienteResponse.getPacDireccion())
                    .add("Telefono", pacienteResponse.getPacTelefono())
                    .add("Nacimiento", fechaStr)
                    .add("Sexo", pacienteResponse.getPacSexo())
                    .build();
            return Response.ok().entity(jsonResponse).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
    
}
