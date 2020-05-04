package app.utilits;

import app.model.dao.SessaoDAO;
import app.model.domain.Sessao;
import java.time.LocalDateTime;

public class Sistema {

    private static Sessao sessao;

    public static Sessao getSessao() {
        return sessao;
    }

    public static void setSessao(Sessao sessao) {
        SessaoDAO sessaoDAO = new SessaoDAO();
        if (sessao == null) {
            Sistema.sessao.setDataTerminoSessao(LocalDateTime.now().toString());
            sessaoDAO.update(Sistema.sessao);
            Sistema.sessao = null;
        } else {
            Sistema.sessao = sessao;
            sessaoDAO.create(sessao);
        }
    }
}
