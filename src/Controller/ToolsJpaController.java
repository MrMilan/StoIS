/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import entity.Tools;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Usedtools;
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
        if (tools.getUsedtoolsCollection() == null) {
            tools.setUsedtoolsCollection(new ArrayList<Usedtools>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Usedtools> attachedUsedtoolsCollection = new ArrayList<Usedtools>();
            for (Usedtools usedtoolsCollectionUsedtoolsToAttach : tools.getUsedtoolsCollection()) {
                usedtoolsCollectionUsedtoolsToAttach = em.getReference(usedtoolsCollectionUsedtoolsToAttach.getClass(), usedtoolsCollectionUsedtoolsToAttach.getIdusedtool());
                attachedUsedtoolsCollection.add(usedtoolsCollectionUsedtoolsToAttach);
            }
            tools.setUsedtoolsCollection(attachedUsedtoolsCollection);
            em.persist(tools);
            for (Usedtools usedtoolsCollectionUsedtools : tools.getUsedtoolsCollection()) {
                Tools oldToolsToolidOfUsedtoolsCollectionUsedtools = usedtoolsCollectionUsedtools.getToolsToolid();
                usedtoolsCollectionUsedtools.setToolsToolid(tools);
                usedtoolsCollectionUsedtools = em.merge(usedtoolsCollectionUsedtools);
                if (oldToolsToolidOfUsedtoolsCollectionUsedtools != null) {
                    oldToolsToolidOfUsedtoolsCollectionUsedtools.getUsedtoolsCollection().remove(usedtoolsCollectionUsedtools);
                    oldToolsToolidOfUsedtoolsCollectionUsedtools = em.merge(oldToolsToolidOfUsedtoolsCollectionUsedtools);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tools tools) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tools persistentTools = em.find(Tools.class, tools.getToolid());
            Collection<Usedtools> usedtoolsCollectionOld = persistentTools.getUsedtoolsCollection();
            Collection<Usedtools> usedtoolsCollectionNew = tools.getUsedtoolsCollection();
            List<String> illegalOrphanMessages = null;
            for (Usedtools usedtoolsCollectionOldUsedtools : usedtoolsCollectionOld) {
                if (!usedtoolsCollectionNew.contains(usedtoolsCollectionOldUsedtools)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usedtools " + usedtoolsCollectionOldUsedtools + " since its toolsToolid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Usedtools> attachedUsedtoolsCollectionNew = new ArrayList<Usedtools>();
            for (Usedtools usedtoolsCollectionNewUsedtoolsToAttach : usedtoolsCollectionNew) {
                usedtoolsCollectionNewUsedtoolsToAttach = em.getReference(usedtoolsCollectionNewUsedtoolsToAttach.getClass(), usedtoolsCollectionNewUsedtoolsToAttach.getIdusedtool());
                attachedUsedtoolsCollectionNew.add(usedtoolsCollectionNewUsedtoolsToAttach);
            }
            usedtoolsCollectionNew = attachedUsedtoolsCollectionNew;
            tools.setUsedtoolsCollection(usedtoolsCollectionNew);
            tools = em.merge(tools);
            for (Usedtools usedtoolsCollectionNewUsedtools : usedtoolsCollectionNew) {
                if (!usedtoolsCollectionOld.contains(usedtoolsCollectionNewUsedtools)) {
                    Tools oldToolsToolidOfUsedtoolsCollectionNewUsedtools = usedtoolsCollectionNewUsedtools.getToolsToolid();
                    usedtoolsCollectionNewUsedtools.setToolsToolid(tools);
                    usedtoolsCollectionNewUsedtools = em.merge(usedtoolsCollectionNewUsedtools);
                    if (oldToolsToolidOfUsedtoolsCollectionNewUsedtools != null && !oldToolsToolidOfUsedtoolsCollectionNewUsedtools.equals(tools)) {
                        oldToolsToolidOfUsedtoolsCollectionNewUsedtools.getUsedtoolsCollection().remove(usedtoolsCollectionNewUsedtools);
                        oldToolsToolidOfUsedtoolsCollectionNewUsedtools = em.merge(oldToolsToolidOfUsedtoolsCollectionNewUsedtools);
                    }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Usedtools> usedtoolsCollectionOrphanCheck = tools.getUsedtoolsCollection();
            for (Usedtools usedtoolsCollectionOrphanCheckUsedtools : usedtoolsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tools (" + tools + ") cannot be destroyed since the Usedtools " + usedtoolsCollectionOrphanCheckUsedtools + " in its usedtoolsCollection field has a non-nullable toolsToolid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
