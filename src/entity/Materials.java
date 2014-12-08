/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Materials.findAll", query = "SELECT m FROM Materials m"),
    @NamedQuery(name = "Materials.findByMaterialid", query = "SELECT m FROM Materials m WHERE m.materialid = :materialid"),
    @NamedQuery(name = "Materials.findByMaterialname", query = "SELECT m FROM Materials m WHERE m.materialname = :materialname"),
    @NamedQuery(name = "Materials.findByMaterialcode", query = "SELECT m FROM Materials m WHERE m.materialcode = :materialcode"),
    @NamedQuery(name = "Materials.findByCanceled", query = "SELECT m FROM Materials m WHERE m.canceled = :canceled")})
public class Materials implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer materialid;
    @Basic(optional = false)
    private String materialname;
    @Basic(optional = false)
    private String materialcode;
    @Basic(optional = false)
    private boolean canceled;
    @JoinTable(name = "usedmaterials", joinColumns = {
        @JoinColumn(name = "materials_materialid", referencedColumnName = "materialid")}, inverseJoinColumns = {
        @JoinColumn(name = "operations_operationsid", referencedColumnName = "operationsid")})
    @ManyToMany
    private Collection<Operations> operationsCollection;

    public Materials() {
    }

    public Materials(Integer materialid) {
        this.materialid = materialid;
    }

    public Materials(Integer materialid, String materialname, String materialcode, boolean canceled) {
        this.materialid = materialid;
        this.materialname = materialname;
        this.materialcode = materialcode;
        this.canceled = canceled;
    }

    public Integer getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Integer materialid) {
        this.materialid = materialid;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    public boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @XmlTransient
    public Collection<Operations> getOperationsCollection() {
        return operationsCollection;
    }

    public void setOperationsCollection(Collection<Operations> operationsCollection) {
        this.operationsCollection = operationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialid != null ? materialid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materials)) {
            return false;
        }
        Materials other = (Materials) object;
        if ((this.materialid == null && other.materialid != null) || (this.materialid != null && !this.materialid.equals(other.materialid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Materials[ materialid=" + materialid + " ]";
    }
    
}
