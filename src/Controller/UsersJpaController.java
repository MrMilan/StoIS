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
import entity.Persons;
import entity.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getPersonsCollection() == null) {
            users.setPersonsCollection(new ArrayList<Persons>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persons> attachedPersonsCollection = new ArrayList<Persons>();
            for (Persons personsCollectionPersonsToAttach : users.getPersonsCollection()) {
                personsCollectionPersonsToAttach = em.getReference(personsCollectionPersonsToAttach.getClass(), personsCollectionPersonsToAttach.getPersonsPK());
                attachedPersonsCollection.add(personsCollectionPersonsToAttach);
            }
            users.setPersonsCollection(attachedPersonsCollection);
            em.persist(users);
            for (Persons personsCollectionPersons : users.getPersonsCollection()) {
                Users oldUsersOfPersonsCollectionPersons = personsCollectionPersons.getUsers();
                personsCollectionPersons.setUsers(users);
                personsCollectionPersons = em.merge(personsCollectionPersons);
                if (oldUsersOfPersonsCollectionPersons != null) {
                    oldUsersOfPersonsCollectionPersons.getPersonsCollection().remove(personsCollectionPersons);
                    oldUsersOfPersonsCollectionPersons = em.merge(oldUsersOfPersonsCollectionPersons);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getUsersid());
            Collection<Persons> personsCollectionOld = persistentUsers.getPersonsCollection();
            Collection<Persons> personsCollectionNew = users.getPersonsCollection();
            List<String> illegalOrphanMessages = null;
            for (Persons personsCollectionOldPersons : personsCollectionOld) {
                if (!personsCollectionNew.contains(personsCollectionOldPersons)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persons " + personsCollectionOldPersons + " since its users field is not nullable.");
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
            users.setPersonsCollection(personsCollectionNew);
            users = em.merge(users);
            for (Persons personsCollectionNewPersons : personsCollectionNew) {
                if (!personsCollectionOld.contains(personsCollectionNewPersons)) {
                    Users oldUsersOfPersonsCollectionNewPersons = personsCollectionNewPersons.getUsers();
                    personsCollectionNewPersons.setUsers(users);
                    personsCollectionNewPersons = em.merge(personsCollectionNewPersons);
                    if (oldUsersOfPersonsCollectionNewPersons != null && !oldUsersOfPersonsCollectionNewPersons.equals(users)) {
                        oldUsersOfPersonsCollectionNewPersons.getPersonsCollection().remove(personsCollectionNewPersons);
                        oldUsersOfPersonsCollectionNewPersons = em.merge(oldUsersOfPersonsCollectionNewPersons);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getUsersid();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getUsersid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Persons> personsCollectionOrphanCheck = users.getPersonsCollection();
            for (Persons personsCollectionOrphanCheckPersons : personsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Persons " + personsCollectionOrphanCheckPersons + " in its personsCollection field has a non-nullable users field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
