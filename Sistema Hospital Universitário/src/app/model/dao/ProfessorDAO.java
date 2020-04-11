package app.model.dao;

import app.model.domain.Professor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProfessorDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ProfessorDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(Professor professor) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(professor);
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
            result = false;
        } finally {
            this.emf.close();
        }
        return result;
    }

    public Professor read(Professor professor) {
        Professor retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(Professor.class, professor.getMatricula());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public boolean update(Professor professor) {
        boolean result = false;
        try {
            result = this.delete(professor) || result;
            if (result) {
                this.em.getTransaction().begin();
                this.em.merge(professor);
                this.em.getTransaction().commit();
                result = true;
            }
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
            result = false;
        } finally {
            this.emf.close();
        }
        return result;
    }

    public boolean delete(Professor professor) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            professor = this.em.find(Professor.class, professor.getMatricula());
            this.em.remove(professor);
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            result = false;
        } finally {
            this.emf.close();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Professor> selectAll() {
        List<Professor> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + Professor.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
