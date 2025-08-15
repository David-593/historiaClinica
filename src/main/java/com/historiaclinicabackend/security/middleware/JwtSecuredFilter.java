package com.historiaclinicabackend.security.middleware;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.historiaclinicabackend.security.itf.IJwtProvider;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *
 * @author andre
 */

@Secured
@Provider
public class JwtSecuredFilter implements ContainerRequestFilter{

    @Inject
    private IJwtProvider jwtProvider;
    
    @Inject 
    private ResourceInfo resourceInfo;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token no presente").build());
            return;
        }
        
        String token = authHeader.substring("Bearer ".length()).trim();
        
        try{
            DecodedJWT jwt = jwtProvider.verifyToken(token);
            String rol = jwt.getClaim("rol").asString();
            
            requestContext.setProperty("rolUsuario", rol);
            
            Secured secured = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
            if(secured == null){
                secured = resourceInfo.getResourceClass().getAnnotation(Secured.class);
            }
            
            if (secured != null && 
                    java.util.Arrays.stream(secured.value()).noneMatch(r -> r.equals(rol))) {

                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                .entity("No tienes permiso").build());
            }
            
        }catch(Exception e){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token inválido o expirado").build());
        }
        
    }
    
}
