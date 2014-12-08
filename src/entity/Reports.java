/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(catalog = "s06", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reports.findAll", query = "SELECT r FROM Reports r"),
    @NamedQuery(name = "Reports.findByReportid", query = "SELECT r FROM Reports r WHERE r.reportsPK.reportid = :reportid"),
    @NamedQuery(name = "Reports.findByDiagnosisDiagnoseid", query = "SELECT r FROM Reports r WHERE r.reportsPK.diagnosisDiagnoseid = :diagnosisDiagnoseid"),
    @NamedQuery(name = "Reports.findByDate", query = "SELECT r FROM Reports r WHERE r.date = :date"),
    @NamedQuery(name = "Reports.findByNote", query = "SELECT r FROM Reports r WHERE r.note = :note")})
public class Reports implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReportsPK reportsPK;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String note;
    @JoinColumn(name = "diagnosis_diagnoseid", referencedColumnName = "diagnoseid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Diagnosis diagnosis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reports")
    private Collection<Actions> actionsCollection;

    public Reports() {
    }

    public Reports(ReportsPK reportsPK) {
        this.reportsPK = reportsPK;
    }

    public Reports(int reportid, int diagnosisDiagnoseid) {
        this.reportsPK = new ReportsPK(reportid, diagnosisDiagnoseid);
    }

    public ReportsPK getReportsPK() {
        return reportsPK;
    }

    public void setReportsPK(ReportsPK reportsPK) {
        this.reportsPK = reportsPK;
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

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
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
        hash += (reportsPK != null ? reportsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reports)) {
            return false;
        }
        Reports other = (Reports) object;
        if ((this.reportsPK == null && other.reportsPK != null) || (this.reportsPK != null && !this.reportsPK.equals(other.reportsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reports[ reportsPK=" + reportsPK + " ]";
    }
    
}
