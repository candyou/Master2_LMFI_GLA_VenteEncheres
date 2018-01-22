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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mohamed
 */
@Embeddable
public class UserAdressePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_adr")
    private int idAdr;

    public UserAdressePK() {
    }

    public UserAdressePK(int idUser, int idAdr) {
        this.idUser = idUser;
        this.idAdr = idAdr;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAdr() {
        return idAdr;
    }

    public void setIdAdr(int idAdr) {
        this.idAdr = idAdr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUser;
        hash += (int) idAdr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAdressePK)) {
            return false;
        }
        UserAdressePK other = (UserAdressePK) object;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idAdr != other.idAdr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserAdressePK[ idUser=" + idUser + ", idAdr=" + idAdr + " ]";
    }
    
}
