/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Persons;
import entity.Telephones;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class TelephonesJpaController implements Serializable {

    public TelephonesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Telephones telephones) {
        if (telephones.getPersonsCollection() == null) {
            telephones.setPersonsCollection(new ArrayList<Persons>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persons> attachedPersonsCollection = new ArrayList<Persons>();
            for (Persons personsCollectionPersonsToAttach : telephones.getPersonsCollection()) {
                personsCollectionPersonsToAttach = em.getReference(personsCollectionPersonsToAttach.getClass(), personsCollectionPersonsToAttach.getPersonsPK());
                attachedPersonsCollection.add(personsCollectionPersonsToAttach);
            }
            telephones.setPersonsCollection(attachedPersonsCollection);
            em.persist(telephones);
            for (Persons personsCollectionPersons : telephones.getPersonsCollection()) {
                personsCollectionPersons.getTelephonesCollection().add(telephones);
                personsCollectionPersons = em.merge(personsCollectionPersons);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Telephones telephones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Telephones persistentTelephones = em.find(Telephones.class, telephones.getTelephoneid());
            Collection<Persons> personsCollectionOld = persistentTelephones.getPersonsCollection();
            Collection<Persons> personsCollectionNew = telephones.getPersonsCollection();
            Collection<Persons> attachedPersonsCollectionNew = new ArrayList<Persons>();
            for (Persons personsCollectionNewPersonsToAttach : personsCollectionNew) {
                personsCollectionNewPersonsToAttach = em.getReference(personsCollectionNewPersonsToAttach.getClass(), personsCollectionNewPersonsToAttach.getPersonsPK());
                attachedPersonsCollectionNew.add(personsCollectionNewPersonsToAttach);
            }
            personsCollectionNew = attachedPersonsCollectionNew;
            telephones.setPersonsCollection(personsCollectionNew);
            telephones = em.merge(telephones);
            for (Persons personsCollectionOldPersons : personsCollectionOld) {
                if (!personsCollectionNew.contains(personsCollectionOldPersons)) {
                    personsCollectionOldPersons.getTelephonesCollection().remove(telephones);
                    personsCollectionOldPersons = em.merge(personsCollectionOldPersons);
                }
            }
            for (Persons personsCollectionNewPersons : personsCollectionNew) {
                if (!personsCollectionOld.contains(personsCollectionNewPersons)) {
                    personsCollectionNewPersons.getTelephonesCollection().add(telephones);
                    personsCollectionNewPersons = em.merge(personsCollectionNewPersons);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = telephones.getTelephoneid();
                if (findTelephones(id) == null) {
                    throw new NonexistentEntityException("The telephones with id " + id + " no longer exists.");
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
            Telephones telephones;
            try {
                telephones = em.getReference(Telephones.class, id);
                telephones.getTelephoneid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The telephones with id " + id + " no longer exists.", enfe);
            }
            Collection<Persons> personsCollection = telephones.getPersonsCollection();
            for (Persons personsCollectionPersons : personsCollection) {
                personsCollectionPersons.getTelephonesCollection().remove(telephones);
                personsCollectionPersons = em.merge(personsCollectionPersons);
            }
            em.remove(telephones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Telephones> findTelephonesEntities() {
        return findTelephonesEntities(true, -1, -1);
    }

    public List<Telephones> findTelephonesEntities(int maxResults, int firstResult) {
        return findTelephonesEntities(false, maxResults, firstResult);
    }

    private List<Telephones> findTelephonesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Telephones.class));
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

    public Telephones findTelephones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Telephones.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelephonesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Telephones> rt = cq.from(Telephones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
