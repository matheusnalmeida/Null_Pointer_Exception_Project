package app.model.domain;

import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "professores")
public class Professor extends Medico {

    @Column(nullable = false)
    private String titulacao;
    @Transient
    private HashMap<String, Aluno> alunos;

    /*public Professor() {
        super(null, null, null, null, 0);
    }

    public Professor(String nome, String matricula, String senha, String crm, String titulacao, int id) {
        super(nome, matricula, senha, crm, id);
        this.titulacao = titulacao;
    }*/
    
    
    public Professor() {
    }

    public Professor(String nome, String matricula, String senha, String crm, String titulacao) {
        super(nome, matricula, senha, crm);
        this.titulacao = titulacao;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
}
