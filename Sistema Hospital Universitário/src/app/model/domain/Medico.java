package app.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "medicos")
public class Medico extends Usuario {
    
    @Column(unique = true, nullable = false)
    private String crm;

    public Medico(String nome, String matricula, String senha, String crm, int id) {
        super(nome, matricula, senha, id);
        this.crm = crm;
    }

    public Medico() {
        super(null, null, null, 0);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
