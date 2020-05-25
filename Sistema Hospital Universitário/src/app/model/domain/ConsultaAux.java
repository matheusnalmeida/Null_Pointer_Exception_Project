package app.model.domain;

public class ConsultaAux {

    private int codigoConsulta;
    private String cpfPaciente;
    private String nomePaciente;
    private String dataConsulta;
    private String medicoAutorizacao;
    private String descricao;
    private String dataAutorizacao;
    private String matriculaAluno;
    private String nomeAluno;
    private String nomeMedicoAutorizacao;

    public ConsultaAux() {
    }

    public ConsultaAux(int codigoConsulta, String cpfPaciente, String nomePaciente,
            String dataConsulta, String medicoAutorizacao, String descricao, String dataAutorizacao) {
        this.codigoConsulta = codigoConsulta;
        this.cpfPaciente = cpfPaciente;
        this.nomePaciente = nomePaciente;
        this.dataConsulta = dataConsulta;
        this.medicoAutorizacao = medicoAutorizacao;
        this.descricao = descricao;
        this.dataAutorizacao = dataAutorizacao;
    }

    public int getCodigoConsulta() {
        return codigoConsulta;
    }

    public void setCodigoConsulta(int codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getMedicoAutorizacao() {
        return medicoAutorizacao;
    }

    public void setMedicoAutorizacao(String medicoAutorizacao) {
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

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeMedicoAutorizacao() {
        return nomeMedicoAutorizacao;
    }

    public void setNomeMedicoAutorizacao(String nomeMedicoAutorizacao) {
        this.nomeMedicoAutorizacao = nomeMedicoAutorizacao;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.codigoConsulta;
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
        final ConsultaAux other = (ConsultaAux) obj;
        if (this.codigoConsulta != other.codigoConsulta) {
            return false;
        }
        return true;
    }
}
