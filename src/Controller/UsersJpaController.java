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
import entity.Users;
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persons personsPersonid = users.getPersonsPersonid();
            if (personsPersonid != null) {
                personsPersonid = em.getReference(personsPersonid.getClass(), personsPersonid.getPersonid());
                users.setPersonsPersonid(personsPersonid);
            }
            em.persist(users);
            if (personsPersonid != null) {
                personsPersonid.getUsersCollection().add(users);
                personsPersonid = em.merge(personsPersonid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getUsersid());
            Persons personsPersonidOld = persistentUsers.getPersonsPersonid();
            Persons personsPersonidNew = users.getPersonsPersonid();
            if (personsPersonidNew != null) {
                personsPersonidNew = em.getReference(personsPersonidNew.getClass(), personsPersonidNew.getPersonid());
                users.setPersonsPersonid(personsPersonidNew);
            }
            users = em.merge(users);
            if (personsPersonidOld != null && !personsPersonidOld.equals(personsPersonidNew)) {
                personsPersonidOld.getUsersCollection().remove(users);
                personsPersonidOld = em.merge(personsPersonidOld);
            }
            if (personsPersonidNew != null && !personsPersonidNew.equals(personsPersonidOld)) {
                personsPersonidNew.getUsersCollection().add(users);
                personsPersonidNew = em.merge(personsPersonidNew);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            Persons personsPersonid = users.getPersonsPersonid();
            if (personsPersonid != null) {
                personsPersonid.getUsersCollection().remove(users);
                personsPersonid = em.merge(personsPersonid);
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

    public Users findUsersByUserName(String username) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM Users AS u WHERE u.username=:username");
            q.setParameter("username", username);
            return (Users) q.getSingleResult();
        } catch (Exception e) {
//           e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
