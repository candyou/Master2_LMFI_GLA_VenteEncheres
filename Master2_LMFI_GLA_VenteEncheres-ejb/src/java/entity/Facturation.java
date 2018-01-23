/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "facturation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturation.findAll", query = "SELECT f FROM Facturation f")
    , @NamedQuery(name = "Facturation.findByIdfacturation", query = "SELECT f FROM Facturation f WHERE f.idfacturation = :idfacturation")
    , @NamedQuery(name = "Facturation.findByNumCb", query = "SELECT f FROM Facturation f WHERE f.numCb = :numCb")
    , @NamedQuery(name = "Facturation.findByDateExp", query = "SELECT f FROM Facturation f WHERE f.dateExp = :dateExp")})
public class Facturation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfacturation")
    private Integer idfacturation;
    @Column(name = "num_cb")
    private Integer numCb;
    @Column(name = "date_exp")
    @Temporal(TemporalType.DATE)
    private Date dateExp;
    @OneToMany(mappedBy = "idFacturation")
    private List<Commande> commandeList;
    @JoinColumn(name = "id_user", referencedColumnName = "idusers")
    @ManyToOne
    private Users idUser;

    public Facturation() {
    }

    public Facturation(Integer idfacturation) {
        this.idfacturation = idfacturation;
    }

    public Integer getIdfacturation() {
        return idfacturation;
    }

    public void setIdfacturation(Integer idfacturation) {
        this.idfacturation = idfacturation;
    }

    public Integer getNumCb() {
        return numCb;
    }

    public void setNumCb(Integer numCb) {
        this.numCb = numCb;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
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
        hash += (idfacturation != null ? idfacturation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturation)) {
            return false;
        }
        Facturation other = (Facturation) object;
        if ((this.idfacturation == null && other.idfacturation != null) || (this.idfacturation != null && !this.idfacturation.equals(other.idfacturation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Facturation[ idfacturation=" + idfacturation + " ]";
    }
    
}
