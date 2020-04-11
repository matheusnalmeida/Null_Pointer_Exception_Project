package app.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emissao_pedidos_exames")
public class EmissaoPedidoExame implements Serializable {

    @Column
    private LocalDateTime dataEmissao;
    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    @Id
    private PedidoExame pedidoExame;

    public EmissaoPedidoExame() {
    }

    public EmissaoPedidoExame(LocalDateTime dataEmissao, Medico medico, Paciente paciente, PedidoExame pedidoExame) {
        this.dataEmissao = dataEmissao;
        this.medico = medico;
        this.paciente = paciente;
        this.pedidoExame = pedidoExame;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
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

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (!Objects.equals(this.pedidoExame, other.pedidoExame)) {
            return false;
        }
        return true;
    }
}
