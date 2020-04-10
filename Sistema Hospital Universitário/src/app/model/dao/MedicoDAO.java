package app.model.dao;

import app.model.domain.Medico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MedicoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public MedicoDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public void create(Medico medico) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(medico);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public Medico read(Medico medico) {
        Medico retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(Medico.class, medico.getMatricula());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public void update(Medico medico) {
        try {
            this.delete(medico);
            this.em.getTransaction().begin();
            this.em.merge(medico);
            this.em.getTransaction().commit();
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    public void delete(Medico medico) {
        try {
            this.em.getTransaction().begin();
            medico = this.em.find(Medico.class, medico.getMatricula());
            this.em.remove(medico);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
        } finally {
            this.emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Medico> selectAll() {
        List<Medico> retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.createQuery("from "
                    + Medico.class.getName()).getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
