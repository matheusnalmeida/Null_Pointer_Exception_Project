package app.model.dao.old;

import app.model.database.DatabaseMySQL;
import app.model.domain.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.model.domain.Usuario;

public class MedicoDAO {

    private Connection connection;

    public MedicoDAO() {
    }

    public boolean create(Medico medico) {
        boolean result = true;
        if (medico != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            result = usuarioDAO.create(medico);
            if (!result) {
                String query1 = "insert into medicos(matricula, crm) values(?, ?)";
                String query2 = "select * from medicos where crm = ?";
                this.connection = DatabaseMySQL.conectar();
                try {
                    PreparedStatement stmt = this.connection.prepareStatement(query2);
                    stmt.setString(1, medico.getCrm());
                    stmt.execute();
                    ResultSet resultSet = stmt.getResultSet();
                    if (!resultSet.next()) {
                        stmt = this.connection.prepareStatement(query1);
                        stmt.setString(1, medico.getMatricula());
                        stmt.setString(2, medico.getCrm());
                        result = stmt.execute();
                    } else {
                        result = true;
                        usuarioDAO.delete(medico);
                    }
                } catch (SQLException exception) {
                    result = true;
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public Medico read(Medico medico) {
        Medico medico2 = null;
        if (medico != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.read(medico);
            if (usuario != null) {
                this.connection = DatabaseMySQL.conectar();
                String query = "select * from medicos where matricula = ?";
                try {
                    PreparedStatement stmt = this.connection.prepareStatement(query);
                    stmt.setString(1, medico.getCrm());
                    stmt.execute();
                    ResultSet resultSet = stmt.getResultSet();
                    while (resultSet.next()) {
                        String nome = resultSet.getString("nome");
                        String crm = resultSet.getString("crm");
                        String matricula = resultSet.getString("matricula");
                        String senha = resultSet.getString("senha");
                        medico2 = new Medico(nome, matricula, senha, crm);
                    }
                } catch (SQLException exception) {
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return medico2;
    }

    public boolean update(Medico medico) {
        boolean result = true;
        if (medico != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            result = usuarioDAO.update(medico);
            if (!result) {
                this.connection = DatabaseMySQL.conectar();
                String query = "update medicos set crm = ? where matricula = ?";
                try {
                    PreparedStatement stmt = this.connection.prepareStatement(query);
                    stmt.setString(1, medico.getCrm());
                    stmt.setString(2, medico.getMatricula());
                    result = stmt.execute();
                } catch (SQLException exception) {
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public boolean delete(Medico medico) {
        boolean result = true;
        if (medico != null) {
            this.connection = DatabaseMySQL.conectar();
            String query = "delete from medicos where matricula = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, medico.getMatricula());
                result = stmt.execute();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                result = usuarioDAO.delete(medico) && result;
            } catch (SQLException exception) {
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }
}
