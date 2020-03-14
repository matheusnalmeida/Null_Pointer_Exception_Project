package model.dao;

import model.database.DatabaseMySQL;
import model.domain.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoDAO {

    private Connection connection;

    public MedicoDAO() {
        this.connection = DatabaseMySQL.conectar();
    }

    public boolean create(Medico medico) {
        if (medico != null) {
            String query = "insert into medicos(nome, crm, matricula, senha) values(?, ?, ?, MD5(?))";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, medico.getNome());
                stmt.setString(2, medico.getCrm());
                stmt.setString(3, medico.getMatricula());
                stmt.setString(4, medico.getSenha());
                return stmt.execute();
            } catch (SQLException exception) {
            }
        }
        return false;
    }

    public Medico read(Medico medico) {
        Medico medico2 = null;
        if (medico != null) {
            String query = "select medicos.nome, medicos.crm, medicos.matricula, medicos.senha from medicos where matricula = ? and senha = MD5(?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, medico.getMatricula());
                stmt.setString(2, medico.getSenha());
                stmt.execute();
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String crm = resultSet.getString("crm");
                    String matricula = resultSet.getString("matricula");
                    String senha = resultSet.getString("senha");
                    medico2 = new Medico(nome, crm, matricula, senha);
                }
            } catch (SQLException exception) {

            }
        }
        return medico2;
    }

    public boolean update(Medico medico) {
        if (medico != null) {
            String query = "update medicos set nome = ?, crm = ?, senha = MD5(?) where matricula = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, medico.getNome());
                stmt.setString(2, medico.getCrm());
                stmt.setString(3, medico.getSenha());
                stmt.setString(4, medico.getMatricula());
                return stmt.execute();
            } catch (SQLException exception) {

            }
        }
        return false;
    }
}
