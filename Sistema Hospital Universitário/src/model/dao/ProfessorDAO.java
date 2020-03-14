package model.dao;

import model.database.DatabaseMySQL;
import model.domain.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO {

    private Connection connection;

    public ProfessorDAO() {
        this.connection = DatabaseMySQL.conectar();
    }

    public boolean create(Professor professor) {
        if (professor != null) {
            String query = "insert into professores(nome, matricula, crm, senha, titulacao) values (?, ?, ?, MD5(?), ?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, professor.getNome());
                stmt.setString(2, professor.getMatricula());
                stmt.setString(3, professor.getCrm());
                stmt.setString(4, professor.getSenha());
                stmt.setString(5, professor.getTitulacao());
                return stmt.execute();
            } catch (SQLException exception) {
            }
        }
        return false;
    }

    public Professor read(Professor professor) {
        Professor professor2 = null;
        if (professor != null) {
            String query = "select professores.nome, professores.matricula, professores.senha, professores.titulacao, professores.crm from professores where matricula = ? and senha = MD5(?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, professor.getMatricula());
                stmt.setString(2, professor.getSenha());
                stmt.execute();
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String matricula = resultSet.getString("matricula");
                    String senha = resultSet.getString("senha");
                    String crm = resultSet.getString("crm");
                    String titulacao = resultSet.getString("titulacao");
                    professor2 = new Professor(nome, titulacao, crm, matricula, senha);
                }
            } catch (SQLException exception) {
            }
        }
        return professor2;
    }

    public boolean update(Professor professor) {
        if (professor != null) {
            String query = "update professores set nome = ?, senha = MD5(?), titulacao = ?, crm = ? where matricula = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, professor.getNome());
                stmt.setString(2, professor.getSenha());
                stmt.setString(3, professor.getTitulacao());
                stmt.setString(4, professor.getCrm());
                stmt.setString(5, professor.getMatricula());
                return stmt.execute();
            } catch (SQLException exception) {
            }
        }
        return false;
    }
}
