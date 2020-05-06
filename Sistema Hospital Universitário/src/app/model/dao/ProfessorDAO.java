package app.model.dao;

import app.model.domain.Aluno;
import app.model.domain.Professor;
import app.model.domain.Relatorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
            result = false;
            this.em.getTransaction().rollback();
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
            this.em.getTransaction().begin();
            this.em.merge(professor);
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

    public boolean delete(Professor professor) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            /*professor = this.em.find(Professor.class, professor.getMatricula());
            this.em.remove(professor);*/
            this.em.remove(this.em.getReference(Professor.class, professor.getMatricula()));
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
    public List<Professor> selectAll() {
        List<Professor> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("FROM "
                    + Professor.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public List<Aluno> getAlunos(Professor professor) {
        List<Aluno> retorno = null;
        try {
            this.em.getTransaction().begin();
            Query query = this.em.createQuery("FROM " + Aluno.class.getName() + " WHERE professor_matricula = :professor_matricula");
            query.setParameter("professor_matricula", professor.getMatricula());
            retorno = (List<Aluno>) query.getResultList();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public List<Relatorio> getRelatoriosAlunos(Professor professor) {
        List<Relatorio> retorno = null;
        List<Aluno> alunos = this.getAlunos(professor);
        AlunoDAO alunoDAO = new AlunoDAO();
        if (alunos != null) {
            //Para cada aluno obter a lista de relatórios concatenando-as em "retorno"
            for (Aluno aluno : alunos) {
                List<Relatorio> relatorios = alunoDAO.getRelatorios(aluno);
                if (relatorios != null) {
                    retorno.addAll(relatorios);
                }
            }
        }
        return retorno;
    }

    public List<Aluno> getAlunosSemProfessor() {
        List<Aluno> retorno = null;
        try {
            this.em.getTransaction().begin();
            Query query = this.em.createQuery("FROM " + Aluno.class.getName() + " WHERE professor_matricula is null");
            retorno = (List<Aluno>) query.getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
