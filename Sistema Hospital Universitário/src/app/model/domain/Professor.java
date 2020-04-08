package app.model.domain;

import java.util.HashMap;

public class Professor extends Medico {

    private String titulacao;
    private HashMap<String, Aluno> alunos;

    public Professor() {
        super(null, null, null, null);
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

    public HashMap<String, Aluno> getAlunos() {
        return alunos;
    }
}
