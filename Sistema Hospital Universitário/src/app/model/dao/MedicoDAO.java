package app.model.dao;

import app.model.domain.Medico;
import app.model.domain.Relatorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MedicoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public MedicoDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(Medico medico) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(medico);
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

    public boolean update(Medico medico) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(medico);
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

    public boolean delete(Medico medico) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            /*medico = this.em.find(Medico.class, medico.getMatricula());
            this.em.remove(medico);*/
            this.em.remove(this.em.getReference(Medico.class, medico.getMatricula()));
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

    /**
     * 
     * @param medico
     * @return lista de relatórios autorizados pelo médico
     */
    public List<Relatorio> getRelatorios(Medico medico) {
        List<Relatorio> retorno = null;
        try {
            this.em.getTransaction().begin();
            Query query = this.em.createQuery("FROM " + Relatorio.class.getName() + " WHERE medicoAutorizacao_matricula = :medicoAutorizacao_matricula");
            query.setParameter("medicoAutorizacao_matricula", medico.getMatricula());
            retorno = (List<Relatorio>) query.getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    /**
     *
     * @return Lista de relatórios que ainda não foram autorizados
     */
    public List<Relatorio> getRelatorios() {
        List<Relatorio> retorno = null;
        try {
            this.em.getTransaction().begin();
            Query query = this.em.createQuery("FROM " + Relatorio.class.getName() + " WHERE medicoAutorizacao_matricula is null");
            retorno = (List<Relatorio>) query.getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
