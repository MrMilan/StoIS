/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Tools;
import entity.Usedtools;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class UsedtoolsJpaController implements Serializable {

    public UsedtoolsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usedtools usedtools) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tools toolsToolid = usedtools.getToolsToolid();
            if (toolsToolid != null) {
                toolsToolid = em.getReference(toolsToolid.getClass(), toolsToolid.getToolid());
                usedtools.setToolsToolid(toolsToolid);
            }
            em.persist(usedtools);
            if (toolsToolid != null) {
                toolsToolid.getUsedtoolsCollection().add(usedtools);
                toolsToolid = em.merge(toolsToolid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsedtools(usedtools.getIdusedtool()) != null) {
                throw new PreexistingEntityException("Usedtools " + usedtools + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usedtools usedtools) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usedtools persistentUsedtools = em.find(Usedtools.class, usedtools.getIdusedtool());
            Tools toolsToolidOld = persistentUsedtools.getToolsToolid();
            Tools toolsToolidNew = usedtools.getToolsToolid();
            if (toolsToolidNew != null) {
                toolsToolidNew = em.getReference(toolsToolidNew.getClass(), toolsToolidNew.getToolid());
                usedtools.setToolsToolid(toolsToolidNew);
            }
            usedtools = em.merge(usedtools);
            if (toolsToolidOld != null && !toolsToolidOld.equals(toolsToolidNew)) {
                toolsToolidOld.getUsedtoolsCollection().remove(usedtools);
                toolsToolidOld = em.merge(toolsToolidOld);
            }
            if (toolsToolidNew != null && !toolsToolidNew.equals(toolsToolidOld)) {
                toolsToolidNew.getUsedtoolsCollection().add(usedtools);
                toolsToolidNew = em.merge(toolsToolidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usedtools.getIdusedtool();
                if (findUsedtools(id) == null) {
                    throw new NonexistentEntityException("The usedtools with id " + id + " no longer exists.");
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
            Usedtools usedtools;
            try {
                usedtools = em.getReference(Usedtools.class, id);
                usedtools.getIdusedtool();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usedtools with id " + id + " no longer exists.", enfe);
            }
            Tools toolsToolid = usedtools.getToolsToolid();
            if (toolsToolid != null) {
                toolsToolid.getUsedtoolsCollection().remove(usedtools);
                toolsToolid = em.merge(toolsToolid);
            }
            em.remove(usedtools);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usedtools> findUsedtoolsEntities() {
        return findUsedtoolsEntities(true, -1, -1);
    }

    public List<Usedtools> findUsedtoolsEntities(int maxResults, int firstResult) {
        return findUsedtoolsEntities(false, maxResults, firstResult);
    }

    private List<Usedtools> findUsedtoolsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usedtools.class));
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

    public Usedtools findUsedtools(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usedtools.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsedtoolsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usedtools> rt = cq.from(Usedtools.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
