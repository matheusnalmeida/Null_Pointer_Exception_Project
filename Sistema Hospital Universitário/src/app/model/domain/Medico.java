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

    public Medico() {
    }
    
    public Medico(String crm) {
        this.crm = crm;
    }

    public Medico(String nome, String matricula, String senha, String crm) {
        super(nome, matricula, senha);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
