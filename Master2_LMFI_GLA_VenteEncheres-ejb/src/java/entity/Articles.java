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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "articles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articles.findAll", query = "SELECT a FROM Articles a")
    , @NamedQuery(name = "Articles.findByIdarticle", query = "SELECT a FROM Articles a WHERE a.idarticle = :idarticle")
    , @NamedQuery(name = "Articles.findByNomArticle", query = "SELECT a FROM Articles a WHERE a.nomArticle = :nomArticle")
    , @NamedQuery(name = "Articles.findByDescription", query = "SELECT a FROM Articles a WHERE a.description = :description")
    , @NamedQuery(name = "Articles.findByPrixDepart", query = "SELECT a FROM Articles a WHERE a.prixDepart = :prixDepart")
    , @NamedQuery(name = "Articles.findByDateLimite", query = "SELECT a FROM Articles a WHERE a.dateLimite = :dateLimite")})
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarticle")
    private Integer idarticle;
    @Column(name = "nom_article")
    private String nomArticle;
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_depart")
    private Double prixDepart;
    @Column(name = "date_limite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLimite;
    @JoinTable(name = "article_categ", joinColumns = {
        @JoinColumn(name = "id_article", referencedColumnName = "idarticle")}, inverseJoinColumns = {
        @JoinColumn(name = "id_categ", referencedColumnName = "idcategorie")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Categorie> categorieList;
    @OneToMany(mappedBy = "idArticle")
    private List<ParticipeEnch> participeEnchList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "articles")
    private List<UserArticle> userArticleList;

    public Articles() {
    }

    public Articles(Integer idarticle) {
        this.idarticle = idarticle;
    }

    public Integer getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(Integer idarticle) {
        this.idarticle = idarticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrixDepart() {
        return prixDepart;
    }

    public void setPrixDepart(Double prixDepart) {
        this.prixDepart = prixDepart;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    @XmlTransient
    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    @XmlTransient
    public List<ParticipeEnch> getParticipeEnchList() {
        return participeEnchList;
    }

    public void setParticipeEnchList(List<ParticipeEnch> participeEnchList) {
        this.participeEnchList = participeEnchList;
    }

    @XmlTransient
    public List<UserArticle> getUserArticleList() {
        return userArticleList;
    }

    public void setUserArticleList(List<UserArticle> userArticleList) {
        this.userArticleList = userArticleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarticle != null ? idarticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articles)) {
            return false;
        }
        Articles other = (Articles) object;
        if ((this.idarticle == null && other.idarticle != null) || (this.idarticle != null && !this.idarticle.equals(other.idarticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Articles[ idarticle=" + idarticle + " ]";
    }
    
}
