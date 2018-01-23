<<<<<<< HEAD
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByIdusers", query = "SELECT u FROM Users u WHERE u.idusers = :idusers")
    , @NamedQuery(name = "Users.findByNom", query = "SELECT u FROM Users u WHERE u.nom = :nom")
    , @NamedQuery(name = "Users.findByPrenom", query = "SELECT u FROM Users u WHERE u.prenom = :prenom")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByTel", query = "SELECT u FROM Users u WHERE u.tel = :tel")
    , @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login")
    , @NamedQuery(name = "Users.findByMdp", query = "SELECT u FROM Users u WHERE u.mdp = :mdp")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusers")
    private Integer idusers;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "email")
    private String email;
    @Column(name = "tel")
    private String tel;
    @Column(name = "login")
    private String login;
    @Column(name = "mdp")
    private String mdp;
    @OneToMany(mappedBy = "idUser")
    private List<ParticipeEnch> participeEnchList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "users")
    private List<UserArticle> userArticleList;
    @OneToMany(mappedBy = "idUser")
    private List<Facturation> facturationList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "users")
    private List<UserAdresse> userAdresseList;

    public Users() {
    }

    public Users(Integer idusers) {
        this.idusers = idusers;
    }

    public Integer getIdusers() {
        return idusers;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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

    @XmlTransient
    public List<Facturation> getFacturationList() {
        return facturationList;
    }

    public void setFacturationList(List<Facturation> facturationList) {
        this.facturationList = facturationList;
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
        hash += (idusers != null ? idusers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idusers == null && other.idusers != null) || (this.idusers != null && !this.idusers.equals(other.idusers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Users[ idusers=" + idusers + " ]";
    }
    
}
=======
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByIdusers", query = "SELECT u FROM Users u WHERE u.idusers = :idusers")
    , @NamedQuery(name = "Users.findByNom", query = "SELECT u FROM Users u WHERE u.nom = :nom")
    , @NamedQuery(name = "Users.findByPrenom", query = "SELECT u FROM Users u WHERE u.prenom = :prenom")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByTel", query = "SELECT u FROM Users u WHERE u.tel = :tel")
    , @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login")
    , @NamedQuery(name = "Users.findByLogPass", query = "SELECT u FROM Users u WHERE u.login = :login AND u.mdp = :mdp")
    , @NamedQuery(name = "Users.findByMdp", query = "SELECT u FROM Users u WHERE u.mdp = :mdp")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusers")
    private Integer idusers;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "email")
    private String email;
    @Column(name = "tel")
    private String tel;
    @Column(name = "login")
    private String login;
    @Column(name = "mdp")
    private String mdp;
    @OneToMany(mappedBy = "idUser")
    private List<ParticipeEnch> participeEnchList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<UserArticle> userArticleList;
    @OneToMany(mappedBy = "idUser")
    private List<Facturation> facturationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<UserAdresse> userAdresseList;

    public Users() {
    }

    public Users(Integer idusers) {
        this.idusers = idusers;
    }

    public Integer getIdusers() {
        return idusers;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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

    @XmlTransient
    public List<Facturation> getFacturationList() {
        return facturationList;
    }

    public void setFacturationList(List<Facturation> facturationList) {
        this.facturationList = facturationList;
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
        hash += (idusers != null ? idusers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idusers == null && other.idusers != null) || (this.idusers != null && !this.idusers.equals(other.idusers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Users[ idusers=" + idusers + " ]";
    }
    
}
>>>>>>> fca7c847d93f01afa0d06ca6f062c78cf80a6d3e
