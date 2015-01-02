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
@Table(name = "addresses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Addresses.findAll", query = "SELECT a FROM Addresses a"),
    @NamedQuery(name = "Addresses.findByAddressid", query = "SELECT a FROM Addresses a WHERE a.addressid = :addressid"),
    @NamedQuery(name = "Addresses.findByStreet", query = "SELECT a FROM Addresses a WHERE a.street = :street"),
    @NamedQuery(name = "Addresses.findByStreetnumber", query = "SELECT a FROM Addresses a WHERE a.streetnumber = :streetnumber"),
    @NamedQuery(name = "Addresses.findByZipcode", query = "SELECT a FROM Addresses a WHERE a.zipcode = :zipcode"),
    @NamedQuery(name = "Addresses.findByTown", query = "SELECT a FROM Addresses a WHERE a.town = :town"),
    @NamedQuery(name = "Addresses.findByCountry", query = "SELECT a FROM Addresses a WHERE a.country = :country"),
    @NamedQuery(name = "Addresses.findByCanceled", query = "SELECT a FROM Addresses a WHERE a.canceled = :canceled")})
public class Addresses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "addressid")
    private Integer addressid;
    @Basic(optional = false)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @Column(name = "streetnumber")
    private int streetnumber;
    @Basic(optional = false)
    @Column(name = "zipcode")
    private int zipcode;
    @Basic(optional = false)
    @Column(name = "town")
    private String town;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @Column(name = "canceled")
    private boolean canceled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressesAddressid")
    private Collection<PersonHasAddress> personHasAddressCollection;

    public Addresses() {
    }

    public Addresses(Integer addressid) {
        this.addressid = addressid;
    }

    public Addresses(Integer addressid, String street, int streetnumber, int zipcode, String town, String country, boolean canceled) {
        this.addressid = addressid;
        this.street = street;
        this.streetnumber = streetnumber;
        this.zipcode = zipcode;
        this.town = town;
        this.country = country;
        this.canceled = canceled;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(int streetnumber) {
        this.streetnumber = streetnumber;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @XmlTransient
    public Collection<PersonHasAddress> getPersonHasAddressCollection() {
        return personHasAddressCollection;
    }

    public void setPersonHasAddressCollection(Collection<PersonHasAddress> personHasAddressCollection) {
        this.personHasAddressCollection = personHasAddressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressid != null ? addressid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Addresses)) {
            return false;
        }
        Addresses other = (Addresses) object;
        if ((this.addressid == null && other.addressid != null) || (this.addressid != null && !this.addressid.equals(other.addressid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Addresses[ addressid=" + addressid + " ]";
    }
    
}
