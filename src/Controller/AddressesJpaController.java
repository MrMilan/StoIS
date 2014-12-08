/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import entity.Addresses;
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
public class AddressesJpaController implements Serializable {

    public AddressesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Addresses addresses) {
        if (addresses.getPersonsCollection() == null) {
            addresses.setPersonsCollection(new ArrayList<Persons>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persons> attachedPersonsCollection = new ArrayList<Persons>();
            for (Persons personsCollectionPersonsToAttach : addresses.getPersonsCollection()) {
                personsCollectionPersonsToAttach = em.getReference(personsCollectionPersonsToAttach.getClass(), personsCollectionPersonsToAttach.getPersonsPK());
                attachedPersonsCollection.add(personsCollectionPersonsToAttach);
            }
            addresses.setPersonsCollection(attachedPersonsCollection);
            em.persist(addresses);
            for (Persons personsCollectionPersons : addresses.getPersonsCollection()) {
                Addresses oldAddressesOfPersonsCollectionPersons = personsCollectionPersons.getAddresses();
                personsCollectionPersons.setAddresses(addresses);
                personsCollectionPersons = em.merge(personsCollectionPersons);
                if (oldAddressesOfPersonsCollectionPersons != null) {
                    oldAddressesOfPersonsCollectionPersons.getPersonsCollection().remove(personsCollectionPersons);
                    oldAddressesOfPersonsCollectionPersons = em.merge(oldAddressesOfPersonsCollectionPersons);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Addresses addresses) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Addresses persistentAddresses = em.find(Addresses.class, addresses.getAddressid());
            Collection<Persons> personsCollectionOld = persistentAddresses.getPersonsCollection();
            Collection<Persons> personsCollectionNew = addresses.getPersonsCollection();
            List<String> illegalOrphanMessages = null;
            for (Persons personsCollectionOldPersons : personsCollectionOld) {
                if (!personsCollectionNew.contains(personsCollectionOldPersons)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persons " + personsCollectionOldPersons + " since its addresses field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Persons> attachedPersonsCollectionNew = new ArrayList<Persons>();
            for (Persons personsCollectionNewPersonsToAttach : personsCollectionNew) {
                personsCollectionNewPersonsToAttach = em.getReference(personsCollectionNewPersonsToAttach.getClass(), personsCollectionNewPersonsToAttach.getPersonsPK());
                attachedPersonsCollectionNew.add(personsCollectionNewPersonsToAttach);
            }
            personsCollectionNew = attachedPersonsCollectionNew;
            addresses.setPersonsCollection(personsCollectionNew);
            addresses = em.merge(addresses);
            for (Persons personsCollectionNewPersons : personsCollectionNew) {
                if (!personsCollectionOld.contains(personsCollectionNewPersons)) {
                    Addresses oldAddressesOfPersonsCollectionNewPersons = personsCollectionNewPersons.getAddresses();
                    personsCollectionNewPersons.setAddresses(addresses);
                    personsCollectionNewPersons = em.merge(personsCollectionNewPersons);
                    if (oldAddressesOfPersonsCollectionNewPersons != null && !oldAddressesOfPersonsCollectionNewPersons.equals(addresses)) {
                        oldAddressesOfPersonsCollectionNewPersons.getPersonsCollection().remove(personsCollectionNewPersons);
                        oldAddressesOfPersonsCollectionNewPersons = em.merge(oldAddressesOfPersonsCollectionNewPersons);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = addresses.getAddressid();
                if (findAddresses(id) == null) {
                    throw new NonexistentEntityException("The addresses with id " + id + " no longer exists.");
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
            Addresses addresses;
            try {
                addresses = em.getReference(Addresses.class, id);
                addresses.getAddressid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The addresses with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Persons> personsCollectionOrphanCheck = addresses.getPersonsCollection();
            for (Persons personsCollectionOrphanCheckPersons : personsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Addresses (" + addresses + ") cannot be destroyed since the Persons " + personsCollectionOrphanCheckPersons + " in its personsCollection field has a non-nullable addresses field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(addresses);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Addresses> findAddressesEntities() {
        return findAddressesEntities(true, -1, -1);
    }

    public List<Addresses> findAddressesEntities(int maxResults, int firstResult) {
        return findAddressesEntities(false, maxResults, firstResult);
    }

    private List<Addresses> findAddressesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Addresses.class));
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

    public Addresses findAddresses(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Addresses.class, id);
        } finally {
            em.close();
        }
    }

    public int getAddressesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Addresses> rt = cq.from(Addresses.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
