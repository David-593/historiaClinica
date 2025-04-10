
package com.historiaclinicabackend.dao.itf;

import com.historiaclinicabackend.entities.Usuarios;


public interface IUsuarioEjb {
    
    Usuarios createUser(Usuarios usuario) throws Exception;
    
    Usuarios getUserByCedula(Usuarios usuario) throws Exception;
}
