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
import entity.PersonsHasTelephones;
import entity.Telephones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class PersonsHasTelephonesJpaController implements Serializable {

    public PersonsHasTelephonesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonsHasTelephones personsHasTelephones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persons personsPersonid = personsHasTelephones.getPersonsPersonid();
            if (personsPersonid != null) {
                personsPersonid = em.getReference(personsPersonid.getClass(), personsPersonid.getPersonid());
                personsHasTelephones.setPersonsPersonid(personsPersonid);
            }
            Telephones telephonesTelephoneid = personsHasTelephones.getTelephonesTelephoneid();
            if (telephonesTelephoneid != null) {
                telephonesTelephoneid = em.getReference(telephonesTelephoneid.getClass(), telephonesTelephoneid.getTelephoneid());
                personsHasTelephones.setTelephonesTelephoneid(telephonesTelephoneid);
            }
            em.persist(personsHasTelephones);
            if (personsPersonid != null) {
                personsPersonid.getPersonsHasTelephonesCollection().add(personsHasTelephones);
                personsPersonid = em.merge(personsPersonid);
            }
            if (telephonesTelephoneid != null) {
                telephonesTelephoneid.getPersonsHasTelephonesCollection().add(personsHasTelephones);
                telephonesTelephoneid = em.merge(telephonesTelephoneid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonsHasTelephones personsHasTelephones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PersonsHasTelephones persistentPersonsHasTelephones = em.find(PersonsHasTelephones.class, personsHasTelephones.getIdpht());
            Persons personsPersonidOld = persistentPersonsHasTelephones.getPersonsPersonid();
            Persons personsPersonidNew = personsHasTelephones.getPersonsPersonid();
            Telephones telephonesTelephoneidOld = persistentPersonsHasTelephones.getTelephonesTelephoneid();
            Telephones telephonesTelephoneidNew = personsHasTelephones.getTelephonesTelephoneid();
            if (personsPersonidNew != null) {
                personsPersonidNew = em.getReference(personsPersonidNew.getClass(), personsPersonidNew.getPersonid());
                personsHasTelephones.setPersonsPersonid(personsPersonidNew);
            }
            if (telephonesTelephoneidNew != null) {
                telephonesTelephoneidNew = em.getReference(telephonesTelephoneidNew.getClass(), telephonesTelephoneidNew.getTelephoneid());
                personsHasTelephones.setTelephonesTelephoneid(telephonesTelephoneidNew);
            }
            personsHasTelephones = em.merge(personsHasTelephones);
            if (personsPersonidOld != null && !personsPersonidOld.equals(personsPersonidNew)) {
                personsPersonidOld.getPersonsHasTelephonesCollection().remove(personsHasTelephones);
                personsPersonidOld = em.merge(personsPersonidOld);
            }
            if (personsPersonidNew != null && !personsPersonidNew.equals(personsPersonidOld)) {
                personsPersonidNew.getPersonsHasTelephonesCollection().add(personsHasTelephones);
                personsPersonidNew = em.merge(personsPersonidNew);
            }
            if (telephonesTelephoneidOld != null && !telephonesTelephoneidOld.equals(telephonesTelephoneidNew)) {
                telephonesTelephoneidOld.getPersonsHasTelephonesCollection().remove(personsHasTelephones);
                telephonesTelephoneidOld = em.merge(telephonesTelephoneidOld);
            }
            if (telephonesTelephoneidNew != null && !telephonesTelephoneidNew.equals(telephonesTelephoneidOld)) {
                telephonesTelephoneidNew.getPersonsHasTelephonesCollection().add(personsHasTelephones);
                telephonesTelephoneidNew = em.merge(telephonesTelephoneidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personsHasTelephones.getIdpht();
                if (findPersonsHasTelephones(id) == null) {
                    throw new NonexistentEntityException("The personsHasTelephones with id " + id + " no longer exists.");
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
            PersonsHasTelephones personsHasTelephones;
            try {
                personsHasTelephones = em.getReference(PersonsHasTelephones.class, id);
                personsHasTelephones.getIdpht();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personsHasTelephones with id " + id + " no longer exists.", enfe);
            }
            Persons personsPersonid = personsHasTelephones.getPersonsPersonid();
            if (personsPersonid != null) {
                personsPersonid.getPersonsHasTelephonesCollection().remove(personsHasTelephones);
                personsPersonid = em.merge(personsPersonid);
            }
            Telephones telephonesTelephoneid = personsHasTelephones.getTelephonesTelephoneid();
            if (telephonesTelephoneid != null) {
                telephonesTelephoneid.getPersonsHasTelephonesCollection().remove(personsHasTelephones);
                telephonesTelephoneid = em.merge(telephonesTelephoneid);
            }
            em.remove(personsHasTelephones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonsHasTelephones> findPersonsHasTelephonesEntities() {
        return findPersonsHasTelephonesEntities(true, -1, -1);
    }

    public List<PersonsHasTelephones> findPersonsHasTelephonesEntities(int maxResults, int firstResult) {
        return findPersonsHasTelephonesEntities(false, maxResults, firstResult);
    }

    private List<PersonsHasTelephones> findPersonsHasTelephonesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonsHasTelephones.class));
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

    public PersonsHasTelephones findPersonsHasTelephones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonsHasTelephones.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonsHasTelephonesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonsHasTelephones> rt = cq.from(PersonsHasTelephones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
