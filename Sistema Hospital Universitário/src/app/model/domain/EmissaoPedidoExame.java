package app.model.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emissao_pedidos_exames")
public class EmissaoPedidoExame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column
    private String dataEmissao;
    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private PedidoExame pedidoExame;

    public EmissaoPedidoExame() {
    }

    public EmissaoPedidoExame(String dataEmissao, Medico medico, Paciente paciente, PedidoExame pedidoExame) {
        this.dataEmissao = dataEmissao;
        this.medico = medico;
        this.paciente = paciente;
        this.pedidoExame = pedidoExame;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public PedidoExame getPedidoExame() {
        return pedidoExame;
    }

    public void setPedidoExame(PedidoExame pedidoExame) {
        this.pedidoExame = pedidoExame;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.codigo;
        hash = 97 * hash + Objects.hashCode(this.medico);
        hash = 97 * hash + Objects.hashCode(this.paciente);
        hash = 97 * hash + Objects.hashCode(this.pedidoExame);
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
        final EmissaoPedidoExame other = (EmissaoPedidoExame) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.pedidoExame, other.pedidoExame)) {
            return false;
        }
        return true;
    }
}
