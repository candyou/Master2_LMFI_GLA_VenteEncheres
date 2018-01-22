/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
    , @NamedQuery(name = "Commande.findByIdcommande", query = "SELECT c FROM Commande c WHERE c.idcommande = :idcommande")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcommande")
    private Integer idcommande;
    @JoinTable(name = "panier", joinColumns = {
        @JoinColumn(name = "idcommande", referencedColumnName = "idcommande")}, inverseJoinColumns = {
        @JoinColumn(name = "idparti", referencedColumnName = "idpartEnch")})
    @ManyToMany
    private List<ParticipeEnch> participeEnchList;
    @JoinColumn(name = "id_adresse", referencedColumnName = "idadresse")
    @ManyToOne
    private Adresses idAdresse;
    @JoinColumn(name = "id_facturation", referencedColumnName = "idfacturation")
    @ManyToOne
    private Facturation idFacturation;

    public Commande() {
    }

    public Commande(Integer idcommande) {
        this.idcommande = idcommande;
    }

    public Integer getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(Integer idcommande) {
        this.idcommande = idcommande;
    }

    @XmlTransient
    public List<ParticipeEnch> getParticipeEnchList() {
        return participeEnchList;
    }

    public void setParticipeEnchList(List<ParticipeEnch> participeEnchList) {
        this.participeEnchList = participeEnchList;
    }

    public Adresses getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Adresses idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Facturation getIdFacturation() {
        return idFacturation;
    }

    public void setIdFacturation(Facturation idFacturation) {
        this.idFacturation = idFacturation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcommande != null ? idcommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idcommande == null && other.idcommande != null) || (this.idcommande != null && !this.idcommande.equals(other.idcommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Commande[ idcommande=" + idcommande + " ]";
    }
    
}
