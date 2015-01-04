/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Milhouse
 */
@Entity
@Table(name = "persons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p"),
    @NamedQuery(name = "Persons.findByPersonid", query = "SELECT p FROM Persons p WHERE p.personid = :personid"),
    @NamedQuery(name = "Persons.findByFirstname", query = "SELECT p FROM Persons p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Persons.findBySurename", query = "SELECT p FROM Persons p WHERE p.surename = :surename"),
    @NamedQuery(name = "Persons.findByGender", query = "SELECT p FROM Persons p WHERE p.gender = :gender"),
    @NamedQuery(name = "Persons.findByBirthnumber", query = "SELECT p FROM Persons p WHERE p.birthnumber = :birthnumber"),
    @NamedQuery(name = "Persons.findByCanceled", query = "SELECT p FROM Persons p WHERE p.canceled = :canceled"),
    @NamedQuery(name = "Persons.findByArchived", query = "SELECT p FROM Persons p WHERE p.archived = :archived")})
public class Persons implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personid")
    private Integer personid;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "surename")
    private String surename;
    @Basic(optional = false)
    @Column(name = "gender")
    private boolean gender;
    @Basic(optional = false)
    @Column(name = "birthnumber")
    private String birthnumber;
    @Basic(optional = false)
    @Column(name = "canceled")
    private boolean canceled;
    @Basic(optional = false)
    @Column(name = "archived")
    private boolean archived;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personsPersonid")
    private Collection<PersonHasAddress> personHasAddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personsPersonid")
    private Collection<PersonsHasRoles> personsHasRolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personsPersonid")
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personsPersonid")
    private Collection<PersonsHasTelephones> personsHasTelephonesCollection;
    @JoinColumn(name = "insurances_insuranceid", referencedColumnName = "insuranceid")
    @ManyToOne(optional = false)
    private Insurances insurancesInsuranceid;

    public Persons() {
    }

    public Persons(Integer personid) {
        this.personid = personid;
    }

    public Persons(Integer personid, String firstname, String surename, boolean gender, String birthnumber, boolean canceled, boolean archived, Insurances insurancesInsuranceid) {
        this.personid = personid;
        this.insurancesInsuranceid = insurancesInsuranceid;
        this.firstname = firstname;
        this.surename = surename;
        this.gender = gender;
        this.birthnumber = birthnumber;
        this.canceled = canceled;
        this.archived = archived;
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

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthnumber() {
        return birthnumber;
    }

    public void setBirthnumber(String birthnumber) {
        this.birthnumber = birthnumber;
    }

    public boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @XmlTransient
    public Collection<PersonHasAddress> getPersonHasAddressCollection() {
        return personHasAddressCollection;
    }

    public void setPersonHasAddressCollection(Collection<PersonHasAddress> personHasAddressCollection) {
        this.personHasAddressCollection = personHasAddressCollection;
    }

    @XmlTransient
    public Collection<PersonsHasRoles> getPersonsHasRolesCollection() {
        return personsHasRolesCollection;
    }

    public void setPersonsHasRolesCollection(Collection<PersonsHasRoles> personsHasRolesCollection) {
        this.personsHasRolesCollection = personsHasRolesCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<PersonsHasTelephones> getPersonsHasTelephonesCollection() {
        return personsHasTelephonesCollection;
    }

    public void setPersonsHasTelephonesCollection(Collection<PersonsHasTelephones> personsHasTelephonesCollection) {
        this.personsHasTelephonesCollection = personsHasTelephonesCollection;
    }

    public Insurances getInsurancesInsuranceid() {
        return insurancesInsuranceid;
    }

    public void setInsurancesInsuranceid(Insurances insurancesInsuranceid) {
        this.insurancesInsuranceid = insurancesInsuranceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personid != null ? personid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persons)) {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.personid == null && other.personid != null) || (this.personid != null && !this.personid.equals(other.personid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstname + ", " + surename + ", " + birthnumber;
    }
    
}
