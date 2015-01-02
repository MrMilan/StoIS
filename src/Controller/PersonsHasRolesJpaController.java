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
import entity.Roles;
import entity.Actions;
import entity.PersonsHasRoles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class PersonsHasRolesJpaController implements Serializable {

    public PersonsHasRolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonsHasRoles personsHasRoles) {
        if (personsHasRoles.getActionsCollection() == null) {
            personsHasRoles.setActionsCollection(new ArrayList<Actions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persons personsPersonid = personsHasRoles.getPersonsPersonid();
            if (personsPersonid != null) {
                personsPersonid = em.getReference(personsPersonid.getClass(), personsPersonid.getPersonid());
                personsHasRoles.setPersonsPersonid(personsPersonid);
            }
            Roles rolesRoleid = personsHasRoles.getRolesRoleid();
            if (rolesRoleid != null) {
                rolesRoleid = em.getReference(rolesRoleid.getClass(), rolesRoleid.getRoleid());
                personsHasRoles.setRolesRoleid(rolesRoleid);
            }
            Collection<Actions> attachedActionsCollection = new ArrayList<Actions>();
            for (Actions actionsCollectionActionsToAttach : personsHasRoles.getActionsCollection()) {
                actionsCollectionActionsToAttach = em.getReference(actionsCollectionActionsToAttach.getClass(), actionsCollectionActionsToAttach.getActionid());
                attachedActionsCollection.add(actionsCollectionActionsToAttach);
            }
            personsHasRoles.setActionsCollection(attachedActionsCollection);
            em.persist(personsHasRoles);
            if (personsPersonid != null) {
                personsPersonid.getPersonsHasRolesCollection().add(personsHasRoles);
                personsPersonid = em.merge(personsPersonid);
            }
            if (rolesRoleid != null) {
                rolesRoleid.getPersonsHasRolesCollection().add(personsHasRoles);
                rolesRoleid = em.merge(rolesRoleid);
            }
            for (Actions actionsCollectionActions : personsHasRoles.getActionsCollection()) {
                PersonsHasRoles oldPersonsHasRolesIdphrOfActionsCollectionActions = actionsCollectionActions.getPersonsHasRolesIdphr();
                actionsCollectionActions.setPersonsHasRolesIdphr(personsHasRoles);
                actionsCollectionActions = em.merge(actionsCollectionActions);
                if (oldPersonsHasRolesIdphrOfActionsCollectionActions != null) {
                    oldPersonsHasRolesIdphrOfActionsCollectionActions.getActionsCollection().remove(actionsCollectionActions);
                    oldPersonsHasRolesIdphrOfActionsCollectionActions = em.merge(oldPersonsHasRolesIdphrOfActionsCollectionActions);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonsHasRoles personsHasRoles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PersonsHasRoles persistentPersonsHasRoles = em.find(PersonsHasRoles.class, personsHasRoles.getIdphr());
            Persons personsPersonidOld = persistentPersonsHasRoles.getPersonsPersonid();
            Persons personsPersonidNew = personsHasRoles.getPersonsPersonid();
            Roles rolesRoleidOld = persistentPersonsHasRoles.getRolesRoleid();
            Roles rolesRoleidNew = personsHasRoles.getRolesRoleid();
            Collection<Actions> actionsCollectionOld = persistentPersonsHasRoles.getActionsCollection();
            Collection<Actions> actionsCollectionNew = personsHasRoles.getActionsCollection();
            List<String> illegalOrphanMessages = null;
            for (Actions actionsCollectionOldActions : actionsCollectionOld) {
                if (!actionsCollectionNew.contains(actionsCollectionOldActions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actions " + actionsCollectionOldActions + " since its personsHasRolesIdphr field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personsPersonidNew != null) {
                personsPersonidNew = em.getReference(personsPersonidNew.getClass(), personsPersonidNew.getPersonid());
                personsHasRoles.setPersonsPersonid(personsPersonidNew);
            }
            if (rolesRoleidNew != null) {
                rolesRoleidNew = em.getReference(rolesRoleidNew.getClass(), rolesRoleidNew.getRoleid());
                personsHasRoles.setRolesRoleid(rolesRoleidNew);
            }
            Collection<Actions> attachedActionsCollectionNew = new ArrayList<Actions>();
            for (Actions actionsCollectionNewActionsToAttach : actionsCollectionNew) {
                actionsCollectionNewActionsToAttach = em.getReference(actionsCollectionNewActionsToAttach.getClass(), actionsCollectionNewActionsToAttach.getActionid());
                attachedActionsCollectionNew.add(actionsCollectionNewActionsToAttach);
            }
            actionsCollectionNew = attachedActionsCollectionNew;
            personsHasRoles.setActionsCollection(actionsCollectionNew);
            personsHasRoles = em.merge(personsHasRoles);
            if (personsPersonidOld != null && !personsPersonidOld.equals(personsPersonidNew)) {
                personsPersonidOld.getPersonsHasRolesCollection().remove(personsHasRoles);
                personsPersonidOld = em.merge(personsPersonidOld);
            }
            if (personsPersonidNew != null && !personsPersonidNew.equals(personsPersonidOld)) {
                personsPersonidNew.getPersonsHasRolesCollection().add(personsHasRoles);
                personsPersonidNew = em.merge(personsPersonidNew);
            }
            if (rolesRoleidOld != null && !rolesRoleidOld.equals(rolesRoleidNew)) {
                rolesRoleidOld.getPersonsHasRolesCollection().remove(personsHasRoles);
                rolesRoleidOld = em.merge(rolesRoleidOld);
            }
            if (rolesRoleidNew != null && !rolesRoleidNew.equals(rolesRoleidOld)) {
                rolesRoleidNew.getPersonsHasRolesCollection().add(personsHasRoles);
                rolesRoleidNew = em.merge(rolesRoleidNew);
            }
            for (Actions actionsCollectionNewActions : actionsCollectionNew) {
                if (!actionsCollectionOld.contains(actionsCollectionNewActions)) {
                    PersonsHasRoles oldPersonsHasRolesIdphrOfActionsCollectionNewActions = actionsCollectionNewActions.getPersonsHasRolesIdphr();
                    actionsCollectionNewActions.setPersonsHasRolesIdphr(personsHasRoles);
                    actionsCollectionNewActions = em.merge(actionsCollectionNewActions);
                    if (oldPersonsHasRolesIdphrOfActionsCollectionNewActions != null && !oldPersonsHasRolesIdphrOfActionsCollectionNewActions.equals(personsHasRoles)) {
                        oldPersonsHasRolesIdphrOfActionsCollectionNewActions.getActionsCollection().remove(actionsCollectionNewActions);
                        oldPersonsHasRolesIdphrOfActionsCollectionNewActions = em.merge(oldPersonsHasRolesIdphrOfActionsCollectionNewActions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personsHasRoles.getIdphr();
                if (findPersonsHasRoles(id) == null) {
                    throw new NonexistentEntityException("The personsHasRoles with id " + id + " no longer exists.");
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
            PersonsHasRoles personsHasRoles;
            try {
                personsHasRoles = em.getReference(PersonsHasRoles.class, id);
                personsHasRoles.getIdphr();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personsHasRoles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Actions> actionsCollectionOrphanCheck = personsHasRoles.getActionsCollection();
            for (Actions actionsCollectionOrphanCheckActions : actionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PersonsHasRoles (" + personsHasRoles + ") cannot be destroyed since the Actions " + actionsCollectionOrphanCheckActions + " in its actionsCollection field has a non-nullable personsHasRolesIdphr field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persons personsPersonid = personsHasRoles.getPersonsPersonid();
            if (personsPersonid != null) {
                personsPersonid.getPersonsHasRolesCollection().remove(personsHasRoles);
                personsPersonid = em.merge(personsPersonid);
            }
            Roles rolesRoleid = personsHasRoles.getRolesRoleid();
            if (rolesRoleid != null) {
                rolesRoleid.getPersonsHasRolesCollection().remove(personsHasRoles);
                rolesRoleid = em.merge(rolesRoleid);
            }
            em.remove(personsHasRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonsHasRoles> findPersonsHasRolesEntities() {
        return findPersonsHasRolesEntities(true, -1, -1);
    }

    public List<PersonsHasRoles> findPersonsHasRolesEntities(int maxResults, int firstResult) {
        return findPersonsHasRolesEntities(false, maxResults, firstResult);
    }

    private List<PersonsHasRoles> findPersonsHasRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonsHasRoles.class));
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

    public PersonsHasRoles findPersonsHasRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonsHasRoles.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonsHasRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonsHasRoles> rt = cq.from(PersonsHasRoles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
