package model.domain;

import java.util.HashMap;

public class Professor extends Autenticavel {

    private String nome;
    private String titulacao;
    private String crm;
    private HashMap<String, Aluno> alunos;

    public Professor() {
        super(null, null);
    }

    public Professor(String nome, String titulacao, String crm, String matricula, String senha) {
        super(matricula, senha);
        this.nome = nome;
        this.titulacao = titulacao;
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public HashMap<String, Aluno> getAlunos() {
        return alunos;
    }
}
