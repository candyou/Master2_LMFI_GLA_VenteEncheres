/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Articles;
import entity.ParticipeEnch;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class ParticipeEnchFacade extends AbstractFacade<ParticipeEnch> {

    @PersistenceContext(unitName = "Master2_LMFI_GLA_VenteEncheres-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipeEnchFacade() {
        super(ParticipeEnch.class);
    }
    
    public List<Articles> getPartByArticle(int idArticle){
        List<ParticipeEnch> listPart = em.createNamedQuery("ParticipeEnch.findByIdArt",ParticipeEnch.class)
                .setParameter("idArticle", (int)idArticle)
                .getResultList(); 
        List<Articles> listArt = new ArrayList<Articles>();
        for (ParticipeEnch participeEnch : listPart) {
            listArt.add(participeEnch.getIdArticle());
        }
        return listArt;
    }
    
    public List<ParticipeEnch> getPartByArti(int idArticle){
        List<ParticipeEnch> listPart = em.createNamedQuery("ParticipeEnch.findByIdArt",ParticipeEnch.class)
                .setParameter("idArticle", (int)idArticle)
                .getResultList(); 
        return listPart;
    }
    
    public double getMaxProp ( int idArticle){
        List<ParticipeEnch> listPart = em.createNamedQuery("ParticipeEnch.findByIdArt",ParticipeEnch.class)
                .setParameter("idArticle", (int)idArticle)
                .getResultList();
        double max = 0;
        for (ParticipeEnch participeEnch : listPart) {
            if (participeEnch.getPrixProp() > max)
                max = participeEnch.getPrixProp();
        }
        return max;
    }
    public List<ParticipeEnch> getMaxPart(int idUser){
        Date d = new Date();
        List<ParticipeEnch> listPart = em.createNamedQuery("ParticipeEnch.getMaxPart",ParticipeEnch.class)
               .setParameter("dateLimite", d)
                .setParameter("idUser", (int)idUser)
                .getResultList();
        return listPart;
    }
      public List<ParticipeEnch> getPanier(int idUser){
     
        List<ParticipeEnch> listPart = em.createNamedQuery("ParticipeEnch.getPanier",ParticipeEnch.class)
             
                .setParameter("idUser", (int)idUser)
                .getResultList();
        return listPart;
    }
      
     @Schedule(second = "*/5", minute = "*", hour = "*")
     public void editStatutPart(){
         //System.out.println("bean.ParticipeEnchFacade.editStatutPart()");
         List<ParticipeEnch> listPart = em.createNamedQuery("ParticipeEnch.getMaxPartEditStatus",ParticipeEnch.class)
                .setParameter("dateLimite", new Date())
                .getResultList();
         for (ParticipeEnch participeEnch : listPart) {
             participeEnch.setEtatParticip(Short.parseShort("1"));
             em.merge(participeEnch);
         }
     }
    
}
