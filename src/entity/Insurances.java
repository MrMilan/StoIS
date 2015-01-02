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
@Table(name = "insurances")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insurances.findAll", query = "SELECT i FROM Insurances i"),
    @NamedQuery(name = "Insurances.findByInsuranceid", query = "SELECT i FROM Insurances i WHERE i.insuranceid = :insuranceid"),
    @NamedQuery(name = "Insurances.findByInsurancename", query = "SELECT i FROM Insurances i WHERE i.insurancename = :insurancename"),
    @NamedQuery(name = "Insurances.findByInsurancecode", query = "SELECT i FROM Insurances i WHERE i.insurancecode = :insurancecode"),
    @NamedQuery(name = "Insurances.findByCanceled", query = "SELECT i FROM Insurances i WHERE i.canceled = :canceled")})
public class Insurances implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "insuranceid")
    private Integer insuranceid;
    @Basic(optional = false)
    @Column(name = "insurancename")
    private String insurancename;
    @Basic(optional = false)
    @Column(name = "insurancecode")
    private String insurancecode;
    @Column(name = "canceled")
    private Boolean canceled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insurancesInsuranceid")
    private Collection<Persons> personsCollection;

    public Insurances() {
    }

    public Insurances(Integer insuranceid) {
        this.insuranceid = insuranceid;
    }

    public Insurances(Integer insuranceid, String insurancename, String insurancecode) {
        this.insuranceid = insuranceid;
        this.insurancename = insurancename;
        this.insurancecode = insurancecode;
    }

    public Integer getInsuranceid() {
        return insuranceid;
    }

    public void setInsuranceid(Integer insuranceid) {
        this.insuranceid = insuranceid;
    }

    public String getInsurancename() {
        return insurancename;
    }

    public void setInsurancename(String insurancename) {
        this.insurancename = insurancename;
    }

    public String getInsurancecode() {
        return insurancecode;
    }

    public void setInsurancecode(String insurancecode) {
        this.insurancecode = insurancecode;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
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
        hash += (insuranceid != null ? insuranceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insurances)) {
            return false;
        }
        Insurances other = (Insurances) object;
        if ((this.insuranceid == null && other.insuranceid != null) || (this.insuranceid != null && !this.insuranceid.equals(other.insuranceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Insurances[ insuranceid=" + insuranceid + " ]";
    }
    
}
