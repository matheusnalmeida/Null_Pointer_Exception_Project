package app.model.dao;

import app.model.domain.Aluno;
import app.model.domain.Medico;
import app.model.domain.Professor;
import app.model.domain.Usuario;
import app.utilits.CredenciaisInvalidasException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UsuarioDAO() {
        this.emf = Persistence.createEntityManagerFactory("hosp");
        this.em = this.emf.createEntityManager();
    }

    public boolean existUser(String matricula) throws Exception {
        boolean result = true;
        this.em.getTransaction().begin();
        if (this.em.find(Usuario.class, matricula) == null) {
            result = false;
        }
        this.emf.close();
        return result;
    }

    public Usuario autenticar(String matricula, String senha) throws CredenciaisInvalidasException {
        Usuario usuario = null;
        char matriculaChar[] = matricula.toCharArray();
        boolean except = false;
        if (matriculaChar.length > 11 || matriculaChar.length < 11) {
            throw new CredenciaisInvalidasException();
        }
        try {
            this.em.getTransaction().begin();
            if (this.em.find(Usuario.class, matricula) != null && this.em.find(Usuario.class, matricula).getSenha().equals(senha)) {
                switch (matriculaChar[10]) {
                    case 'A':
                        usuario = (Aluno) this.em.find(Aluno.class, matricula);
                        break;
                    case 'M':
                        usuario = (Medico) this.em.find(Medico.class, matricula);
                        break;
                    case 'P':
                        usuario = (Professor) this.em.find(Professor.class, matricula);
                        break;
                    default:
                        this.emf.close();
                        except = true;
                }
            } else {
                this.emf.close();
                except = true;
            }
        } catch (Exception exception) {
        } finally {
            this.emf.close();
        }
        if (except) {
            throw new CredenciaisInvalidasException();
        }
        return usuario;
    }
}
