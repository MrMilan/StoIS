/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import entity.Materials;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Operations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class MaterialsJpaController implements Serializable {

    public MaterialsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materials materials) {
        if (materials.getOperationsCollection() == null) {
            materials.setOperationsCollection(new ArrayList<Operations>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Operations> attachedOperationsCollection = new ArrayList<Operations>();
            for (Operations operationsCollectionOperationsToAttach : materials.getOperationsCollection()) {
                operationsCollectionOperationsToAttach = em.getReference(operationsCollectionOperationsToAttach.getClass(), operationsCollectionOperationsToAttach.getOperationsid());
                attachedOperationsCollection.add(operationsCollectionOperationsToAttach);
            }
            materials.setOperationsCollection(attachedOperationsCollection);
            em.persist(materials);
            for (Operations operationsCollectionOperations : materials.getOperationsCollection()) {
                operationsCollectionOperations.getMaterialsCollection().add(materials);
                operationsCollectionOperations = em.merge(operationsCollectionOperations);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materials materials) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materials persistentMaterials = em.find(Materials.class, materials.getMaterialid());
            Collection<Operations> operationsCollectionOld = persistentMaterials.getOperationsCollection();
            Collection<Operations> operationsCollectionNew = materials.getOperationsCollection();
            Collection<Operations> attachedOperationsCollectionNew = new ArrayList<Operations>();
            for (Operations operationsCollectionNewOperationsToAttach : operationsCollectionNew) {
                operationsCollectionNewOperationsToAttach = em.getReference(operationsCollectionNewOperationsToAttach.getClass(), operationsCollectionNewOperationsToAttach.getOperationsid());
                attachedOperationsCollectionNew.add(operationsCollectionNewOperationsToAttach);
            }
            operationsCollectionNew = attachedOperationsCollectionNew;
            materials.setOperationsCollection(operationsCollectionNew);
            materials = em.merge(materials);
            for (Operations operationsCollectionOldOperations : operationsCollectionOld) {
                if (!operationsCollectionNew.contains(operationsCollectionOldOperations)) {
                    operationsCollectionOldOperations.getMaterialsCollection().remove(materials);
                    operationsCollectionOldOperations = em.merge(operationsCollectionOldOperations);
                }
            }
            for (Operations operationsCollectionNewOperations : operationsCollectionNew) {
                if (!operationsCollectionOld.contains(operationsCollectionNewOperations)) {
                    operationsCollectionNewOperations.getMaterialsCollection().add(materials);
                    operationsCollectionNewOperations = em.merge(operationsCollectionNewOperations);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materials.getMaterialid();
                if (findMaterials(id) == null) {
                    throw new NonexistentEntityException("The materials with id " + id + " no longer exists.");
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
            Materials materials;
            try {
                materials = em.getReference(Materials.class, id);
                materials.getMaterialid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materials with id " + id + " no longer exists.", enfe);
            }
            Collection<Operations> operationsCollection = materials.getOperationsCollection();
            for (Operations operationsCollectionOperations : operationsCollection) {
                operationsCollectionOperations.getMaterialsCollection().remove(materials);
                operationsCollectionOperations = em.merge(operationsCollectionOperations);
            }
            em.remove(materials);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materials> findMaterialsEntities() {
        return findMaterialsEntities(true, -1, -1);
    }

    public List<Materials> findMaterialsEntities(int maxResults, int firstResult) {
        return findMaterialsEntities(false, maxResults, firstResult);
    }

    private List<Materials> findMaterialsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materials.class));
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

    public Materials findMaterials(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materials.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materials> rt = cq.from(Materials.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
