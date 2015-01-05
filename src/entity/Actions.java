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
import javax.persistence.JoinColumn;
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
@Table(name = "actions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actions.findAll", query = "SELECT a FROM Actions a"),
    @NamedQuery(name = "Actions.findByActionid", query = "SELECT a FROM Actions a WHERE a.actionid = :actionid")})
public class Actions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "actionid")
    private Integer actionid;
    @JoinColumn(name = "operations_operationsid", referencedColumnName = "operationsid")
    @ManyToOne(optional = false)
    private Operations operationsOperationsid;
    @JoinColumn(name = "persons_has_roles_idphr", referencedColumnName = "idphr")
    @ManyToOne(optional = false)
    private PersonsHasRoles personsHasRolesIdphr;
    @JoinColumn(name = "reports_reportid", referencedColumnName = "reportid")
    @ManyToOne(optional = false)
    private Reports reportsReportid;

    public Actions() {
    }

    public Actions(Integer actionid) {
        this.actionid = actionid;
    }

    public Integer getActionid() {
        return actionid;
    }

    public void setActionid(Integer actionid) {
        this.actionid = actionid;
    }

    public Operations getOperationsOperationsid() {
        return operationsOperationsid;
    }

    public void setOperationsOperationsid(Operations operationsOperationsid) {
        this.operationsOperationsid = operationsOperationsid;
    }

    public PersonsHasRoles getPersonsHasRolesIdphr() {
        return personsHasRolesIdphr;
    }

    public void setPersonsHasRolesIdphr(PersonsHasRoles personsHasRolesIdphr) {
        this.personsHasRolesIdphr = personsHasRolesIdphr;
    }

    public Reports getReportsReportid() {
        return reportsReportid;
    }

    public void setReportsReportid(Reports reportsReportid) {
        this.reportsReportid = reportsReportid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actionid != null ? actionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actions)) {
            return false;
        }
        Actions other = (Actions) object;
        if ((this.actionid == null && other.actionid != null) || (this.actionid != null && !this.actionid.equals(other.actionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + actionid + "";
    }
    
}
