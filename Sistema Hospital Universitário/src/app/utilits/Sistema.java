package app.utilits;

import app.model.domain.Sessao;

public class Sistema {
    
    private static Sessao sessao;

    public static Sessao getSessao() {
        return sessao;
    }

    public static void setSessao(Sessao sessao) {
        Sistema.sessao = sessao;
    }
}
