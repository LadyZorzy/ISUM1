/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.isum.controller;

import com.metropolitan.isum.controller.exceptions.NonexistentEntityException;
import com.metropolitan.isum.controller.exceptions.PreexistingEntityException;
import com.metropolitan.isum.controller.exceptions.RollbackFailureException;
import com.metropolitan.isum.entiteti.Predmet;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lenovo
 */
public class PredmetJpaController implements Serializable {

    public PredmetJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Predmet predmet) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(predmet);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPredmet(predmet.getSifraPredmeta()) != null) {
                throw new PreexistingEntityException("Predmet " + predmet + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Predmet predmet) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            predmet = em.merge(predmet);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = predmet.getSifraPredmeta();
                if (findPredmet(id) == null) {
                    throw new NonexistentEntityException("The predmet with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Predmet predmet;
            try {
                predmet = em.getReference(Predmet.class, id);
                predmet.getSifraPredmeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The predmet with id " + id + " no longer exists.", enfe);
            }
            em.remove(predmet);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Predmet> findPredmetEntities() {
        return findPredmetEntities(true, -1, -1);
    }

    public List<Predmet> findPredmetEntities(int maxResults, int firstResult) {
        return findPredmetEntities(false, maxResults, firstResult);
    }

    private List<Predmet> findPredmetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Predmet.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Predmet findPredmet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Predmet.class, id);
        } finally {
            em.close();
        }
    }

    public int getPredmetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Predmet> rt = cq.from(Predmet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
