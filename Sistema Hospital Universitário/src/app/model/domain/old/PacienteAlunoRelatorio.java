/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.domain.old;

import java.time.LocalDateTime;

public class PacienteAlunoRelatorio {

    private int codigo;
    private LocalDateTime dataAtendimento;
    private Paciente paciente;
    private Aluno aluno;
    private Relatorio relatorio;

    public PacienteAlunoRelatorio() {
    }
    
    public PacienteAlunoRelatorio(int codigo, LocalDateTime dataConsulta, Paciente paciente, Aluno aluno) {
        this.codigo = codigo;
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

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataConsulta(LocalDateTime dataAtendimento) {
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