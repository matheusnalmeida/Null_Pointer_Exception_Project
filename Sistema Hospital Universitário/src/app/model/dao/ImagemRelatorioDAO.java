package app.model.dao;

import app.model.domain.ImagemRelatorio;
import app.model.domain.PacienteAlunoRelatorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ImagemRelatorioDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ImagemRelatorioDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean create(ImagemRelatorio imagemRelatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.persist(imagemRelatorio);
            this.em.getTransaction().commit();
            result = true;
        } catch (Exception exception) {
            this.em.getTransaction().rollback();
            result = false;
            System.out.println(exception.getMessage());
        } finally {
            this.emf.close();
        }
        return result;
    }

    public ImagemRelatorio read(ImagemRelatorio imagemRelatorio) {
        ImagemRelatorio retorno = null;
        try {
            this.em.getTransaction().begin();
            retorno = this.em.find(ImagemRelatorio.class, imagemRelatorio.getCodigo());
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }

    public boolean update(ImagemRelatorio imagemRelatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.merge(imagemRelatorio);
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

    public boolean delete(ImagemRelatorio imagemRelatorio) {
        boolean result = false;
        try {
            this.em.getTransaction().begin();
            this.em.remove(this.em.getReference(ImagemRelatorio.class, imagemRelatorio.getCodigo()));
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
    public List<ImagemRelatorio> selectAll(PacienteAlunoRelatorio pacientealunoRelatorio) {
        List<ImagemRelatorio> retorno = null;
        try {
            this.em.getTransaction().begin();
            Query query = this.em.createQuery("SELECT imagemRelatorio FROM "
                    + ImagemRelatorio.class.getName() + " imagemRelatorio WHERE imagemRelatorio.pacienteAlunoRelatorio_codigo = :pacientealunoRelatorioCodigo");
            query.setParameter("pacientealunoRelatorioCodigo", pacientealunoRelatorio.getCodigo());
            retorno = query.getResultList();
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        return retorno;
    }
}
