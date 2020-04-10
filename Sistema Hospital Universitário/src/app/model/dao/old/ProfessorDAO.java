package app.model.dao.old;

import app.model.database.DatabaseMySQL;
import app.model.domain.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.model.domain.Medico;

public class ProfessorDAO {

    private Connection connection;

    public ProfessorDAO() {
        this.connection = DatabaseMySQL.conectar();
    }

    public boolean create(Professor professor) {
        boolean result = true;
        MedicoDAO medicoDAO = new MedicoDAO();
        result = medicoDAO.create(professor);
        if (!result) {
            String query = "insert into professores(matricula, titulacao) values (?, ?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, professor.getMatricula());
                stmt.setString(2, professor.getTitulacao());
                result = stmt.execute();
            } catch (SQLException exception) {
                result = true;
                medicoDAO.delete(professor);
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public Professor read(Professor professor) {
        Professor professor2 = null;
        if (professor != null) {
            MedicoDAO medicoDAO = new MedicoDAO();
            Medico medico = medicoDAO.read(professor);
            if (medico != null) {
                String query = "select titulacao from professores where matricula = ?";
                try {
                    PreparedStatement stmt = this.connection.prepareStatement(query);
                    stmt.setString(1, professor.getMatricula());
                    stmt.execute();
                    ResultSet resultSet = stmt.getResultSet();
                    while (resultSet.next()) {
                        String nome = medico.getNome();
                        String matricula = medico.getMatricula();
                        String senha = medico.getSenha();
                        String crm = medico.getCrm();
                        String titulacao = resultSet.getString("titulacao");
                        professor2 = new Professor(nome, matricula, senha, crm, titulacao);
                    }
                } catch (SQLException exception) {
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return professor2;
    }

    public boolean update(Professor professor) {
        boolean result = true;
        if (professor != null) {
            MedicoDAO medicoDAO = new MedicoDAO();
            result = medicoDAO.update(professor);
            if (!result) {
                String query = "update professores set titulacao = ? where matricula = ?";
                try {
                    PreparedStatement stmt = this.connection.prepareStatement(query);
                    stmt.setString(1, professor.getTitulacao());
                    stmt.setString(2, professor.getMatricula());
                    result = stmt.execute();
                } catch (SQLException exception) {
                    result = true;
                }
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public boolean delete(Professor professor) {
        boolean result = true;
        if (professor != null) {
            String query = "delete from professores where matricula = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, professor.getMatricula());
                result = stmt.execute();
                MedicoDAO medicoDAO = new MedicoDAO();
                result = medicoDAO.delete(professor) && result;
            } catch (SQLException exception) {
                result = true;
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }
}
