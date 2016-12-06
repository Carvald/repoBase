/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esquema.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)

public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacionPersonalizada authProvider;
        
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        
            /*
		builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
				.password("admin").roles("ADMIN");
            */
            
        builder.authenticationProvider(authProvider);
          
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
            
                /* Inicio
                 *********************** Manejo de sesión y autenticación **************************************/
                http
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("/esquema/**").fullyAuthenticated()
                .and()
                    .httpBasic();
                    //.formLogin();
                /********************** Manejo de sesión y autenticación ***************************************
                * Fin */
                
                /* Inicio
                 *********************** Hace que el request sea solo por HTTPS **************************************
                http
                    .requiresChannel().antMatchers("/escribe tu ruta aquí/**").requiresSecure();
		http.csrf().disable();
                /********************** Hace que el request sea solo por HTTPS ***************************************
                * Fin */
                
	}

}
