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

    public void create(Sessao sessao) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(sessao);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
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

    public void update(Sessao sessao) {
        try {
            this.delete(sessao);
            this.em.getTransaction().begin();
            this.em.persist(sessao);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public void delete(Sessao sessao) {
        try {
            this.em.getTransaction().begin();
            sessao = this.em.find(Sessao.class, sessao.getCodigo());
            this.em.remove(sessao);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
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
