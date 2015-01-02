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
@Table(name = "diagnosis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnosis.findAll", query = "SELECT d FROM Diagnosis d"),
    @NamedQuery(name = "Diagnosis.findByDiagnoseid", query = "SELECT d FROM Diagnosis d WHERE d.diagnoseid = :diagnoseid"),
    @NamedQuery(name = "Diagnosis.findByDiagnosecode", query = "SELECT d FROM Diagnosis d WHERE d.diagnosecode = :diagnosecode"),
    @NamedQuery(name = "Diagnosis.findByDiagnosenote", query = "SELECT d FROM Diagnosis d WHERE d.diagnosenote = :diagnosenote")})
public class Diagnosis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "diagnoseid")
    private Integer diagnoseid;
    @Basic(optional = false)
    @Column(name = "diagnosecode")
    private String diagnosecode;
    @Basic(optional = false)
    @Column(name = "diagnosenote")
    private String diagnosenote;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosisDiagnoseid")
    private Collection<Reports> reportsCollection;

    public Diagnosis() {
    }

    public Diagnosis(Integer diagnoseid) {
        this.diagnoseid = diagnoseid;
    }

    public Diagnosis(Integer diagnoseid, String diagnosecode, String diagnosenote) {
        this.diagnoseid = diagnoseid;
        this.diagnosecode = diagnosecode;
        this.diagnosenote = diagnosenote;
    }

    public Integer getDiagnoseid() {
        return diagnoseid;
    }

    public void setDiagnoseid(Integer diagnoseid) {
        this.diagnoseid = diagnoseid;
    }

    public String getDiagnosecode() {
        return diagnosecode;
    }

    public void setDiagnosecode(String diagnosecode) {
        this.diagnosecode = diagnosecode;
    }

    public String getDiagnosenote() {
        return diagnosenote;
    }

    public void setDiagnosenote(String diagnosenote) {
        this.diagnosenote = diagnosenote;
    }

    @XmlTransient
    public Collection<Reports> getReportsCollection() {
        return reportsCollection;
    }

    public void setReportsCollection(Collection<Reports> reportsCollection) {
        this.reportsCollection = reportsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diagnoseid != null ? diagnoseid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diagnosis)) {
            return false;
        }
        Diagnosis other = (Diagnosis) object;
        if ((this.diagnoseid == null && other.diagnoseid != null) || (this.diagnoseid != null && !this.diagnoseid.equals(other.diagnoseid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Diagnosis[ diagnoseid=" + diagnoseid + " ]";
    }
    
}
