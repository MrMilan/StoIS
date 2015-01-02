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
import entity.Addresses;
import entity.PersonHasAddress;
import entity.Persons;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class PersonHasAddressJpaController implements Serializable {

    public PersonHasAddressJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonHasAddress personHasAddress) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Addresses addressesAddressid = personHasAddress.getAddressesAddressid();
            if (addressesAddressid != null) {
                addressesAddressid = em.getReference(addressesAddressid.getClass(), addressesAddressid.getAddressid());
                personHasAddress.setAddressesAddressid(addressesAddressid);
            }
            Persons personsPersonid = personHasAddress.getPersonsPersonid();
            if (personsPersonid != null) {
                personsPersonid = em.getReference(personsPersonid.getClass(), personsPersonid.getPersonid());
                personHasAddress.setPersonsPersonid(personsPersonid);
            }
            em.persist(personHasAddress);
            if (addressesAddressid != null) {
                addressesAddressid.getPersonHasAddressCollection().add(personHasAddress);
                addressesAddressid = em.merge(addressesAddressid);
            }
            if (personsPersonid != null) {
                personsPersonid.getPersonHasAddressCollection().add(personHasAddress);
                personsPersonid = em.merge(personsPersonid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonHasAddress personHasAddress) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PersonHasAddress persistentPersonHasAddress = em.find(PersonHasAddress.class, personHasAddress.getIdpersonHasAddress());
            Addresses addressesAddressidOld = persistentPersonHasAddress.getAddressesAddressid();
            Addresses addressesAddressidNew = personHasAddress.getAddressesAddressid();
            Persons personsPersonidOld = persistentPersonHasAddress.getPersonsPersonid();
            Persons personsPersonidNew = personHasAddress.getPersonsPersonid();
            if (addressesAddressidNew != null) {
                addressesAddressidNew = em.getReference(addressesAddressidNew.getClass(), addressesAddressidNew.getAddressid());
                personHasAddress.setAddressesAddressid(addressesAddressidNew);
            }
            if (personsPersonidNew != null) {
                personsPersonidNew = em.getReference(personsPersonidNew.getClass(), personsPersonidNew.getPersonid());
                personHasAddress.setPersonsPersonid(personsPersonidNew);
            }
            personHasAddress = em.merge(personHasAddress);
            if (addressesAddressidOld != null && !addressesAddressidOld.equals(addressesAddressidNew)) {
                addressesAddressidOld.getPersonHasAddressCollection().remove(personHasAddress);
                addressesAddressidOld = em.merge(addressesAddressidOld);
            }
            if (addressesAddressidNew != null && !addressesAddressidNew.equals(addressesAddressidOld)) {
                addressesAddressidNew.getPersonHasAddressCollection().add(personHasAddress);
                addressesAddressidNew = em.merge(addressesAddressidNew);
            }
            if (personsPersonidOld != null && !personsPersonidOld.equals(personsPersonidNew)) {
                personsPersonidOld.getPersonHasAddressCollection().remove(personHasAddress);
                personsPersonidOld = em.merge(personsPersonidOld);
            }
            if (personsPersonidNew != null && !personsPersonidNew.equals(personsPersonidOld)) {
                personsPersonidNew.getPersonHasAddressCollection().add(personHasAddress);
                personsPersonidNew = em.merge(personsPersonidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personHasAddress.getIdpersonHasAddress();
                if (findPersonHasAddress(id) == null) {
                    throw new NonexistentEntityException("The personHasAddress with id " + id + " no longer exists.");
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
            PersonHasAddress personHasAddress;
            try {
                personHasAddress = em.getReference(PersonHasAddress.class, id);
                personHasAddress.getIdpersonHasAddress();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personHasAddress with id " + id + " no longer exists.", enfe);
            }
            Addresses addressesAddressid = personHasAddress.getAddressesAddressid();
            if (addressesAddressid != null) {
                addressesAddressid.getPersonHasAddressCollection().remove(personHasAddress);
                addressesAddressid = em.merge(addressesAddressid);
            }
            Persons personsPersonid = personHasAddress.getPersonsPersonid();
            if (personsPersonid != null) {
                personsPersonid.getPersonHasAddressCollection().remove(personHasAddress);
                personsPersonid = em.merge(personsPersonid);
            }
            em.remove(personHasAddress);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonHasAddress> findPersonHasAddressEntities() {
        return findPersonHasAddressEntities(true, -1, -1);
    }

    public List<PersonHasAddress> findPersonHasAddressEntities(int maxResults, int firstResult) {
        return findPersonHasAddressEntities(false, maxResults, firstResult);
    }

    private List<PersonHasAddress> findPersonHasAddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonHasAddress.class));
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

    public PersonHasAddress findPersonHasAddress(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonHasAddress.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonHasAddressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonHasAddress> rt = cq.from(PersonHasAddress.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
