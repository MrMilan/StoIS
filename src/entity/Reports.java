/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Milhouse
 */
@Entity
@Table(name = "reports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reports.findAll", query = "SELECT r FROM Reports r"),
    @NamedQuery(name = "Reports.findByReportid", query = "SELECT r FROM Reports r WHERE r.reportid = :reportid"),
    @NamedQuery(name = "Reports.findByDate", query = "SELECT r FROM Reports r WHERE r.date = :date"),
    @NamedQuery(name = "Reports.findByNote", query = "SELECT r FROM Reports r WHERE r.note = :note")})
public class Reports implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reportid")
    private Integer reportid;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "diagnosis_diagnoseid", referencedColumnName = "diagnoseid")
    @ManyToOne(optional = false)
    private Diagnosis diagnosisDiagnoseid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportsReportid")
    private Collection<Actions> actionsCollection;

    public Reports() {
    }

    public Reports(Integer reportid) {
        this.reportid = reportid;
    }

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Diagnosis getDiagnosisDiagnoseid() {
        return diagnosisDiagnoseid;
    }

    public void setDiagnosisDiagnoseid(Diagnosis diagnosisDiagnoseid) {
        this.diagnosisDiagnoseid = diagnosisDiagnoseid;
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
        hash += (reportid != null ? reportid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reports)) {
            return false;
        }
        Reports other = (Reports) object;
        if ((this.reportid == null && other.reportid != null) || (this.reportid != null && !this.reportid.equals(other.reportid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Reports[ reportid=" + reportid + " ]";
    }
    
}
