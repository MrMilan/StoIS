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
@Table(name = "telephones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telephones.findAll", query = "SELECT t FROM Telephones t"),
    @NamedQuery(name = "Telephones.findByTelephoneid", query = "SELECT t FROM Telephones t WHERE t.telephoneid = :telephoneid"),
    @NamedQuery(name = "Telephones.findByTelephonenumber", query = "SELECT t FROM Telephones t WHERE t.telephonenumber = :telephonenumber"),
    @NamedQuery(name = "Telephones.findByCanceled", query = "SELECT t FROM Telephones t WHERE t.canceled = :canceled")})
public class Telephones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "telephoneid")
    private Integer telephoneid;
    @Basic(optional = false)
    @Column(name = "telephonenumber")
    private int telephonenumber;
    @Basic(optional = false)
    @Column(name = "canceled")
    private boolean canceled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telephonesTelephoneid")
    private Collection<PersonsHasTelephones> personsHasTelephonesCollection;

    public Telephones() {
    }

    public Telephones(Integer telephoneid) {
        this.telephoneid = telephoneid;
    }

    public Telephones(Integer telephoneid, Integer telephonenumber, boolean canceled) {
        this.telephoneid = telephoneid;
        this.telephonenumber = telephonenumber;
        this.canceled = canceled;
    }

    public Integer getTelephoneid() {
        return telephoneid;
    }

    public void setTelephoneid(Integer telephoneid) {
        this.telephoneid = telephoneid;
    }

    public int getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(int telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @XmlTransient
    public Collection<PersonsHasTelephones> getPersonsHasTelephonesCollection() {
        return personsHasTelephonesCollection;
    }

    public void setPersonsHasTelephonesCollection(Collection<PersonsHasTelephones> personsHasTelephonesCollection) {
        this.personsHasTelephonesCollection = personsHasTelephonesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (telephoneid != null ? telephoneid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telephones)) {
            return false;
        }
        Telephones other = (Telephones) object;
        if ((this.telephoneid == null && other.telephoneid != null) || (this.telephoneid != null && !this.telephoneid.equals(other.telephoneid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Telephones[ telephoneid=" + telephoneid + " ]";
    }
    
}
