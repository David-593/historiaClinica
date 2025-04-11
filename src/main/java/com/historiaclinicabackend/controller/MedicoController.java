package com.historiaclinicabackend.controller;

import com.historiaclinicabackend.entities.Medicos;
import com.historiaclinicabackend.service.itf.IMedicoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author andre
 */
@Path("/medico")
@RequestScoped
public class MedicoController {

    @Inject
    private IMedicoService MedicoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/registerMed")
    public Response registerMedico(JsonObject medico) {
        try {
            if (!medico.containsKey("Cedula")) {
                String message = "Este medico no existe";
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
            }
            Medicos medicoResponse = MedicoService.registerMedico(medico);

            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("Cedula", medicoResponse.getMedCedulaUsuario())
                    .add("Especialidad", medicoResponse.getMedEspecialidad())
                    .add("Telefono", medicoResponse.getMedTelefono())
                    .build();
            return Response.status(Response.Status.CREATED).entity(jsonResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMedico")
    public Response getMedCedulaUser(JsonObject medico) {
        try {
            if (!medico.containsKey("Cedula")) {
                String message = "No existe medico con esta cedula";
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
            }

            Medicos medicoResponse = MedicoService.getMedByCedula(medico);
            if (medicoResponse == null) {
                String message = "Medico no encontrado con la cédula proporcionada";
                return Response.status(Response.Status.NOT_FOUND).entity(message).build();
            }

            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("Cedula", medicoResponse.getMedCedulaUsuario())
                    .add("Especialidad", medicoResponse.getMedEspecialidad())
                    .add("Telefono", medicoResponse.getMedTelefono())
                    .build();
            return Response.ok().entity(jsonResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response loginMed(JsonObject loginJson) {
        try {
            String message = MedicoService.loginMed(loginJson);
            JsonObject response = Json.createObjectBuilder()
                    .add("message", message)
                    .build();
            return Response.ok().entity(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Json.createObjectBuilder()
                            .add("error", e.getMessage()).build()).build();
        }
    }

}
