/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.isum.controller;

import com.metropolitan.isum.entiteti.Predmet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lenovo
 */
@Stateless
public class PredmetFacade extends AbstractFacade<Predmet> {

    @PersistenceContext(unitName = "ISUM_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PredmetFacade() {
        super(Predmet.class);
    }
    
}
