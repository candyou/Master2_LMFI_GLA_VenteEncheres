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
@Table(name = "participe_ench")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipeEnch.findAll", query = "SELECT p FROM ParticipeEnch p  ORDER BY p.prixProp ASC")
    , @NamedQuery(name = "ParticipeEnch.findByIdpartEnch", query = "SELECT p FROM ParticipeEnch p WHERE p.idpartEnch = :idpartEnch ORDER BY p.prixProp ASC")
    , @NamedQuery(name = "ParticipeEnch.findByPrixProp", query = "SELECT p FROM ParticipeEnch p WHERE p.prixProp = :prixProp ORDER BY p.prixProp ASC")
    , @NamedQuery(name = "ParticipeEnch.findByEtatParticip", query = "SELECT p FROM ParticipeEnch p WHERE p.etatParticip = :etatParticip ORDER BY p.prixProp ASC")
    , @NamedQuery(name = "ParticipeEnch.findByEtatAchat", query = "SELECT p FROM ParticipeEnch p WHERE p.etatAchat = :etatAchat ORDER BY p.prixProp ASC")})
public class ParticipeEnch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpartEnch")
    private Integer idpartEnch;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_prop")
    private Double prixProp;
    @Column(name = "etat_particip")
    private Short etatParticip;
    @Column(name = "etat_achat")
    private Short etatAchat;
    @ManyToMany(mappedBy = "participeEnchList")
    private List<Commande> commandeList;
    @JoinColumn(name = "id_article", referencedColumnName = "idarticle")
    @ManyToOne
    private Articles idArticle;
    @JoinColumn(name = "id_user", referencedColumnName = "idusers")
    @ManyToOne
    private Users idUser;

    public ParticipeEnch() {
    }

    public ParticipeEnch(Integer idpartEnch) {
        this.idpartEnch = idpartEnch;
    }

    public Integer getIdpartEnch() {
        return idpartEnch;
    }

    public void setIdpartEnch(Integer idpartEnch) {
        this.idpartEnch = idpartEnch;
    }

    public Double getPrixProp() {
        return prixProp;
    }

    public void setPrixProp(Double prixProp) {
        this.prixProp = prixProp;
    }

    public Short getEtatParticip() {
        return etatParticip;
    }

    public void setEtatParticip(Short etatParticip) {
        this.etatParticip = etatParticip;
    }

    public Short getEtatAchat() {
        return etatAchat;
    }

    public void setEtatAchat(Short etatAchat) {
        this.etatAchat = etatAchat;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    public Articles getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Articles idArticle) {
        this.idArticle = idArticle;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpartEnch != null ? idpartEnch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipeEnch)) {
            return false;
        }
        ParticipeEnch other = (ParticipeEnch) object;
        if ((this.idpartEnch == null && other.idpartEnch != null) || (this.idpartEnch != null && !this.idpartEnch.equals(other.idpartEnch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParticipeEnch[ idpartEnch=" + idpartEnch + " ]";
    }
    
}
