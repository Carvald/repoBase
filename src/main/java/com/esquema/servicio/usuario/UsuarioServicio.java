/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esquema.servicio.usuario;

import com.esquema.modelo.s_user;
import java.util.List;

/**
 *
 * @author roberto.espinoza
 */
public interface UsuarioServicio {
    
    public void borrar(Long deleted);
        
    public List<s_user> buscaTodos();
    
    public s_user buscaPorId(Long id);
    
    public s_user guardaOactualiza(s_user persisted);
    
    public boolean existe(Long usuario);
    
    public s_user buscaPorUserlogin(String userlogin);
    
}
