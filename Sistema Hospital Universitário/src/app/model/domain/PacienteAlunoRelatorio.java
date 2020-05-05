package app.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pacientes_alunos_relatorios")
public class PacienteAlunoRelatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(nullable = false)
    private String dataAtendimento;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Aluno aluno;
    //@ManyToOne
    @OneToOne
    private Relatorio relatorio;

    public PacienteAlunoRelatorio() {
    }

    public PacienteAlunoRelatorio(String dataConsulta, Paciente paciente, Aluno aluno) {
        this.dataAtendimento = dataConsulta;
        this.paciente = paciente;
        this.aluno = aluno;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(String dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.codigo;
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
        final PacienteAlunoRelatorio other = (PacienteAlunoRelatorio) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
}
