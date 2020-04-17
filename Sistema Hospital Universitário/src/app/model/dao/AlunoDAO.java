package app.model.dao;

import app.model.domain.Aluno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlunoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public AlunoDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(Aluno aluno) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(aluno);
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

    public Aluno read(Aluno aluno) {
        Aluno retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(Aluno.class, aluno.getMatricula());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public boolean update(Aluno aluno) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(aluno);
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

    public boolean delete(Aluno aluno) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            /*aluno = this.em.find(Aluno.class, aluno.getMatricula());
            this.em.remove(aluno);*/
            this.em.remove(this.em.getReference(Aluno.class, aluno.getMatricula()));
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
    public List<Aluno> selectAll() {
        List<Aluno> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + Aluno.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
