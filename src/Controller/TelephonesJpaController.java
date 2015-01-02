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
import entity.PersonsHasTelephones;
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
        if (telephones.getPersonsHasTelephonesCollection() == null) {
            telephones.setPersonsHasTelephonesCollection(new ArrayList<PersonsHasTelephones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PersonsHasTelephones> attachedPersonsHasTelephonesCollection = new ArrayList<PersonsHasTelephones>();
            for (PersonsHasTelephones personsHasTelephonesCollectionPersonsHasTelephonesToAttach : telephones.getPersonsHasTelephonesCollection()) {
                personsHasTelephonesCollectionPersonsHasTelephonesToAttach = em.getReference(personsHasTelephonesCollectionPersonsHasTelephonesToAttach.getClass(), personsHasTelephonesCollectionPersonsHasTelephonesToAttach.getIdpht());
                attachedPersonsHasTelephonesCollection.add(personsHasTelephonesCollectionPersonsHasTelephonesToAttach);
            }
            telephones.setPersonsHasTelephonesCollection(attachedPersonsHasTelephonesCollection);
            em.persist(telephones);
            for (PersonsHasTelephones personsHasTelephonesCollectionPersonsHasTelephones : telephones.getPersonsHasTelephonesCollection()) {
                Telephones oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionPersonsHasTelephones = personsHasTelephonesCollectionPersonsHasTelephones.getTelephonesTelephoneid();
                personsHasTelephonesCollectionPersonsHasTelephones.setTelephonesTelephoneid(telephones);
                personsHasTelephonesCollectionPersonsHasTelephones = em.merge(personsHasTelephonesCollectionPersonsHasTelephones);
                if (oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionPersonsHasTelephones != null) {
                    oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionPersonsHasTelephones.getPersonsHasTelephonesCollection().remove(personsHasTelephonesCollectionPersonsHasTelephones);
                    oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionPersonsHasTelephones = em.merge(oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionPersonsHasTelephones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Telephones telephones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Telephones persistentTelephones = em.find(Telephones.class, telephones.getTelephoneid());
            Collection<PersonsHasTelephones> personsHasTelephonesCollectionOld = persistentTelephones.getPersonsHasTelephonesCollection();
            Collection<PersonsHasTelephones> personsHasTelephonesCollectionNew = telephones.getPersonsHasTelephonesCollection();
            List<String> illegalOrphanMessages = null;
            for (PersonsHasTelephones personsHasTelephonesCollectionOldPersonsHasTelephones : personsHasTelephonesCollectionOld) {
                if (!personsHasTelephonesCollectionNew.contains(personsHasTelephonesCollectionOldPersonsHasTelephones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonsHasTelephones " + personsHasTelephonesCollectionOldPersonsHasTelephones + " since its telephonesTelephoneid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<PersonsHasTelephones> attachedPersonsHasTelephonesCollectionNew = new ArrayList<PersonsHasTelephones>();
            for (PersonsHasTelephones personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach : personsHasTelephonesCollectionNew) {
                personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach = em.getReference(personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach.getClass(), personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach.getIdpht());
                attachedPersonsHasTelephonesCollectionNew.add(personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach);
            }
            personsHasTelephonesCollectionNew = attachedPersonsHasTelephonesCollectionNew;
            telephones.setPersonsHasTelephonesCollection(personsHasTelephonesCollectionNew);
            telephones = em.merge(telephones);
            for (PersonsHasTelephones personsHasTelephonesCollectionNewPersonsHasTelephones : personsHasTelephonesCollectionNew) {
                if (!personsHasTelephonesCollectionOld.contains(personsHasTelephonesCollectionNewPersonsHasTelephones)) {
                    Telephones oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones = personsHasTelephonesCollectionNewPersonsHasTelephones.getTelephonesTelephoneid();
                    personsHasTelephonesCollectionNewPersonsHasTelephones.setTelephonesTelephoneid(telephones);
                    personsHasTelephonesCollectionNewPersonsHasTelephones = em.merge(personsHasTelephonesCollectionNewPersonsHasTelephones);
                    if (oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones != null && !oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones.equals(telephones)) {
                        oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones.getPersonsHasTelephonesCollection().remove(personsHasTelephonesCollectionNewPersonsHasTelephones);
                        oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones = em.merge(oldTelephonesTelephoneidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones);
                    }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<PersonsHasTelephones> personsHasTelephonesCollectionOrphanCheck = telephones.getPersonsHasTelephonesCollection();
            for (PersonsHasTelephones personsHasTelephonesCollectionOrphanCheckPersonsHasTelephones : personsHasTelephonesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Telephones (" + telephones + ") cannot be destroyed since the PersonsHasTelephones " + personsHasTelephonesCollectionOrphanCheckPersonsHasTelephones + " in its personsHasTelephonesCollection field has a non-nullable telephonesTelephoneid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
