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
import entity.Operationscodes;
import entity.Usedtools;
import java.util.ArrayList;
import java.util.Collection;
import entity.Usedmaterials;
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
        if (operations.getUsedtoolsCollection() == null) {
            operations.setUsedtoolsCollection(new ArrayList<Usedtools>());
        }
        if (operations.getUsedmaterialsCollection() == null) {
            operations.setUsedmaterialsCollection(new ArrayList<Usedmaterials>());
        }
        if (operations.getActionsCollection() == null) {
            operations.setActionsCollection(new ArrayList<Actions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operationscodes operationscodesIdoperationscodes = operations.getOperationscodesIdoperationscodes();
            if (operationscodesIdoperationscodes != null) {
                operationscodesIdoperationscodes = em.getReference(operationscodesIdoperationscodes.getClass(), operationscodesIdoperationscodes.getIdoperationscodes());
                operations.setOperationscodesIdoperationscodes(operationscodesIdoperationscodes);
            }
            Collection<Usedtools> attachedUsedtoolsCollection = new ArrayList<Usedtools>();
            for (Usedtools usedtoolsCollectionUsedtoolsToAttach : operations.getUsedtoolsCollection()) {
                usedtoolsCollectionUsedtoolsToAttach = em.getReference(usedtoolsCollectionUsedtoolsToAttach.getClass(), usedtoolsCollectionUsedtoolsToAttach.getIdusedtool());
                attachedUsedtoolsCollection.add(usedtoolsCollectionUsedtoolsToAttach);
            }
            operations.setUsedtoolsCollection(attachedUsedtoolsCollection);
            Collection<Usedmaterials> attachedUsedmaterialsCollection = new ArrayList<Usedmaterials>();
            for (Usedmaterials usedmaterialsCollectionUsedmaterialsToAttach : operations.getUsedmaterialsCollection()) {
                usedmaterialsCollectionUsedmaterialsToAttach = em.getReference(usedmaterialsCollectionUsedmaterialsToAttach.getClass(), usedmaterialsCollectionUsedmaterialsToAttach.getIdusedmateria());
                attachedUsedmaterialsCollection.add(usedmaterialsCollectionUsedmaterialsToAttach);
            }
            operations.setUsedmaterialsCollection(attachedUsedmaterialsCollection);
            Collection<Actions> attachedActionsCollection = new ArrayList<Actions>();
            for (Actions actionsCollectionActionsToAttach : operations.getActionsCollection()) {
                actionsCollectionActionsToAttach = em.getReference(actionsCollectionActionsToAttach.getClass(), actionsCollectionActionsToAttach.getActionid());
                attachedActionsCollection.add(actionsCollectionActionsToAttach);
            }
            operations.setActionsCollection(attachedActionsCollection);
            em.persist(operations);
            if (operationscodesIdoperationscodes != null) {
                operationscodesIdoperationscodes.getOperationsCollection().add(operations);
                operationscodesIdoperationscodes = em.merge(operationscodesIdoperationscodes);
            }
            for (Usedtools usedtoolsCollectionUsedtools : operations.getUsedtoolsCollection()) {
                Operations oldOperationsOperationsidOfUsedtoolsCollectionUsedtools = usedtoolsCollectionUsedtools.getOperationsOperationsid();
                usedtoolsCollectionUsedtools.setOperationsOperationsid(operations);
                usedtoolsCollectionUsedtools = em.merge(usedtoolsCollectionUsedtools);
                if (oldOperationsOperationsidOfUsedtoolsCollectionUsedtools != null) {
                    oldOperationsOperationsidOfUsedtoolsCollectionUsedtools.getUsedtoolsCollection().remove(usedtoolsCollectionUsedtools);
                    oldOperationsOperationsidOfUsedtoolsCollectionUsedtools = em.merge(oldOperationsOperationsidOfUsedtoolsCollectionUsedtools);
                }
            }
            for (Usedmaterials usedmaterialsCollectionUsedmaterials : operations.getUsedmaterialsCollection()) {
                Operations oldOperationsOperationsidOfUsedmaterialsCollectionUsedmaterials = usedmaterialsCollectionUsedmaterials.getOperationsOperationsid();
                usedmaterialsCollectionUsedmaterials.setOperationsOperationsid(operations);
                usedmaterialsCollectionUsedmaterials = em.merge(usedmaterialsCollectionUsedmaterials);
                if (oldOperationsOperationsidOfUsedmaterialsCollectionUsedmaterials != null) {
                    oldOperationsOperationsidOfUsedmaterialsCollectionUsedmaterials.getUsedmaterialsCollection().remove(usedmaterialsCollectionUsedmaterials);
                    oldOperationsOperationsidOfUsedmaterialsCollectionUsedmaterials = em.merge(oldOperationsOperationsidOfUsedmaterialsCollectionUsedmaterials);
                }
            }
            for (Actions actionsCollectionActions : operations.getActionsCollection()) {
                Operations oldOperationsOperationsidOfActionsCollectionActions = actionsCollectionActions.getOperationsOperationsid();
                actionsCollectionActions.setOperationsOperationsid(operations);
                actionsCollectionActions = em.merge(actionsCollectionActions);
                if (oldOperationsOperationsidOfActionsCollectionActions != null) {
                    oldOperationsOperationsidOfActionsCollectionActions.getActionsCollection().remove(actionsCollectionActions);
                    oldOperationsOperationsidOfActionsCollectionActions = em.merge(oldOperationsOperationsidOfActionsCollectionActions);
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
            Operationscodes operationscodesIdoperationscodesOld = persistentOperations.getOperationscodesIdoperationscodes();
            Operationscodes operationscodesIdoperationscodesNew = operations.getOperationscodesIdoperationscodes();
            Collection<Usedtools> usedtoolsCollectionOld = persistentOperations.getUsedtoolsCollection();
            Collection<Usedtools> usedtoolsCollectionNew = operations.getUsedtoolsCollection();
            Collection<Usedmaterials> usedmaterialsCollectionOld = persistentOperations.getUsedmaterialsCollection();
            Collection<Usedmaterials> usedmaterialsCollectionNew = operations.getUsedmaterialsCollection();
            Collection<Actions> actionsCollectionOld = persistentOperations.getActionsCollection();
            Collection<Actions> actionsCollectionNew = operations.getActionsCollection();
            List<String> illegalOrphanMessages = null;
            for (Usedtools usedtoolsCollectionOldUsedtools : usedtoolsCollectionOld) {
                if (!usedtoolsCollectionNew.contains(usedtoolsCollectionOldUsedtools)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usedtools " + usedtoolsCollectionOldUsedtools + " since its operationsOperationsid field is not nullable.");
                }
            }
            for (Usedmaterials usedmaterialsCollectionOldUsedmaterials : usedmaterialsCollectionOld) {
                if (!usedmaterialsCollectionNew.contains(usedmaterialsCollectionOldUsedmaterials)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usedmaterials " + usedmaterialsCollectionOldUsedmaterials + " since its operationsOperationsid field is not nullable.");
                }
            }
            for (Actions actionsCollectionOldActions : actionsCollectionOld) {
                if (!actionsCollectionNew.contains(actionsCollectionOldActions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actions " + actionsCollectionOldActions + " since its operationsOperationsid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (operationscodesIdoperationscodesNew != null) {
                operationscodesIdoperationscodesNew = em.getReference(operationscodesIdoperationscodesNew.getClass(), operationscodesIdoperationscodesNew.getIdoperationscodes());
                operations.setOperationscodesIdoperationscodes(operationscodesIdoperationscodesNew);
            }
            Collection<Usedtools> attachedUsedtoolsCollectionNew = new ArrayList<Usedtools>();
            for (Usedtools usedtoolsCollectionNewUsedtoolsToAttach : usedtoolsCollectionNew) {
                usedtoolsCollectionNewUsedtoolsToAttach = em.getReference(usedtoolsCollectionNewUsedtoolsToAttach.getClass(), usedtoolsCollectionNewUsedtoolsToAttach.getIdusedtool());
                attachedUsedtoolsCollectionNew.add(usedtoolsCollectionNewUsedtoolsToAttach);
            }
            usedtoolsCollectionNew = attachedUsedtoolsCollectionNew;
            operations.setUsedtoolsCollection(usedtoolsCollectionNew);
            Collection<Usedmaterials> attachedUsedmaterialsCollectionNew = new ArrayList<Usedmaterials>();
            for (Usedmaterials usedmaterialsCollectionNewUsedmaterialsToAttach : usedmaterialsCollectionNew) {
                usedmaterialsCollectionNewUsedmaterialsToAttach = em.getReference(usedmaterialsCollectionNewUsedmaterialsToAttach.getClass(), usedmaterialsCollectionNewUsedmaterialsToAttach.getIdusedmateria());
                attachedUsedmaterialsCollectionNew.add(usedmaterialsCollectionNewUsedmaterialsToAttach);
            }
            usedmaterialsCollectionNew = attachedUsedmaterialsCollectionNew;
            operations.setUsedmaterialsCollection(usedmaterialsCollectionNew);
            Collection<Actions> attachedActionsCollectionNew = new ArrayList<Actions>();
            for (Actions actionsCollectionNewActionsToAttach : actionsCollectionNew) {
                actionsCollectionNewActionsToAttach = em.getReference(actionsCollectionNewActionsToAttach.getClass(), actionsCollectionNewActionsToAttach.getActionid());
                attachedActionsCollectionNew.add(actionsCollectionNewActionsToAttach);
            }
            actionsCollectionNew = attachedActionsCollectionNew;
            operations.setActionsCollection(actionsCollectionNew);
            operations = em.merge(operations);
            if (operationscodesIdoperationscodesOld != null && !operationscodesIdoperationscodesOld.equals(operationscodesIdoperationscodesNew)) {
                operationscodesIdoperationscodesOld.getOperationsCollection().remove(operations);
                operationscodesIdoperationscodesOld = em.merge(operationscodesIdoperationscodesOld);
            }
            if (operationscodesIdoperationscodesNew != null && !operationscodesIdoperationscodesNew.equals(operationscodesIdoperationscodesOld)) {
                operationscodesIdoperationscodesNew.getOperationsCollection().add(operations);
                operationscodesIdoperationscodesNew = em.merge(operationscodesIdoperationscodesNew);
            }
            for (Usedtools usedtoolsCollectionNewUsedtools : usedtoolsCollectionNew) {
                if (!usedtoolsCollectionOld.contains(usedtoolsCollectionNewUsedtools)) {
                    Operations oldOperationsOperationsidOfUsedtoolsCollectionNewUsedtools = usedtoolsCollectionNewUsedtools.getOperationsOperationsid();
                    usedtoolsCollectionNewUsedtools.setOperationsOperationsid(operations);
                    usedtoolsCollectionNewUsedtools = em.merge(usedtoolsCollectionNewUsedtools);
                    if (oldOperationsOperationsidOfUsedtoolsCollectionNewUsedtools != null && !oldOperationsOperationsidOfUsedtoolsCollectionNewUsedtools.equals(operations)) {
                        oldOperationsOperationsidOfUsedtoolsCollectionNewUsedtools.getUsedtoolsCollection().remove(usedtoolsCollectionNewUsedtools);
                        oldOperationsOperationsidOfUsedtoolsCollectionNewUsedtools = em.merge(oldOperationsOperationsidOfUsedtoolsCollectionNewUsedtools);
                    }
                }
            }
            for (Usedmaterials usedmaterialsCollectionNewUsedmaterials : usedmaterialsCollectionNew) {
                if (!usedmaterialsCollectionOld.contains(usedmaterialsCollectionNewUsedmaterials)) {
                    Operations oldOperationsOperationsidOfUsedmaterialsCollectionNewUsedmaterials = usedmaterialsCollectionNewUsedmaterials.getOperationsOperationsid();
                    usedmaterialsCollectionNewUsedmaterials.setOperationsOperationsid(operations);
                    usedmaterialsCollectionNewUsedmaterials = em.merge(usedmaterialsCollectionNewUsedmaterials);
                    if (oldOperationsOperationsidOfUsedmaterialsCollectionNewUsedmaterials != null && !oldOperationsOperationsidOfUsedmaterialsCollectionNewUsedmaterials.equals(operations)) {
                        oldOperationsOperationsidOfUsedmaterialsCollectionNewUsedmaterials.getUsedmaterialsCollection().remove(usedmaterialsCollectionNewUsedmaterials);
                        oldOperationsOperationsidOfUsedmaterialsCollectionNewUsedmaterials = em.merge(oldOperationsOperationsidOfUsedmaterialsCollectionNewUsedmaterials);
                    }
                }
            }
            for (Actions actionsCollectionNewActions : actionsCollectionNew) {
                if (!actionsCollectionOld.contains(actionsCollectionNewActions)) {
                    Operations oldOperationsOperationsidOfActionsCollectionNewActions = actionsCollectionNewActions.getOperationsOperationsid();
                    actionsCollectionNewActions.setOperationsOperationsid(operations);
                    actionsCollectionNewActions = em.merge(actionsCollectionNewActions);
                    if (oldOperationsOperationsidOfActionsCollectionNewActions != null && !oldOperationsOperationsidOfActionsCollectionNewActions.equals(operations)) {
                        oldOperationsOperationsidOfActionsCollectionNewActions.getActionsCollection().remove(actionsCollectionNewActions);
                        oldOperationsOperationsidOfActionsCollectionNewActions = em.merge(oldOperationsOperationsidOfActionsCollectionNewActions);
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
            Collection<Usedtools> usedtoolsCollectionOrphanCheck = operations.getUsedtoolsCollection();
            for (Usedtools usedtoolsCollectionOrphanCheckUsedtools : usedtoolsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Operations (" + operations + ") cannot be destroyed since the Usedtools " + usedtoolsCollectionOrphanCheckUsedtools + " in its usedtoolsCollection field has a non-nullable operationsOperationsid field.");
            }
            Collection<Usedmaterials> usedmaterialsCollectionOrphanCheck = operations.getUsedmaterialsCollection();
            for (Usedmaterials usedmaterialsCollectionOrphanCheckUsedmaterials : usedmaterialsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Operations (" + operations + ") cannot be destroyed since the Usedmaterials " + usedmaterialsCollectionOrphanCheckUsedmaterials + " in its usedmaterialsCollection field has a non-nullable operationsOperationsid field.");
            }
            Collection<Actions> actionsCollectionOrphanCheck = operations.getActionsCollection();
            for (Actions actionsCollectionOrphanCheckActions : actionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Operations (" + operations + ") cannot be destroyed since the Actions " + actionsCollectionOrphanCheckActions + " in its actionsCollection field has a non-nullable operationsOperationsid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
