package app.model.dao;

import app.model.database.DatabaseMySQL;
import app.model.domain.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PacienteDAO {

    private Connection connection;

    public PacienteDAO() {
        this.connection = DatabaseMySQL.conectar();
    }

    public boolean create(Paciente paciente) {
        boolean result = true;
        if (paciente != null) {
            String query = "insert into pacientes(nome, cor, dataNascimento, cpf, sexo) values(?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                String nome = paciente.getNome();
                String cor = paciente.getCor();
                String cpf = paciente.getCpf();
                String sexo = paciente.getSexo();
                LocalDate dataNascimento = paciente.getDataNascimento();
                stmt.setString(1, nome);
                stmt.setString(2, cor);
                stmt.setString(3, dataNascimento.toString());
                stmt.setString(4, cpf);
                stmt.setString(5, sexo);
                result = stmt.execute();
            } catch (SQLException exception) {
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public Paciente read(Paciente paciente) {
        Paciente paciente2 = null;
        if (paciente != null) {
            String query = "select nome, cor, dataNascimento, cpf, sexo from pacientes where cpf = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, paciente.getCpf());
                stmt.execute();
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cor = resultSet.getString("cor");
                    String dataNascimento[] = resultSet.getString("dataNascimento").split("-");
                    String cpf = resultSet.getString("cpf");
                    String sexo = resultSet.getString("sexo");
                    LocalDate data = LocalDate.of(Integer.parseInt(dataNascimento[0]), Integer.parseInt(dataNascimento[1]), Integer.parseInt(dataNascimento[2]));
                    paciente2 = new Paciente(nome, sexo, cor, cpf, data);
                }
            } catch (SQLException exception) {
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return paciente2;
    }

    public boolean update(Paciente paciente) {
        boolean result = true;
        if (paciente != null) {
            String query = "update pacientes set nome = ?, cor = ?, dataNascimento = ?, sexo = ? where cpf = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                LocalDate dataNascimento = paciente.getDataNascimento();
                stmt.setString(1, paciente.getNome());
                stmt.setString(2, paciente.getCor());
                stmt.setString(3, dataNascimento.toString());
                stmt.setString(4, paciente.getSexo());
                stmt.setString(5, paciente.getCpf());
                result = stmt.execute();
            } catch (SQLException exception) {
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }
}
