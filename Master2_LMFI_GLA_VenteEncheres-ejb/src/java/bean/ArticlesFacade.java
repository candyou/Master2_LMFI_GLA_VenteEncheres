/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Articles;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mohamed
 */
@Stateless
public class ArticlesFacade extends AbstractFacade<Articles> {

    @PersistenceContext(unitName = "Master2_LMFI_GLA_VenteEncheres-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticlesFacade() {
        super(Articles.class);
    }
    
     public List<Articles> seachByName(String name){
            Date d = new Date();
         Query query = em.createNamedQuery("Articles.findByNomArticle");
        query.setParameter("nomArticle",name);
        query.setParameter("dateLimite", d);
        return query.getResultList();
    }
     public List<Articles> contraintelimite(){
         Date d = new Date();
          Query query = em.createNamedQuery("Articles.contrainte");
        query.setParameter("dateLimite",d);
         System.out.println("bean.ArticlesFacade.contraintelimite()" + d);
        return query.getResultList();
     }
}
