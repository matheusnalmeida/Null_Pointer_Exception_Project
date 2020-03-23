package model.domain;

public class Medico extends Usuario {

    private String crm;

    public Medico(String nome, String matricula, String senha, String crm) {
        super(nome, matricula, senha);
        this.crm = crm;
    }

    public Medico() {
        super(null, null, null);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
