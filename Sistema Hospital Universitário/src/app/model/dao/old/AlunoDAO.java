package app.model.dao.old;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.model.domain.Aluno;
import java.time.LocalDate;
import app.model.database.DatabaseMySQL;
import app.model.domain.Usuario;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO() {
    }

    /**
     * Insere os dados de um aluno no banco de dados.
     *
     * @param aluno
     * @param matriculaProfessor
     * @return
     */
    public boolean create(Aluno aluno, String matriculaProfessor) {
        boolean result = true;
        if (aluno != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            result = usuarioDAO.create(aluno);
            if (!result) {
                String query1 = "insert into alunos(matricula, anoResidencia, dataNascimento, matriculaProfessor) values(?, ?, ?, ?)";
                this.connection = DatabaseMySQL.conectar();
                try {
                    PreparedStatement stmt = this.connection.prepareStatement(query1);
                    String matricula = aluno.getMatricula();
                    int anoResidencia = aluno.getAnoResidencia();
                    LocalDate dataNascimento = aluno.getDataNascimento();
                    stmt.setString(1, matricula);
                    stmt.setInt(2, anoResidencia);
                    stmt.setString(3, dataNascimento.toString());
                    stmt.setString(4, matriculaProfessor);
                    result = stmt.execute();
                } catch (SQLException exception) {
                    result = true;
                    usuarioDAO.delete(aluno);
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    /**
     * Resgata os dados de um aluno no banco de dados.
     *
     * @param aluno
     * @return
     */
    public Aluno read(Aluno aluno) {
        Aluno aluno2 = null;
        if (aluno != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.read(aluno);
            if (usuario != null) {
                String query = "select * from alunos where matricula = ?";
                this.connection = DatabaseMySQL.conectar();
                try {
                    PreparedStatement stmt = this.connection.prepareStatement(query);
                    stmt.setString(1, usuario.getMatricula());
                    stmt.execute();
                    ResultSet resultSet = stmt.getResultSet();
                    while (resultSet.next()) {
                        String nome = usuario.getNome();
                        String matricula = usuario.getMatricula();
                        String senha = usuario.getSenha();
                        int anoResidencia = resultSet.getInt("anoResidencia");
                        String dataNascimento[] = resultSet.getString("dataNascimento").split("-");
                        LocalDate data = LocalDate.of(Integer.parseInt(dataNascimento[0]), Integer.parseInt(dataNascimento[1]), Integer.parseInt(dataNascimento[2]));
                        aluno2 = new Aluno(nome, matricula, senha, anoResidencia, data);
                    }
                } catch (SQLException exception) {
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return aluno2;
    }

    /**
     * Atualiza os dados de um aluno.
     *
     * @param aluno
     * @return
     */
    public boolean update(Aluno aluno) {
        boolean result = true;
        if (aluno != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            result = usuarioDAO.update(aluno);
            if (!result) {
                String query = "update alunos set anoResidencia = ?, dataNascimento = ? where matricula = ?";
                this.connection = DatabaseMySQL.conectar();
                try {
                    PreparedStatement stmt = this.connection.prepareStatement(query);
                    int anoResidencia = aluno.getAnoResidencia();
                    LocalDate dataNascimento = aluno.getDataNascimento();
                    String matricula = aluno.getMatricula();
                    stmt.setInt(1, anoResidencia);
                    stmt.setString(2, dataNascimento.toString());
                    stmt.setString(3, matricula);
                    result = stmt.execute();
                } catch (SQLException exception) {
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public boolean delete(Aluno aluno) {
        boolean result = true;
        if (aluno != null) {
            String query = "delete from alunos where matricula = ?";
            this.connection = DatabaseMySQL.conectar();
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, aluno.getMatricula());
                result = stmt.execute();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                result = usuarioDAO.delete(aluno) && result;
            } catch (SQLException exception) {
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }
}
