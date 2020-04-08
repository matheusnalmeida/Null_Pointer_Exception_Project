package app.model.domain;

import java.time.LocalDate;

public class Aluno extends Usuario {

    private int anoResidencia;
    private LocalDate dataNascimento;
    private Professor professor;

    public Aluno() {
        super(null, null, null);
    }

    public Aluno(String nome, String matricula, String senha, int anoResidencia, LocalDate dataNascimento, Professor professor) {
        super(nome, matricula, senha);
        this.anoResidencia = anoResidencia;
        this.dataNascimento = dataNascimento;
        this.professor = professor;
    }

    public Aluno(String nome, String matricula, String senha, int anoResidencia, LocalDate dataNascimento) {
        super(nome, matricula, senha);
        this.anoResidencia = anoResidencia;
        this.dataNascimento = dataNascimento;
    }

    public int getAnoResidencia() {
        return anoResidencia;
    }

    public void setAnoResidencia(int anoResidencia) {
        this.anoResidencia = anoResidencia;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
