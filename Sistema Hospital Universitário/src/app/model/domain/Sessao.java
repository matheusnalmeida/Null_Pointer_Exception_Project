package app.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sessoes")
public class Sessao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @ManyToOne
    private Usuario usuario;
    @Column
    private LocalDateTime dataInicioSessao;
    @Column
    private LocalDateTime dataTerminoSessao;

    public Sessao() {
    }

    public Sessao(int codigo, Usuario usuario, LocalDateTime dataInicioSessao, LocalDateTime dataTerminoSessao) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.dataInicioSessao = dataInicioSessao;
        this.dataTerminoSessao = dataTerminoSessao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataInicioSessao() {
        return dataInicioSessao;
    }

    public void setDataInicioSessao(LocalDateTime dataInicioSessao) {
        this.dataInicioSessao = dataInicioSessao;
    }

    public LocalDateTime getDataTerminoSessao() {
        return dataTerminoSessao;
    }

    public void setDataTerminoSessao(LocalDateTime dataTerminoSessao) {
        this.dataTerminoSessao = dataTerminoSessao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.codigo;
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
        final Sessao other = (Sessao) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
}
