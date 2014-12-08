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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(catalog = "s06", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operations.findAll", query = "SELECT o FROM Operations o"),
    @NamedQuery(name = "Operations.findByOperationsid", query = "SELECT o FROM Operations o WHERE o.operationsid = :operationsid"),
    @NamedQuery(name = "Operations.findByTypeofwork", query = "SELECT o FROM Operations o WHERE o.typeofwork = :typeofwork"),
    @NamedQuery(name = "Operations.findByWorkcode", query = "SELECT o FROM Operations o WHERE o.workcode = :workcode")})
public class Operations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer operationsid;
    private String typeofwork;
    private Integer workcode;
    @JoinTable(name = "usedtools", joinColumns = {
        @JoinColumn(name = "operations_operationsid", referencedColumnName = "operationsid")}, inverseJoinColumns = {
        @JoinColumn(name = "tools_toolid", referencedColumnName = "toolid")})
    @ManyToMany
    private Collection<Tools> toolsCollection;
    @ManyToMany(mappedBy = "operationsCollection")
    private Collection<Materials> materialsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operations")
    private Collection<Actions> actionsCollection;

    public Operations() {
    }

    public Operations(Integer operationsid) {
        this.operationsid = operationsid;
    }

    public Integer getOperationsid() {
        return operationsid;
    }

    public void setOperationsid(Integer operationsid) {
        this.operationsid = operationsid;
    }

    public String getTypeofwork() {
        return typeofwork;
    }

    public void setTypeofwork(String typeofwork) {
        this.typeofwork = typeofwork;
    }

    public Integer getWorkcode() {
        return workcode;
    }

    public void setWorkcode(Integer workcode) {
        this.workcode = workcode;
    }

    @XmlTransient
    public Collection<Tools> getToolsCollection() {
        return toolsCollection;
    }

    public void setToolsCollection(Collection<Tools> toolsCollection) {
        this.toolsCollection = toolsCollection;
    }

    @XmlTransient
    public Collection<Materials> getMaterialsCollection() {
        return materialsCollection;
    }

    public void setMaterialsCollection(Collection<Materials> materialsCollection) {
        this.materialsCollection = materialsCollection;
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
        hash += (operationsid != null ? operationsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operations)) {
            return false;
        }
        Operations other = (Operations) object;
        if ((this.operationsid == null && other.operationsid != null) || (this.operationsid != null && !this.operationsid.equals(other.operationsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Operations[ operationsid=" + operationsid + " ]";
    }
    
}
