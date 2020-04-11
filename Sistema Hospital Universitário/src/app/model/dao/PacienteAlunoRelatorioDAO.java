package app.model.dao;

import app.model.domain.PacienteAlunoRelatorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PacienteAlunoRelatorioDAO {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public PacienteAlunoRelatorioDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public void create(PacienteAlunoRelatorio pacienteAlunoRelatorio) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(pacienteAlunoRelatorio);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
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

    public void update(PacienteAlunoRelatorio pacienteAlunoRelatorio) {
        try {
            this.delete(pacienteAlunoRelatorio);
            this.em.getTransaction().begin();
            this.em.merge(pacienteAlunoRelatorio);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public void delete(PacienteAlunoRelatorio pacienteAlunoRelatorio) {
        try {
            this.em.getTransaction().begin();
            pacienteAlunoRelatorio = this.em.find(PacienteAlunoRelatorio.class, pacienteAlunoRelatorio.getCodigo());
            this.em.remove(pacienteAlunoRelatorio);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<PacienteAlunoRelatorio> selectAll() {
        List<PacienteAlunoRelatorio> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + PacienteAlunoRelatorio.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
