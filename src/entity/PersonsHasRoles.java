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
@Table(name = "persons_has_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonsHasRoles.findAll", query = "SELECT p FROM PersonsHasRoles p"),
    @NamedQuery(name = "PersonsHasRoles.findByIdphr", query = "SELECT p FROM PersonsHasRoles p WHERE p.idphr = :idphr")})
public class PersonsHasRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idphr")
    private Integer idphr;
    @JoinColumn(name = "persons_personid", referencedColumnName = "personid")
    @ManyToOne(optional = false)
    private Persons personsPersonid;
    @JoinColumn(name = "roles_roleid", referencedColumnName = "roleid")
    @ManyToOne(optional = false)
    private Roles rolesRoleid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personsHasRolesIdphr")
    private Collection<Actions> actionsCollection;

    public PersonsHasRoles() {
    }

    public PersonsHasRoles(Integer idphr) {
        this.idphr = idphr;
    }

    public Integer getIdphr() {
        return idphr;
    }

    public void setIdphr(Integer idphr) {
        this.idphr = idphr;
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
        return "stois.Entity.PersonsHasRoles[ idphr=" + idphr + " ]";
    }
    
}
