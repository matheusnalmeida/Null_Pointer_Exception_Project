package app.model.domain;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relatorios")
public class Relatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    //@Column(nullable = false)
    @Column
    private String dataRelatorio;
    @Column(nullable = false)
    private String descricao;
    @ManyToOne
    private Medico medicoAutorizacao;
    @Column
    private String dataAutorizacao;
    @Column
    private File relatorio;

    public Relatorio() {
    }

    public Relatorio(String dataRelatorio, String descricao, Medico medicoAutorizacao,
            String dataAutorizacao, File relatorio) {
        this.dataRelatorio = dataRelatorio;
        this.descricao = descricao;
        this.medicoAutorizacao = medicoAutorizacao;
        this.dataAutorizacao = dataAutorizacao;
        this.relatorio = relatorio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDataRelatorio() {
        return dataRelatorio;
    }

    public void setDataRelatorio(String dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Medico getMedicoAutorizacao() {
        return medicoAutorizacao;
    }

    public void setMedicoAutorizacao(Medico medicoAutorizacao) {
        this.medicoAutorizacao = medicoAutorizacao;
    }

    public String getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(String dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public File getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(File relatorio) {
        this.relatorio = relatorio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.codigo;
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
        final Relatorio other = (Relatorio) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
}
