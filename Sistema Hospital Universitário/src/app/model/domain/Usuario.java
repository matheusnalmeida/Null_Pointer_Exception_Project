package app.model.domain;

import app.model.dao.UsuarioDAO;
import app.utilits.EncryptionPassword;
import app.utilits.CredenciaisInvalidasException;
import app.utilits.Sistema;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable {

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Id
    private String matricula;
    @Column(nullable = false)
    private String senha;
    /*@Column(name = "id", unique = true)
    private int id;*/

    public Usuario() {
    }

    /*public Usuario(String nome, String matricula, String senha, int id) {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = EncryptionPassword.encrypt(senha);
        this.id = id;
    }*/

    public Usuario(String nome, String matricula, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
    }
    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }

    public static boolean autenticar(String matricula, String senha) throws CredenciaisInvalidasException {
        senha = EncryptionPassword.encrypt(senha);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar(matricula, senha);
        if (usuario == null) {
            return false;
        }
        Sessao sessao = new Sessao(usuario, LocalDateTime.now(), null);
        Sistema.setSessao(sessao);
        return true;
    }
}
