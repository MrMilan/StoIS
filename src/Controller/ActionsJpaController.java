/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import entity.Actions;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Operations;
import entity.PersonsHasRoles;
import entity.Reports;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class ActionsJpaController implements Serializable {

    public ActionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actions actions) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operations operationsOperationsid = actions.getOperationsOperationsid();
            if (operationsOperationsid != null) {
                operationsOperationsid = em.getReference(operationsOperationsid.getClass(), operationsOperationsid.getOperationsid());
                actions.setOperationsOperationsid(operationsOperationsid);
            }
            PersonsHasRoles personsHasRolesIdphr = actions.getPersonsHasRolesIdphr();
            if (personsHasRolesIdphr != null) {
                personsHasRolesIdphr = em.getReference(personsHasRolesIdphr.getClass(), personsHasRolesIdphr.getIdphr());
                actions.setPersonsHasRolesIdphr(personsHasRolesIdphr);
            }
            Reports reportsReportid = actions.getReportsReportid();
            if (reportsReportid != null) {
                reportsReportid = em.getReference(reportsReportid.getClass(), reportsReportid.getReportid());
                actions.setReportsReportid(reportsReportid);
            }
            em.persist(actions);
            if (operationsOperationsid != null) {
                operationsOperationsid.getActionsCollection().add(actions);
                operationsOperationsid = em.merge(operationsOperationsid);
            }
            if (personsHasRolesIdphr != null) {
                personsHasRolesIdphr.getActionsCollection().add(actions);
                personsHasRolesIdphr = em.merge(personsHasRolesIdphr);
            }
            if (reportsReportid != null) {
                reportsReportid.getActionsCollection().add(actions);
                reportsReportid = em.merge(reportsReportid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actions actions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actions persistentActions = em.find(Actions.class, actions.getActionid());
            Operations operationsOperationsidOld = persistentActions.getOperationsOperationsid();
            Operations operationsOperationsidNew = actions.getOperationsOperationsid();
            PersonsHasRoles personsHasRolesIdphrOld = persistentActions.getPersonsHasRolesIdphr();
            PersonsHasRoles personsHasRolesIdphrNew = actions.getPersonsHasRolesIdphr();
            Reports reportsReportidOld = persistentActions.getReportsReportid();
            Reports reportsReportidNew = actions.getReportsReportid();
            if (operationsOperationsidNew != null) {
                operationsOperationsidNew = em.getReference(operationsOperationsidNew.getClass(), operationsOperationsidNew.getOperationsid());
                actions.setOperationsOperationsid(operationsOperationsidNew);
            }
            if (personsHasRolesIdphrNew != null) {
                personsHasRolesIdphrNew = em.getReference(personsHasRolesIdphrNew.getClass(), personsHasRolesIdphrNew.getIdphr());
                actions.setPersonsHasRolesIdphr(personsHasRolesIdphrNew);
            }
            if (reportsReportidNew != null) {
                reportsReportidNew = em.getReference(reportsReportidNew.getClass(), reportsReportidNew.getReportid());
                actions.setReportsReportid(reportsReportidNew);
            }
            actions = em.merge(actions);
            if (operationsOperationsidOld != null && !operationsOperationsidOld.equals(operationsOperationsidNew)) {
                operationsOperationsidOld.getActionsCollection().remove(actions);
                operationsOperationsidOld = em.merge(operationsOperationsidOld);
            }
            if (operationsOperationsidNew != null && !operationsOperationsidNew.equals(operationsOperationsidOld)) {
                operationsOperationsidNew.getActionsCollection().add(actions);
                operationsOperationsidNew = em.merge(operationsOperationsidNew);
            }
            if (personsHasRolesIdphrOld != null && !personsHasRolesIdphrOld.equals(personsHasRolesIdphrNew)) {
                personsHasRolesIdphrOld.getActionsCollection().remove(actions);
                personsHasRolesIdphrOld = em.merge(personsHasRolesIdphrOld);
            }
            if (personsHasRolesIdphrNew != null && !personsHasRolesIdphrNew.equals(personsHasRolesIdphrOld)) {
                personsHasRolesIdphrNew.getActionsCollection().add(actions);
                personsHasRolesIdphrNew = em.merge(personsHasRolesIdphrNew);
            }
            if (reportsReportidOld != null && !reportsReportidOld.equals(reportsReportidNew)) {
                reportsReportidOld.getActionsCollection().remove(actions);
                reportsReportidOld = em.merge(reportsReportidOld);
            }
            if (reportsReportidNew != null && !reportsReportidNew.equals(reportsReportidOld)) {
                reportsReportidNew.getActionsCollection().add(actions);
                reportsReportidNew = em.merge(reportsReportidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actions.getActionid();
                if (findActions(id) == null) {
                    throw new NonexistentEntityException("The actions with id " + id + " no longer exists.");
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
            Actions actions;
            try {
                actions = em.getReference(Actions.class, id);
                actions.getActionid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actions with id " + id + " no longer exists.", enfe);
            }
            Operations operationsOperationsid = actions.getOperationsOperationsid();
            if (operationsOperationsid != null) {
                operationsOperationsid.getActionsCollection().remove(actions);
                operationsOperationsid = em.merge(operationsOperationsid);
            }
            PersonsHasRoles personsHasRolesIdphr = actions.getPersonsHasRolesIdphr();
            if (personsHasRolesIdphr != null) {
                personsHasRolesIdphr.getActionsCollection().remove(actions);
                personsHasRolesIdphr = em.merge(personsHasRolesIdphr);
            }
            Reports reportsReportid = actions.getReportsReportid();
            if (reportsReportid != null) {
                reportsReportid.getActionsCollection().remove(actions);
                reportsReportid = em.merge(reportsReportid);
            }
            em.remove(actions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actions> findActionsEntities() {
        return findActionsEntities(true, -1, -1);
    }

    public List<Actions> findActionsEntities(int maxResults, int firstResult) {
        return findActionsEntities(false, maxResults, firstResult);
    }

    private List<Actions> findActionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actions.class));
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

    public Actions findActions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actions.class, id);
        } finally {
            em.close();
        }
    }

    public int getActionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actions> rt = cq.from(Actions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
