/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Milhouse
 */
@Entity
@Table(name = "personswithusersinroles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personswithusersinroles.findAll", query = "SELECT p FROM Personswithusersinroles p"),
    @NamedQuery(name = "Personswithusersinroles.findByPersonid", query = "SELECT p FROM Personswithusersinroles p WHERE p.personid = :personid"),
    @NamedQuery(name = "Personswithusersinroles.findByFirstname", query = "SELECT p FROM Personswithusersinroles p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Personswithusersinroles.findBySurename", query = "SELECT p FROM Personswithusersinroles p WHERE p.surename = :surename"),
    @NamedQuery(name = "Personswithusersinroles.findByUsername", query = "SELECT p FROM Personswithusersinroles p WHERE p.username = :username"),
    @NamedQuery(name = "Personswithusersinroles.findByUsersid", query = "SELECT p FROM Personswithusersinroles p WHERE p.usersid = :usersid"),
    @NamedQuery(name = "Personswithusersinroles.findByRolename", query = "SELECT p FROM Personswithusersinroles p WHERE p.rolename = :rolename"),
    @NamedQuery(name = "Personswithusersinroles.findByRoleid", query = "SELECT p FROM Personswithusersinroles p WHERE p.roleid = :roleid")})
public class Personswithusersinroles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "personid")
    private Integer personid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "surename")
    private String surename;
    @Column(name = "username")
    private String username;
    @Column(name = "usersid")
    private Integer usersid;
    @Column(name = "rolename")
    private String rolename;
    @Column(name = "roleid")
    private Integer roleid;

    public Personswithusersinroles() {
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUsersid() {
        return usersid;
    }

    public void setUsersid(Integer usersid) {
        this.usersid = usersid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    
}
