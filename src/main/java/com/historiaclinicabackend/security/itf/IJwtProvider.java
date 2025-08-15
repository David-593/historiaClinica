package com.historiaclinicabackend.security.itf;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.historiaclinicabackend.entities.Usuarios;

/**
 *
 * @author andre
 */
public interface IJwtProvider {
    
    String generateToken(Usuarios usuario) throws Exception;

    DecodedJWT verifyToken(String token) throws Exception;
}
