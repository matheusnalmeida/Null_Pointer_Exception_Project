package app.model.dao;

import app.model.domain.EmissaoPedidoExame;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmissaoPedidoExameDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public EmissaoPedidoExameDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public void create(EmissaoPedidoExame emissaoPedidoExame) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(emissaoPedidoExame);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public EmissaoPedidoExame read(EmissaoPedidoExame emissaoPedidoExame) {
        EmissaoPedidoExame retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(EmissaoPedidoExame.class, emissaoPedidoExame.getPedidoExame());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public void update(EmissaoPedidoExame emissaoPedidoExame) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(emissaoPedidoExame);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public void delete(EmissaoPedidoExame emissaoPedidoExame) {
        try {
            this.em.getTransaction().begin();
            emissaoPedidoExame = this.em.find(EmissaoPedidoExame.class, emissaoPedidoExame.getPedidoExame());
            this.em.remove(emissaoPedidoExame);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<EmissaoPedidoExame> selectAll() {
        List<EmissaoPedidoExame> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + EmissaoPedidoExame.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
