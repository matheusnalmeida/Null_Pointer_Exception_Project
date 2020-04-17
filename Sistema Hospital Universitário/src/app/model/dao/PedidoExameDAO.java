package app.model.dao;

import app.model.domain.PedidoExame;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PedidoExameDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PedidoExameDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(PedidoExame pedidoExame) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(pedidoExame);
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception exception) {
            result = false;
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
        return result;
    }

    public PedidoExame read(PedidoExame pedidoExame) {
        PedidoExame retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(PedidoExame.class, pedidoExame.getCodigo());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public boolean update(PedidoExame pedidoExame) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(pedidoExame);
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception exception) {
            result = false;
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
        return result;
    }

    public boolean delete(PedidoExame pedidoExame) {
        boolean result = true;
        try {
            this.em.getTransaction().begin();
            /*pedidoExame = this.em.find(PedidoExame.class, pedidoExame.getCodigo());
            this.em.remove(pedidoExame);*/
            this.em.remove(this.em.getReference(PedidoExame.class, pedidoExame.getCodigo()));
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception ex) {
            result = false;
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<PedidoExame> selectAll() {
        List<PedidoExame> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + PedidoExame.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
