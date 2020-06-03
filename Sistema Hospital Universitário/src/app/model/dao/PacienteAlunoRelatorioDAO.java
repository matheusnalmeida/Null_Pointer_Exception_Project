package app.model.dao;

import app.model.domain.ImagemRelatorio;
import app.model.domain.PacienteAlunoRelatorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PacienteAlunoRelatorioDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PacienteAlunoRelatorioDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(PacienteAlunoRelatorio pacienteAlunoRelatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(pacienteAlunoRelatorio);
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

    public PacienteAlunoRelatorio read(PacienteAlunoRelatorio pacienteAlunoRelatorio) {
        PacienteAlunoRelatorio retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(PacienteAlunoRelatorio.class, pacienteAlunoRelatorio.getCodigo());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public boolean update(PacienteAlunoRelatorio pacienteAlunoRelatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(pacienteAlunoRelatorio);
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception exception) {
            exception.printStackTrace();
            result = false;
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
        return result;
    }

    public boolean delete(PacienteAlunoRelatorio pacienteAlunoRelatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            Query query = this.em.createQuery("DELETE FROM " + ImagemRelatorio.class.getName() + " WHERE pacienteAlunoRelatorio_codigo = :codigo");
            query.setParameter("codigo", pacienteAlunoRelatorio.getCodigo());
            query.executeUpdate();
            this.em.remove(this.em.getReference(PacienteAlunoRelatorio.class, pacienteAlunoRelatorio.getCodigo()));
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

    @SuppressWarnings("unchecked")
    public List<PacienteAlunoRelatorio> selectAll() {
        List<PacienteAlunoRelatorio> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("FROM "
                    + PacienteAlunoRelatorio.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
