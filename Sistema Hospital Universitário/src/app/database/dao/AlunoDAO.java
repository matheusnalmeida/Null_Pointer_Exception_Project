package app.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.models.Aluno;
import java.sql.Date;
import java.time.LocalDate;
import app.database.DatabaseMySQL;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO() {
        this.connection = DatabaseMySQL.conectar();
    }

    /**
     * Insere os dados de um aluno no banco de dados.
     *
     * @param aluno
     * @param crmProfessor
     * @return
     */
    public boolean create(Aluno aluno, String crmProfessor) {
        if (aluno != null) {
            String query = "insert into alunos(nome, matricula, senha, anoResidencia, dataNascimento, crmProfessor) values (?, ?, MD5(?), ?, ?, ?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                String nome = aluno.getNome();
                String matricula = aluno.getMatricula();
                String senha = aluno.getSenha();
                int anoResidencia = aluno.getAnoResidencia();
                Date dataNascimento = new Date(aluno.getDataNascimento().getYear(), aluno.getDataNascimento().getMonthValue(), aluno.getDataNascimento().getDayOfMonth());
                stmt.setString(1, nome);
                stmt.setString(2, matricula);
                stmt.setString(3, senha);
                stmt.setInt(4, anoResidencia);
                stmt.setDate(5, dataNascimento);
                stmt.setString(6, crmProfessor);
                return stmt.execute();
            } catch (SQLException exception) {
            }
        }
        return false;
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
            String query = "select alunos.nome, alunos.matricula, alunos.senha, alunos.anoResidencia, alunos.dataNascimento from alunos where matricula = ? and senha = MD5(?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, aluno.getMatricula());
                stmt.setString(2, aluno.getSenha());
                stmt.execute();
                ResultSet resultSet = stmt.getResultSet();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String matricula = resultSet.getString("matricula");
                    String senha = resultSet.getString("senha");
                    int anoResidencia = resultSet.getInt("anoResidencia");
                    Date dataNascimento = resultSet.getDate("dataNascimento");
                    LocalDate data = LocalDate.of(dataNascimento.getYear(), dataNascimento.getMonth(), dataNascimento.getDay());
                    aluno2 = new Aluno(nome, matricula, senha, anoResidencia, data, null);
                }
            } catch (SQLException exception) {
            }
        }
        return aluno2;
    }

    /**
     * Atualiza os dados de um aluno.
     *
     * @param aluno
     * @return
     */
    public boolean update(Aluno aluno) {
        if (aluno != null) {
            String query = "update alunos set nome = ?, senha = MD5(?), anoResidencia = ?, dataNascimento = ?, crmProfessor = ? where matricula = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                String nome = aluno.getNome();
                String matricula = aluno.getMatricula();
                String senha = aluno.getSenha();
                int anoResidencia = aluno.getAnoResidencia();
                Date dataNascimento = new Date(aluno.getDataNascimento().getYear(), aluno.getDataNascimento().getMonthValue(), aluno.getDataNascimento().getDayOfMonth());
                stmt.setString(1, nome);
                stmt.setString(2, senha);
                stmt.setInt(3, anoResidencia);
                stmt.setDate(4, dataNascimento);
                stmt.setString(5, aluno.getProfessor().getCrm());
                stmt.setString(6, matricula);
                return stmt.execute();
            } catch (SQLException exception) {
            }
        }
        return false;
    }
}
