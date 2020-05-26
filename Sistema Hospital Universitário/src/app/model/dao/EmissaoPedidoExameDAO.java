package app.model.dao;

import app.model.domain.EmissaoPedidoExame;
import app.model.domain.PedidoExame;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmissaoPedidoExameDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public EmissaoPedidoExameDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(EmissaoPedidoExame emissaoPedidoExame) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(emissaoPedidoExame);
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

    public boolean update(EmissaoPedidoExame emissaoPedidoExame) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(emissaoPedidoExame);
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

    public boolean delete(EmissaoPedidoExame emissaoPedidoExame) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.remove(this.em.getReference(EmissaoPedidoExame.class, emissaoPedidoExame.getPedidoExame()));
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

    public boolean deletarPorPedidoExame(PedidoExame pedidoExame) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            Query query = this.em.createQuery("DELETE FROM " + EmissaoPedidoExame.class.getName() + " WHERE pedidoExame_codigo = :pedidoExame_codigo");
            query.setParameter("pedidoExame_codigo", pedidoExame.getCodigo());
            query.executeUpdate();
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception exception) {
            result = false;
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
            this.em.close();
        }
        return result;
    }
}
