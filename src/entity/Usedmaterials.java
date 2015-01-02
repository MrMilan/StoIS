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
@Table(name = "usedmaterials")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usedmaterials.findAll", query = "SELECT u FROM Usedmaterials u"),
    @NamedQuery(name = "Usedmaterials.findByIdusedmateria", query = "SELECT u FROM Usedmaterials u WHERE u.idusedmateria = :idusedmateria")})
public class Usedmaterials implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusedmateria")
    private Integer idusedmateria;
    @JoinColumn(name = "materials_materialid", referencedColumnName = "materialid")
    @ManyToOne(optional = false)
    private Materials materialsMaterialid;
    @JoinColumn(name = "operations_operationsid", referencedColumnName = "operationsid")
    @ManyToOne(optional = false)
    private Operations operationsOperationsid;

    public Usedmaterials() {
    }

    public Usedmaterials(Integer idusedmateria) {
        this.idusedmateria = idusedmateria;
    }

    public Integer getIdusedmateria() {
        return idusedmateria;
    }

    public void setIdusedmateria(Integer idusedmateria) {
        this.idusedmateria = idusedmateria;
    }

    public Materials getMaterialsMaterialid() {
        return materialsMaterialid;
    }

    public void setMaterialsMaterialid(Materials materialsMaterialid) {
        this.materialsMaterialid = materialsMaterialid;
    }

    public Operations getOperationsOperationsid() {
        return operationsOperationsid;
    }

    public void setOperationsOperationsid(Operations operationsOperationsid) {
        this.operationsOperationsid = operationsOperationsid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusedmateria != null ? idusedmateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usedmaterials)) {
            return false;
        }
        Usedmaterials other = (Usedmaterials) object;
        if ((this.idusedmateria == null && other.idusedmateria != null) || (this.idusedmateria != null && !this.idusedmateria.equals(other.idusedmateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Usedmaterials[ idusedmateria=" + idusedmateria + " ]";
    }
    
}
