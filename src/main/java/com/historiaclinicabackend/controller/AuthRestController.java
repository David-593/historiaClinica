package com.historiaclinicabackend.controller;

import com.historiaclinicabackend.service.itf.IAuthRestService;
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

/**
 *
 * @author andre
 */
@Path("/auth")
@RequestScoped
public class AuthRestController {
    
    @Inject
    private IAuthRestService AuthService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response loginAuth(JsonObject authJson){
        try{
            String token = AuthService.login(authJson);
            JsonObject response = Json.createObjectBuilder()
                    .add("Token", token)
                    .build();
            return Response.ok().entity(response).build();
        } catch(Exception e){
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Json.createObjectBuilder()
                            .add("Error", e.getMessage()))
                    .build();
        }
    }
    
}
