/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "Master2_LMFI_GLA_VenteEncheres-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users findByUsernameAndPass(String login, String password){
        try{
        Users us = em.createNamedQuery("Users.findByLogPass",Users.class)
                .setParameter("login", login)
                .setParameter("mdp", password)
                .getSingleResult();
        return us;
        }catch(NoResultException e){
            return null;
        }
    } 
    
}
