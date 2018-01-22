/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Mohamed
 */
@Entity
@Table(name = "user_article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserArticle.findAll", query = "SELECT u FROM UserArticle u")
    , @NamedQuery(name = "UserArticle.findByIdUser", query = "SELECT u FROM UserArticle u WHERE u.userArticlePK.idUser = :idUser")
    , @NamedQuery(name = "UserArticle.findByIdArticle", query = "SELECT u FROM UserArticle u WHERE u.userArticlePK.idArticle = :idArticle")
    , @NamedQuery(name = "UserArticle.findByDateCreate", query = "SELECT u FROM UserArticle u WHERE u.dateCreate = :dateCreate")})
public class UserArticle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserArticlePK userArticlePK;
    @Column(name = "date_create")
    @Temporal(TemporalType.DATE)
    private Date dateCreate;
    @JoinColumn(name = "id_article", referencedColumnName = "idarticle", insertable = false, updatable = false)
    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    private Articles articles;
    @JoinColumn(name = "id_user", referencedColumnName = "idusers", insertable = false, updatable = false)
    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    private Users users;

    public UserArticle() {
    }

    public UserArticle(UserArticlePK userArticlePK) {
        this.userArticlePK = userArticlePK;
    }

    public UserArticle(int idUser, int idArticle) {
        this.userArticlePK = new UserArticlePK(idUser, idArticle);
    }

    public UserArticlePK getUserArticlePK() {
        return userArticlePK;
    }

    public void setUserArticlePK(UserArticlePK userArticlePK) {
        this.userArticlePK = userArticlePK;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
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
        hash += (userArticlePK != null ? userArticlePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserArticle)) {
            return false;
        }
        UserArticle other = (UserArticle) object;
        if ((this.userArticlePK == null && other.userArticlePK != null) || (this.userArticlePK != null && !this.userArticlePK.equals(other.userArticlePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserArticle[ userArticlePK=" + userArticlePK + " ]";
    }
    
}
