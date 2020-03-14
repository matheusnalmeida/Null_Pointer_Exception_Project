package model.dao;

import model.database.DatabaseMySQL;
import model.domain.Consulta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultaDAO {

    private Connection connection;

    public ConsultaDAO() {
        this.connection = DatabaseMySQL.conectar();
    }

    public boolean create(Consulta consulta) {
        if (consulta != null) {
            String query = "insert into pacientes_alunos_relatorios(cpfPaciente, codigoAluno, dataAtendimento, codigoAtendimento, codigoRelatorio) values(?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, consulta.getPaciente().getCpf());
                stmt.setString(2, consulta.getAluno().getMatricula());
                Date date = new Date(consulta.getDataConsulta().getDayOfMonth(), consulta.getDataConsulta().getMonthValue(), consulta.getDataConsulta().getYear());
                stmt.setDate(3, date);
                stmt.setInt(4, 0);
                stmt.setInt(5, consulta.getRelatorio().getCodigo());
                return stmt.execute();
            } catch (SQLException exception) {
            }
        }
        return false;
    }

    public Consulta read(Consulta consulta) {
        Consulta retorno = null;
        if(consulta != null) {
            String query = "select pacientes.nome, pacientes.cor, pacientes.dataNascimento, pacientes.cpf, pacientes.sexo where cpf = ?";
        }
        return retorno;
    }
}
