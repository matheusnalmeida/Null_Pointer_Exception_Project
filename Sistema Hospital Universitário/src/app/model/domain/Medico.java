package app.model.domain;

import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "medicos")
public class Medico extends Usuario {
    
    @Column(unique = true, nullable = false)
    private String crm;
    @Transient
    private HashMap<Integer, Relatorio> relatoriosAutorizados;
    @Transient
    private ArrayList<EmissaoPedidoExame> pedidosExamesEmitidos;

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
