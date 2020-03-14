package model.domain;

public class Medico extends Autenticavel {

    private String nome;
    private String crm;

    public Medico(String nome, String crm, String matricula, String senha) {
        super(matricula, senha);
        this.nome = nome;
        this.crm = crm;
    }

    public Medico() {
        super(null, null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
