/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Milhouse
 */
@Embeddable
public class PersonsPK implements Serializable {
    @Basic(optional = false)
    private int personid;
    @Basic(optional = false)
    @Column(name = "users_usersid")
    private int usersUsersid;
    @Basic(optional = false)
    @Column(name = "addresses_addressid")
    private int addressesAddressid;
    @Basic(optional = false)
    @Column(name = "insurances_insuranceid")
    private int insurancesInsuranceid;

    public PersonsPK() {
    }

    public PersonsPK(int personid, int usersUsersid, int addressesAddressid, int insurancesInsuranceid) {
        this.personid = personid;
        this.usersUsersid = usersUsersid;
        this.addressesAddressid = addressesAddressid;
        this.insurancesInsuranceid = insurancesInsuranceid;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public int getUsersUsersid() {
        return usersUsersid;
    }

    public void setUsersUsersid(int usersUsersid) {
        this.usersUsersid = usersUsersid;
    }

    public int getAddressesAddressid() {
        return addressesAddressid;
    }

    public void setAddressesAddressid(int addressesAddressid) {
        this.addressesAddressid = addressesAddressid;
    }

    public int getInsurancesInsuranceid() {
        return insurancesInsuranceid;
    }

    public void setInsurancesInsuranceid(int insurancesInsuranceid) {
        this.insurancesInsuranceid = insurancesInsuranceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) personid;
        hash += (int) usersUsersid;
        hash += (int) addressesAddressid;
        hash += (int) insurancesInsuranceid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonsPK)) {
            return false;
        }
        PersonsPK other = (PersonsPK) object;
        if (this.personid != other.personid) {
            return false;
        }
        if (this.usersUsersid != other.usersUsersid) {
            return false;
        }
        if (this.addressesAddressid != other.addressesAddressid) {
            return false;
        }
        if (this.insurancesInsuranceid != other.insurancesInsuranceid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PersonsPK[ personid=" + personid + ", usersUsersid=" + usersUsersid + ", addressesAddressid=" + addressesAddressid + ", insurancesInsuranceid=" + insurancesInsuranceid + " ]";
    }
    
}
