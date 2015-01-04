/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import entity.Diagnosis;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Milhouse
 */
public class DiagnosisJpaController implements Serializable {

    public DiagnosisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diagnosis diagnosis) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(diagnosis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diagnosis diagnosis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            diagnosis = em.merge(diagnosis);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diagnosis.getDiagnoseid();
                if (findDiagnosis(id) == null) {
                    throw new NonexistentEntityException("The diagnosis with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diagnosis diagnosis;
            try {
                diagnosis = em.getReference(Diagnosis.class, id);
                diagnosis.getDiagnoseid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diagnosis with id " + id + " no longer exists.", enfe);
            }
            em.remove(diagnosis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diagnosis> findDiagnosisEntities() {
        return findDiagnosisEntities(true, -1, -1);
    }

    public List<Diagnosis> findDiagnosisEntities(int maxResults, int firstResult) {
        return findDiagnosisEntities(false, maxResults, firstResult);
    }

    private List<Diagnosis> findDiagnosisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diagnosis.class));
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

    public Diagnosis findDiagnosis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diagnosis.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiagnosisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diagnosis> rt = cq.from(Diagnosis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
