/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ParticipeEnch;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class ParticipeEnchFacade extends AbstractFacade<ParticipeEnch> {

    @PersistenceContext(unitName = "Master2_LMFI_GLA_VenteEncheres-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipeEnchFacade() {
        super(ParticipeEnch.class);
    }
    
}
