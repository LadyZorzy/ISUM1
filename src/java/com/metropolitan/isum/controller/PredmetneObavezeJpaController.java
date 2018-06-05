/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.isum.controller;

import com.metropolitan.isum.controller.exceptions.NonexistentEntityException;
import com.metropolitan.isum.controller.exceptions.PreexistingEntityException;
import com.metropolitan.isum.controller.exceptions.RollbackFailureException;
import com.metropolitan.isum.entiteti.PredmetneObaveze;
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
public class PredmetneObavezeJpaController implements Serializable {

    public PredmetneObavezeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PredmetneObaveze predmetneObaveze) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(predmetneObaveze);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPredmetneObaveze(predmetneObaveze.getSifraPredmeta()) != null) {
                throw new PreexistingEntityException("PredmetneObaveze " + predmetneObaveze + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PredmetneObaveze predmetneObaveze) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            predmetneObaveze = em.merge(predmetneObaveze);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = predmetneObaveze.getSifraPredmeta();
                if (findPredmetneObaveze(id) == null) {
                    throw new NonexistentEntityException("The predmetneObaveze with id " + id + " no longer exists.");
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
            PredmetneObaveze predmetneObaveze;
            try {
                predmetneObaveze = em.getReference(PredmetneObaveze.class, id);
                predmetneObaveze.getSifraPredmeta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The predmetneObaveze with id " + id + " no longer exists.", enfe);
            }
            em.remove(predmetneObaveze);
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

    public List<PredmetneObaveze> findPredmetneObavezeEntities() {
        return findPredmetneObavezeEntities(true, -1, -1);
    }

    public List<PredmetneObaveze> findPredmetneObavezeEntities(int maxResults, int firstResult) {
        return findPredmetneObavezeEntities(false, maxResults, firstResult);
    }

    private List<PredmetneObaveze> findPredmetneObavezeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PredmetneObaveze.class));
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

    public PredmetneObaveze findPredmetneObaveze(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PredmetneObaveze.class, id);
        } finally {
            em.close();
        }
    }

    public int getPredmetneObavezeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PredmetneObaveze> rt = cq.from(PredmetneObaveze.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
