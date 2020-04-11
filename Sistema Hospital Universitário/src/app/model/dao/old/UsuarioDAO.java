package app.model.dao.old;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.model.database.DatabaseMySQL;
import app.model.domain.Aluno;
import app.model.domain.Medico;
import app.model.domain.Professor;
import app.model.domain.Usuario;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
    }

    public boolean create(Usuario usuario) {
        boolean result = true;
        if (usuario != null) {
            if (this.read(usuario) == null) {
                String query = "insert into usuarios(nome, matricula, senha) values(?, ?, MD5(?))";
                this.connection = DatabaseMySQL.conectar();
                try {
                    String nome = usuario.getNome();
                    String matricula = usuario.getMatricula();
                    String senha = usuario.getSenha();
                    PreparedStatement stmt = this.connection.prepareStatement(query);
                    stmt.setString(1, nome);
                    stmt.setString(2, matricula);
                    stmt.setString(3, senha);
                    result = stmt.execute();
                } catch (SQLException exception) {
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public Usuario read(Usuario usuario) {
        Usuario retorno = null;
        if (usuario != null) {
            String query = "select * from usuarios where matricula = ?";
            this.connection = DatabaseMySQL.conectar();
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, usuario.getMatricula());
                stmt.execute();
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String matricula = resultSet.getString("matricula");
                    String senha = resultSet.getString("senha");
                    if (matricula.contains("A")) {
                        retorno = new Aluno(nome, matricula, senha, 0, null, null, 0);
                    } else if (matricula.contains("M")) {
                        retorno = new Medico(nome, matricula, senha, null, 0);
                    } else if (matricula.contains("P")) {
                        retorno = new Professor(nome, matricula, senha, null, null, 0);
                    }
                }
            } catch (SQLException exception) {
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return retorno;
    }

    public boolean update(Usuario usuario) {
        boolean result = true;
        if (usuario != null) {
            this.connection = DatabaseMySQL.conectar();
            String query = "update table usuarios set nome = ?, senha = MD5(?) where matricula = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getSenha());
                result = stmt.execute();
            } catch (SQLException exception) {
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public boolean delete(Usuario usuario) {
        boolean result = true;
        if (usuario != null) {
            this.connection = DatabaseMySQL.conectar();
            String query = "delete from usuarios where matricula = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, usuario.getMatricula());
                result = stmt.execute();
            } catch (SQLException exception) {
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }
}
