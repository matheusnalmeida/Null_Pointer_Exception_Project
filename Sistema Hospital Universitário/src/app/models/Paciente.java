package app.models;

import java.time.LocalDate;

public class Paciente {

    private String nome;
    private String sexo;
    private String cor;
    private LocalDate dataNascimento;

    public Paciente(String nome, String sexo, String cor, LocalDate dataNascimento) {
        this.nome = nome;
        this.sexo = sexo;
        this.cor = cor;
        this.dataNascimento = dataNascimento;
    }

    public Paciente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
