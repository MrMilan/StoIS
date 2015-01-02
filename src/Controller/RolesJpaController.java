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
import entity.PersonsHasRoles;
import entity.Roles;
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

    public void create(Roles roles) {
        if (roles.getPersonsHasRolesCollection() == null) {
            roles.setPersonsHasRolesCollection(new ArrayList<PersonsHasRoles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PersonsHasRoles> attachedPersonsHasRolesCollection = new ArrayList<PersonsHasRoles>();
            for (PersonsHasRoles personsHasRolesCollectionPersonsHasRolesToAttach : roles.getPersonsHasRolesCollection()) {
                personsHasRolesCollectionPersonsHasRolesToAttach = em.getReference(personsHasRolesCollectionPersonsHasRolesToAttach.getClass(), personsHasRolesCollectionPersonsHasRolesToAttach.getIdphr());
                attachedPersonsHasRolesCollection.add(personsHasRolesCollectionPersonsHasRolesToAttach);
            }
            roles.setPersonsHasRolesCollection(attachedPersonsHasRolesCollection);
            em.persist(roles);
            for (PersonsHasRoles personsHasRolesCollectionPersonsHasRoles : roles.getPersonsHasRolesCollection()) {
                Roles oldRolesRoleidOfPersonsHasRolesCollectionPersonsHasRoles = personsHasRolesCollectionPersonsHasRoles.getRolesRoleid();
                personsHasRolesCollectionPersonsHasRoles.setRolesRoleid(roles);
                personsHasRolesCollectionPersonsHasRoles = em.merge(personsHasRolesCollectionPersonsHasRoles);
                if (oldRolesRoleidOfPersonsHasRolesCollectionPersonsHasRoles != null) {
                    oldRolesRoleidOfPersonsHasRolesCollectionPersonsHasRoles.getPersonsHasRolesCollection().remove(personsHasRolesCollectionPersonsHasRoles);
                    oldRolesRoleidOfPersonsHasRolesCollectionPersonsHasRoles = em.merge(oldRolesRoleidOfPersonsHasRolesCollectionPersonsHasRoles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Roles roles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getRoleid());
            Collection<PersonsHasRoles> personsHasRolesCollectionOld = persistentRoles.getPersonsHasRolesCollection();
            Collection<PersonsHasRoles> personsHasRolesCollectionNew = roles.getPersonsHasRolesCollection();
            List<String> illegalOrphanMessages = null;
            for (PersonsHasRoles personsHasRolesCollectionOldPersonsHasRoles : personsHasRolesCollectionOld) {
                if (!personsHasRolesCollectionNew.contains(personsHasRolesCollectionOldPersonsHasRoles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonsHasRoles " + personsHasRolesCollectionOldPersonsHasRoles + " since its rolesRoleid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<PersonsHasRoles> attachedPersonsHasRolesCollectionNew = new ArrayList<PersonsHasRoles>();
            for (PersonsHasRoles personsHasRolesCollectionNewPersonsHasRolesToAttach : personsHasRolesCollectionNew) {
                personsHasRolesCollectionNewPersonsHasRolesToAttach = em.getReference(personsHasRolesCollectionNewPersonsHasRolesToAttach.getClass(), personsHasRolesCollectionNewPersonsHasRolesToAttach.getIdphr());
                attachedPersonsHasRolesCollectionNew.add(personsHasRolesCollectionNewPersonsHasRolesToAttach);
            }
            personsHasRolesCollectionNew = attachedPersonsHasRolesCollectionNew;
            roles.setPersonsHasRolesCollection(personsHasRolesCollectionNew);
            roles = em.merge(roles);
            for (PersonsHasRoles personsHasRolesCollectionNewPersonsHasRoles : personsHasRolesCollectionNew) {
                if (!personsHasRolesCollectionOld.contains(personsHasRolesCollectionNewPersonsHasRoles)) {
                    Roles oldRolesRoleidOfPersonsHasRolesCollectionNewPersonsHasRoles = personsHasRolesCollectionNewPersonsHasRoles.getRolesRoleid();
                    personsHasRolesCollectionNewPersonsHasRoles.setRolesRoleid(roles);
                    personsHasRolesCollectionNewPersonsHasRoles = em.merge(personsHasRolesCollectionNewPersonsHasRoles);
                    if (oldRolesRoleidOfPersonsHasRolesCollectionNewPersonsHasRoles != null && !oldRolesRoleidOfPersonsHasRolesCollectionNewPersonsHasRoles.equals(roles)) {
                        oldRolesRoleidOfPersonsHasRolesCollectionNewPersonsHasRoles.getPersonsHasRolesCollection().remove(personsHasRolesCollectionNewPersonsHasRoles);
                        oldRolesRoleidOfPersonsHasRolesCollectionNewPersonsHasRoles = em.merge(oldRolesRoleidOfPersonsHasRolesCollectionNewPersonsHasRoles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roles.getRoleid();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getRoleid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PersonsHasRoles> personsHasRolesCollectionOrphanCheck = roles.getPersonsHasRolesCollection();
            for (PersonsHasRoles personsHasRolesCollectionOrphanCheckPersonsHasRoles : personsHasRolesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Roles (" + roles + ") cannot be destroyed since the PersonsHasRoles " + personsHasRolesCollectionOrphanCheckPersonsHasRoles + " in its personsHasRolesCollection field has a non-nullable rolesRoleid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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

    public Roles findRoles(Integer id) {
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
