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
public class ActionsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "operations_operationsid")
    private int operationsOperationsid;
    @Basic(optional = false)
    @Column(name = "persons_insurances_insuranceid")
    private int personsInsurancesInsuranceid;
    @Basic(optional = false)
    @Column(name = "persons_addresses_addressid")
    private int personsAddressesAddressid;
    @Basic(optional = false)
    @Column(name = "persons_users_usersid")
    private int personsUsersUsersid;
    @Basic(optional = false)
    @Column(name = "persons_personid")
    private int personsPersonid;
    @Basic(optional = false)
    @Column(name = "reports_diagnosis_diagnoseid")
    private int reportsDiagnosisDiagnoseid;
    @Basic(optional = false)
    @Column(name = "reports_reportid")
    private int reportsReportid;

    public ActionsPK() {
    }

    public ActionsPK(int operationsOperationsid, int personsInsurancesInsuranceid, int personsAddressesAddressid, int personsUsersUsersid, int personsPersonid, int reportsDiagnosisDiagnoseid, int reportsReportid) {
        this.operationsOperationsid = operationsOperationsid;
        this.personsInsurancesInsuranceid = personsInsurancesInsuranceid;
        this.personsAddressesAddressid = personsAddressesAddressid;
        this.personsUsersUsersid = personsUsersUsersid;
        this.personsPersonid = personsPersonid;
        this.reportsDiagnosisDiagnoseid = reportsDiagnosisDiagnoseid;
        this.reportsReportid = reportsReportid;
    }

    public int getOperationsOperationsid() {
        return operationsOperationsid;
    }

    public void setOperationsOperationsid(int operationsOperationsid) {
        this.operationsOperationsid = operationsOperationsid;
    }

    public int getPersonsInsurancesInsuranceid() {
        return personsInsurancesInsuranceid;
    }

    public void setPersonsInsurancesInsuranceid(int personsInsurancesInsuranceid) {
        this.personsInsurancesInsuranceid = personsInsurancesInsuranceid;
    }

    public int getPersonsAddressesAddressid() {
        return personsAddressesAddressid;
    }

    public void setPersonsAddressesAddressid(int personsAddressesAddressid) {
        this.personsAddressesAddressid = personsAddressesAddressid;
    }

    public int getPersonsUsersUsersid() {
        return personsUsersUsersid;
    }

    public void setPersonsUsersUsersid(int personsUsersUsersid) {
        this.personsUsersUsersid = personsUsersUsersid;
    }

    public int getPersonsPersonid() {
        return personsPersonid;
    }

    public void setPersonsPersonid(int personsPersonid) {
        this.personsPersonid = personsPersonid;
    }

    public int getReportsDiagnosisDiagnoseid() {
        return reportsDiagnosisDiagnoseid;
    }

    public void setReportsDiagnosisDiagnoseid(int reportsDiagnosisDiagnoseid) {
        this.reportsDiagnosisDiagnoseid = reportsDiagnosisDiagnoseid;
    }

    public int getReportsReportid() {
        return reportsReportid;
    }

    public void setReportsReportid(int reportsReportid) {
        this.reportsReportid = reportsReportid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) operationsOperationsid;
        hash += (int) personsInsurancesInsuranceid;
        hash += (int) personsAddressesAddressid;
        hash += (int) personsUsersUsersid;
        hash += (int) personsPersonid;
        hash += (int) reportsDiagnosisDiagnoseid;
        hash += (int) reportsReportid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionsPK)) {
            return false;
        }
        ActionsPK other = (ActionsPK) object;
        if (this.operationsOperationsid != other.operationsOperationsid) {
            return false;
        }
        if (this.personsInsurancesInsuranceid != other.personsInsurancesInsuranceid) {
            return false;
        }
        if (this.personsAddressesAddressid != other.personsAddressesAddressid) {
            return false;
        }
        if (this.personsUsersUsersid != other.personsUsersUsersid) {
            return false;
        }
        if (this.personsPersonid != other.personsPersonid) {
            return false;
        }
        if (this.reportsDiagnosisDiagnoseid != other.reportsDiagnosisDiagnoseid) {
            return false;
        }
        if (this.reportsReportid != other.reportsReportid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ActionsPK[ operationsOperationsid=" + operationsOperationsid + ", personsInsurancesInsuranceid=" + personsInsurancesInsuranceid + ", personsAddressesAddressid=" + personsAddressesAddressid + ", personsUsersUsersid=" + personsUsersUsersid + ", personsPersonid=" + personsPersonid + ", reportsDiagnosisDiagnoseid=" + reportsDiagnosisDiagnoseid + ", reportsReportid=" + reportsReportid + " ]";
    }
    
}
