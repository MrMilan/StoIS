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
@Table(name = "persons_has_telephones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonsHasTelephones.findAll", query = "SELECT p FROM PersonsHasTelephones p"),
    @NamedQuery(name = "PersonsHasTelephones.findByIdpht", query = "SELECT p FROM PersonsHasTelephones p WHERE p.idpht = :idpht")})
public class PersonsHasTelephones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpht")
    private Integer idpht;
    @JoinColumn(name = "persons_personid", referencedColumnName = "personid")
    @ManyToOne(optional = false)
    private Persons personsPersonid;
    @JoinColumn(name = "telephones_telephoneid", referencedColumnName = "telephoneid")
    @ManyToOne(optional = false)
    private Telephones telephonesTelephoneid;

    public PersonsHasTelephones() {
    }

    public PersonsHasTelephones(Integer idpht) {
        this.idpht = idpht;
    }

    public Integer getIdpht() {
        return idpht;
    }

    public void setIdpht(Integer idpht) {
        this.idpht = idpht;
    }

    public Persons getPersonsPersonid() {
        return personsPersonid;
    }

    public void setPersonsPersonid(Persons personsPersonid) {
        this.personsPersonid = personsPersonid;
    }

    public Telephones getTelephonesTelephoneid() {
        return telephonesTelephoneid;
    }

    public void setTelephonesTelephoneid(Telephones telephonesTelephoneid) {
        this.telephonesTelephoneid = telephonesTelephoneid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpht != null ? idpht.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonsHasTelephones)) {
            return false;
        }
        PersonsHasTelephones other = (PersonsHasTelephones) object;
        if ((this.idpht == null && other.idpht != null) || (this.idpht != null && !this.idpht.equals(other.idpht))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.PersonsHasTelephones[ idpht=" + idpht + " ]";
    }
    
}
