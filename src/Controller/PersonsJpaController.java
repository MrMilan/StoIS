/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Addresses;
import entity.Insurances;
import entity.Users;
import entity.Roles;
import java.util.ArrayList;
import java.util.Collection;
import entity.Telephones;
import entity.Actions;
import entity.Persons;
import entity.PersonsPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class PersonsJpaController implements Serializable {

    public PersonsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persons persons) throws PreexistingEntityException, Exception {
        if (persons.getPersonsPK() == null) {
            persons.setPersonsPK(new PersonsPK());
        }
        if (persons.getRolesCollection() == null) {
            persons.setRolesCollection(new ArrayList<Roles>());
        }
        if (persons.getTelephonesCollection() == null) {
            persons.setTelephonesCollection(new ArrayList<Telephones>());
        }
        if (persons.getActionsCollection() == null) {
            persons.setActionsCollection(new ArrayList<Actions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Addresses addresses = persons.getAddresses();
            if (addresses != null) {
                addresses = em.getReference(addresses.getClass(), addresses.getAddressid());
                persons.setAddresses(addresses);
            }
            Insurances insurances = persons.getInsurances();
            if (insurances != null) {
                insurances = em.getReference(insurances.getClass(), insurances.getInsuranceid());
                persons.setInsurances(insurances);
            }
            Users users = persons.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getUsersid());
                persons.setUsers(users);
            }
            Collection<Roles> attachedRolesCollection = new ArrayList<Roles>();
            for (Roles rolesCollectionRolesToAttach : persons.getRolesCollection()) {
                rolesCollectionRolesToAttach = em.getReference(rolesCollectionRolesToAttach.getClass(), rolesCollectionRolesToAttach.getRolesPK());
                attachedRolesCollection.add(rolesCollectionRolesToAttach);
            }
            persons.setRolesCollection(attachedRolesCollection);
            Collection<Telephones> attachedTelephonesCollection = new ArrayList<Telephones>();
            for (Telephones telephonesCollectionTelephonesToAttach : persons.getTelephonesCollection()) {
                telephonesCollectionTelephonesToAttach = em.getReference(telephonesCollectionTelephonesToAttach.getClass(), telephonesCollectionTelephonesToAttach.getTelephoneid());
                attachedTelephonesCollection.add(telephonesCollectionTelephonesToAttach);
            }
            persons.setTelephonesCollection(attachedTelephonesCollection);
            Collection<Actions> attachedActionsCollection = new ArrayList<Actions>();
            for (Actions actionsCollectionActionsToAttach : persons.getActionsCollection()) {
                actionsCollectionActionsToAttach = em.getReference(actionsCollectionActionsToAttach.getClass(), actionsCollectionActionsToAttach.getActionsPK());
                attachedActionsCollection.add(actionsCollectionActionsToAttach);
            }
            persons.setActionsCollection(attachedActionsCollection);
            em.persist(persons);
            if (addresses != null) {
                addresses.getPersonsCollection().add(persons);
                addresses = em.merge(addresses);
            }
            if (insurances != null) {
                insurances.getPersonsCollection().add(persons);
                insurances = em.merge(insurances);
            }
            if (users != null) {
                users.getPersonsCollection().add(persons);
                users = em.merge(users);
            }
            for (Roles rolesCollectionRoles : persons.getRolesCollection()) {
                rolesCollectionRoles.getPersonsCollection().add(persons);
                rolesCollectionRoles = em.merge(rolesCollectionRoles);
            }
            for (Telephones telephonesCollectionTelephones : persons.getTelephonesCollection()) {
                telephonesCollectionTelephones.getPersonsCollection().add(persons);
                telephonesCollectionTelephones = em.merge(telephonesCollectionTelephones);
            }
            for (Actions actionsCollectionActions : persons.getActionsCollection()) {
                Persons oldPersonsOfActionsCollectionActions = actionsCollectionActions.getPersons();
                actionsCollectionActions.setPersons(persons);
                actionsCollectionActions = em.merge(actionsCollectionActions);
                if (oldPersonsOfActionsCollectionActions != null) {
                    oldPersonsOfActionsCollectionActions.getActionsCollection().remove(actionsCollectionActions);
                    oldPersonsOfActionsCollectionActions = em.merge(oldPersonsOfActionsCollectionActions);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersons(persons.getPersonsPK()) != null) {
                throw new PreexistingEntityException("Persons " + persons + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persons persons) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persons persistentPersons = em.find(Persons.class, persons.getPersonsPK());
            Addresses addressesOld = persistentPersons.getAddresses();
            Addresses addressesNew = persons.getAddresses();
            Insurances insurancesOld = persistentPersons.getInsurances();
            Insurances insurancesNew = persons.getInsurances();
            Users usersOld = persistentPersons.getUsers();
            Users usersNew = persons.getUsers();
            Collection<Roles> rolesCollectionOld = persistentPersons.getRolesCollection();
            Collection<Roles> rolesCollectionNew = persons.getRolesCollection();
            Collection<Telephones> telephonesCollectionOld = persistentPersons.getTelephonesCollection();
            Collection<Telephones> telephonesCollectionNew = persons.getTelephonesCollection();
            Collection<Actions> actionsCollectionOld = persistentPersons.getActionsCollection();
            Collection<Actions> actionsCollectionNew = persons.getActionsCollection();
            List<String> illegalOrphanMessages = null;
            for (Actions actionsCollectionOldActions : actionsCollectionOld) {
                if (!actionsCollectionNew.contains(actionsCollectionOldActions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actions " + actionsCollectionOldActions + " since its persons field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (addressesNew != null) {
                addressesNew = em.getReference(addressesNew.getClass(), addressesNew.getAddressid());
                persons.setAddresses(addressesNew);
            }
            if (insurancesNew != null) {
                insurancesNew = em.getReference(insurancesNew.getClass(), insurancesNew.getInsuranceid());
                persons.setInsurances(insurancesNew);
            }
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getUsersid());
                persons.setUsers(usersNew);
            }
            Collection<Roles> attachedRolesCollectionNew = new ArrayList<Roles>();
            for (Roles rolesCollectionNewRolesToAttach : rolesCollectionNew) {
                rolesCollectionNewRolesToAttach = em.getReference(rolesCollectionNewRolesToAttach.getClass(), rolesCollectionNewRolesToAttach.getRolesPK());
                attachedRolesCollectionNew.add(rolesCollectionNewRolesToAttach);
            }
            rolesCollectionNew = attachedRolesCollectionNew;
            persons.setRolesCollection(rolesCollectionNew);
            Collection<Telephones> attachedTelephonesCollectionNew = new ArrayList<Telephones>();
            for (Telephones telephonesCollectionNewTelephonesToAttach : telephonesCollectionNew) {
                telephonesCollectionNewTelephonesToAttach = em.getReference(telephonesCollectionNewTelephonesToAttach.getClass(), telephonesCollectionNewTelephonesToAttach.getTelephoneid());
                attachedTelephonesCollectionNew.add(telephonesCollectionNewTelephonesToAttach);
            }
            telephonesCollectionNew = attachedTelephonesCollectionNew;
            persons.setTelephonesCollection(telephonesCollectionNew);
            Collection<Actions> attachedActionsCollectionNew = new ArrayList<Actions>();
            for (Actions actionsCollectionNewActionsToAttach : actionsCollectionNew) {
                actionsCollectionNewActionsToAttach = em.getReference(actionsCollectionNewActionsToAttach.getClass(), actionsCollectionNewActionsToAttach.getActionsPK());
                attachedActionsCollectionNew.add(actionsCollectionNewActionsToAttach);
            }
            actionsCollectionNew = attachedActionsCollectionNew;
            persons.setActionsCollection(actionsCollectionNew);
            persons = em.merge(persons);
            if (addressesOld != null && !addressesOld.equals(addressesNew)) {
                addressesOld.getPersonsCollection().remove(persons);
                addressesOld = em.merge(addressesOld);
            }
            if (addressesNew != null && !addressesNew.equals(addressesOld)) {
                addressesNew.getPersonsCollection().add(persons);
                addressesNew = em.merge(addressesNew);
            }
            if (insurancesOld != null && !insurancesOld.equals(insurancesNew)) {
                insurancesOld.getPersonsCollection().remove(persons);
                insurancesOld = em.merge(insurancesOld);
            }
            if (insurancesNew != null && !insurancesNew.equals(insurancesOld)) {
                insurancesNew.getPersonsCollection().add(persons);
                insurancesNew = em.merge(insurancesNew);
            }
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getPersonsCollection().remove(persons);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getPersonsCollection().add(persons);
                usersNew = em.merge(usersNew);
            }
            for (Roles rolesCollectionOldRoles : rolesCollectionOld) {
                if (!rolesCollectionNew.contains(rolesCollectionOldRoles)) {
                    rolesCollectionOldRoles.getPersonsCollection().remove(persons);
                    rolesCollectionOldRoles = em.merge(rolesCollectionOldRoles);
                }
            }
            for (Roles rolesCollectionNewRoles : rolesCollectionNew) {
                if (!rolesCollectionOld.contains(rolesCollectionNewRoles)) {
                    rolesCollectionNewRoles.getPersonsCollection().add(persons);
                    rolesCollectionNewRoles = em.merge(rolesCollectionNewRoles);
                }
            }
            for (Telephones telephonesCollectionOldTelephones : telephonesCollectionOld) {
                if (!telephonesCollectionNew.contains(telephonesCollectionOldTelephones)) {
                    telephonesCollectionOldTelephones.getPersonsCollection().remove(persons);
                    telephonesCollectionOldTelephones = em.merge(telephonesCollectionOldTelephones);
                }
            }
            for (Telephones telephonesCollectionNewTelephones : telephonesCollectionNew) {
                if (!telephonesCollectionOld.contains(telephonesCollectionNewTelephones)) {
                    telephonesCollectionNewTelephones.getPersonsCollection().add(persons);
                    telephonesCollectionNewTelephones = em.merge(telephonesCollectionNewTelephones);
                }
            }
            for (Actions actionsCollectionNewActions : actionsCollectionNew) {
                if (!actionsCollectionOld.contains(actionsCollectionNewActions)) {
                    Persons oldPersonsOfActionsCollectionNewActions = actionsCollectionNewActions.getPersons();
                    actionsCollectionNewActions.setPersons(persons);
                    actionsCollectionNewActions = em.merge(actionsCollectionNewActions);
                    if (oldPersonsOfActionsCollectionNewActions != null && !oldPersonsOfActionsCollectionNewActions.equals(persons)) {
                        oldPersonsOfActionsCollectionNewActions.getActionsCollection().remove(actionsCollectionNewActions);
                        oldPersonsOfActionsCollectionNewActions = em.merge(oldPersonsOfActionsCollectionNewActions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PersonsPK id = persons.getPersonsPK();
                if (findPersons(id) == null) {
                    throw new NonexistentEntityException("The persons with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PersonsPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persons persons;
            try {
                persons = em.getReference(Persons.class, id);
                persons.getPersonsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persons with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Actions> actionsCollectionOrphanCheck = persons.getActionsCollection();
            for (Actions actionsCollectionOrphanCheckActions : actionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persons (" + persons + ") cannot be destroyed since the Actions " + actionsCollectionOrphanCheckActions + " in its actionsCollection field has a non-nullable persons field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Addresses addresses = persons.getAddresses();
            if (addresses != null) {
                addresses.getPersonsCollection().remove(persons);
                addresses = em.merge(addresses);
            }
            Insurances insurances = persons.getInsurances();
            if (insurances != null) {
                insurances.getPersonsCollection().remove(persons);
                insurances = em.merge(insurances);
            }
            Users users = persons.getUsers();
            if (users != null) {
                users.getPersonsCollection().remove(persons);
                users = em.merge(users);
            }
            Collection<Roles> rolesCollection = persons.getRolesCollection();
            for (Roles rolesCollectionRoles : rolesCollection) {
                rolesCollectionRoles.getPersonsCollection().remove(persons);
                rolesCollectionRoles = em.merge(rolesCollectionRoles);
            }
            Collection<Telephones> telephonesCollection = persons.getTelephonesCollection();
            for (Telephones telephonesCollectionTelephones : telephonesCollection) {
                telephonesCollectionTelephones.getPersonsCollection().remove(persons);
                telephonesCollectionTelephones = em.merge(telephonesCollectionTelephones);
            }
            em.remove(persons);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persons> findPersonsEntities() {
        return findPersonsEntities(true, -1, -1);
    }

    public List<Persons> findPersonsEntities(int maxResults, int firstResult) {
        return findPersonsEntities(false, maxResults, firstResult);
    }

    private List<Persons> findPersonsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persons.class));
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

    public Persons findPersons(PersonsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persons.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persons> rt = cq.from(Persons.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
