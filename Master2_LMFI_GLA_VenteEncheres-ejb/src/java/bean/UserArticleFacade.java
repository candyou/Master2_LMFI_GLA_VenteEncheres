/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.UserArticle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class UserArticleFacade extends AbstractFacade<UserArticle> {

    @PersistenceContext(unitName = "Master2_LMFI_GLA_VenteEncheres-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserArticle findByArtUser(int idArt,int idUer){
        return em.createNamedQuery("UserArticle.findByArtUser",UserArticle.class)
                .setParameter("idUser", idUer)
                .setParameter("idArticle", idArt)
                .getSingleResult();
    }

    public UserArticleFacade() {
        super(UserArticle.class);
    }
    
}
