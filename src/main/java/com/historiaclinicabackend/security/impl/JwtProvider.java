
package com.historiaclinicabackend.security.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.historiaclinicabackend.security.itf.IJwtProvider;
import com.historiaclinicabackend.entities.Usuarios;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.Date;

/**
 *
 * @author andre
 */

@Stateless
@Named("jwtProvider")
public class JwtProvider implements IJwtProvider{
    
    private static final String SECRET_KEY = "sniper593";
    
    Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    @Override
    public String generateToken(Usuarios usuario) throws Exception {
        return JWT.create()
                .withSubject(usuario.getUsuCedula())
                .withClaim("rol", usuario.getUsuRol().toLowerCase())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .sign(algorithm);
    }

    @Override
    public DecodedJWT verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
    
}
