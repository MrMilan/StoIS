/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Milhouse
 */
@Embeddable
public class ReportsPK implements Serializable {
    @Basic(optional = false)
    private int reportid;
    @Basic(optional = false)
    @Column(name = "diagnosis_diagnoseid")
    private int diagnosisDiagnoseid;

    public ReportsPK() {
    }

    public ReportsPK(int reportid, int diagnosisDiagnoseid) {
        this.reportid = reportid;
        this.diagnosisDiagnoseid = diagnosisDiagnoseid;
    }

    public int getReportid() {
        return reportid;
    }

    public void setReportid(int reportid) {
        this.reportid = reportid;
    }

    public int getDiagnosisDiagnoseid() {
        return diagnosisDiagnoseid;
    }

    public void setDiagnosisDiagnoseid(int diagnosisDiagnoseid) {
        this.diagnosisDiagnoseid = diagnosisDiagnoseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) reportid;
        hash += (int) diagnosisDiagnoseid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportsPK)) {
            return false;
        }
        ReportsPK other = (ReportsPK) object;
        if (this.reportid != other.reportid) {
            return false;
        }
        if (this.diagnosisDiagnoseid != other.diagnosisDiagnoseid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReportsPK[ reportid=" + reportid + ", diagnosisDiagnoseid=" + diagnosisDiagnoseid + " ]";
    }
    
}
