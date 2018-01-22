/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.UserAdresse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class UserAdresseFacade extends AbstractFacade<UserAdresse> {

    @PersistenceContext(unitName = "Master2_LMFI_GLA_VenteEncheres-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserAdresseFacade() {
        super(UserAdresse.class);
    }
    
}
