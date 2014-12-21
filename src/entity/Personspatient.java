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
@Table(name = "personspatient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personspatient.findAll", query = "SELECT p FROM Personspatient p"),
    @NamedQuery(name = "Personspatient.findByPersonid", query = "SELECT p FROM Personspatient p WHERE p.personid = :personid"),
    @NamedQuery(name = "Personspatient.findByFirstname", query = "SELECT p FROM Personspatient p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Personspatient.findBySurename", query = "SELECT p FROM Personspatient p WHERE p.surename = :surename")})
public class Personspatient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "personid")
    private Integer personid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "surename")
    private String surename;

    public Personspatient() {
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
    
}
