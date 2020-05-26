package app.model.dao;

import app.model.domain.RelatorioTemplate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RelatorioTemplateDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public RelatorioTemplateDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(RelatorioTemplate relatorioTemplate) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(relatorioTemplate);
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

    public RelatorioTemplate read(RelatorioTemplate relatorioTemplate) {
        RelatorioTemplate retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(RelatorioTemplate.class, relatorioTemplate.getCodigo());
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
            this.em.close();
        }
        return retorno;
    }

    public boolean update(RelatorioTemplate relatorioTemplate) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(relatorioTemplate);
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception exception) {
            result = false;
            this.em.getTransaction().rollback();
        } finally {
            this.em.close();
            this.emf.close();
        }
        return result;
    }

    public boolean delete(RelatorioTemplate relatorioTemplate) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.remove(this.em.getReference(RelatorioTemplate.class, relatorioTemplate.getCodigo()));
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            result = false;
            this.em.getTransaction().rollback();
        } finally {
            this.em.close();
            this.emf.close();
        }
        return result;
    }

    public List<RelatorioTemplate> selectAll() {
        List<RelatorioTemplate> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("FROM "
                    + RelatorioTemplate.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
            this.em.close();
        }
        return retorno;
    }
}
