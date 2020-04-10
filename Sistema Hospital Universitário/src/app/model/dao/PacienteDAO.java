package app.model.dao;

import app.model.domain.Paciente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PacienteDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PacienteDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public void create(Paciente paciente) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(paciente);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public Paciente read(Paciente paciente) {
        Paciente retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(Paciente.class, paciente.getCpf());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public void update(Paciente paciente) {
        try {
            this.delete(paciente);
            this.em.getTransaction().begin();
            this.em.persist(paciente);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public void delete(Paciente paciente) {
        try {
            this.em.getTransaction().begin();
            paciente = this.em.find(Paciente.class, paciente.getCpf());
            this.em.remove(paciente);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Paciente> selectAll() {
        List<Paciente> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + Paciente.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
