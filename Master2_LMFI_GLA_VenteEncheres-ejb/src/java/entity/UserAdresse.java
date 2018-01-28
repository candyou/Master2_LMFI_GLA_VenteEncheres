/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "user_adresse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAdresse.findAll", query = "SELECT u FROM UserAdresse u")
    , @NamedQuery(name = "UserAdresse.findByIdUser", query = "SELECT u FROM UserAdresse u WHERE u.userAdressePK.idUser = :idUser")
    , @NamedQuery(name = "UserAdresse.findByIdAdr", query = "SELECT u FROM UserAdresse u WHERE u.userAdressePK.idAdr = :idAdr")
    , @NamedQuery(name = "UserAdresse.findByDefaultAdr", query = "SELECT u FROM UserAdresse u WHERE u.defaultAdr = :defaultAdr")})
public class UserAdresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserAdressePK userAdressePK;
    @Column(name = "default_adr")
    private Short defaultAdr;
    @JoinColumn(name = "id_adr", referencedColumnName = "idadresse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Adresses adresses;
    @JoinColumn(name = "id_user", referencedColumnName = "idusers", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UserAdresse() {
    }

    public UserAdresse(UserAdressePK userAdressePK) {
        this.userAdressePK = userAdressePK;
    }

    public UserAdresse(int idUser, int idAdr) {
        this.userAdressePK = new UserAdressePK(idUser, idAdr);
    }

    public UserAdressePK getUserAdressePK() {
        return userAdressePK;
    }

    public void setUserAdressePK(UserAdressePK userAdressePK) {
        this.userAdressePK = userAdressePK;
    }

    public Short getDefaultAdr() {
        return defaultAdr;
    }

    public void setDefaultAdr(Short defaultAdr) {
        this.defaultAdr = defaultAdr;
    }

    public Adresses getAdresses() {
        return adresses;
    }

    public void setAdresses(Adresses adresses) {
        this.adresses = adresses;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAdressePK != null ? userAdressePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAdresse)) {
            return false;
        }
        UserAdresse other = (UserAdresse) object;
        if ((this.userAdressePK == null && other.userAdressePK != null) || (this.userAdressePK != null && !this.userAdressePK.equals(other.userAdressePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return adresses.getNumRue() + " " + adresses.getNomRue() + "  " + adresses.getCp()+  "  " + adresses.getVille();
    }
    
}
