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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "Diagnosis.findByDiagnosenote", query = "SELECT d FROM Diagnosis d WHERE d.diagnosenote = :diagnosenote"),
    @NamedQuery(name = "Diagnosis.findByCanceled", query = "SELECT d FROM Diagnosis d WHERE d.canceled = :canceled")})
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
    @Basic(optional = false)
    @Column(name = "canceled")
    private boolean canceled;

    public Diagnosis() {
    }

    public Diagnosis(Integer diagnoseid) {
        this.diagnoseid = diagnoseid;
    }

    public Diagnosis(Integer diagnoseid, String diagnosecode, String diagnosenote, boolean canceled) {
        this.diagnoseid = diagnoseid;
        this.diagnosecode = diagnosecode;
        this.diagnosenote = diagnosenote;
        this.canceled = canceled;
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

    public boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
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
        return "entity.Diagnosis[ diagnoseid=" + diagnoseid + " ]";
    }
    
}
