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
@Table(name = "tools")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tools.findAll", query = "SELECT t FROM Tools t"),
    @NamedQuery(name = "Tools.findByToolid", query = "SELECT t FROM Tools t WHERE t.toolid = :toolid"),
    @NamedQuery(name = "Tools.findByToolname", query = "SELECT t FROM Tools t WHERE t.toolname = :toolname"),
    @NamedQuery(name = "Tools.findByToolcode", query = "SELECT t FROM Tools t WHERE t.toolcode = :toolcode"),
    @NamedQuery(name = "Tools.findByCanceled", query = "SELECT t FROM Tools t WHERE t.canceled = :canceled"),
    @NamedQuery(name = "Tools.findByArchived", query = "SELECT t FROM Tools t WHERE t.archived = :archived")})
public class Tools implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "toolid")
    private Integer toolid;
    @Basic(optional = false)
    @Column(name = "toolname")
    private String toolname;
    @Basic(optional = false)
    @Column(name = "toolcode")
    private String toolcode;
    @Basic(optional = false)
    @Column(name = "canceled")
    private boolean canceled;
    @Basic(optional = false)
    @Column(name = "archived")
    private boolean archived;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toolsToolid")
    private Collection<Usedtools> usedtoolsCollection;

    public Tools() {
    }

    public Tools(Integer toolid) {
        this.toolid = toolid;
    }

    public Tools(Integer toolid, String toolname, String toolcode, boolean canceled, boolean archived) {
        this.toolid = toolid;
        this.toolname = toolname;
        this.toolcode = toolcode;
        this.canceled = canceled;
        this.archived = archived;
    }

    public Integer getToolid() {
        return toolid;
    }

    public void setToolid(Integer toolid) {
        this.toolid = toolid;
    }

    public String getToolname() {
        return toolname;
    }

    public void setToolname(String toolname) {
        this.toolname = toolname;
    }

    public String getToolcode() {
        return toolcode;
    }

    public void setToolcode(String toolcode) {
        this.toolcode = toolcode;
    }

    public boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @XmlTransient
    public Collection<Usedtools> getUsedtoolsCollection() {
        return usedtoolsCollection;
    }

    public void setUsedtoolsCollection(Collection<Usedtools> usedtoolsCollection) {
        this.usedtoolsCollection = usedtoolsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (toolid != null ? toolid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tools)) {
            return false;
        }
        Tools other = (Tools) object;
        if ((this.toolid == null && other.toolid != null) || (this.toolid != null && !this.toolid.equals(other.toolid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Tools[ toolid=" + toolid + " ]";
    }
    
}
