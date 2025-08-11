
package com.historiaclinicabackend.controller;

import com.historiaclinicabackend.entities.Usuarios;
import com.historiaclinicabackend.service.itf.IUsuarioService;
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


@Path("/usuario")
@RequestScoped
public class UsuarioController {

    @Inject
    private IUsuarioService usuarioService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerUser(JsonObject usuario) {
        try {
            if (!usuario.containsKey("Cedula") || !usuario.containsKey("Nombres")
                    || !usuario.containsKey("Apellidos") || !usuario.containsKey("Email")
                    || !usuario.containsKey("Nacimiento") || !usuario.containsKey("Sexo")
                    || !usuario.containsKey("Clave") || !usuario.containsKey("Rol")) {
                String message = "Campos obligatorios";
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
            }
            Usuarios usuarioResponse = usuarioService.createUser(usuario);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = sdf.format(usuarioResponse.getUsuNacimiento());

            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("Cedula", usuarioResponse.getUsuCedula())
                    .add("Nombres", usuarioResponse.getUsuNombres())
                    .add("Apellidos", usuarioResponse.getUsuApellidos())
                    .add("Email", usuarioResponse.getUsuEmail())
                    .add("Nacimiento", fechaStr)
                    .add("Sexo", usuarioResponse.getUsuSexo())
                    .add("Clave", usuarioResponse.getUsuClave())
                    .add("Rol", usuarioResponse.getUsuRol())
                    .build();
            return Response.status(Response.Status.CREATED).entity(jsonResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUser")
    public Response getUserByCedula(JsonObject usuario) {
        try {
            if (!usuario.containsKey("Cedula")) {
                String message = "Usuario con esta cedula no existe";
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
            }
            Usuarios usuarioResponse = usuarioService.getUserByCedula(usuario);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = sdf.format(usuarioResponse.getUsuNacimiento());

            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("Cedula", usuarioResponse.getUsuCedula())
                    .add("Nombres", usuarioResponse.getUsuNombres())
                    .add("Apellidos", usuarioResponse.getUsuApellidos())
                    .add("Email", usuarioResponse.getUsuEmail())
                    .add("Nacimiento", fechaStr)
                    .add("Sexo", usuarioResponse.getUsuSexo())
                    .add("Clave", usuarioResponse.getUsuClave())
                    .add("Rol", usuarioResponse.getUsuRol())
                    .build();
            return Response.ok().entity(jsonResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    //Login del super usuario
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(JsonObject usuarioJson) {
        try {
            String message = usuarioService.loginUser(usuarioJson);
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
