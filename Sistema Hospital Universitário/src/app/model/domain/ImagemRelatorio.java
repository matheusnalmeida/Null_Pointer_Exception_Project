package app.model.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imagem_relatorio")
public class ImagemRelatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @ManyToOne
    private PacienteAlunoRelatorio pacienteAlunoRelatorio;
    @Lob
    @Column(name = "arquivo", columnDefinition = "LONGBLOB")
    private Byte[] arquivo;

    public ImagemRelatorio() {
    }

    public ImagemRelatorio(PacienteAlunoRelatorio pacienteAlunoRelatorio, Byte[] arquivo) {
        this.pacienteAlunoRelatorio = pacienteAlunoRelatorio;
        this.arquivo = arquivo;
    }

    public ImagemRelatorio(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public PacienteAlunoRelatorio getPacienteAlunoRelatorio() {
        return pacienteAlunoRelatorio;
    }

    public void setPacienteAlunoRelatorio(PacienteAlunoRelatorio pacienteAlunoRelatorio) {
        this.pacienteAlunoRelatorio = pacienteAlunoRelatorio;
    }

    public Byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(Byte[] arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codigo;
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
        final ImagemRelatorio other = (ImagemRelatorio) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
}
