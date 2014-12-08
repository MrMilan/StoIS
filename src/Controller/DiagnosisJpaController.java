/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import entity.Diagnosis;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class DiagnosisJpaController implements Serializable {

    public DiagnosisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diagnosis diagnosis) {
        if (diagnosis.getReportsCollection() == null) {
            diagnosis.setReportsCollection(new ArrayList<Reports>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Reports> attachedReportsCollection = new ArrayList<Reports>();
            for (Reports reportsCollectionReportsToAttach : diagnosis.getReportsCollection()) {
                reportsCollectionReportsToAttach = em.getReference(reportsCollectionReportsToAttach.getClass(), reportsCollectionReportsToAttach.getReportsPK());
                attachedReportsCollection.add(reportsCollectionReportsToAttach);
            }
            diagnosis.setReportsCollection(attachedReportsCollection);
            em.persist(diagnosis);
            for (Reports reportsCollectionReports : diagnosis.getReportsCollection()) {
                Diagnosis oldDiagnosisOfReportsCollectionReports = reportsCollectionReports.getDiagnosis();
                reportsCollectionReports.setDiagnosis(diagnosis);
                reportsCollectionReports = em.merge(reportsCollectionReports);
                if (oldDiagnosisOfReportsCollectionReports != null) {
                    oldDiagnosisOfReportsCollectionReports.getReportsCollection().remove(reportsCollectionReports);
                    oldDiagnosisOfReportsCollectionReports = em.merge(oldDiagnosisOfReportsCollectionReports);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diagnosis diagnosis) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diagnosis persistentDiagnosis = em.find(Diagnosis.class, diagnosis.getDiagnoseid());
            Collection<Reports> reportsCollectionOld = persistentDiagnosis.getReportsCollection();
            Collection<Reports> reportsCollectionNew = diagnosis.getReportsCollection();
            List<String> illegalOrphanMessages = null;
            for (Reports reportsCollectionOldReports : reportsCollectionOld) {
                if (!reportsCollectionNew.contains(reportsCollectionOldReports)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reports " + reportsCollectionOldReports + " since its diagnosis field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Reports> attachedReportsCollectionNew = new ArrayList<Reports>();
            for (Reports reportsCollectionNewReportsToAttach : reportsCollectionNew) {
                reportsCollectionNewReportsToAttach = em.getReference(reportsCollectionNewReportsToAttach.getClass(), reportsCollectionNewReportsToAttach.getReportsPK());
                attachedReportsCollectionNew.add(reportsCollectionNewReportsToAttach);
            }
            reportsCollectionNew = attachedReportsCollectionNew;
            diagnosis.setReportsCollection(reportsCollectionNew);
            diagnosis = em.merge(diagnosis);
            for (Reports reportsCollectionNewReports : reportsCollectionNew) {
                if (!reportsCollectionOld.contains(reportsCollectionNewReports)) {
                    Diagnosis oldDiagnosisOfReportsCollectionNewReports = reportsCollectionNewReports.getDiagnosis();
                    reportsCollectionNewReports.setDiagnosis(diagnosis);
                    reportsCollectionNewReports = em.merge(reportsCollectionNewReports);
                    if (oldDiagnosisOfReportsCollectionNewReports != null && !oldDiagnosisOfReportsCollectionNewReports.equals(diagnosis)) {
                        oldDiagnosisOfReportsCollectionNewReports.getReportsCollection().remove(reportsCollectionNewReports);
                        oldDiagnosisOfReportsCollectionNewReports = em.merge(oldDiagnosisOfReportsCollectionNewReports);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diagnosis.getDiagnoseid();
                if (findDiagnosis(id) == null) {
                    throw new NonexistentEntityException("The diagnosis with id " + id + " no longer exists.");
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
            Diagnosis diagnosis;
            try {
                diagnosis = em.getReference(Diagnosis.class, id);
                diagnosis.getDiagnoseid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diagnosis with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reports> reportsCollectionOrphanCheck = diagnosis.getReportsCollection();
            for (Reports reportsCollectionOrphanCheckReports : reportsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Diagnosis (" + diagnosis + ") cannot be destroyed since the Reports " + reportsCollectionOrphanCheckReports + " in its reportsCollection field has a non-nullable diagnosis field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(diagnosis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diagnosis> findDiagnosisEntities() {
        return findDiagnosisEntities(true, -1, -1);
    }

    public List<Diagnosis> findDiagnosisEntities(int maxResults, int firstResult) {
        return findDiagnosisEntities(false, maxResults, firstResult);
    }

    private List<Diagnosis> findDiagnosisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diagnosis.class));
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

    public Diagnosis findDiagnosis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diagnosis.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiagnosisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diagnosis> rt = cq.from(Diagnosis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
