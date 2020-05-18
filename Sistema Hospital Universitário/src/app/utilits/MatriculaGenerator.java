package app.utilits;

import app.model.dao.UsuarioDAO;
import java.util.Random;

public class MatriculaGenerator {

    public String gerarMatricula(String tipoUsuario) throws Exception {
        String matriculaString = "";
        Random random = new Random();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean result = false;
        do {
            StringBuilder matricula = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                matricula.append(random.nextInt(10));
            }
            matriculaString = matricula.toString();
        } while (usuarioDAO.existUser(matriculaString));
        String end = "";
        if (tipoUsuario.equals("A")) {
            end = "3";
        } else if (tipoUsuario.equals("P")) {
            end = "2";
        } else if (tipoUsuario.equals("M")) {
            end = "1";
        }
        return matriculaString.concat(end);
    }
}
