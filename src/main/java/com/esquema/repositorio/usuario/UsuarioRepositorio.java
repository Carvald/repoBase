/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esquema.repositorio.usuario;

import com.esquema.modelo.s_user;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
 
//@NoRepositoryBean
public interface UsuarioRepositorio extends JpaRepository<s_user, Long> {

    s_user findByUserlogin(String userlogin);

}