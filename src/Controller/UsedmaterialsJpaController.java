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
import entity.Materials;
import entity.Usedmaterials;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Milhouse
 */
public class UsedmaterialsJpaController implements Serializable {

    public UsedmaterialsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usedmaterials usedmaterials) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materials materialsMaterialid = usedmaterials.getMaterialsMaterialid();
            if (materialsMaterialid != null) {
                materialsMaterialid = em.getReference(materialsMaterialid.getClass(), materialsMaterialid.getMaterialid());
                usedmaterials.setMaterialsMaterialid(materialsMaterialid);
            }
            em.persist(usedmaterials);
            if (materialsMaterialid != null) {
                materialsMaterialid.getUsedmaterialsCollection().add(usedmaterials);
                materialsMaterialid = em.merge(materialsMaterialid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usedmaterials usedmaterials) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usedmaterials persistentUsedmaterials = em.find(Usedmaterials.class, usedmaterials.getIdusedmateria());
            Materials materialsMaterialidOld = persistentUsedmaterials.getMaterialsMaterialid();
            Materials materialsMaterialidNew = usedmaterials.getMaterialsMaterialid();
            if (materialsMaterialidNew != null) {
                materialsMaterialidNew = em.getReference(materialsMaterialidNew.getClass(), materialsMaterialidNew.getMaterialid());
                usedmaterials.setMaterialsMaterialid(materialsMaterialidNew);
            }
            usedmaterials = em.merge(usedmaterials);
            if (materialsMaterialidOld != null && !materialsMaterialidOld.equals(materialsMaterialidNew)) {
                materialsMaterialidOld.getUsedmaterialsCollection().remove(usedmaterials);
                materialsMaterialidOld = em.merge(materialsMaterialidOld);
            }
            if (materialsMaterialidNew != null && !materialsMaterialidNew.equals(materialsMaterialidOld)) {
                materialsMaterialidNew.getUsedmaterialsCollection().add(usedmaterials);
                materialsMaterialidNew = em.merge(materialsMaterialidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usedmaterials.getIdusedmateria();
                if (findUsedmaterials(id) == null) {
                    throw new NonexistentEntityException("The usedmaterials with id " + id + " no longer exists.");
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
            Usedmaterials usedmaterials;
            try {
                usedmaterials = em.getReference(Usedmaterials.class, id);
                usedmaterials.getIdusedmateria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usedmaterials with id " + id + " no longer exists.", enfe);
            }
            Materials materialsMaterialid = usedmaterials.getMaterialsMaterialid();
            if (materialsMaterialid != null) {
                materialsMaterialid.getUsedmaterialsCollection().remove(usedmaterials);
                materialsMaterialid = em.merge(materialsMaterialid);
            }
            em.remove(usedmaterials);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usedmaterials> findUsedmaterialsEntities() {
        return findUsedmaterialsEntities(true, -1, -1);
    }

    public List<Usedmaterials> findUsedmaterialsEntities(int maxResults, int firstResult) {
        return findUsedmaterialsEntities(false, maxResults, firstResult);
    }

    private List<Usedmaterials> findUsedmaterialsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usedmaterials.class));
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

    public Usedmaterials findUsedmaterials(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usedmaterials.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsedmaterialsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usedmaterials> rt = cq.from(Usedmaterials.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
