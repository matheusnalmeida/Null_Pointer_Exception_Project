package app.utilits;

import java.util.ArrayList;
import java.util.Objects;

public class CPF {

    private String cpfString;
    private ArrayList<Integer> cpf = new ArrayList<>();

    public CPF(String cpf) throws CPFInvalidoException {
        if (cpf.length() < 11) {
            throw new CPFInvalidoException("\nCPF Inválido\n");
        }
        char cpfChars[] = cpf.toCharArray();
        if (cpf.length() == 11) {
            for (int i = 0; i < cpfChars.length; i++) {
                if (i == 3 || i == 7 || i == 11) {
                    if (cpfChars[i] != '.' && cpfChars[i] != '-') {
                        char cpfFormatado[] = formatarCPF(cpfChars);
                        validarCPF(cpfFormatado);
                    }
                    break;
                }
            }
        } else {
            try {
                validarCPF(cpfChars);
            } catch (CPFInvalidoException exception) {
                System.out.print(exception.getMessage());
            }
        }
    }

    public String getCpfString() {
        return cpfString;
    }

    public ArrayList<Integer> getCpf() {
        return cpf;
    }

    private char[] formatarCPF(char[] cpfChars) {
        char[] cpfFormatado = new char[14];
        int contador = 0;
        StringBuilder stringCpf = new StringBuilder();
        for (int i = 0; i < cpfFormatado.length; i++) {
            if (i == 3 || i == 7 || i == 11) {
                if (i == 3 || i == 7) {
                    cpfFormatado[i] = '.';
                } else {
                    cpfFormatado[i] = '-';
                }
            } else {
                cpfFormatado[i] = cpfChars[contador];
                contador++;
            }
            stringCpf.append(cpfFormatado[i]);
        }
        this.cpfString = stringCpf.toString();
        return cpfFormatado;
    }

    private void validarCPF(char cpfChars[]) throws CPFInvalidoException {
        ArrayList<Integer> vetorAux1 = new ArrayList();
        ArrayList<Integer> vetorAux2 = new ArrayList();
        ArrayList<Integer> vetorAux3 = new ArrayList();
        int escalar = 0;
        int decimo = 0;
        int decimoPrimeiro = 0;
        for (int i = 0; i < cpfChars.length; i++) {
            if (i != 3 && i != 7 && i != 11) {
                cpf.add(Integer.parseInt(String.valueOf(cpfChars[i])));
            }
        }
        for (int i = 10; i > 0; i--) {
            vetorAux2.add(i);
        }
        for (int i = 11; i > 0; i--) {
            vetorAux3.add(i);
        }
        for (int i = 0; i < cpf.size(); i++) {
            if (i < cpf.size() - 2) {
                vetorAux1.add(cpf.get(i));
            }
        }
        for (int i = 0; i < vetorAux1.size(); i++) {
            escalar += (vetorAux1.get(i) * vetorAux2.get(i));
        }
        if (escalar % 11 == 0 || escalar % 11 == 1) {
            decimo = 0;
        } else if (escalar % 11 >= 2 && escalar % 11 <= 10) {
            decimo = 11 - (escalar % 11);
        }
        vetorAux1.add(decimo);
        escalar = 0;
        for (int i = 0; i < vetorAux1.size(); i++) {
            escalar += (vetorAux1.get(i) * vetorAux3.get(i));
        }
        if (escalar % 11 == 0 || escalar % 11 == 1) {
            decimoPrimeiro = 0;
        } else if (escalar % 11 >= 2 && escalar % 11 <= 10) {
            decimoPrimeiro = 11 - (escalar % 11);
        }
        vetorAux1.add(decimoPrimeiro);
        if (!Objects.equals(cpf.get(9), vetorAux1.get(9)) || !Objects.equals(
                cpf.get(10), vetorAux1.get(10))) {
            throw new CPFInvalidoException("\nCPF Inválido\n");
        }
    }

    public boolean equals(CPF cpf) {
        return Objects.equals(this.cpf.get(10), cpf.getCpf().get(10))
                && Objects.equals(this.cpf.get(9), cpf.getCpf().get(9));
    }
}