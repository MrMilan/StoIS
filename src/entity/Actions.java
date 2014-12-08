/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Milhouse
 */
@Entity
@Table(catalog = "s06", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actions.findAll", query = "SELECT a FROM Actions a"),
    @NamedQuery(name = "Actions.findByOperationsOperationsid", query = "SELECT a FROM Actions a WHERE a.actionsPK.operationsOperationsid = :operationsOperationsid"),
    @NamedQuery(name = "Actions.findByPersonsInsurancesInsuranceid", query = "SELECT a FROM Actions a WHERE a.actionsPK.personsInsurancesInsuranceid = :personsInsurancesInsuranceid"),
    @NamedQuery(name = "Actions.findByPersonsAddressesAddressid", query = "SELECT a FROM Actions a WHERE a.actionsPK.personsAddressesAddressid = :personsAddressesAddressid"),
    @NamedQuery(name = "Actions.findByPersonsUsersUsersid", query = "SELECT a FROM Actions a WHERE a.actionsPK.personsUsersUsersid = :personsUsersUsersid"),
    @NamedQuery(name = "Actions.findByPersonsPersonid", query = "SELECT a FROM Actions a WHERE a.actionsPK.personsPersonid = :personsPersonid"),
    @NamedQuery(name = "Actions.findByReportsDiagnosisDiagnoseid", query = "SELECT a FROM Actions a WHERE a.actionsPK.reportsDiagnosisDiagnoseid = :reportsDiagnosisDiagnoseid"),
    @NamedQuery(name = "Actions.findByReportsReportid", query = "SELECT a FROM Actions a WHERE a.actionsPK.reportsReportid = :reportsReportid")})
public class Actions implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActionsPK actionsPK;
    @JoinColumn(name = "operations_operationsid", referencedColumnName = "operationsid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Operations operations;
    @JoinColumns({
        @JoinColumn(name = "persons_personid", referencedColumnName = "personid", insertable = false, updatable = false),
        @JoinColumn(name = "persons_users_usersid", referencedColumnName = "users_usersid", insertable = false, updatable = false),
        @JoinColumn(name = "persons_addresses_addressid", referencedColumnName = "addresses_addressid", insertable = false, updatable = false),
        @JoinColumn(name = "persons_insurances_insuranceid", referencedColumnName = "insurances_insuranceid", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Persons persons;
    @JoinColumns({
        @JoinColumn(name = "reports_reportid", referencedColumnName = "reportid", insertable = false, updatable = false),
        @JoinColumn(name = "reports_diagnosis_diagnoseid", referencedColumnName = "diagnosis_diagnoseid", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Reports reports;

    public Actions() {
    }

    public Actions(ActionsPK actionsPK) {
        this.actionsPK = actionsPK;
    }

    public Actions(int operationsOperationsid, int personsInsurancesInsuranceid, int personsAddressesAddressid, int personsUsersUsersid, int personsPersonid, int reportsDiagnosisDiagnoseid, int reportsReportid) {
        this.actionsPK = new ActionsPK(operationsOperationsid, personsInsurancesInsuranceid, personsAddressesAddressid, personsUsersUsersid, personsPersonid, reportsDiagnosisDiagnoseid, reportsReportid);
    }

    public ActionsPK getActionsPK() {
        return actionsPK;
    }

    public void setActionsPK(ActionsPK actionsPK) {
        this.actionsPK = actionsPK;
    }

    public Operations getOperations() {
        return operations;
    }

    public void setOperations(Operations operations) {
        this.operations = operations;
    }

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }

    public Reports getReports() {
        return reports;
    }

    public void setReports(Reports reports) {
        this.reports = reports;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actionsPK != null ? actionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actions)) {
            return false;
        }
        Actions other = (Actions) object;
        if ((this.actionsPK == null && other.actionsPK != null) || (this.actionsPK != null && !this.actionsPK.equals(other.actionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Actions[ actionsPK=" + actionsPK + " ]";
    }
    
}
