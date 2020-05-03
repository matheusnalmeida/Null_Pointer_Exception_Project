package app.model.dao;

import app.model.domain.Sessao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessaoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public SessaoDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(Sessao sessao) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(sessao);
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

    public Sessao read(Sessao sessao) {
        Sessao retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(Sessao.class, sessao.getCodigo());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public boolean update(Sessao sessao) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(sessao);
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

    public boolean delete(Sessao sessao) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            /*sessao = this.em.find(Sessao.class, sessao.getCodigo());
            this.em.remove(sessao);*/
            this.em.remove(this.em.getReference(Sessao.class, sessao.getCodigo()));
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
    public List<Sessao> selectAll() {
        List<Sessao> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + Sessao.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
