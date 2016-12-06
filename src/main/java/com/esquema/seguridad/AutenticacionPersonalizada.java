/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esquema.seguridad;

import com.esquema.DbContextHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.esquema.modelo.s_user;
import com.esquema.servicio.usuario.UsuarioServicio;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author roberto.espinoza
 */

@Service("userService") 
public class AutenticacionPersonalizada implements AuthenticationProvider {
    /*
    @Autowired
    private HibernateTransactionManager transactionManager;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    */
    
    //@Autowired
    //LdapContextSource contextSource;
    @Autowired
    private Environment env;
    
    @Autowired
    //private UsuarioService<s_user> servicioUsuario;
    private UsuarioServicio servicioUsuario;
    
    @Autowired
    private LdapTemplate ldapTemplate;
    
    //@Autowired
    //private UsuarioService<At04Control> at04ControlService;
    
    @Transactional
    private s_user encuentra(String usuarioBD) {
        //jdbcTemplate.setDataSource(getSessionDataSource());
        
        s_user usuario = null;
        
        try {
            // Hace uso de la conexion SQLServer, debido que es la conexion por defecto
            usuario = servicioUsuario.buscaPorUserlogin(usuarioBD);
            
            //DbContextHolder.setDbType(DbType.Oracle);
            //at04Control = at04ControlService.buscaPorCartera("AGRICOLA");
            /*
            s_user usuario = (s_user) jdbcTemplate.queryForObject(
            "select userlogin, passwd, enabled from s_user where userlogin = ?"
            ,new Object[] { usuarioBD }
            ,new BeanPropertyRowMapper(s_user.class));
            */
            return usuario;
            
        }
        catch (org.springframework.dao.EmptyResultDataAccessException e) {
                throw new BadCredentialsException("Usuario no existe");
        }
        finally {
            DbContextHolder.clearDbType();
        }
        
        /*new RowMapper<s_user>() {
            public s_user mapRow(ResultSet rs, int rowNum) throws SQLException {
                s_user usuario = new s_user();
                usuario.setUserlogin(rs.getString("userlogin"));
                usuario.setPasswd(rs.getString("passwd"));
                return usuario;
            }
        });*/
        
    } 
    
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        
            UsernamePasswordAuthenticationToken auth;
            String usuario = String.valueOf(authentication.getPrincipal());
            //System.out.println("Usuario..: " + usuario);

            String password = null;
            try {
                //password = encr.encr(authentication.getCredentials().toString());
                password = authentication.getCredentials().toString();
                //System.out.println("Passwordss..: " + password);
                
            
            } catch (Exception ex) {
                Logger.getLogger(AutenticacionPersonalizada.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (usuario.isEmpty() && !password.isEmpty()) {
                throw new BadCredentialsException("Usuario no válido");
            }
            
            if (password.isEmpty() && !usuario.isEmpty()) {
                throw new BadCredentialsException("Clave no válida");
            }
            
            if (usuario.isEmpty() && password.isEmpty()) {
                throw new BadCredentialsException("Credenciales no válidas");
            }
            
            //Llamado al método encuentra. Está más arriba.
            s_user usuarioBD = encuentra(usuario);
            
            if (usuarioBD.getEnabled() == 0) {
                throw new BadCredentialsException("El usuario [" + usuario.toUpperCase() + "] se encuentra bloqueado");
            }

            /*
            System.out.println("Clave de HTTPS..: " + clave);
            System.out.println("Resultado del query..: " + usuarioBD);
            System.out.println("Usuario BD..: " + usuarioBD.getUserlogin());
            System.out.println("Password BD..: " + usuarioBD.getPasswd());
            */
            
            if (usuarioBD.getIs_ldap() == 1) {
                try {
                    ldapTemplate.afterPropertiesSet();
                } catch (Exception ex) {
                    Logger.getLogger(AutenticacionPersonalizada.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Perform the authentication.
                Filter filter = new EqualsFilter("sAMAccountName", usuario);

                if (!ldapTemplate.authenticate(env.getProperty("ldap.base"), filter.encode(), password)) {
                    throw new BadCredentialsException("Clave o Usuario inválido");
                }
            } else {
                
                String clave = null;
                try {
                    //create MD5 hash using the string: userlogin:passwd
                    java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                    byte[] b = (usuario + ":" + password).getBytes();
                    //byte[] b = (password).getBytes();
                    byte[] hash = md.digest(b);
                    clave = Base64.encodeToString( hash, true );
                } catch (Exception ex) {
                    Logger.getLogger(AutenticacionPersonalizada.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                if(usuarioBD == null || (! clave.equals(usuarioBD.getPasswd()))) {
                    throw new BadCredentialsException("Clave o Usuario inválido");
                }    
            }
            
            /*
            System.out.println("Usuario BD..: " + usuarioBD.getUserlogin());
            System.out.println("Password BD..: " + usuarioBD.getPasswd());
            */
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

            auth = new UsernamePasswordAuthenticationToken(usuario, password, grantedAuths);
            auth.setDetails(usuarioBD);

            return auth;
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        //System.out.println("Autorizacion..: " + SecurityContextHolder.getContext().getAuthentication());
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    public String encripta(String usuario, String password) {
            String clave = null;
            try {
                //create MD5 hash using the string: userlogin:passwd
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] b = (usuario + ":" + password).getBytes();
                //byte[] b = (password).getBytes();
                byte[] hash = md.digest(b);
                clave = Base64.encodeToString( hash, true );
            } catch (Exception ex) {
                Logger.getLogger(AutenticacionPersonalizada.class.getName()).log(Level.SEVERE, null, ex);
            }
            return clave;
    }
    
}
