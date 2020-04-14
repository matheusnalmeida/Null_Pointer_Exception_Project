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

    public boolean create(Paciente paciente) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(paciente);
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

    public boolean update(Paciente paciente) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(paciente);
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

    public boolean delete(Paciente paciente) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            paciente = this.em.find(Paciente.class, paciente.getCpf());
            this.em.remove(paciente);
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
