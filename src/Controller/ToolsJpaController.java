/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Operations;
import entity.Tools;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class ToolsJpaController implements Serializable {

    public ToolsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tools tools) {
        if (tools.getOperationsCollection() == null) {
            tools.setOperationsCollection(new ArrayList<Operations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Operations> attachedOperationsCollection = new ArrayList<Operations>();
            for (Operations operationsCollectionOperationsToAttach : tools.getOperationsCollection()) {
                operationsCollectionOperationsToAttach = em.getReference(operationsCollectionOperationsToAttach.getClass(), operationsCollectionOperationsToAttach.getOperationsid());
                attachedOperationsCollection.add(operationsCollectionOperationsToAttach);
            }
            tools.setOperationsCollection(attachedOperationsCollection);
            em.persist(tools);
            for (Operations operationsCollectionOperations : tools.getOperationsCollection()) {
                operationsCollectionOperations.getToolsCollection().add(tools);
                operationsCollectionOperations = em.merge(operationsCollectionOperations);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tools tools) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tools persistentTools = em.find(Tools.class, tools.getToolid());
            Collection<Operations> operationsCollectionOld = persistentTools.getOperationsCollection();
            Collection<Operations> operationsCollectionNew = tools.getOperationsCollection();
            Collection<Operations> attachedOperationsCollectionNew = new ArrayList<Operations>();
            for (Operations operationsCollectionNewOperationsToAttach : operationsCollectionNew) {
                operationsCollectionNewOperationsToAttach = em.getReference(operationsCollectionNewOperationsToAttach.getClass(), operationsCollectionNewOperationsToAttach.getOperationsid());
                attachedOperationsCollectionNew.add(operationsCollectionNewOperationsToAttach);
            }
            operationsCollectionNew = attachedOperationsCollectionNew;
            tools.setOperationsCollection(operationsCollectionNew);
            tools = em.merge(tools);
            for (Operations operationsCollectionOldOperations : operationsCollectionOld) {
                if (!operationsCollectionNew.contains(operationsCollectionOldOperations)) {
                    operationsCollectionOldOperations.getToolsCollection().remove(tools);
                    operationsCollectionOldOperations = em.merge(operationsCollectionOldOperations);
                }
            }
            for (Operations operationsCollectionNewOperations : operationsCollectionNew) {
                if (!operationsCollectionOld.contains(operationsCollectionNewOperations)) {
                    operationsCollectionNewOperations.getToolsCollection().add(tools);
                    operationsCollectionNewOperations = em.merge(operationsCollectionNewOperations);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tools.getToolid();
                if (findTools(id) == null) {
                    throw new NonexistentEntityException("The tools with id " + id + " no longer exists.");
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
            Tools tools;
            try {
                tools = em.getReference(Tools.class, id);
                tools.getToolid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tools with id " + id + " no longer exists.", enfe);
            }
            Collection<Operations> operationsCollection = tools.getOperationsCollection();
            for (Operations operationsCollectionOperations : operationsCollection) {
                operationsCollectionOperations.getToolsCollection().remove(tools);
                operationsCollectionOperations = em.merge(operationsCollectionOperations);
            }
            em.remove(tools);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tools> findToolsEntities() {
        return findToolsEntities(true, -1, -1);
    }

    public List<Tools> findToolsEntities(int maxResults, int firstResult) {
        return findToolsEntities(false, maxResults, firstResult);
    }

    private List<Tools> findToolsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tools.class));
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

    public Tools findTools(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tools.class, id);
        } finally {
            em.close();
        }
    }

    public int getToolsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tools> rt = cq.from(Tools.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
