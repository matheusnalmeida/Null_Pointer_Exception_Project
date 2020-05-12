package app.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pacientes_alunos_relatorios")
public class PacienteAlunoRelatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Medico medicoAutorizacao;
    @Column(nullable = false)
    private String dataAtendimento;
    @Column(nullable = false, length = 1000)
    private String descricao;
    private String dataAutorizacao;

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

    public Medico getMedicoAutorizacao() {
        return medicoAutorizacao;
    }

    public void setMedicoAutorizacao(Medico medicoAutorizacao) {
        this.medicoAutorizacao = medicoAutorizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(String dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.codigo;
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
