/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import entity.Operations;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Operationscodes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class OperationsJpaController implements Serializable {

    public OperationsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operations operations) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operationscodes operationscodesIdoperationscodes = operations.getOperationscodesIdoperationscodes();
            if (operationscodesIdoperationscodes != null) {
                operationscodesIdoperationscodes = em.getReference(operationscodesIdoperationscodes.getClass(), operationscodesIdoperationscodes.getIdoperationscodes());
                operations.setOperationscodesIdoperationscodes(operationscodesIdoperationscodes);
            }
            em.persist(operations);
            if (operationscodesIdoperationscodes != null) {
                operationscodesIdoperationscodes.getOperationsCollection().add(operations);
                operationscodesIdoperationscodes = em.merge(operationscodesIdoperationscodes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operations operations) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operations persistentOperations = em.find(Operations.class, operations.getOperationsid());
            Operationscodes operationscodesIdoperationscodesOld = persistentOperations.getOperationscodesIdoperationscodes();
            Operationscodes operationscodesIdoperationscodesNew = operations.getOperationscodesIdoperationscodes();
            if (operationscodesIdoperationscodesNew != null) {
                operationscodesIdoperationscodesNew = em.getReference(operationscodesIdoperationscodesNew.getClass(), operationscodesIdoperationscodesNew.getIdoperationscodes());
                operations.setOperationscodesIdoperationscodes(operationscodesIdoperationscodesNew);
            }
            operations = em.merge(operations);
            if (operationscodesIdoperationscodesOld != null && !operationscodesIdoperationscodesOld.equals(operationscodesIdoperationscodesNew)) {
                operationscodesIdoperationscodesOld.getOperationsCollection().remove(operations);
                operationscodesIdoperationscodesOld = em.merge(operationscodesIdoperationscodesOld);
            }
            if (operationscodesIdoperationscodesNew != null && !operationscodesIdoperationscodesNew.equals(operationscodesIdoperationscodesOld)) {
                operationscodesIdoperationscodesNew.getOperationsCollection().add(operations);
                operationscodesIdoperationscodesNew = em.merge(operationscodesIdoperationscodesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = operations.getOperationsid();
                if (findOperations(id) == null) {
                    throw new NonexistentEntityException("The operations with id " + id + " no longer exists.");
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
            Operations operations;
            try {
                operations = em.getReference(Operations.class, id);
                operations.getOperationsid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operations with id " + id + " no longer exists.", enfe);
            }
            Operationscodes operationscodesIdoperationscodes = operations.getOperationscodesIdoperationscodes();
            if (operationscodesIdoperationscodes != null) {
                operationscodesIdoperationscodes.getOperationsCollection().remove(operations);
                operationscodesIdoperationscodes = em.merge(operationscodesIdoperationscodes);
            }
            em.remove(operations);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Operations> findOperationsEntities() {
        return findOperationsEntities(true, -1, -1);
    }

    public List<Operations> findOperationsEntities(int maxResults, int firstResult) {
        return findOperationsEntities(false, maxResults, firstResult);
    }

    private List<Operations> findOperationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operations.class));
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

    public Operations findOperations(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operations.class, id);
        } finally {
            em.close();
        }
    }

    public int getOperationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Operations> rt = cq.from(Operations.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
