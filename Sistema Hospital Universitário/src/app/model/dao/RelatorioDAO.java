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

    public boolean create(Relatorio relatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(relatorio);
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

    public boolean update(Relatorio relatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(relatorio);
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

    public boolean delete(Relatorio relatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            relatorio = this.em.find(Relatorio.class, relatorio.getCodigo());
            this.em.remove(relatorio);
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
