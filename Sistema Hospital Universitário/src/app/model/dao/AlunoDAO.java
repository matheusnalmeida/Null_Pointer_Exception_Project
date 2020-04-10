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

    public void create(Aluno aluno) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(aluno);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
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

    public void update(Aluno aluno) {
        try {
            this.delete(aluno);
            this.em.getTransaction().begin();
            this.em.persist(aluno);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public void delete(Aluno aluno) {
        try {
            this.em.getTransaction().begin();
            aluno = this.em.find(Aluno.class, aluno.getMatricula());
            this.em.remove(aluno);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
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
