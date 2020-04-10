package app.model.dao;

import app.model.domain.Relatorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RelatorioDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public RelatorioDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public void create(Relatorio relatorio) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(relatorio);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public Relatorio read(Relatorio relatorio) {
        Relatorio retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(Relatorio.class, relatorio.getCodigo());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public void update(Relatorio relatorio) {
        try {
            this.delete(relatorio);
            this.em.getTransaction().begin();
            this.em.merge(relatorio);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public void delete(Relatorio relatorio) {
        try {
            this.em.getTransaction().begin();
            relatorio = this.em.find(Relatorio.class, relatorio.getCodigo());
            this.em.remove(relatorio);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Relatorio> selectAll() {
        List<Relatorio> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + Relatorio.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
