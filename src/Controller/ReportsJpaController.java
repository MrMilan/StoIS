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
import entity.Actions;
import entity.Reports;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class ReportsJpaController implements Serializable {

    public ReportsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reports reports) {
        if (reports.getActionsCollection() == null) {
            reports.setActionsCollection(new ArrayList<Actions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Actions> attachedActionsCollection = new ArrayList<Actions>();
            for (Actions actionsCollectionActionsToAttach : reports.getActionsCollection()) {
                actionsCollectionActionsToAttach = em.getReference(actionsCollectionActionsToAttach.getClass(), actionsCollectionActionsToAttach.getActionid());
                attachedActionsCollection.add(actionsCollectionActionsToAttach);
            }
            reports.setActionsCollection(attachedActionsCollection);
            em.persist(reports);
            for (Actions actionsCollectionActions : reports.getActionsCollection()) {
                Reports oldReportsReportidOfActionsCollectionActions = actionsCollectionActions.getReportsReportid();
                actionsCollectionActions.setReportsReportid(reports);
                actionsCollectionActions = em.merge(actionsCollectionActions);
                if (oldReportsReportidOfActionsCollectionActions != null) {
                    oldReportsReportidOfActionsCollectionActions.getActionsCollection().remove(actionsCollectionActions);
                    oldReportsReportidOfActionsCollectionActions = em.merge(oldReportsReportidOfActionsCollectionActions);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reports reports) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reports persistentReports = em.find(Reports.class, reports.getReportid());
            Collection<Actions> actionsCollectionOld = persistentReports.getActionsCollection();
            Collection<Actions> actionsCollectionNew = reports.getActionsCollection();
            List<String> illegalOrphanMessages = null;
            for (Actions actionsCollectionOldActions : actionsCollectionOld) {
                if (!actionsCollectionNew.contains(actionsCollectionOldActions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actions " + actionsCollectionOldActions + " since its reportsReportid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Actions> attachedActionsCollectionNew = new ArrayList<Actions>();
            for (Actions actionsCollectionNewActionsToAttach : actionsCollectionNew) {
                actionsCollectionNewActionsToAttach = em.getReference(actionsCollectionNewActionsToAttach.getClass(), actionsCollectionNewActionsToAttach.getActionid());
                attachedActionsCollectionNew.add(actionsCollectionNewActionsToAttach);
            }
            actionsCollectionNew = attachedActionsCollectionNew;
            reports.setActionsCollection(actionsCollectionNew);
            reports = em.merge(reports);
            for (Actions actionsCollectionNewActions : actionsCollectionNew) {
                if (!actionsCollectionOld.contains(actionsCollectionNewActions)) {
                    Reports oldReportsReportidOfActionsCollectionNewActions = actionsCollectionNewActions.getReportsReportid();
                    actionsCollectionNewActions.setReportsReportid(reports);
                    actionsCollectionNewActions = em.merge(actionsCollectionNewActions);
                    if (oldReportsReportidOfActionsCollectionNewActions != null && !oldReportsReportidOfActionsCollectionNewActions.equals(reports)) {
                        oldReportsReportidOfActionsCollectionNewActions.getActionsCollection().remove(actionsCollectionNewActions);
                        oldReportsReportidOfActionsCollectionNewActions = em.merge(oldReportsReportidOfActionsCollectionNewActions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reports.getReportid();
                if (findReports(id) == null) {
                    throw new NonexistentEntityException("The reports with id " + id + " no longer exists.");
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
            Reports reports;
            try {
                reports = em.getReference(Reports.class, id);
                reports.getReportid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reports with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Actions> actionsCollectionOrphanCheck = reports.getActionsCollection();
            for (Actions actionsCollectionOrphanCheckActions : actionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reports (" + reports + ") cannot be destroyed since the Actions " + actionsCollectionOrphanCheckActions + " in its actionsCollection field has a non-nullable reportsReportid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(reports);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reports> findReportsEntities() {
        return findReportsEntities(true, -1, -1);
    }

    public List<Reports> findReportsEntities(int maxResults, int firstResult) {
        return findReportsEntities(false, maxResults, firstResult);
    }

    private List<Reports> findReportsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reports.class));
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

    public Reports findReports(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reports.class, id);
        } finally {
            em.close();
        }
    }

    public int getReportsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reports> rt = cq.from(Reports.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
