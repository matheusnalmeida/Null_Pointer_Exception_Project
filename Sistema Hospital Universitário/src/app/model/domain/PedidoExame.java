package app.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos_exames")
public class PedidoExame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(nullable = false)
    private String recomendacoes;
    @Column(nullable = false)
    private LocalDate dataExame;
    @Column(nullable = false)
    private String hipoteseDiagnostica;
    @Column(nullable = false)
    private String tipoExame;

    public PedidoExame() {
    }

    public PedidoExame(int codigo, String recomendacoes, LocalDate dataExame, String hipoteseDiagnostica, String tipoExame) {
        this.codigo = codigo;
        this.recomendacoes = recomendacoes;
        this.dataExame = dataExame;
        this.hipoteseDiagnostica = hipoteseDiagnostica;
        this.tipoExame = tipoExame;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(String recomendacoes) {
        this.recomendacoes = recomendacoes;
    }

    public LocalDate getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDate dataExame) {
        this.dataExame = dataExame;
    }

    public String getHipoteseDiagnostica() {
        return hipoteseDiagnostica;
    }

    public void setHipoteseDiagnostica(String hipoteseDiagnostica) {
        this.hipoteseDiagnostica = hipoteseDiagnostica;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.codigo;
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
        final PedidoExame other = (PedidoExame) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
}
