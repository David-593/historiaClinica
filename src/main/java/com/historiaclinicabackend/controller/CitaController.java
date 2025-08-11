
package com.historiaclinicabackend.controller;

import com.historiaclinicabackend.entities.Citas;
import com.historiaclinicabackend.service.itf.ICitaService;
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
import java.time.format.DateTimeFormatter;

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
    
}
