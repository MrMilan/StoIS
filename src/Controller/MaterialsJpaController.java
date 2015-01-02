/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import entity.Materials;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Usedmaterials;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class MaterialsJpaController implements Serializable {

    public MaterialsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materials materials) {
        if (materials.getUsedmaterialsCollection() == null) {
            materials.setUsedmaterialsCollection(new ArrayList<Usedmaterials>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Usedmaterials> attachedUsedmaterialsCollection = new ArrayList<Usedmaterials>();
            for (Usedmaterials usedmaterialsCollectionUsedmaterialsToAttach : materials.getUsedmaterialsCollection()) {
                usedmaterialsCollectionUsedmaterialsToAttach = em.getReference(usedmaterialsCollectionUsedmaterialsToAttach.getClass(), usedmaterialsCollectionUsedmaterialsToAttach.getIdusedmateria());
                attachedUsedmaterialsCollection.add(usedmaterialsCollectionUsedmaterialsToAttach);
            }
            materials.setUsedmaterialsCollection(attachedUsedmaterialsCollection);
            em.persist(materials);
            for (Usedmaterials usedmaterialsCollectionUsedmaterials : materials.getUsedmaterialsCollection()) {
                Materials oldMaterialsMaterialidOfUsedmaterialsCollectionUsedmaterials = usedmaterialsCollectionUsedmaterials.getMaterialsMaterialid();
                usedmaterialsCollectionUsedmaterials.setMaterialsMaterialid(materials);
                usedmaterialsCollectionUsedmaterials = em.merge(usedmaterialsCollectionUsedmaterials);
                if (oldMaterialsMaterialidOfUsedmaterialsCollectionUsedmaterials != null) {
                    oldMaterialsMaterialidOfUsedmaterialsCollectionUsedmaterials.getUsedmaterialsCollection().remove(usedmaterialsCollectionUsedmaterials);
                    oldMaterialsMaterialidOfUsedmaterialsCollectionUsedmaterials = em.merge(oldMaterialsMaterialidOfUsedmaterialsCollectionUsedmaterials);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materials materials) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materials persistentMaterials = em.find(Materials.class, materials.getMaterialid());
            Collection<Usedmaterials> usedmaterialsCollectionOld = persistentMaterials.getUsedmaterialsCollection();
            Collection<Usedmaterials> usedmaterialsCollectionNew = materials.getUsedmaterialsCollection();
            List<String> illegalOrphanMessages = null;
            for (Usedmaterials usedmaterialsCollectionOldUsedmaterials : usedmaterialsCollectionOld) {
                if (!usedmaterialsCollectionNew.contains(usedmaterialsCollectionOldUsedmaterials)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usedmaterials " + usedmaterialsCollectionOldUsedmaterials + " since its materialsMaterialid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Usedmaterials> attachedUsedmaterialsCollectionNew = new ArrayList<Usedmaterials>();
            for (Usedmaterials usedmaterialsCollectionNewUsedmaterialsToAttach : usedmaterialsCollectionNew) {
                usedmaterialsCollectionNewUsedmaterialsToAttach = em.getReference(usedmaterialsCollectionNewUsedmaterialsToAttach.getClass(), usedmaterialsCollectionNewUsedmaterialsToAttach.getIdusedmateria());
                attachedUsedmaterialsCollectionNew.add(usedmaterialsCollectionNewUsedmaterialsToAttach);
            }
            usedmaterialsCollectionNew = attachedUsedmaterialsCollectionNew;
            materials.setUsedmaterialsCollection(usedmaterialsCollectionNew);
            materials = em.merge(materials);
            for (Usedmaterials usedmaterialsCollectionNewUsedmaterials : usedmaterialsCollectionNew) {
                if (!usedmaterialsCollectionOld.contains(usedmaterialsCollectionNewUsedmaterials)) {
                    Materials oldMaterialsMaterialidOfUsedmaterialsCollectionNewUsedmaterials = usedmaterialsCollectionNewUsedmaterials.getMaterialsMaterialid();
                    usedmaterialsCollectionNewUsedmaterials.setMaterialsMaterialid(materials);
                    usedmaterialsCollectionNewUsedmaterials = em.merge(usedmaterialsCollectionNewUsedmaterials);
                    if (oldMaterialsMaterialidOfUsedmaterialsCollectionNewUsedmaterials != null && !oldMaterialsMaterialidOfUsedmaterialsCollectionNewUsedmaterials.equals(materials)) {
                        oldMaterialsMaterialidOfUsedmaterialsCollectionNewUsedmaterials.getUsedmaterialsCollection().remove(usedmaterialsCollectionNewUsedmaterials);
                        oldMaterialsMaterialidOfUsedmaterialsCollectionNewUsedmaterials = em.merge(oldMaterialsMaterialidOfUsedmaterialsCollectionNewUsedmaterials);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materials.getMaterialid();
                if (findMaterials(id) == null) {
                    throw new NonexistentEntityException("The materials with id " + id + " no longer exists.");
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
            Materials materials;
            try {
                materials = em.getReference(Materials.class, id);
                materials.getMaterialid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materials with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Usedmaterials> usedmaterialsCollectionOrphanCheck = materials.getUsedmaterialsCollection();
            for (Usedmaterials usedmaterialsCollectionOrphanCheckUsedmaterials : usedmaterialsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Materials (" + materials + ") cannot be destroyed since the Usedmaterials " + usedmaterialsCollectionOrphanCheckUsedmaterials + " in its usedmaterialsCollection field has a non-nullable materialsMaterialid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(materials);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materials> findMaterialsEntities() {
        return findMaterialsEntities(true, -1, -1);
    }

    public List<Materials> findMaterialsEntities(int maxResults, int firstResult) {
        return findMaterialsEntities(false, maxResults, firstResult);
    }

    private List<Materials> findMaterialsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materials.class));
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

    public Materials findMaterials(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materials.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materials> rt = cq.from(Materials.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
