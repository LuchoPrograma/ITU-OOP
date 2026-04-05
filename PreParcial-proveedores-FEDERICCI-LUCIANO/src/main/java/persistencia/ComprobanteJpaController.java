/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import logica.Recibo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author lucia
 */
public class ComprobanteJpaController implements Serializable {

    public ComprobanteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("proveedoresPU");
    }
    
    public ComprobanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recibo recibo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(recibo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recibo recibo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            recibo = em.merge(recibo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = recibo.getNumero();
                if (findRecibo(id) == null) {
                    throw new NonexistentEntityException("The recibo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recibo recibo;
            try {
                recibo = em.getReference(Recibo.class, id);
                recibo.getNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recibo with id " + id + " no longer exists.", enfe);
            }
            em.remove(recibo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recibo> findReciboEntities() {
        return findReciboEntities(true, -1, -1);
    }

    public List<Recibo> findReciboEntities(int maxResults, int firstResult) {
        return findReciboEntities(false, maxResults, firstResult);
    }

    private List<Recibo> findReciboEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recibo.class));
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

    public Recibo findRecibo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recibo.class, id);
        } finally {
            em.close();
        }
    }

    public int getReciboCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recibo> rt = cq.from(Recibo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
