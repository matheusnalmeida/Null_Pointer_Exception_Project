package app.model.domain;

public class PedidoExameAux {
    
    private int codigo;
    private String dataExame;
    private String nomePaciente;
    private String hipoteseDiagnostica;
    private String recomendacoes;
    private String tipoExame;
    private String cpfPaciente;

    public PedidoExameAux() {
    }

    public PedidoExameAux(int codigo, String dataExame, String nomePaciente, String hipoteseDiagnostica, String recomendacoes, String tipoExame) {
        this.codigo = codigo;
        this.dataExame = dataExame;
        this.nomePaciente = nomePaciente;
        this.hipoteseDiagnostica = hipoteseDiagnostica;
        this.recomendacoes = recomendacoes;
        this.tipoExame = tipoExame;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDataExame() {
        return dataExame;
    }

    public void setDataExame(String dataExame) {
        this.dataExame = dataExame;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getHipoteseDiagnostica() {
        return hipoteseDiagnostica;
    }

    public void setHipoteseDiagnostica(String hipoteseDiagnostica) {
        this.hipoteseDiagnostica = hipoteseDiagnostica;
    }

    public String getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(String recomendacoes) {
        this.recomendacoes = recomendacoes;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.codigo;
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
        final PedidoExameAux other = (PedidoExameAux) obj;
        return this.codigo == other.codigo;
    }
}
