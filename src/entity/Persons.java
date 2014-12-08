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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(catalog = "s06", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p"),
    @NamedQuery(name = "Persons.findByPersonid", query = "SELECT p FROM Persons p WHERE p.personsPK.personid = :personid"),
    @NamedQuery(name = "Persons.findByUsersUsersid", query = "SELECT p FROM Persons p WHERE p.personsPK.usersUsersid = :usersUsersid"),
    @NamedQuery(name = "Persons.findByAddressesAddressid", query = "SELECT p FROM Persons p WHERE p.personsPK.addressesAddressid = :addressesAddressid"),
    @NamedQuery(name = "Persons.findByInsurancesInsuranceid", query = "SELECT p FROM Persons p WHERE p.personsPK.insurancesInsuranceid = :insurancesInsuranceid"),
    @NamedQuery(name = "Persons.findByFirstname", query = "SELECT p FROM Persons p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Persons.findBySurename", query = "SELECT p FROM Persons p WHERE p.surename = :surename"),
    @NamedQuery(name = "Persons.findByGender", query = "SELECT p FROM Persons p WHERE p.gender = :gender"),
    @NamedQuery(name = "Persons.findByBirthnumber", query = "SELECT p FROM Persons p WHERE p.birthnumber = :birthnumber"),
    @NamedQuery(name = "Persons.findByCanceled", query = "SELECT p FROM Persons p WHERE p.canceled = :canceled"),
    @NamedQuery(name = "Persons.findByArchived", query = "SELECT p FROM Persons p WHERE p.archived = :archived")})
public class Persons implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonsPK personsPK;
    @Basic(optional = false)
    private String firstname;
    @Basic(optional = false)
    private String surename;
    @Basic(optional = false)
    private boolean gender;
    @Basic(optional = false)
    private String birthnumber;
    @Basic(optional = false)
    private boolean canceled;
    @Basic(optional = false)
    private boolean archived;
    @JoinTable(name = "persons_has_roles", joinColumns = {
        @JoinColumn(name = "persons_users_usersid", referencedColumnName = "users_usersid"),
        @JoinColumn(name = "persons_personid", referencedColumnName = "personid"),
        @JoinColumn(name = "persons_addresses_addressid", referencedColumnName = "addresses_addressid"),
        @JoinColumn(name = "persons_insurances_insuranceid", referencedColumnName = "insurances_insuranceid")}, inverseJoinColumns = {
        @JoinColumn(name = "roles_rolename", referencedColumnName = "rolename"),
        @JoinColumn(name = "roles_roleid", referencedColumnName = "roleid")})
    @ManyToMany
    private Collection<Roles> rolesCollection;
    @JoinTable(name = "persons_has_telephones", joinColumns = {
        @JoinColumn(name = "persons_users_usersid", referencedColumnName = "users_usersid"),
        @JoinColumn(name = "persons_personid", referencedColumnName = "personid"),
        @JoinColumn(name = "persons_addresses_addressid", referencedColumnName = "addresses_addressid"),
        @JoinColumn(name = "persons_insurances_insuranceid", referencedColumnName = "insurances_insuranceid")}, inverseJoinColumns = {
        @JoinColumn(name = "telephones_telephoneid", referencedColumnName = "telephoneid")})
    @ManyToMany
    private Collection<Telephones> telephonesCollection;
    @JoinColumn(name = "addresses_addressid", referencedColumnName = "addressid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Addresses addresses;
    @JoinColumn(name = "insurances_insuranceid", referencedColumnName = "insuranceid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Insurances insurances;
    @JoinColumn(name = "users_usersid", referencedColumnName = "usersid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persons")
    private Collection<Actions> actionsCollection;

    public Persons() {
    }

    public Persons(PersonsPK personsPK) {
        this.personsPK = personsPK;
    }

    public Persons(PersonsPK personsPK, String firstname, String surename, boolean gender, String birthnumber, boolean canceled, boolean archived) {
        this.personsPK = personsPK;
        this.firstname = firstname;
        this.surename = surename;
        this.gender = gender;
        this.birthnumber = birthnumber;
        this.canceled = canceled;
        this.archived = archived;
    }

    public Persons(int personid, int usersUsersid, int addressesAddressid, int insurancesInsuranceid) {
        this.personsPK = new PersonsPK(personid, usersUsersid, addressesAddressid, insurancesInsuranceid);
    }

    public PersonsPK getPersonsPK() {
        return personsPK;
    }

    public void setPersonsPK(PersonsPK personsPK) {
        this.personsPK = personsPK;
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
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @XmlTransient
    public Collection<Telephones> getTelephonesCollection() {
        return telephonesCollection;
    }

    public void setTelephonesCollection(Collection<Telephones> telephonesCollection) {
        this.telephonesCollection = telephonesCollection;
    }

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public Insurances getInsurances() {
        return insurances;
    }

    public void setInsurances(Insurances insurances) {
        this.insurances = insurances;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @XmlTransient
    public Collection<Actions> getActionsCollection() {
        return actionsCollection;
    }

    public void setActionsCollection(Collection<Actions> actionsCollection) {
        this.actionsCollection = actionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personsPK != null ? personsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persons)) {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.personsPK == null && other.personsPK != null) || (this.personsPK != null && !this.personsPK.equals(other.personsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Persons[ personsPK=" + personsPK + " ]";
    }
    
}
