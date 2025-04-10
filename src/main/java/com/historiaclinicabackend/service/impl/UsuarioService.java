
package com.historiaclinicabackend.service.impl;

import com.historiaclinicabackend.dao.itf.IUsuarioEjb;
import com.historiaclinicabackend.entities.Usuarios;
import com.historiaclinicabackend.service.itf.IUsuarioService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.JsonObject;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequestScoped
@Named (value = "UsuarioService")
public class UsuarioService implements IUsuarioService {

    @EJB
    private IUsuarioEjb UsuarioEjb;
    
    @Override
    public Usuarios createUser(JsonObject usuarioJson) throws Exception {
        
        //Pasar la fecha de date a string para usar el JsonObject
        String fechaStr = usuarioJson.getString("Nacimiento");
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDate fechaNacimiento = LocalDate.parse(fechaStr, formatter);
        
        Date fechaDate = Date.from(fechaNacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(usuarioJson.getString("Cedula"));
        usuario.setUsuNombres(usuarioJson.getString("Nombres"));
        usuario.setUsuApellidos(usuarioJson.getString("Apellidos"));
        usuario.setUsuEmail(usuarioJson.getString("Email"));
        usuario.setUsuNacimiento(fechaDate);
        usuario.setUsuSexo(usuarioJson.getString("Sexo"));
        usuario.setUsuClave(usuarioJson.getString("Clave"));
        usuario.setUsuRol(usuarioJson.getString("Rol"));
        usuario.setUsuEstado("Activo");
        
        return UsuarioEjb.createUser(usuario);
    }

    @Override
    public Usuarios getUserByCedula(JsonObject usuarioJson) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(usuarioJson.getString("Cedula"));
        
        return UsuarioEjb.getUserByCedula(usuario);
    }

    @Override
    public String loginUser(JsonObject usuarioJson) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(usuarioJson.getString("Cedula"));
        usuario.setUsuClave(usuarioJson.getString("Clave"));
        
        Usuarios user = getUserByCedula(usuarioJson);
        
        if(user != null){
            if(user.getUsuClave().equals(usuario.getUsuClave())){
                String message = "Succesfull";
                return message;
            }else{
                throw new Exception("Clave incorrecta");
            }
        }else{
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }
    
}
