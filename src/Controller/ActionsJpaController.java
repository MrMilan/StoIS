/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import entity.Actions;
import entity.ActionsPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Operations;
import entity.Persons;
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

    public void create(Actions actions) throws PreexistingEntityException, Exception {
        if (actions.getActionsPK() == null) {
            actions.setActionsPK(new ActionsPK());
        }
        actions.getActionsPK().setPersonsInsurancesInsuranceid(actions.getPersons().getPersonsPK().getInsurancesInsuranceid());
        actions.getActionsPK().setPersonsUsersUsersid(actions.getPersons().getPersonsPK().getUsersUsersid());
        actions.getActionsPK().setPersonsAddressesAddressid(actions.getPersons().getPersonsPK().getAddressesAddressid());
        actions.getActionsPK().setReportsDiagnosisDiagnoseid(actions.getReports().getReportsPK().getDiagnosisDiagnoseid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operations operations = actions.getOperations();
            if (operations != null) {
                operations = em.getReference(operations.getClass(), operations.getOperationsid());
                actions.setOperations(operations);
            }
            Persons persons = actions.getPersons();
            if (persons != null) {
                persons = em.getReference(persons.getClass(), persons.getPersonsPK());
                actions.setPersons(persons);
            }
            Reports reports = actions.getReports();
            if (reports != null) {
                reports = em.getReference(reports.getClass(), reports.getReportsPK());
                actions.setReports(reports);
            }
            em.persist(actions);
            if (operations != null) {
                operations.getActionsCollection().add(actions);
                operations = em.merge(operations);
            }
            if (persons != null) {
                persons.getActionsCollection().add(actions);
                persons = em.merge(persons);
            }
            if (reports != null) {
                reports.getActionsCollection().add(actions);
                reports = em.merge(reports);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findActions(actions.getActionsPK()) != null) {
                throw new PreexistingEntityException("Actions " + actions + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actions actions) throws NonexistentEntityException, Exception {
        actions.getActionsPK().setPersonsInsurancesInsuranceid(actions.getPersons().getPersonsPK().getInsurancesInsuranceid());
        actions.getActionsPK().setPersonsUsersUsersid(actions.getPersons().getPersonsPK().getUsersUsersid());
        actions.getActionsPK().setPersonsAddressesAddressid(actions.getPersons().getPersonsPK().getAddressesAddressid());
        actions.getActionsPK().setReportsDiagnosisDiagnoseid(actions.getReports().getReportsPK().getDiagnosisDiagnoseid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actions persistentActions = em.find(Actions.class, actions.getActionsPK());
            Operations operationsOld = persistentActions.getOperations();
            Operations operationsNew = actions.getOperations();
            Persons personsOld = persistentActions.getPersons();
            Persons personsNew = actions.getPersons();
            Reports reportsOld = persistentActions.getReports();
            Reports reportsNew = actions.getReports();
            if (operationsNew != null) {
                operationsNew = em.getReference(operationsNew.getClass(), operationsNew.getOperationsid());
                actions.setOperations(operationsNew);
            }
            if (personsNew != null) {
                personsNew = em.getReference(personsNew.getClass(), personsNew.getPersonsPK());
                actions.setPersons(personsNew);
            }
            if (reportsNew != null) {
                reportsNew = em.getReference(reportsNew.getClass(), reportsNew.getReportsPK());
                actions.setReports(reportsNew);
            }
            actions = em.merge(actions);
            if (operationsOld != null && !operationsOld.equals(operationsNew)) {
                operationsOld.getActionsCollection().remove(actions);
                operationsOld = em.merge(operationsOld);
            }
            if (operationsNew != null && !operationsNew.equals(operationsOld)) {
                operationsNew.getActionsCollection().add(actions);
                operationsNew = em.merge(operationsNew);
            }
            if (personsOld != null && !personsOld.equals(personsNew)) {
                personsOld.getActionsCollection().remove(actions);
                personsOld = em.merge(personsOld);
            }
            if (personsNew != null && !personsNew.equals(personsOld)) {
                personsNew.getActionsCollection().add(actions);
                personsNew = em.merge(personsNew);
            }
            if (reportsOld != null && !reportsOld.equals(reportsNew)) {
                reportsOld.getActionsCollection().remove(actions);
                reportsOld = em.merge(reportsOld);
            }
            if (reportsNew != null && !reportsNew.equals(reportsOld)) {
                reportsNew.getActionsCollection().add(actions);
                reportsNew = em.merge(reportsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ActionsPK id = actions.getActionsPK();
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

    public void destroy(ActionsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actions actions;
            try {
                actions = em.getReference(Actions.class, id);
                actions.getActionsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actions with id " + id + " no longer exists.", enfe);
            }
            Operations operations = actions.getOperations();
            if (operations != null) {
                operations.getActionsCollection().remove(actions);
                operations = em.merge(operations);
            }
            Persons persons = actions.getPersons();
            if (persons != null) {
                persons.getActionsCollection().remove(actions);
                persons = em.merge(persons);
            }
            Reports reports = actions.getReports();
            if (reports != null) {
                reports.getActionsCollection().remove(actions);
                reports = em.merge(reports);
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

    public Actions findActions(ActionsPK id) {
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
