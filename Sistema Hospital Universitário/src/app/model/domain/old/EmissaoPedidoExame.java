
package app.model.domain.old;

import java.time.LocalDateTime;

public class EmissaoPedidoExame {
    
    private LocalDateTime dataEmissao;
    private Medico medico;
    private Paciente paciente;
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
}