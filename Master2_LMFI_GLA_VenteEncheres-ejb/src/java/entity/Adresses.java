/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "adresses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresses.findAll", query = "SELECT a FROM Adresses a")
    , @NamedQuery(name = "Adresses.findByIdadresse", query = "SELECT a FROM Adresses a WHERE a.idadresse = :idadresse")
    , @NamedQuery(name = "Adresses.findByNumRue", query = "SELECT a FROM Adresses a WHERE a.numRue = :numRue")
    , @NamedQuery(name = "Adresses.findByNomRue", query = "SELECT a FROM Adresses a WHERE a.nomRue = :nomRue")
    , @NamedQuery(name = "Adresses.findByVille", query = "SELECT a FROM Adresses a WHERE a.ville = :ville")
    , @NamedQuery(name = "Adresses.findByCp", query = "SELECT a FROM Adresses a WHERE a.cp = :cp")})
public class Adresses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadresse")
    private Integer idadresse;
    @Column(name = "num_rue")
    private Integer numRue;
    @Column(name = "nom_rue")
    private String nomRue;
    @Column(name = "ville")
    private String ville;
    @Column(name = "cp")
    private Integer cp;
    @OneToMany(mappedBy = "idAdresse")
    private List<Commande> commandeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adresses")
    private List<UserAdresse> userAdresseList;

    public Adresses() {
    }

    public Adresses(Integer idadresse) {
        this.idadresse = idadresse;
    }

    public Integer getIdadresse() {
        return idadresse;
    }

    public void setIdadresse(Integer idadresse) {
        this.idadresse = idadresse;
    }

    public Integer getNumRue() {
        return numRue;
    }

    public void setNumRue(Integer numRue) {
        this.numRue = numRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @XmlTransient
    public List<UserAdresse> getUserAdresseList() {
        return userAdresseList;
    }

    public void setUserAdresseList(List<UserAdresse> userAdresseList) {
        this.userAdresseList = userAdresseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadresse != null ? idadresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresses)) {
            return false;
        }
        Adresses other = (Adresses) object;
        if ((this.idadresse == null && other.idadresse != null) || (this.idadresse != null && !this.idadresse.equals(other.idadresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Numero " + getNumRue() + " Rue " + getNomRue() + "  CP : " + getCp()+  "  Ville : " + getVille();
    }
    
}
