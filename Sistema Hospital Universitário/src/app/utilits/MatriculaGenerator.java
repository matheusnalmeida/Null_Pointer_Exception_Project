package app.utilits;

import app.model.dao.UsuarioDAO;
//import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.Random;

public class MatriculaGenerator {

    /**
     * @param cp
     * @param tipo A matricula do usuário é gerada pela combinação: Dia do
     * cadastro + mês do cadastro + ano do cadastro + digitos verificadores do
     * cpf do usuario + o tipo do usuário. O cpf não será armazenado no banco de
     * dados. Apenas utilizado para gerar a matrícula.
     * @return matricula
     * @throws CPFInvalidoException
     */
    /*public String generateToken(String cp, String tipo) throws CPFInvalidoException {
        CPF cpf = new CPF(cp);
        ArrayList<Integer> aux1 = cpf.getCpf();
        int digito1 = aux1.get(aux1.size() - 2);
        int digito2 = aux1.get(aux1.size() - 1);
        LocalDate data = LocalDate.now();
        String aux2 = "";
        String aux3 = "";
        if (data.getMonthValue() < 10) {
            aux2 = "0";
        }
        if (data.getDayOfMonth() < 10) {
            aux3 = "0";
        }
        String stringData = aux3 + Integer.toString(data.getDayOfMonth()) + aux2 + Integer.toString(data.getMonthValue()) + Integer.toString(data.getYear());
        return stringData + Integer.toString(digito1) + Integer.toString(digito2) + tipo;
    }*/
    
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
        return matriculaString.concat(tipoUsuario);
    }
}
