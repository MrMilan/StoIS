/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Persons;
import entity.Roles;
import entity.RolesPK;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class RolesJpaController implements Serializable {

    public RolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Roles roles) throws PreexistingEntityException, Exception {
        if (roles.getRolesPK() == null) {
            roles.setRolesPK(new RolesPK());
        }
        if (roles.getPersonsCollection() == null) {
            roles.setPersonsCollection(new ArrayList<Persons>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persons> attachedPersonsCollection = new ArrayList<Persons>();
            for (Persons personsCollectionPersonsToAttach : roles.getPersonsCollection()) {
                personsCollectionPersonsToAttach = em.getReference(personsCollectionPersonsToAttach.getClass(), personsCollectionPersonsToAttach.getPersonsPK());
                attachedPersonsCollection.add(personsCollectionPersonsToAttach);
            }
            roles.setPersonsCollection(attachedPersonsCollection);
            em.persist(roles);
            for (Persons personsCollectionPersons : roles.getPersonsCollection()) {
                personsCollectionPersons.getRolesCollection().add(roles);
                personsCollectionPersons = em.merge(personsCollectionPersons);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRoles(roles.getRolesPK()) != null) {
                throw new PreexistingEntityException("Roles " + roles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Roles roles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getRolesPK());
            Collection<Persons> personsCollectionOld = persistentRoles.getPersonsCollection();
            Collection<Persons> personsCollectionNew = roles.getPersonsCollection();
            Collection<Persons> attachedPersonsCollectionNew = new ArrayList<Persons>();
            for (Persons personsCollectionNewPersonsToAttach : personsCollectionNew) {
                personsCollectionNewPersonsToAttach = em.getReference(personsCollectionNewPersonsToAttach.getClass(), personsCollectionNewPersonsToAttach.getPersonsPK());
                attachedPersonsCollectionNew.add(personsCollectionNewPersonsToAttach);
            }
            personsCollectionNew = attachedPersonsCollectionNew;
            roles.setPersonsCollection(personsCollectionNew);
            roles = em.merge(roles);
            for (Persons personsCollectionOldPersons : personsCollectionOld) {
                if (!personsCollectionNew.contains(personsCollectionOldPersons)) {
                    personsCollectionOldPersons.getRolesCollection().remove(roles);
                    personsCollectionOldPersons = em.merge(personsCollectionOldPersons);
                }
            }
            for (Persons personsCollectionNewPersons : personsCollectionNew) {
                if (!personsCollectionOld.contains(personsCollectionNewPersons)) {
                    personsCollectionNewPersons.getRolesCollection().add(roles);
                    personsCollectionNewPersons = em.merge(personsCollectionNewPersons);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RolesPK id = roles.getRolesPK();
                if (findRoles(id) == null) {
                    throw new NonexistentEntityException("The roles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RolesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getRolesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            Collection<Persons> personsCollection = roles.getPersonsCollection();
            for (Persons personsCollectionPersons : personsCollection) {
                personsCollectionPersons.getRolesCollection().remove(roles);
                personsCollectionPersons = em.merge(personsCollectionPersons);
            }
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Roles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<Roles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    private List<Roles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Roles.class));
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

    public Roles findRoles(RolesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Roles> rt = cq.from(Roles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
