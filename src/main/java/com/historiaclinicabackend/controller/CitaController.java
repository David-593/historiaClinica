
package com.historiaclinicabackend.controller;

import com.historiaclinicabackend.entities.Citas;
import com.historiaclinicabackend.service.itf.ICitaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author andre
 */

@Path("/cita")
@RequestScoped
public class CitaController {
    
    @Inject
    private ICitaService CitaService;
    
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/registerCita")
   public Response RegisterCita(JsonObject cita){
       try{
           Citas citasResponse = CitaService.createCita(cita);
           
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           String fechaStr = sdf.format(citasResponse.getCitaFecha());
            
           DateTimeFormatter sdfHora = DateTimeFormatter.ofPattern("HH:mm");
           String horaStr = citasResponse.getCitaHora().format(sdfHora); 
           
           JsonObject jsonResponse = Json.createObjectBuilder()
                   .add("CedulaPaciente", cita)
                   .add("CedulaMedico", cita)
                   .add("FechaCita", fechaStr)
                   .add("HoraCita", horaStr)
                   .build();
           return Response.status(Response.Status.CREATED).entity(jsonResponse).build();
           
       }catch(Exception e){
           return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
       }
   }
   
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/getCita")
   public Response getAllCitasByCed(JsonObject cita){
       try{
           List<Citas> citasResponse = CitaService.getAllCitasByCed(cita);
           
           
           if(citasResponse == null || citasResponse.isEmpty()) {
                String message = "No se encontraron citas para la cédula dada";
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
           }   
           
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            DateTimeFormatter sdfHora = DateTimeFormatter.ofPattern("HH:mm");

            List<JsonObject> citasJson = citasResponse.stream()
                .map(c -> Json.createObjectBuilder()
                    .add("CedulaPaciente", c.getCitaCedulaPaciente().getPacCedulaUsuario())
                    .add("CedulaMedico", c.getCitaCedulaMedico().getMedCedulaUsuario())
                    .add("FechaCita", sdf.format(c.getCitaFecha()))
                    .add("HoraCita", c.getCitaHora().format(sdfHora))
                    .build()
                )
                .toList();
            
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            citasJson.forEach(arrayBuilder::add);

            JsonObject jsonResponse = Json.createObjectBuilder()
                .add("Citas", arrayBuilder.build())
                .build();

            return Response.ok().entity(jsonResponse).build();
       }catch(Exception e){
           return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
       }
   }
    
}
