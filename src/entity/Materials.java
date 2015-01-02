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
@Table(name = "materials")
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
    @Column(name = "materialid")
    private Integer materialid;
    @Basic(optional = false)
    @Column(name = "materialname")
    private String materialname;
    @Basic(optional = false)
    @Column(name = "materialcode")
    private String materialcode;
    @Basic(optional = false)
    @Column(name = "canceled")
    private boolean canceled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialsMaterialid")
    private Collection<Usedmaterials> usedmaterialsCollection;

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
    public Collection<Usedmaterials> getUsedmaterialsCollection() {
        return usedmaterialsCollection;
    }

    public void setUsedmaterialsCollection(Collection<Usedmaterials> usedmaterialsCollection) {
        this.usedmaterialsCollection = usedmaterialsCollection;
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
        return "stois.Entity.Materials[ materialid=" + materialid + " ]";
    }
    
}
