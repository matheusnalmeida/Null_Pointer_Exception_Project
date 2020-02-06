package app.models;

import java.time.LocalDate;

public class Aluno {

    private String nome;
    private long matricula;
    private LocalDate anoResidencia;

    public Aluno(String nome, long matricula, LocalDate anoResidencia) {
        this.nome = nome;
        this.matricula = matricula;
        this.anoResidencia = anoResidencia;
    }

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public LocalDate getAnoResidencia() {
        return anoResidencia;
    }

    public void setAnoResidencia(LocalDate anoResidencia) {
        this.anoResidencia = anoResidencia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.matricula ^ (this.matricula >>> 32));
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
        final Aluno other = (Aluno) obj;
        if (this.matricula != other.matricula) {
            return false;
        }
        return true;
    }
}
