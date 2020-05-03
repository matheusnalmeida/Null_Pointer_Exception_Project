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
        if (sessao == null) {
            Sistema.getSessao().setDataTerminoSessao(LocalDateTime.now().toString());
            SessaoDAO sessaoDAO = new SessaoDAO();
            sessaoDAO.create(Sistema.getSessao());
            Sistema.sessao = null;
        }
        Sistema.sessao = sessao;
    }
}
