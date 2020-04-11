package app.model.domain.old;

import java.time.LocalDate;

public class PedidoExame {

    private int codigo;
    private String recomendacoes;
    private LocalDate dataExame;
    private String hipoteseDiagnostica;
    private String tipoExame;
    private Paciente paciente;

    public PedidoExame() {
    }

    public PedidoExame(int codigo, String recomendacoes, LocalDate dataExame, String hipoteseDiagnostica, String tipoExame, Paciente paciente) {
        this.codigo = codigo;
        this.recomendacoes = recomendacoes;
        this.dataExame = dataExame;
        this.hipoteseDiagnostica = hipoteseDiagnostica;
        this.tipoExame = tipoExame;
        this.paciente = paciente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(String recomendacoes) {
        this.recomendacoes = recomendacoes;
    }

    public LocalDate getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDate dataExame) {
        this.dataExame = dataExame;
    }

    public String getHipoteseDiagnostica() {
        return hipoteseDiagnostica;
    }

    public void setHipoteseDiagnostica(String hipoteseDiagnostica) {
        this.hipoteseDiagnostica = hipoteseDiagnostica;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.codigo;
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
        final PedidoExame other = (PedidoExame) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
}