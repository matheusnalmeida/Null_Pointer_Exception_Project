package app.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "alunos")
public class Aluno extends Usuario {

    @Column(nullable = false)
    private int anoResidencia;
    @Column(nullable = false)
    private LocalDate dataNascimento;
    @ManyToOne
    private Professor professor;
    @Transient
    private ArrayList<PacienteAlunoRelatorio> atendimentosRealizados;

    public Aluno() {
        super(null, null, null, 0);
    }

    public Aluno(String nome, String matricula, String senha, int anoResidencia, LocalDate dataNascimento, Professor professor, int id) {
        super(nome, matricula, senha, id);
        this.anoResidencia = anoResidencia;
        this.dataNascimento = dataNascimento;
        this.professor = professor;
    }

    public Aluno(String nome, String matricula, String senha, int anoResidencia, LocalDate dataNascimento, int id) {
        super(nome, matricula, senha, id);
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

    @Override
    public String toString() {
        return this.getNome() + "   " + this.getMatricula();
    }
}