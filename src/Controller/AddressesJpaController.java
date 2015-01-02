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
import entity.PersonHasAddress;
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
        if (addresses.getPersonHasAddressCollection() == null) {
            addresses.setPersonHasAddressCollection(new ArrayList<PersonHasAddress>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PersonHasAddress> attachedPersonHasAddressCollection = new ArrayList<PersonHasAddress>();
            for (PersonHasAddress personHasAddressCollectionPersonHasAddressToAttach : addresses.getPersonHasAddressCollection()) {
                personHasAddressCollectionPersonHasAddressToAttach = em.getReference(personHasAddressCollectionPersonHasAddressToAttach.getClass(), personHasAddressCollectionPersonHasAddressToAttach.getIdpersonHasAddress());
                attachedPersonHasAddressCollection.add(personHasAddressCollectionPersonHasAddressToAttach);
            }
            addresses.setPersonHasAddressCollection(attachedPersonHasAddressCollection);
            em.persist(addresses);
            for (PersonHasAddress personHasAddressCollectionPersonHasAddress : addresses.getPersonHasAddressCollection()) {
                Addresses oldAddressesAddressidOfPersonHasAddressCollectionPersonHasAddress = personHasAddressCollectionPersonHasAddress.getAddressesAddressid();
                personHasAddressCollectionPersonHasAddress.setAddressesAddressid(addresses);
                personHasAddressCollectionPersonHasAddress = em.merge(personHasAddressCollectionPersonHasAddress);
                if (oldAddressesAddressidOfPersonHasAddressCollectionPersonHasAddress != null) {
                    oldAddressesAddressidOfPersonHasAddressCollectionPersonHasAddress.getPersonHasAddressCollection().remove(personHasAddressCollectionPersonHasAddress);
                    oldAddressesAddressidOfPersonHasAddressCollectionPersonHasAddress = em.merge(oldAddressesAddressidOfPersonHasAddressCollectionPersonHasAddress);
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
            Collection<PersonHasAddress> personHasAddressCollectionOld = persistentAddresses.getPersonHasAddressCollection();
            Collection<PersonHasAddress> personHasAddressCollectionNew = addresses.getPersonHasAddressCollection();
            List<String> illegalOrphanMessages = null;
            for (PersonHasAddress personHasAddressCollectionOldPersonHasAddress : personHasAddressCollectionOld) {
                if (!personHasAddressCollectionNew.contains(personHasAddressCollectionOldPersonHasAddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonHasAddress " + personHasAddressCollectionOldPersonHasAddress + " since its addressesAddressid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<PersonHasAddress> attachedPersonHasAddressCollectionNew = new ArrayList<PersonHasAddress>();
            for (PersonHasAddress personHasAddressCollectionNewPersonHasAddressToAttach : personHasAddressCollectionNew) {
                personHasAddressCollectionNewPersonHasAddressToAttach = em.getReference(personHasAddressCollectionNewPersonHasAddressToAttach.getClass(), personHasAddressCollectionNewPersonHasAddressToAttach.getIdpersonHasAddress());
                attachedPersonHasAddressCollectionNew.add(personHasAddressCollectionNewPersonHasAddressToAttach);
            }
            personHasAddressCollectionNew = attachedPersonHasAddressCollectionNew;
            addresses.setPersonHasAddressCollection(personHasAddressCollectionNew);
            addresses = em.merge(addresses);
            for (PersonHasAddress personHasAddressCollectionNewPersonHasAddress : personHasAddressCollectionNew) {
                if (!personHasAddressCollectionOld.contains(personHasAddressCollectionNewPersonHasAddress)) {
                    Addresses oldAddressesAddressidOfPersonHasAddressCollectionNewPersonHasAddress = personHasAddressCollectionNewPersonHasAddress.getAddressesAddressid();
                    personHasAddressCollectionNewPersonHasAddress.setAddressesAddressid(addresses);
                    personHasAddressCollectionNewPersonHasAddress = em.merge(personHasAddressCollectionNewPersonHasAddress);
                    if (oldAddressesAddressidOfPersonHasAddressCollectionNewPersonHasAddress != null && !oldAddressesAddressidOfPersonHasAddressCollectionNewPersonHasAddress.equals(addresses)) {
                        oldAddressesAddressidOfPersonHasAddressCollectionNewPersonHasAddress.getPersonHasAddressCollection().remove(personHasAddressCollectionNewPersonHasAddress);
                        oldAddressesAddressidOfPersonHasAddressCollectionNewPersonHasAddress = em.merge(oldAddressesAddressidOfPersonHasAddressCollectionNewPersonHasAddress);
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
            Collection<PersonHasAddress> personHasAddressCollectionOrphanCheck = addresses.getPersonHasAddressCollection();
            for (PersonHasAddress personHasAddressCollectionOrphanCheckPersonHasAddress : personHasAddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Addresses (" + addresses + ") cannot be destroyed since the PersonHasAddress " + personHasAddressCollectionOrphanCheckPersonHasAddress + " in its personHasAddressCollection field has a non-nullable addressesAddressid field.");
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
