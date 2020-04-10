/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.domain.old;

public class Medico extends Usuario {

    private String crm;

    public Medico(String nome, String matricula, String senha, String crm) {
        super(nome, matricula, senha);
        this.crm = crm;
    }

    public Medico() {
        super(null, null, null);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}