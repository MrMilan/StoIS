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
import entity.Insurances;
import entity.PersonHasAddress;
import entity.Persons;
import java.util.ArrayList;
import java.util.Collection;
import entity.PersonsHasRoles;
import entity.Users;
import entity.PersonsHasTelephones;
import entity.Roles;
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

    public void create(Persons persons) {
        if (persons.getPersonHasAddressCollection() == null) {
            persons.setPersonHasAddressCollection(new ArrayList<PersonHasAddress>());
        }
        if (persons.getPersonsHasRolesCollection() == null) {
            persons.setPersonsHasRolesCollection(new ArrayList<PersonsHasRoles>());
        }
        if (persons.getUsersCollection() == null) {
            persons.setUsersCollection(new ArrayList<Users>());
        }
        if (persons.getPersonsHasTelephonesCollection() == null) {
            persons.setPersonsHasTelephonesCollection(new ArrayList<PersonsHasTelephones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insurances insurancesInsuranceid = persons.getInsurancesInsuranceid();
            if (insurancesInsuranceid != null) {
                insurancesInsuranceid = em.getReference(insurancesInsuranceid.getClass(), insurancesInsuranceid.getInsuranceid());
                persons.setInsurancesInsuranceid(insurancesInsuranceid);
            }
            Collection<PersonHasAddress> attachedPersonHasAddressCollection = new ArrayList<PersonHasAddress>();
            for (PersonHasAddress personHasAddressCollectionPersonHasAddressToAttach : persons.getPersonHasAddressCollection()) {
                personHasAddressCollectionPersonHasAddressToAttach = em.getReference(personHasAddressCollectionPersonHasAddressToAttach.getClass(), personHasAddressCollectionPersonHasAddressToAttach.getIdpersonHasAddress());
                attachedPersonHasAddressCollection.add(personHasAddressCollectionPersonHasAddressToAttach);
            }
            persons.setPersonHasAddressCollection(attachedPersonHasAddressCollection);
            Collection<PersonsHasRoles> attachedPersonsHasRolesCollection = new ArrayList<PersonsHasRoles>();
            for (PersonsHasRoles personsHasRolesCollectionPersonsHasRolesToAttach : persons.getPersonsHasRolesCollection()) {
                personsHasRolesCollectionPersonsHasRolesToAttach = em.getReference(personsHasRolesCollectionPersonsHasRolesToAttach.getClass(), personsHasRolesCollectionPersonsHasRolesToAttach.getIdphr());
                attachedPersonsHasRolesCollection.add(personsHasRolesCollectionPersonsHasRolesToAttach);
            }
            persons.setPersonsHasRolesCollection(attachedPersonsHasRolesCollection);
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : persons.getUsersCollection()) {
                usersCollectionUsersToAttach = em.getReference(usersCollectionUsersToAttach.getClass(), usersCollectionUsersToAttach.getUsersid());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            persons.setUsersCollection(attachedUsersCollection);
            Collection<PersonsHasTelephones> attachedPersonsHasTelephonesCollection = new ArrayList<PersonsHasTelephones>();
            for (PersonsHasTelephones personsHasTelephonesCollectionPersonsHasTelephonesToAttach : persons.getPersonsHasTelephonesCollection()) {
                personsHasTelephonesCollectionPersonsHasTelephonesToAttach = em.getReference(personsHasTelephonesCollectionPersonsHasTelephonesToAttach.getClass(), personsHasTelephonesCollectionPersonsHasTelephonesToAttach.getIdpht());
                attachedPersonsHasTelephonesCollection.add(personsHasTelephonesCollectionPersonsHasTelephonesToAttach);
            }
            persons.setPersonsHasTelephonesCollection(attachedPersonsHasTelephonesCollection);
            em.persist(persons);
            if (insurancesInsuranceid != null) {
                insurancesInsuranceid.getPersonsCollection().add(persons);
                insurancesInsuranceid = em.merge(insurancesInsuranceid);
            }
            for (PersonHasAddress personHasAddressCollectionPersonHasAddress : persons.getPersonHasAddressCollection()) {
                Persons oldPersonsPersonidOfPersonHasAddressCollectionPersonHasAddress = personHasAddressCollectionPersonHasAddress.getPersonsPersonid();
                personHasAddressCollectionPersonHasAddress.setPersonsPersonid(persons);
                personHasAddressCollectionPersonHasAddress = em.merge(personHasAddressCollectionPersonHasAddress);
                if (oldPersonsPersonidOfPersonHasAddressCollectionPersonHasAddress != null) {
                    oldPersonsPersonidOfPersonHasAddressCollectionPersonHasAddress.getPersonHasAddressCollection().remove(personHasAddressCollectionPersonHasAddress);
                    oldPersonsPersonidOfPersonHasAddressCollectionPersonHasAddress = em.merge(oldPersonsPersonidOfPersonHasAddressCollectionPersonHasAddress);
                }
            }
            for (PersonsHasRoles personsHasRolesCollectionPersonsHasRoles : persons.getPersonsHasRolesCollection()) {
                Persons oldPersonsPersonidOfPersonsHasRolesCollectionPersonsHasRoles = personsHasRolesCollectionPersonsHasRoles.getPersonsPersonid();
                personsHasRolesCollectionPersonsHasRoles.setPersonsPersonid(persons);
                personsHasRolesCollectionPersonsHasRoles = em.merge(personsHasRolesCollectionPersonsHasRoles);
                if (oldPersonsPersonidOfPersonsHasRolesCollectionPersonsHasRoles != null) {
                    oldPersonsPersonidOfPersonsHasRolesCollectionPersonsHasRoles.getPersonsHasRolesCollection().remove(personsHasRolesCollectionPersonsHasRoles);
                    oldPersonsPersonidOfPersonsHasRolesCollectionPersonsHasRoles = em.merge(oldPersonsPersonidOfPersonsHasRolesCollectionPersonsHasRoles);
                }
            }
            for (Users usersCollectionUsers : persons.getUsersCollection()) {
                Persons oldPersonsPersonidOfUsersCollectionUsers = usersCollectionUsers.getPersonsPersonid();
                usersCollectionUsers.setPersonsPersonid(persons);
                usersCollectionUsers = em.merge(usersCollectionUsers);
                if (oldPersonsPersonidOfUsersCollectionUsers != null) {
                    oldPersonsPersonidOfUsersCollectionUsers.getUsersCollection().remove(usersCollectionUsers);
                    oldPersonsPersonidOfUsersCollectionUsers = em.merge(oldPersonsPersonidOfUsersCollectionUsers);
                }
            }
            for (PersonsHasTelephones personsHasTelephonesCollectionPersonsHasTelephones : persons.getPersonsHasTelephonesCollection()) {
                Persons oldPersonsPersonidOfPersonsHasTelephonesCollectionPersonsHasTelephones = personsHasTelephonesCollectionPersonsHasTelephones.getPersonsPersonid();
                personsHasTelephonesCollectionPersonsHasTelephones.setPersonsPersonid(persons);
                personsHasTelephonesCollectionPersonsHasTelephones = em.merge(personsHasTelephonesCollectionPersonsHasTelephones);
                if (oldPersonsPersonidOfPersonsHasTelephonesCollectionPersonsHasTelephones != null) {
                    oldPersonsPersonidOfPersonsHasTelephonesCollectionPersonsHasTelephones.getPersonsHasTelephonesCollection().remove(personsHasTelephonesCollectionPersonsHasTelephones);
                    oldPersonsPersonidOfPersonsHasTelephonesCollectionPersonsHasTelephones = em.merge(oldPersonsPersonidOfPersonsHasTelephonesCollectionPersonsHasTelephones);
                }
            }
            em.getTransaction().commit();
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
            Persons persistentPersons = em.find(Persons.class, persons.getPersonid());
            Insurances insurancesInsuranceidOld = persistentPersons.getInsurancesInsuranceid();
            Insurances insurancesInsuranceidNew = persons.getInsurancesInsuranceid();
            Collection<PersonHasAddress> personHasAddressCollectionOld = persistentPersons.getPersonHasAddressCollection();
            Collection<PersonHasAddress> personHasAddressCollectionNew = persons.getPersonHasAddressCollection();
            Collection<PersonsHasRoles> personsHasRolesCollectionOld = persistentPersons.getPersonsHasRolesCollection();
            Collection<PersonsHasRoles> personsHasRolesCollectionNew = persons.getPersonsHasRolesCollection();
            Collection<Users> usersCollectionOld = persistentPersons.getUsersCollection();
            Collection<Users> usersCollectionNew = persons.getUsersCollection();
            Collection<PersonsHasTelephones> personsHasTelephonesCollectionOld = persistentPersons.getPersonsHasTelephonesCollection();
            Collection<PersonsHasTelephones> personsHasTelephonesCollectionNew = persons.getPersonsHasTelephonesCollection();
            List<String> illegalOrphanMessages = null;
            for (PersonHasAddress personHasAddressCollectionOldPersonHasAddress : personHasAddressCollectionOld) {
                if (!personHasAddressCollectionNew.contains(personHasAddressCollectionOldPersonHasAddress)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonHasAddress " + personHasAddressCollectionOldPersonHasAddress + " since its personsPersonid field is not nullable.");
                }
            }
            for (PersonsHasRoles personsHasRolesCollectionOldPersonsHasRoles : personsHasRolesCollectionOld) {
                if (!personsHasRolesCollectionNew.contains(personsHasRolesCollectionOldPersonsHasRoles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonsHasRoles " + personsHasRolesCollectionOldPersonsHasRoles + " since its personsPersonid field is not nullable.");
                }
            }
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Users " + usersCollectionOldUsers + " since its personsPersonid field is not nullable.");
                }
            }
            for (PersonsHasTelephones personsHasTelephonesCollectionOldPersonsHasTelephones : personsHasTelephonesCollectionOld) {
                if (!personsHasTelephonesCollectionNew.contains(personsHasTelephonesCollectionOldPersonsHasTelephones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonsHasTelephones " + personsHasTelephonesCollectionOldPersonsHasTelephones + " since its personsPersonid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (insurancesInsuranceidNew != null) {
                insurancesInsuranceidNew = em.getReference(insurancesInsuranceidNew.getClass(), insurancesInsuranceidNew.getInsuranceid());
                persons.setInsurancesInsuranceid(insurancesInsuranceidNew);
            }
            Collection<PersonHasAddress> attachedPersonHasAddressCollectionNew = new ArrayList<PersonHasAddress>();
            for (PersonHasAddress personHasAddressCollectionNewPersonHasAddressToAttach : personHasAddressCollectionNew) {
                personHasAddressCollectionNewPersonHasAddressToAttach = em.getReference(personHasAddressCollectionNewPersonHasAddressToAttach.getClass(), personHasAddressCollectionNewPersonHasAddressToAttach.getIdpersonHasAddress());
                attachedPersonHasAddressCollectionNew.add(personHasAddressCollectionNewPersonHasAddressToAttach);
            }
            personHasAddressCollectionNew = attachedPersonHasAddressCollectionNew;
            persons.setPersonHasAddressCollection(personHasAddressCollectionNew);
            Collection<PersonsHasRoles> attachedPersonsHasRolesCollectionNew = new ArrayList<PersonsHasRoles>();
            for (PersonsHasRoles personsHasRolesCollectionNewPersonsHasRolesToAttach : personsHasRolesCollectionNew) {
                personsHasRolesCollectionNewPersonsHasRolesToAttach = em.getReference(personsHasRolesCollectionNewPersonsHasRolesToAttach.getClass(), personsHasRolesCollectionNewPersonsHasRolesToAttach.getIdphr());
                attachedPersonsHasRolesCollectionNew.add(personsHasRolesCollectionNewPersonsHasRolesToAttach);
            }
            personsHasRolesCollectionNew = attachedPersonsHasRolesCollectionNew;
            persons.setPersonsHasRolesCollection(personsHasRolesCollectionNew);
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = em.getReference(usersCollectionNewUsersToAttach.getClass(), usersCollectionNewUsersToAttach.getUsersid());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            persons.setUsersCollection(usersCollectionNew);
            Collection<PersonsHasTelephones> attachedPersonsHasTelephonesCollectionNew = new ArrayList<PersonsHasTelephones>();
            for (PersonsHasTelephones personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach : personsHasTelephonesCollectionNew) {
                personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach = em.getReference(personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach.getClass(), personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach.getIdpht());
                attachedPersonsHasTelephonesCollectionNew.add(personsHasTelephonesCollectionNewPersonsHasTelephonesToAttach);
            }
            personsHasTelephonesCollectionNew = attachedPersonsHasTelephonesCollectionNew;
            persons.setPersonsHasTelephonesCollection(personsHasTelephonesCollectionNew);
            persons = em.merge(persons);
            if (insurancesInsuranceidOld != null && !insurancesInsuranceidOld.equals(insurancesInsuranceidNew)) {
                insurancesInsuranceidOld.getPersonsCollection().remove(persons);
                insurancesInsuranceidOld = em.merge(insurancesInsuranceidOld);
            }
            if (insurancesInsuranceidNew != null && !insurancesInsuranceidNew.equals(insurancesInsuranceidOld)) {
                insurancesInsuranceidNew.getPersonsCollection().add(persons);
                insurancesInsuranceidNew = em.merge(insurancesInsuranceidNew);
            }
            for (PersonHasAddress personHasAddressCollectionNewPersonHasAddress : personHasAddressCollectionNew) {
                if (!personHasAddressCollectionOld.contains(personHasAddressCollectionNewPersonHasAddress)) {
                    Persons oldPersonsPersonidOfPersonHasAddressCollectionNewPersonHasAddress = personHasAddressCollectionNewPersonHasAddress.getPersonsPersonid();
                    personHasAddressCollectionNewPersonHasAddress.setPersonsPersonid(persons);
                    personHasAddressCollectionNewPersonHasAddress = em.merge(personHasAddressCollectionNewPersonHasAddress);
                    if (oldPersonsPersonidOfPersonHasAddressCollectionNewPersonHasAddress != null && !oldPersonsPersonidOfPersonHasAddressCollectionNewPersonHasAddress.equals(persons)) {
                        oldPersonsPersonidOfPersonHasAddressCollectionNewPersonHasAddress.getPersonHasAddressCollection().remove(personHasAddressCollectionNewPersonHasAddress);
                        oldPersonsPersonidOfPersonHasAddressCollectionNewPersonHasAddress = em.merge(oldPersonsPersonidOfPersonHasAddressCollectionNewPersonHasAddress);
                    }
                }
            }
            for (PersonsHasRoles personsHasRolesCollectionNewPersonsHasRoles : personsHasRolesCollectionNew) {
                if (!personsHasRolesCollectionOld.contains(personsHasRolesCollectionNewPersonsHasRoles)) {
                    Persons oldPersonsPersonidOfPersonsHasRolesCollectionNewPersonsHasRoles = personsHasRolesCollectionNewPersonsHasRoles.getPersonsPersonid();
                    personsHasRolesCollectionNewPersonsHasRoles.setPersonsPersonid(persons);
                    personsHasRolesCollectionNewPersonsHasRoles = em.merge(personsHasRolesCollectionNewPersonsHasRoles);
                    if (oldPersonsPersonidOfPersonsHasRolesCollectionNewPersonsHasRoles != null && !oldPersonsPersonidOfPersonsHasRolesCollectionNewPersonsHasRoles.equals(persons)) {
                        oldPersonsPersonidOfPersonsHasRolesCollectionNewPersonsHasRoles.getPersonsHasRolesCollection().remove(personsHasRolesCollectionNewPersonsHasRoles);
                        oldPersonsPersonidOfPersonsHasRolesCollectionNewPersonsHasRoles = em.merge(oldPersonsPersonidOfPersonsHasRolesCollectionNewPersonsHasRoles);
                    }
                }
            }
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    Persons oldPersonsPersonidOfUsersCollectionNewUsers = usersCollectionNewUsers.getPersonsPersonid();
                    usersCollectionNewUsers.setPersonsPersonid(persons);
                    usersCollectionNewUsers = em.merge(usersCollectionNewUsers);
                    if (oldPersonsPersonidOfUsersCollectionNewUsers != null && !oldPersonsPersonidOfUsersCollectionNewUsers.equals(persons)) {
                        oldPersonsPersonidOfUsersCollectionNewUsers.getUsersCollection().remove(usersCollectionNewUsers);
                        oldPersonsPersonidOfUsersCollectionNewUsers = em.merge(oldPersonsPersonidOfUsersCollectionNewUsers);
                    }
                }
            }
            for (PersonsHasTelephones personsHasTelephonesCollectionNewPersonsHasTelephones : personsHasTelephonesCollectionNew) {
                if (!personsHasTelephonesCollectionOld.contains(personsHasTelephonesCollectionNewPersonsHasTelephones)) {
                    Persons oldPersonsPersonidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones = personsHasTelephonesCollectionNewPersonsHasTelephones.getPersonsPersonid();
                    personsHasTelephonesCollectionNewPersonsHasTelephones.setPersonsPersonid(persons);
                    personsHasTelephonesCollectionNewPersonsHasTelephones = em.merge(personsHasTelephonesCollectionNewPersonsHasTelephones);
                    if (oldPersonsPersonidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones != null && !oldPersonsPersonidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones.equals(persons)) {
                        oldPersonsPersonidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones.getPersonsHasTelephonesCollection().remove(personsHasTelephonesCollectionNewPersonsHasTelephones);
                        oldPersonsPersonidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones = em.merge(oldPersonsPersonidOfPersonsHasTelephonesCollectionNewPersonsHasTelephones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persons.getPersonid();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persons persons;
            try {
                persons = em.getReference(Persons.class, id);
                persons.getPersonid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persons with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PersonHasAddress> personHasAddressCollectionOrphanCheck = persons.getPersonHasAddressCollection();
            for (PersonHasAddress personHasAddressCollectionOrphanCheckPersonHasAddress : personHasAddressCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persons (" + persons + ") cannot be destroyed since the PersonHasAddress " + personHasAddressCollectionOrphanCheckPersonHasAddress + " in its personHasAddressCollection field has a non-nullable personsPersonid field.");
            }
            Collection<PersonsHasRoles> personsHasRolesCollectionOrphanCheck = persons.getPersonsHasRolesCollection();
            for (PersonsHasRoles personsHasRolesCollectionOrphanCheckPersonsHasRoles : personsHasRolesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persons (" + persons + ") cannot be destroyed since the PersonsHasRoles " + personsHasRolesCollectionOrphanCheckPersonsHasRoles + " in its personsHasRolesCollection field has a non-nullable personsPersonid field.");
            }
            Collection<Users> usersCollectionOrphanCheck = persons.getUsersCollection();
            for (Users usersCollectionOrphanCheckUsers : usersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persons (" + persons + ") cannot be destroyed since the Users " + usersCollectionOrphanCheckUsers + " in its usersCollection field has a non-nullable personsPersonid field.");
            }
            Collection<PersonsHasTelephones> personsHasTelephonesCollectionOrphanCheck = persons.getPersonsHasTelephonesCollection();
            for (PersonsHasTelephones personsHasTelephonesCollectionOrphanCheckPersonsHasTelephones : personsHasTelephonesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persons (" + persons + ") cannot be destroyed since the PersonsHasTelephones " + personsHasTelephonesCollectionOrphanCheckPersonsHasTelephones + " in its personsHasTelephonesCollection field has a non-nullable personsPersonid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Insurances insurancesInsuranceid = persons.getInsurancesInsuranceid();
            if (insurancesInsuranceid != null) {
                insurancesInsuranceid.getPersonsCollection().remove(persons);
                insurancesInsuranceid = em.merge(insurancesInsuranceid);
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
    
    public List<Persons> findPersonsEntitiesNotCanceledNotAssignet() {
        return findPersonsForUsers();
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

    private List<Persons> findPersonsForUsers() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p from Persons AS p LEFT JOIN  p.usersCollection AS u LEFT JOIN p.personsHasRolesCollection AS phr LEFT JOIN phr.rolesRoleid AS r WHERE (p.canceled=false AND p.archived=false) AND r.roleid != 4 AND p.personid != (SELECT p.personid from Persons AS p LEFT JOIN  p.usersCollection AS u)");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Persons findPersons(Integer id) {
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

    public List<Roles> getUsersRoles(Integer usersId) {
        EntityManager em = getEntityManager();
        try {
            Query q;
            q = em.createQuery("SELECT r from Persons AS p LEFT JOIN  p.usersCollection AS u LEFT JOIN p.personsHasRolesCollection AS phr LEFT JOIN phr.rolesRoleid AS r WHERE u.usersid=:userId");
            q.setParameter("userId", usersId);
            return (List<Roles>) q.getResultList();
        } catch (Exception e) {
//           e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

}
