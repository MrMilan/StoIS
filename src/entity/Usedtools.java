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
@Table(name = "usedtools")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usedtools.findAll", query = "SELECT u FROM Usedtools u"),
    @NamedQuery(name = "Usedtools.findByIdusedtool", query = "SELECT u FROM Usedtools u WHERE u.idusedtool = :idusedtool")})
public class Usedtools implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idusedtool")
    private Integer idusedtool;
    @JoinColumn(name = "operations_operationsid", referencedColumnName = "operationsid")
    @ManyToOne(optional = false)
    private Operations operationsOperationsid;
    @JoinColumn(name = "tools_toolid", referencedColumnName = "toolid")
    @ManyToOne(optional = false)
    private Tools toolsToolid;

    public Usedtools() {
    }

    public Usedtools(Integer idusedtool) {
        this.idusedtool = idusedtool;
    }

    public Integer getIdusedtool() {
        return idusedtool;
    }

    public void setIdusedtool(Integer idusedtool) {
        this.idusedtool = idusedtool;
    }

    public Operations getOperationsOperationsid() {
        return operationsOperationsid;
    }

    public void setOperationsOperationsid(Operations operationsOperationsid) {
        this.operationsOperationsid = operationsOperationsid;
    }

    public Tools getToolsToolid() {
        return toolsToolid;
    }

    public void setToolsToolid(Tools toolsToolid) {
        this.toolsToolid = toolsToolid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusedtool != null ? idusedtool.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usedtools)) {
            return false;
        }
        Usedtools other = (Usedtools) object;
        if ((this.idusedtool == null && other.idusedtool != null) || (this.idusedtool != null && !this.idusedtool.equals(other.idusedtool))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Usedtools[ idusedtool=" + idusedtool + " ]";
    }
    
}
