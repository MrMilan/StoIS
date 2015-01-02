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
@Table(name = "operationscodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operationscodes.findAll", query = "SELECT o FROM Operationscodes o"),
    @NamedQuery(name = "Operationscodes.findByIdoperationscodes", query = "SELECT o FROM Operationscodes o WHERE o.idoperationscodes = :idoperationscodes"),
    @NamedQuery(name = "Operationscodes.findByOperationcode", query = "SELECT o FROM Operationscodes o WHERE o.operationcode = :operationcode")})
public class Operationscodes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idoperationscodes")
    private Integer idoperationscodes;
    @Basic(optional = false)
    @Column(name = "operationcode")
    private String operationcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operationscodesIdoperationscodes")
    private Collection<Operations> operationsCollection;

    public Operationscodes() {
    }

    public Operationscodes(Integer idoperationscodes) {
        this.idoperationscodes = idoperationscodes;
    }

    public Operationscodes(Integer idoperationscodes, String operationcode) {
        this.idoperationscodes = idoperationscodes;
        this.operationcode = operationcode;
    }

    public Integer getIdoperationscodes() {
        return idoperationscodes;
    }

    public void setIdoperationscodes(Integer idoperationscodes) {
        this.idoperationscodes = idoperationscodes;
    }

    public String getOperationcode() {
        return operationcode;
    }

    public void setOperationcode(String operationcode) {
        this.operationcode = operationcode;
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
        hash += (idoperationscodes != null ? idoperationscodes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operationscodes)) {
            return false;
        }
        Operationscodes other = (Operationscodes) object;
        if ((this.idoperationscodes == null && other.idoperationscodes != null) || (this.idoperationscodes != null && !this.idoperationscodes.equals(other.idoperationscodes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Operationscodes[ idoperationscodes=" + idoperationscodes + " ]";
    }
    
}
