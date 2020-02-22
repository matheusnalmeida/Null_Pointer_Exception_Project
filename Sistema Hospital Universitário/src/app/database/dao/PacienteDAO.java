package app.database.dao;

import app.database.DatabaseMySQL;
import app.models.Paciente;
import java.sql.Connection;
import java.sql.Date;
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
        if (paciente != null) {
            String query = "insert into pacientes(nome, cor, dataNascimento, cpf, sexo) values(?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                String nome = paciente.getNome();
                String cor = paciente.getCor();
                String cpf = paciente.getCpf();
                String sexo = paciente.getSexo();
                Date dataNascimento = new Date(paciente.getDataNascimento().getYear(), paciente.getDataNascimento().getMonthValue(), paciente.getDataNascimento().getDayOfMonth());
                stmt.setString(1, nome);
                stmt.setString(2, cor);
                stmt.setDate(3, dataNascimento);
                stmt.setString(4, cpf);
                stmt.setString(5, sexo);
                return stmt.execute();
            } catch (SQLException exception) {
            }
        }
        return false;
    }

    public Paciente read(Paciente paciente) {
        Paciente paciente2 = null;
        if (paciente != null) {
            String query = "select pacientes.nome, pacientes.cor, pacientes.dataNascimento, pacientes.cpf, pacientes.sexo from pacientes where cpf = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, paciente.getCpf());
                stmt.execute();
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cor = resultSet.getString("cor");
                    Date dataNascimento = resultSet.getDate("dataNascimento");
                    String cpf = resultSet.getString("cpf");
                    String sexo = resultSet.getString("sexo");
                    LocalDate data = LocalDate.of(dataNascimento.getYear(), dataNascimento.getMonth(), dataNascimento.getDay());
                    paciente2 = new Paciente(nome, sexo, cor, cpf, data);
                }
            } catch (SQLException exception) {

            }
        }
        return paciente2;
    }

    public boolean update(Paciente paciente) {
        if (paciente != null) {
            String query = "update pacientes set nome = ?, cor = ?, dataNascimento = ?, sexo = ? where cpf = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                Date dataNascimento = new Date(paciente.getDataNascimento().getYear(), paciente.getDataNascimento().getMonthValue(), paciente.getDataNascimento().getDayOfMonth());
                stmt.setString(1, paciente.getNome());
                stmt.setString(2, paciente.getCor());
                stmt.setDate(3, dataNascimento);
                stmt.setString(4, paciente.getSexo());
                stmt.setString(5, paciente.getCpf());
                return stmt.execute();
            } catch (SQLException exception) {
            }
        }
        return false;
    }
}
