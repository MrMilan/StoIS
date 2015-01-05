/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Milhouse
 */
@Entity
@Table(name = "persons_has_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonsHasRoles.findAll", query = "SELECT p FROM PersonsHasRoles p"),
    @NamedQuery(name = "PersonsHasRoles.findByIdphr", query = "SELECT p FROM PersonsHasRoles p WHERE p.idphr = :idphr"),
    @NamedQuery(name = "PersonsHasRoles.findByCanceled", query = "SELECT p FROM PersonsHasRoles p WHERE p.canceled = :canceled")})
public class PersonsHasRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idphr")
    private Integer idphr;
    @Column(name = "canceled")
    private Boolean canceled;
    @JoinColumn(name = "persons_personid", referencedColumnName = "personid")
    @ManyToOne(optional = false)
    private Persons personsPersonid;
    @JoinColumn(name = "roles_roleid", referencedColumnName = "roleid")
    @ManyToOne(optional = false)
    private Roles rolesRoleid;

    public PersonsHasRoles() {
    }

    public PersonsHasRoles(Integer idphr, Persons personsPersonid, Roles rolesRoleid, boolean canceled) {
        this.personsPersonid = personsPersonid;
        this.idphr = idphr;
        this.rolesRoleid = rolesRoleid;
        this.canceled = canceled;
    }

    public Integer getIdphr() {
        return idphr;
    }

    public void setIdphr(Integer idphr) {
        this.idphr = idphr;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Persons getPersonsPersonid() {
        return personsPersonid;
    }

    public void setPersonsPersonid(Persons personsPersonid) {
        this.personsPersonid = personsPersonid;
    }

    public Roles getRolesRoleid() {
        return rolesRoleid;
    }

    public void setRolesRoleid(Roles rolesRoleid) {
        this.rolesRoleid = rolesRoleid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idphr != null ? idphr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonsHasRoles)) {
            return false;
        }
        PersonsHasRoles other = (PersonsHasRoles) object;
        if ((this.idphr == null && other.idphr != null) || (this.idphr != null && !this.idphr.equals(other.idphr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " " + personsPersonid.toString() + " " + rolesRoleid.toString();
    }
    
}
