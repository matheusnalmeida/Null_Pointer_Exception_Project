package app.model.dao;

import app.model.database.DatabaseMySQL;
import app.model.domain.PacienteAlunoRelatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PacienteAlunoRelatorioDAO {

    private Connection connection;

    public PacienteAlunoRelatorioDAO() {
    }

    public boolean create(PacienteAlunoRelatorio consulta) {
        boolean result = true;
        if (consulta != null) {
            this.connection = DatabaseMySQL.conectar();
            String query = "insert into pacientes_alunos_relatorios(cpfPaciente, codigoAluno, dataAtendimento, codigoAtendimento, codigoRelatorio) values(?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, consulta.getPaciente().getCpf());
                stmt.setString(2, consulta.getAluno().getMatricula());
                LocalDateTime dataAtendimento = consulta.getDataAtendimento();
                stmt.setString(3, dataAtendimento.toString());
                stmt.setInt(4, 0);
                stmt.setInt(5, consulta.getRelatorio().getCodigo());
                result = stmt.execute();
            } catch (SQLException exception) {
                result = true;
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public PacienteAlunoRelatorio read(PacienteAlunoRelatorio consulta) {
        PacienteAlunoRelatorio retorno = null;
        if (consulta != null) {
            this.connection = DatabaseMySQL.conectar();
            String query = "";
        }
        DatabaseMySQL.desconectar(this.connection);
        return retorno;
    }

    public boolean update(PacienteAlunoRelatorio consulta) {
        boolean result = true;
        if (consulta != null) {
            this.connection = DatabaseMySQL.conectar();
            String query = "update table pacientes_alunos_relatorios set cpfPaciente = ?, matriculaAluno = ?, dataAtendimento = ?, codigoRelatorio = ? where codigoAtendimento = ?";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, consulta.getPaciente().getCpf());
                stmt.setString(2, consulta.getAluno().getMatricula());
                stmt.setString(3, consulta.getDataAtendimento().toString());
                stmt.setInt(4, consulta.getRelatorio().getCodigo());
                stmt.setInt(5, consulta.getCodigo());
                result = stmt.execute();
            } catch (SQLException exception) {
                result = true;
            }
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }

    public boolean delete(PacienteAlunoRelatorio consulta) {
        boolean result = true;
        if (consulta != null) {
            this.connection = DatabaseMySQL.conectar();
            String query = "";
        }
        DatabaseMySQL.desconectar(this.connection);
        return result;
    }
}
