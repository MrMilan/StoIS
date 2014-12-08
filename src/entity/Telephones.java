/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Telephones.findAll", query = "SELECT t FROM Telephones t"),
    @NamedQuery(name = "Telephones.findByTelephoneid", query = "SELECT t FROM Telephones t WHERE t.telephoneid = :telephoneid"),
    @NamedQuery(name = "Telephones.findByTelephonenumber", query = "SELECT t FROM Telephones t WHERE t.telephonenumber = :telephonenumber"),
    @NamedQuery(name = "Telephones.findByCanceled", query = "SELECT t FROM Telephones t WHERE t.canceled = :canceled")})
public class Telephones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer telephoneid;
    @Basic(optional = false)
    private int telephonenumber;
    @Basic(optional = false)
    private boolean canceled;
    @ManyToMany(mappedBy = "telephonesCollection")
    private Collection<Persons> personsCollection;

    public Telephones() {
    }

    public Telephones(Integer telephoneid) {
        this.telephoneid = telephoneid;
    }

    public Telephones(Integer telephoneid, int telephonenumber, boolean canceled) {
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
    public Collection<Persons> getPersonsCollection() {
        return personsCollection;
    }

    public void setPersonsCollection(Collection<Persons> personsCollection) {
        this.personsCollection = personsCollection;
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
        return "entity.Telephones[ telephoneid=" + telephoneid + " ]";
    }
    
}
