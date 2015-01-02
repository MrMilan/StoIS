/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import entity.Insurances;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Persons;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class InsurancesJpaController implements Serializable {

    public InsurancesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Insurances insurances) {
        if (insurances.getPersonsCollection() == null) {
            insurances.setPersonsCollection(new ArrayList<Persons>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persons> attachedPersonsCollection = new ArrayList<Persons>();
            for (Persons personsCollectionPersonsToAttach : insurances.getPersonsCollection()) {
                personsCollectionPersonsToAttach = em.getReference(personsCollectionPersonsToAttach.getClass(), personsCollectionPersonsToAttach.getPersonid());
                attachedPersonsCollection.add(personsCollectionPersonsToAttach);
            }
            insurances.setPersonsCollection(attachedPersonsCollection);
            em.persist(insurances);
            for (Persons personsCollectionPersons : insurances.getPersonsCollection()) {
                Insurances oldInsurancesInsuranceidOfPersonsCollectionPersons = personsCollectionPersons.getInsurancesInsuranceid();
                personsCollectionPersons.setInsurancesInsuranceid(insurances);
                personsCollectionPersons = em.merge(personsCollectionPersons);
                if (oldInsurancesInsuranceidOfPersonsCollectionPersons != null) {
                    oldInsurancesInsuranceidOfPersonsCollectionPersons.getPersonsCollection().remove(personsCollectionPersons);
                    oldInsurancesInsuranceidOfPersonsCollectionPersons = em.merge(oldInsurancesInsuranceidOfPersonsCollectionPersons);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Insurances insurances) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insurances persistentInsurances = em.find(Insurances.class, insurances.getInsuranceid());
            Collection<Persons> personsCollectionOld = persistentInsurances.getPersonsCollection();
            Collection<Persons> personsCollectionNew = insurances.getPersonsCollection();
            List<String> illegalOrphanMessages = null;
            for (Persons personsCollectionOldPersons : personsCollectionOld) {
                if (!personsCollectionNew.contains(personsCollectionOldPersons)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persons " + personsCollectionOldPersons + " since its insurancesInsuranceid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Persons> attachedPersonsCollectionNew = new ArrayList<Persons>();
            for (Persons personsCollectionNewPersonsToAttach : personsCollectionNew) {
                personsCollectionNewPersonsToAttach = em.getReference(personsCollectionNewPersonsToAttach.getClass(), personsCollectionNewPersonsToAttach.getPersonid());
                attachedPersonsCollectionNew.add(personsCollectionNewPersonsToAttach);
            }
            personsCollectionNew = attachedPersonsCollectionNew;
            insurances.setPersonsCollection(personsCollectionNew);
            insurances = em.merge(insurances);
            for (Persons personsCollectionNewPersons : personsCollectionNew) {
                if (!personsCollectionOld.contains(personsCollectionNewPersons)) {
                    Insurances oldInsurancesInsuranceidOfPersonsCollectionNewPersons = personsCollectionNewPersons.getInsurancesInsuranceid();
                    personsCollectionNewPersons.setInsurancesInsuranceid(insurances);
                    personsCollectionNewPersons = em.merge(personsCollectionNewPersons);
                    if (oldInsurancesInsuranceidOfPersonsCollectionNewPersons != null && !oldInsurancesInsuranceidOfPersonsCollectionNewPersons.equals(insurances)) {
                        oldInsurancesInsuranceidOfPersonsCollectionNewPersons.getPersonsCollection().remove(personsCollectionNewPersons);
                        oldInsurancesInsuranceidOfPersonsCollectionNewPersons = em.merge(oldInsurancesInsuranceidOfPersonsCollectionNewPersons);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = insurances.getInsuranceid();
                if (findInsurances(id) == null) {
                    throw new NonexistentEntityException("The insurances with id " + id + " no longer exists.");
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
            Insurances insurances;
            try {
                insurances = em.getReference(Insurances.class, id);
                insurances.getInsuranceid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insurances with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Persons> personsCollectionOrphanCheck = insurances.getPersonsCollection();
            for (Persons personsCollectionOrphanCheckPersons : personsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Insurances (" + insurances + ") cannot be destroyed since the Persons " + personsCollectionOrphanCheckPersons + " in its personsCollection field has a non-nullable insurancesInsuranceid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(insurances);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Insurances> findInsurancesEntities() {
        return findInsurancesEntities(true, -1, -1);
    }

    public List<Insurances> findInsurancesEntities(int maxResults, int firstResult) {
        return findInsurancesEntities(false, maxResults, firstResult);
    }

    private List<Insurances> findInsurancesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Insurances.class));
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

    public Insurances findInsurances(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Insurances.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsurancesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Insurances> rt = cq.from(Insurances.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
