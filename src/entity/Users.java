/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Milhouse
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsersid", query = "SELECT u FROM Users u WHERE u.usersid = :usersid"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword2", query = "SELECT u FROM Users u WHERE u.password2 = :password2"),
    @NamedQuery(name = "Users.findByPasswordsalt", query = "SELECT u FROM Users u WHERE u.passwordsalt = :passwordsalt"),
    @NamedQuery(name = "Users.findByPasswordanswer", query = "SELECT u FROM Users u WHERE u.passwordanswer = :passwordanswer"),
    @NamedQuery(name = "Users.findByDatetimelastlogin", query = "SELECT u FROM Users u WHERE u.datetimelastlogin = :datetimelastlogin")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usersid")
    private Integer usersid;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password_2")
    private String password2;
    @Basic(optional = false)
    @Column(name = "passwordsalt")
    private String passwordsalt;
    @Basic(optional = false)
    @Column(name = "passwordanswer")
    private String passwordanswer;
    @Basic(optional = false)
    @Column(name = "datetimelastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimelastlogin;
    @JoinColumn(name = "persons_personid", referencedColumnName = "personid")
    @ManyToOne(optional = false)
    private Persons personsPersonid;

    public Users() {
    }

    public Users(Integer usersid) {
        this.usersid = usersid;
    }

    public Users(Integer usersid, String username, String password2, String passwordsalt, String passwordanswer, Date datetimelastlogin) {
        this.usersid = usersid;
        this.username = username;
        this.password2 = password2;
        this.passwordsalt = passwordsalt;
        this.passwordanswer = passwordanswer;
        this.datetimelastlogin = datetimelastlogin;
    }

    public Integer getUsersid() {
        return usersid;
    }

    public void setUsersid(Integer usersid) {
        this.usersid = usersid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPasswordsalt() {
        return passwordsalt;
    }

    public void setPasswordsalt(String passwordsalt) {
        this.passwordsalt = passwordsalt;
    }

    public String getPasswordanswer() {
        return passwordanswer;
    }

    public void setPasswordanswer(String passwordanswer) {
        this.passwordanswer = passwordanswer;
    }

    public Date getDatetimelastlogin() {
        return datetimelastlogin;
    }

    public void setDatetimelastlogin(Date datetimelastlogin) {
        this.datetimelastlogin = datetimelastlogin;
    }

    public Persons getPersonsPersonid() {
        return personsPersonid;
    }

    public void setPersonsPersonid(Persons personsPersonid) {
        this.personsPersonid = personsPersonid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersid != null ? usersid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usersid == null && other.usersid != null) || (this.usersid != null && !this.usersid.equals(other.usersid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stois.Entity.Users[ usersid=" + usersid + " ]";
    }
    
}
