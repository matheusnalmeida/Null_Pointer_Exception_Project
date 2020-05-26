package app.model.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "relatorio_template")
public class RelatorioTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(name = "template", nullable = false, length = 100000, columnDefinition = "BLOB")
    private Byte[] template;
    @Transient
    private File arquivo;

    public RelatorioTemplate() {
    }
    
    public RelatorioTemplate(File arquivo) throws FileNotFoundException, IOException {
        this.arquivo = arquivo;
        byte[] fileContent = Files.readAllBytes(this.arquivo.toPath());
        this.template = new Byte[fileContent.length];
        int i = 0;
        for (byte b : fileContent) {
            this.template[i++] = b;
        }
    }

    public RelatorioTemplate(Byte[] template) {
        this.template = template;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Byte[] getTemplate() {
        return template;
    }

    public void setTemplate(Byte[] template) {
        this.template = template;
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
        final RelatorioTemplate other = (RelatorioTemplate) obj;
        return this.codigo == other.codigo;
    }
    
    /**
     * Converte de array de bytes para arquivo .pdf em um path do sistema específico
     * @param path
     */
    public void writeBytes(String path) {
        try {
            File file = new File(path + "relatorio.pdf");
            OutputStream os = new FileOutputStream(file);
            byte[] bytes = new byte[this.template.length];
            for(int i = 0; i < bytes.length; i++){
                bytes[i] = this.template[i];
            }
            os.write(bytes);
            os.close();
        } catch (Exception exception) {
        }
    }
}
