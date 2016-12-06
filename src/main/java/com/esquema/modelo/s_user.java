/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esquema.modelo;

/**
 *
 * @author roberto.espinoza
 */
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class s_user  implements Serializable {
  
    @Id
    @GeneratedValue
    private Integer user_id;
    
    private String userlogin;
    private String passwd;
    private String lname;
    private String fname;
    private String email;
    private Integer enabled;
    private Integer pwd_policy;
    private Integer force_newpass;
    private String locale;
    private Integer is_ldap;
    private String dn;
    private String direccion_ip;
    //private String id_fideicomiso;

    /**
     * @return the user_id
     */
    
    public s_user() {
        super();
    }

    /**
     * @return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the userlogin
     */
    public String getUserlogin() {
        return userlogin;
    }

    /**
     * @param userlogin the userlogin to set
     */
    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the enabled
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the pwd_policy
     */
    public Integer getPwd_policy() {
        return pwd_policy;
    }

    /**
     * @param pwd_policy the pwd_policy to set
     */
    public void setPwd_policy(Integer pwd_policy) {
        this.pwd_policy = pwd_policy;
    }

    /**
     * @return the force_newpass
     */
    public Integer getForce_newpass() {
        return force_newpass;
    }

    /**
     * @param force_newpass the force_newpass to set
     */
    public void setForce_newpass(Integer force_newpass) {
        this.force_newpass = force_newpass;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the is_ldap
     */
    public Integer getIs_ldap() {
        return (is_ldap == null) ?  0 : is_ldap;
    }

    /**
     * @param is_ldap the is_ldap to set
     */
    public void setIs_ldap(Integer is_ldap) {
        
        this.is_ldap = (is_ldap == null) ?  0 : is_ldap;
    }

    /**
     * @return the dn
     */
    public String getDn() {
        return dn;
    }

    /**
     * @param dn the dn to set
     */
    public void setDn(String dn) {
        this.dn = dn;
    }

    /**
     * @return the direccion_ip
     */
    public String getDireccion_ip() {
        return direccion_ip;
    }

    /**
     * @param direccion_ip the direccion_ip to set
     */
    public void setDireccion_ip(String direccion_ip) {
        this.direccion_ip = direccion_ip;
    }

    /**
     * @return the id_fideicomiso
     */
    //public String getId_fideicomiso() {
    //    return id_fideicomiso;
    //}

    /**
     * @param id_fideicomiso the id_fideicomiso to set
     */
    //public void setId_fideicomiso(String id_fideicomiso) {
    //    this.id_fideicomiso = id_fideicomiso;
    //}
    

    
}