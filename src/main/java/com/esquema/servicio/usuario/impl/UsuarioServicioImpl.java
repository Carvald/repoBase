/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esquema.servicio.usuario.impl;

import com.esquema.modelo.s_user;
import com.esquema.repositorio.usuario.UsuarioRepositorio;
import com.esquema.servicio.usuario.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author roberto.espinoza
 */

@Service
@Transactional
//public class ServicioUsuarioImpl<T> implements UsuarioService<T> {
public class UsuarioServicioImpl implements UsuarioServicio {
//public abstract class ServicioGeneral<T> {

    @Autowired
    private UsuarioRepositorio repositorio;
    
    @Override
    public void borrar(Long deleted) {
        repositorio.delete(deleted);
    }

    @Override
    public List<s_user> buscaTodos() {
        return repositorio.findAll();
    }

    @Override
    public s_user buscaPorId(Long id) {
        return repositorio.findOne(id);
    }

    @Override
    public s_user guardaOactualiza(s_user persisted) {
        return repositorio.save(persisted);
    }

    /**
     *
     * @param usuario
     * @return
     */
    @Override
    public boolean existe(Long usuario) {
        return repositorio.exists(usuario);
    }

    /**
     *
     * @param userlogin
     * @return
     */
    @Override
    public s_user buscaPorUserlogin(String userlogin) {
        return repositorio.findByUserlogin(userlogin);
    }

}
