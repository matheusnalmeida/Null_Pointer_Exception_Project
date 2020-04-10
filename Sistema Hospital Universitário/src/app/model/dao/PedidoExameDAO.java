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

    public void create(PedidoExame pedidoExame) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(pedidoExame);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
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

    public void update(PedidoExame pedidoExame) {
        try {
            this.delete(pedidoExame);
            this.em.getTransaction().begin();
            this.em.merge(pedidoExame);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public void delete(PedidoExame pedidoExame) {
        try {
            this.em.getTransaction().begin();
            pedidoExame = this.em.find(PedidoExame.class, pedidoExame.getCodigo());
            this.em.remove(pedidoExame);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
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
