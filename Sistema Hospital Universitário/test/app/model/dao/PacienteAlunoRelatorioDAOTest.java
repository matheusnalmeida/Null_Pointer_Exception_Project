package app.model.dao;

import app.model.domain.Aluno;
import app.model.domain.Medico;
import app.model.domain.Paciente;
import app.model.domain.PacienteAlunoRelatorio;
import app.utilits.EncryptionPassword;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PacienteAlunoRelatorioDAOTest {

    private Paciente paciente;
    private Aluno aluno;
    private PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO;
    private PacienteAlunoRelatorio pacienteAlunoRelatorio;

    public PacienteAlunoRelatorioDAOTest() {
        this.pacienteAlunoRelatorioDAO = new PacienteAlunoRelatorioDAO();
        this.aluno = new Aluno();
        this.aluno.setMatricula("67841997433");
        this.paciente = new Paciente();
        this.paciente.setCpf("111.111.111-11");
        this.pacienteAlunoRelatorio = new PacienteAlunoRelatorio(LocalDateTime.now().toString(), paciente, aluno);
        this.pacienteAlunoRelatorio.setDescricao("descriçao");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("Criando uma consulta.");
        boolean result = this.pacienteAlunoRelatorioDAO.create(this.pacienteAlunoRelatorio);
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("Obtendo uma consulta do banco.");
        PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio();
        pacienteAlunoRelatorio.setCodigo(13);
        PacienteAlunoRelatorio result = this.pacienteAlunoRelatorioDAO.read(pacienteAlunoRelatorio);
        assertEquals(pacienteAlunoRelatorio, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("Atualizando uma consulta no banco.");
        this.pacienteAlunoRelatorio.setDescricao("teste");
        boolean expResult = true;
        boolean result = this.pacienteAlunoRelatorioDAO.update(pacienteAlunoRelatorio);
        assertEquals(expResult, result);
    }

    @Test
    public void testDelete() {
        System.out.println("Removendo uma consulta do banco.");
        boolean expResult = true;
        boolean result = this.pacienteAlunoRelatorioDAO.delete(this.pacienteAlunoRelatorio);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("Obtendo todas as consultas do banco.");
        List<PacienteAlunoRelatorio> result = this.pacienteAlunoRelatorioDAO.selectAll();
        assertEquals(3, result.size());
    }
}
