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
import entity.Tools;
import java.util.ArrayList;
import java.util.Collection;
import entity.Materials;
import entity.Actions;
import entity.Operations;
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
        if (operations.getToolsCollection() == null) {
            operations.setToolsCollection(new ArrayList<Tools>());
        }
        if (operations.getMaterialsCollection() == null) {
            operations.setMaterialsCollection(new ArrayList<Materials>());
        }
        if (operations.getActionsCollection() == null) {
            operations.setActionsCollection(new ArrayList<Actions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tools> attachedToolsCollection = new ArrayList<Tools>();
            for (Tools toolsCollectionToolsToAttach : operations.getToolsCollection()) {
                toolsCollectionToolsToAttach = em.getReference(toolsCollectionToolsToAttach.getClass(), toolsCollectionToolsToAttach.getToolid());
                attachedToolsCollection.add(toolsCollectionToolsToAttach);
            }
            operations.setToolsCollection(attachedToolsCollection);
            Collection<Materials> attachedMaterialsCollection = new ArrayList<Materials>();
            for (Materials materialsCollectionMaterialsToAttach : operations.getMaterialsCollection()) {
                materialsCollectionMaterialsToAttach = em.getReference(materialsCollectionMaterialsToAttach.getClass(), materialsCollectionMaterialsToAttach.getMaterialid());
                attachedMaterialsCollection.add(materialsCollectionMaterialsToAttach);
            }
            operations.setMaterialsCollection(attachedMaterialsCollection);
            Collection<Actions> attachedActionsCollection = new ArrayList<Actions>();
            for (Actions actionsCollectionActionsToAttach : operations.getActionsCollection()) {
                actionsCollectionActionsToAttach = em.getReference(actionsCollectionActionsToAttach.getClass(), actionsCollectionActionsToAttach.getActionsPK());
                attachedActionsCollection.add(actionsCollectionActionsToAttach);
            }
            operations.setActionsCollection(attachedActionsCollection);
            em.persist(operations);
            for (Tools toolsCollectionTools : operations.getToolsCollection()) {
                toolsCollectionTools.getOperationsCollection().add(operations);
                toolsCollectionTools = em.merge(toolsCollectionTools);
            }
            for (Materials materialsCollectionMaterials : operations.getMaterialsCollection()) {
                materialsCollectionMaterials.getOperationsCollection().add(operations);
                materialsCollectionMaterials = em.merge(materialsCollectionMaterials);
            }
            for (Actions actionsCollectionActions : operations.getActionsCollection()) {
                Operations oldOperationsOfActionsCollectionActions = actionsCollectionActions.getOperations();
                actionsCollectionActions.setOperations(operations);
                actionsCollectionActions = em.merge(actionsCollectionActions);
                if (oldOperationsOfActionsCollectionActions != null) {
                    oldOperationsOfActionsCollectionActions.getActionsCollection().remove(actionsCollectionActions);
                    oldOperationsOfActionsCollectionActions = em.merge(oldOperationsOfActionsCollectionActions);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operations operations) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operations persistentOperations = em.find(Operations.class, operations.getOperationsid());
            Collection<Tools> toolsCollectionOld = persistentOperations.getToolsCollection();
            Collection<Tools> toolsCollectionNew = operations.getToolsCollection();
            Collection<Materials> materialsCollectionOld = persistentOperations.getMaterialsCollection();
            Collection<Materials> materialsCollectionNew = operations.getMaterialsCollection();
            Collection<Actions> actionsCollectionOld = persistentOperations.getActionsCollection();
            Collection<Actions> actionsCollectionNew = operations.getActionsCollection();
            List<String> illegalOrphanMessages = null;
            for (Actions actionsCollectionOldActions : actionsCollectionOld) {
                if (!actionsCollectionNew.contains(actionsCollectionOldActions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actions " + actionsCollectionOldActions + " since its operations field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tools> attachedToolsCollectionNew = new ArrayList<Tools>();
            for (Tools toolsCollectionNewToolsToAttach : toolsCollectionNew) {
                toolsCollectionNewToolsToAttach = em.getReference(toolsCollectionNewToolsToAttach.getClass(), toolsCollectionNewToolsToAttach.getToolid());
                attachedToolsCollectionNew.add(toolsCollectionNewToolsToAttach);
            }
            toolsCollectionNew = attachedToolsCollectionNew;
            operations.setToolsCollection(toolsCollectionNew);
            Collection<Materials> attachedMaterialsCollectionNew = new ArrayList<Materials>();
            for (Materials materialsCollectionNewMaterialsToAttach : materialsCollectionNew) {
                materialsCollectionNewMaterialsToAttach = em.getReference(materialsCollectionNewMaterialsToAttach.getClass(), materialsCollectionNewMaterialsToAttach.getMaterialid());
                attachedMaterialsCollectionNew.add(materialsCollectionNewMaterialsToAttach);
            }
            materialsCollectionNew = attachedMaterialsCollectionNew;
            operations.setMaterialsCollection(materialsCollectionNew);
            Collection<Actions> attachedActionsCollectionNew = new ArrayList<Actions>();
            for (Actions actionsCollectionNewActionsToAttach : actionsCollectionNew) {
                actionsCollectionNewActionsToAttach = em.getReference(actionsCollectionNewActionsToAttach.getClass(), actionsCollectionNewActionsToAttach.getActionsPK());
                attachedActionsCollectionNew.add(actionsCollectionNewActionsToAttach);
            }
            actionsCollectionNew = attachedActionsCollectionNew;
            operations.setActionsCollection(actionsCollectionNew);
            operations = em.merge(operations);
            for (Tools toolsCollectionOldTools : toolsCollectionOld) {
                if (!toolsCollectionNew.contains(toolsCollectionOldTools)) {
                    toolsCollectionOldTools.getOperationsCollection().remove(operations);
                    toolsCollectionOldTools = em.merge(toolsCollectionOldTools);
                }
            }
            for (Tools toolsCollectionNewTools : toolsCollectionNew) {
                if (!toolsCollectionOld.contains(toolsCollectionNewTools)) {
                    toolsCollectionNewTools.getOperationsCollection().add(operations);
                    toolsCollectionNewTools = em.merge(toolsCollectionNewTools);
                }
            }
            for (Materials materialsCollectionOldMaterials : materialsCollectionOld) {
                if (!materialsCollectionNew.contains(materialsCollectionOldMaterials)) {
                    materialsCollectionOldMaterials.getOperationsCollection().remove(operations);
                    materialsCollectionOldMaterials = em.merge(materialsCollectionOldMaterials);
                }
            }
            for (Materials materialsCollectionNewMaterials : materialsCollectionNew) {
                if (!materialsCollectionOld.contains(materialsCollectionNewMaterials)) {
                    materialsCollectionNewMaterials.getOperationsCollection().add(operations);
                    materialsCollectionNewMaterials = em.merge(materialsCollectionNewMaterials);
                }
            }
            for (Actions actionsCollectionNewActions : actionsCollectionNew) {
                if (!actionsCollectionOld.contains(actionsCollectionNewActions)) {
                    Operations oldOperationsOfActionsCollectionNewActions = actionsCollectionNewActions.getOperations();
                    actionsCollectionNewActions.setOperations(operations);
                    actionsCollectionNewActions = em.merge(actionsCollectionNewActions);
                    if (oldOperationsOfActionsCollectionNewActions != null && !oldOperationsOfActionsCollectionNewActions.equals(operations)) {
                        oldOperationsOfActionsCollectionNewActions.getActionsCollection().remove(actionsCollectionNewActions);
                        oldOperationsOfActionsCollectionNewActions = em.merge(oldOperationsOfActionsCollectionNewActions);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Actions> actionsCollectionOrphanCheck = operations.getActionsCollection();
            for (Actions actionsCollectionOrphanCheckActions : actionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Operations (" + operations + ") cannot be destroyed since the Actions " + actionsCollectionOrphanCheckActions + " in its actionsCollection field has a non-nullable operations field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tools> toolsCollection = operations.getToolsCollection();
            for (Tools toolsCollectionTools : toolsCollection) {
                toolsCollectionTools.getOperationsCollection().remove(operations);
                toolsCollectionTools = em.merge(toolsCollectionTools);
            }
            Collection<Materials> materialsCollection = operations.getMaterialsCollection();
            for (Materials materialsCollectionMaterials : materialsCollection) {
                materialsCollectionMaterials.getOperationsCollection().remove(operations);
                materialsCollectionMaterials = em.merge(materialsCollectionMaterials);
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
