package app.model.dao;

import app.model.domain.Paciente;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PacienteDAOTest {

    public PacienteDAOTest() {
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
        System.out.println("Criando um paciente no banco de dados");
        Paciente paciente = new Paciente("Teste Paciente", "M", "Pardo", "222.222.222-22", LocalDate.of(1998, 12, 1));
        PacienteDAO pacienteDAO = new PacienteDAO();
        boolean expResult = true;
        boolean result = pacienteDAO.create(paciente);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("Obtendo um paciente do banco de dados");
        Paciente paciente = new Paciente();
        paciente.setCpf("222.222.222-22");
        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente expResult = new Paciente();
        expResult.setCpf("222.222.222-22");
        Paciente result = pacienteDAO.read(paciente);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("Atualizando os dados de um paciente.");
        Paciente paciente = new Paciente("José Pedro Barreto Santos", "M", "Pardo", "222.222.222-22", LocalDate.of(1999, 1, 1));
        PacienteDAO pacienteDAO = new PacienteDAO();
        boolean expResult = true;
        boolean result = pacienteDAO.update(paciente);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("Obtendo todos os pacientes do banco de dados. SELECT * FROM pacientes.");
        PacienteDAO pacienteDAO = new PacienteDAO();
        List<Paciente> result = pacienteDAO.selectAll();
        assertEquals(4, result.size());
    }

    @Test
    public void testDelete() {
        System.out.println("Removendo um paciente do banco de dados.");
        Paciente paciente = new Paciente("José Pedro Barreto Santos", "M", "Pardo", "222.222.222-22", LocalDate.of(1999, 1, 1));
        PacienteDAO pacienteDAO = new PacienteDAO();
        boolean expResult = true;
        boolean result = pacienteDAO.delete(paciente);
        assertEquals(expResult, result);
    }
}
