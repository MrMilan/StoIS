/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Diagnosis;
import entity.Actions;
import entity.Reports;
import entity.ReportsPK;
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

    public void create(Reports reports) throws PreexistingEntityException, Exception {
        if (reports.getReportsPK() == null) {
            reports.setReportsPK(new ReportsPK());
        }
        if (reports.getActionsCollection() == null) {
            reports.setActionsCollection(new ArrayList<Actions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diagnosis diagnosis = reports.getDiagnosis();
            if (diagnosis != null) {
                diagnosis = em.getReference(diagnosis.getClass(), diagnosis.getDiagnoseid());
                reports.setDiagnosis(diagnosis);
            }
            Collection<Actions> attachedActionsCollection = new ArrayList<Actions>();
            for (Actions actionsCollectionActionsToAttach : reports.getActionsCollection()) {
                actionsCollectionActionsToAttach = em.getReference(actionsCollectionActionsToAttach.getClass(), actionsCollectionActionsToAttach.getActionsPK());
                attachedActionsCollection.add(actionsCollectionActionsToAttach);
            }
            reports.setActionsCollection(attachedActionsCollection);
            em.persist(reports);
            if (diagnosis != null) {
                diagnosis.getReportsCollection().add(reports);
                diagnosis = em.merge(diagnosis);
            }
            for (Actions actionsCollectionActions : reports.getActionsCollection()) {
                Reports oldReportsOfActionsCollectionActions = actionsCollectionActions.getReports();
                actionsCollectionActions.setReports(reports);
                actionsCollectionActions = em.merge(actionsCollectionActions);
                if (oldReportsOfActionsCollectionActions != null) {
                    oldReportsOfActionsCollectionActions.getActionsCollection().remove(actionsCollectionActions);
                    oldReportsOfActionsCollectionActions = em.merge(oldReportsOfActionsCollectionActions);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReports(reports.getReportsPK()) != null) {
                throw new PreexistingEntityException("Reports " + reports + " already exists.", ex);
            }
            throw ex;
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
            Reports persistentReports = em.find(Reports.class, reports.getReportsPK());
            Diagnosis diagnosisOld = persistentReports.getDiagnosis();
            Diagnosis diagnosisNew = reports.getDiagnosis();
            Collection<Actions> actionsCollectionOld = persistentReports.getActionsCollection();
            Collection<Actions> actionsCollectionNew = reports.getActionsCollection();
            List<String> illegalOrphanMessages = null;
            for (Actions actionsCollectionOldActions : actionsCollectionOld) {
                if (!actionsCollectionNew.contains(actionsCollectionOldActions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actions " + actionsCollectionOldActions + " since its reports field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (diagnosisNew != null) {
                diagnosisNew = em.getReference(diagnosisNew.getClass(), diagnosisNew.getDiagnoseid());
                reports.setDiagnosis(diagnosisNew);
            }
            Collection<Actions> attachedActionsCollectionNew = new ArrayList<Actions>();
            for (Actions actionsCollectionNewActionsToAttach : actionsCollectionNew) {
                actionsCollectionNewActionsToAttach = em.getReference(actionsCollectionNewActionsToAttach.getClass(), actionsCollectionNewActionsToAttach.getActionsPK());
                attachedActionsCollectionNew.add(actionsCollectionNewActionsToAttach);
            }
            actionsCollectionNew = attachedActionsCollectionNew;
            reports.setActionsCollection(actionsCollectionNew);
            reports = em.merge(reports);
            if (diagnosisOld != null && !diagnosisOld.equals(diagnosisNew)) {
                diagnosisOld.getReportsCollection().remove(reports);
                diagnosisOld = em.merge(diagnosisOld);
            }
            if (diagnosisNew != null && !diagnosisNew.equals(diagnosisOld)) {
                diagnosisNew.getReportsCollection().add(reports);
                diagnosisNew = em.merge(diagnosisNew);
            }
            for (Actions actionsCollectionNewActions : actionsCollectionNew) {
                if (!actionsCollectionOld.contains(actionsCollectionNewActions)) {
                    Reports oldReportsOfActionsCollectionNewActions = actionsCollectionNewActions.getReports();
                    actionsCollectionNewActions.setReports(reports);
                    actionsCollectionNewActions = em.merge(actionsCollectionNewActions);
                    if (oldReportsOfActionsCollectionNewActions != null && !oldReportsOfActionsCollectionNewActions.equals(reports)) {
                        oldReportsOfActionsCollectionNewActions.getActionsCollection().remove(actionsCollectionNewActions);
                        oldReportsOfActionsCollectionNewActions = em.merge(oldReportsOfActionsCollectionNewActions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ReportsPK id = reports.getReportsPK();
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

    public void destroy(ReportsPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reports reports;
            try {
                reports = em.getReference(Reports.class, id);
                reports.getReportsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reports with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Actions> actionsCollectionOrphanCheck = reports.getActionsCollection();
            for (Actions actionsCollectionOrphanCheckActions : actionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reports (" + reports + ") cannot be destroyed since the Actions " + actionsCollectionOrphanCheckActions + " in its actionsCollection field has a non-nullable reports field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Diagnosis diagnosis = reports.getDiagnosis();
            if (diagnosis != null) {
                diagnosis.getReportsCollection().remove(reports);
                diagnosis = em.merge(diagnosis);
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

    public Reports findReports(ReportsPK id) {
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
