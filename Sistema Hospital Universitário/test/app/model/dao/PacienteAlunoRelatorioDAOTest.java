package app.model.dao;

import app.model.domain.Aluno;
import app.model.domain.Medico;
import app.model.domain.Paciente;
import app.model.domain.PacienteAlunoRelatorio;
import app.model.domain.Relatorio;
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

/**
 *
 * @author Usuario
 */
public class PacienteAlunoRelatorioDAOTest {

    private Paciente paciente;
    private Relatorio relatorio;
    private Aluno aluno;
    private PacienteAlunoRelatorioDAO pacienteAlunoRelatorioDAO;
    private PacienteAlunoRelatorio pacienteAlunoRelatorio;

    public PacienteAlunoRelatorioDAOTest() {
        this.pacienteAlunoRelatorioDAO = new PacienteAlunoRelatorioDAO();
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE");
        this.relatorio = new Relatorio(LocalDate.of(2020, 4, 13), "Test", medico,
                LocalDate.of(2020, 4, 27), null);
        senha = "75395145682";
        senha = EncryptionPassword.encrypt(senha);
        this.aluno = new Aluno("José Pedro Barreto Santos", "1204202018A", senha,
                1, LocalDate.of(1998, 12, 1));
        this.paciente = new Paciente("José Pedro Barreto Santos", "M", "Pardo", "111.111.111-11", LocalDate.of(1998, 12, 1));
        this.pacienteAlunoRelatorio = new PacienteAlunoRelatorio(LocalDateTime.now(), paciente, aluno);
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

    /**
     * Test of create method, of class PacienteAlunoRelatorioDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        boolean result = this.pacienteAlunoRelatorioDAO.create(this.pacienteAlunoRelatorio);
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class PacienteAlunoRelatorioDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        PacienteAlunoRelatorio pacienteAlunoRelatorio = new PacienteAlunoRelatorio();
        pacienteAlunoRelatorio.setCodigo(0);
        PacienteAlunoRelatorio result = this.pacienteAlunoRelatorioDAO.read(pacienteAlunoRelatorio);
        assertEquals(pacienteAlunoRelatorio, result);
    }

    /**
     * Test of update method, of class PacienteAlunoRelatorioDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        this.pacienteAlunoRelatorio.setRelatorio(this.relatorio);
        boolean expResult = true;
        boolean result = this.pacienteAlunoRelatorioDAO.update(pacienteAlunoRelatorio);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class PacienteAlunoRelatorioDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        boolean expResult = true;
        boolean result = this.pacienteAlunoRelatorioDAO.delete(this.pacienteAlunoRelatorio);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAll method, of class PacienteAlunoRelatorioDAO.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        List<PacienteAlunoRelatorio> result = this.pacienteAlunoRelatorioDAO.selectAll();
        assertEquals(1, result.size());
    }
}
