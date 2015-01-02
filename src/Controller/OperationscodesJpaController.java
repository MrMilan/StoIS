/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Operations;
import entity.Operationscodes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class OperationscodesJpaController implements Serializable {

    public OperationscodesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operationscodes operationscodes) {
        if (operationscodes.getOperationsCollection() == null) {
            operationscodes.setOperationsCollection(new ArrayList<Operations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Operations> attachedOperationsCollection = new ArrayList<Operations>();
            for (Operations operationsCollectionOperationsToAttach : operationscodes.getOperationsCollection()) {
                operationsCollectionOperationsToAttach = em.getReference(operationsCollectionOperationsToAttach.getClass(), operationsCollectionOperationsToAttach.getOperationsid());
                attachedOperationsCollection.add(operationsCollectionOperationsToAttach);
            }
            operationscodes.setOperationsCollection(attachedOperationsCollection);
            em.persist(operationscodes);
            for (Operations operationsCollectionOperations : operationscodes.getOperationsCollection()) {
                Operationscodes oldOperationscodesIdoperationscodesOfOperationsCollectionOperations = operationsCollectionOperations.getOperationscodesIdoperationscodes();
                operationsCollectionOperations.setOperationscodesIdoperationscodes(operationscodes);
                operationsCollectionOperations = em.merge(operationsCollectionOperations);
                if (oldOperationscodesIdoperationscodesOfOperationsCollectionOperations != null) {
                    oldOperationscodesIdoperationscodesOfOperationsCollectionOperations.getOperationsCollection().remove(operationsCollectionOperations);
                    oldOperationscodesIdoperationscodesOfOperationsCollectionOperations = em.merge(oldOperationscodesIdoperationscodesOfOperationsCollectionOperations);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operationscodes operationscodes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operationscodes persistentOperationscodes = em.find(Operationscodes.class, operationscodes.getIdoperationscodes());
            Collection<Operations> operationsCollectionOld = persistentOperationscodes.getOperationsCollection();
            Collection<Operations> operationsCollectionNew = operationscodes.getOperationsCollection();
            List<String> illegalOrphanMessages = null;
            for (Operations operationsCollectionOldOperations : operationsCollectionOld) {
                if (!operationsCollectionNew.contains(operationsCollectionOldOperations)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operations " + operationsCollectionOldOperations + " since its operationscodesIdoperationscodes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Operations> attachedOperationsCollectionNew = new ArrayList<Operations>();
            for (Operations operationsCollectionNewOperationsToAttach : operationsCollectionNew) {
                operationsCollectionNewOperationsToAttach = em.getReference(operationsCollectionNewOperationsToAttach.getClass(), operationsCollectionNewOperationsToAttach.getOperationsid());
                attachedOperationsCollectionNew.add(operationsCollectionNewOperationsToAttach);
            }
            operationsCollectionNew = attachedOperationsCollectionNew;
            operationscodes.setOperationsCollection(operationsCollectionNew);
            operationscodes = em.merge(operationscodes);
            for (Operations operationsCollectionNewOperations : operationsCollectionNew) {
                if (!operationsCollectionOld.contains(operationsCollectionNewOperations)) {
                    Operationscodes oldOperationscodesIdoperationscodesOfOperationsCollectionNewOperations = operationsCollectionNewOperations.getOperationscodesIdoperationscodes();
                    operationsCollectionNewOperations.setOperationscodesIdoperationscodes(operationscodes);
                    operationsCollectionNewOperations = em.merge(operationsCollectionNewOperations);
                    if (oldOperationscodesIdoperationscodesOfOperationsCollectionNewOperations != null && !oldOperationscodesIdoperationscodesOfOperationsCollectionNewOperations.equals(operationscodes)) {
                        oldOperationscodesIdoperationscodesOfOperationsCollectionNewOperations.getOperationsCollection().remove(operationsCollectionNewOperations);
                        oldOperationscodesIdoperationscodesOfOperationsCollectionNewOperations = em.merge(oldOperationscodesIdoperationscodesOfOperationsCollectionNewOperations);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = operationscodes.getIdoperationscodes();
                if (findOperationscodes(id) == null) {
                    throw new NonexistentEntityException("The operationscodes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operationscodes operationscodes;
            try {
                operationscodes = em.getReference(Operationscodes.class, id);
                operationscodes.getIdoperationscodes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operationscodes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Operations> operationsCollectionOrphanCheck = operationscodes.getOperationsCollection();
            for (Operations operationsCollectionOrphanCheckOperations : operationsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Operationscodes (" + operationscodes + ") cannot be destroyed since the Operations " + operationsCollectionOrphanCheckOperations + " in its operationsCollection field has a non-nullable operationscodesIdoperationscodes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(operationscodes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Operationscodes> findOperationscodesEntities() {
        return findOperationscodesEntities(true, -1, -1);
    }

    public List<Operationscodes> findOperationscodesEntities(int maxResults, int firstResult) {
        return findOperationscodesEntities(false, maxResults, firstResult);
    }

    private List<Operationscodes> findOperationscodesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operationscodes.class));
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

    public Operationscodes findOperationscodes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operationscodes.class, id);
        } finally {
            em.close();
        }
    }

    public int getOperationscodesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Operationscodes> rt = cq.from(Operationscodes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
