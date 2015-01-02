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
@Table(name = "operations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operations.findAll", query = "SELECT o FROM Operations o"),
    @NamedQuery(name = "Operations.findByOperationsid", query = "SELECT o FROM Operations o WHERE o.operationsid = :operationsid"),
    @NamedQuery(name = "Operations.findByNote", query = "SELECT o FROM Operations o WHERE o.note = :note")})
public class Operations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "operationsid")
    private Integer operationsid;
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "operationscodes_idoperationscodes", referencedColumnName = "idoperationscodes")
    @ManyToOne(optional = false)
    private Operationscodes operationscodesIdoperationscodes;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Operationscodes getOperationscodesIdoperationscodes() {
        return operationscodesIdoperationscodes;
    }

    public void setOperationscodesIdoperationscodes(Operationscodes operationscodesIdoperationscodes) {
        this.operationscodesIdoperationscodes = operationscodesIdoperationscodes;
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
