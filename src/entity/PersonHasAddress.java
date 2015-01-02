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
@Table(name = "person_has_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonHasAddress.findAll", query = "SELECT p FROM PersonHasAddress p"),
    @NamedQuery(name = "PersonHasAddress.findByIdpersonHasAddress", query = "SELECT p FROM PersonHasAddress p WHERE p.idpersonHasAddress = :idpersonHasAddress")})
public class PersonHasAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperson_has_address")
    private Integer idpersonHasAddress;
    @JoinColumn(name = "addresses_addressid", referencedColumnName = "addressid")
    @ManyToOne(optional = false)
    private Addresses addressesAddressid;
    @JoinColumn(name = "persons_personid", referencedColumnName = "personid")
    @ManyToOne(optional = false)
    private Persons personsPersonid;

    public PersonHasAddress() {
    }

    public PersonHasAddress(Integer idpersonHasAddress) {
        this.idpersonHasAddress = idpersonHasAddress;
    }

    public Integer getIdpersonHasAddress() {
        return idpersonHasAddress;
    }

    public void setIdpersonHasAddress(Integer idpersonHasAddress) {
        this.idpersonHasAddress = idpersonHasAddress;
    }

    public Addresses getAddressesAddressid() {
        return addressesAddressid;
    }

    public void setAddressesAddressid(Addresses addressesAddressid) {
        this.addressesAddressid = addressesAddressid;
    }

    public Persons getPersonsPersonid() {
        return personsPersonid;
    }

    public void setPersonsPersonid(Persons personsPersonid) {
        this.personsPersonid = personsPersonid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonHasAddress != null ? idpersonHasAddress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonHasAddress)) {
            return false;
        }
        PersonHasAddress other = (PersonHasAddress) object;
        if ((this.idpersonHasAddress == null && other.idpersonHasAddress != null) || (this.idpersonHasAddress != null && !this.idpersonHasAddress.equals(other.idpersonHasAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.PersonHasAddress[ idpersonHasAddress=" + idpersonHasAddress + " ]";
    }
    
}
