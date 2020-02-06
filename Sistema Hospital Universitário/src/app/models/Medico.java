package app.models;

import java.util.Objects;

public class Medico {
    
    private String nome;
    private String CRM;

    public Medico(String nome, String CRM) {
        this.nome = nome;
        this.CRM = CRM;
    }

    public Medico() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.CRM);
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
        final Medico other = (Medico) obj;
        if (!Objects.equals(this.CRM, other.CRM)) {
            return false;
        }
        return true;
    }
}
