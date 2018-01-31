/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Articles;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TimerService;
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
    
    @Resource
    private TimerService timerService;
    
    public List<Articles> listRandoArt;


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
        return query.getResultList();
     }
     
     public List<Articles> getValideArti(int iduser){
         return em.createNamedQuery("Articles.findByValide",Articles.class)
                 .setParameter("datelimite", new Date())
                 .setParameter("idusers", iduser)
                 .getResultList();
     }
     
     public List<Articles> getByCatName(String nomArticle, int idCat,int iduser){
         return em.createNamedQuery("Articles.findByCatAndName",Articles.class)
                 .setParameter("nomArticle", nomArticle)
                 .setParameter("idCat",idCat)
                .setParameter("idusers", iduser)
                  .setParameter("datelimite", new Date())
                 .getResultList();               
     }
     
     @Schedule(second="0", minute="5", hour="01",
        dayOfMonth="*", month="*", year="*")
        public void sendRandoList() {
            System.out.println("bean.ArticlesFacade.sendRandoList()");
           Vector<Articles> rdm = new Vector<Articles>();
        List<Integer> nums = new Vector<Integer>();
        List<Articles> tmp = new Vector<>();
            tmp= em.createNamedQuery("Articles.findAll",Articles.class).getResultList();
            int rnum;
              
          Random randomGenerator = new Random();
           for (int idx = 1; idx <= 3; ++idx){
                 
               int randomInt = randomGenerator.nextInt(tmp.size()-1);
               if(!nums.contains(randomInt)){ nums.add(randomInt);
               idx--;
               }
               }
           
           for(int j=0;j<3;j++ ){
               
               rdm.add(tmp.get(nums.get(j)));
           }
           listRandoArt = rdm;
        }
        }
