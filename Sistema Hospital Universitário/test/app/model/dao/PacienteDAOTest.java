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

/**
 *
 * @author Usuario
 */
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

    /**
     * Test of create method, of class PacienteDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Paciente paciente = new Paciente("José Pedro Barreto Santos", "M", "Pardo", "111.111.111-11", LocalDate.of(1998, 12, 1));
        PacienteDAO pacienteDAO = new PacienteDAO();
        boolean expResult = true;
        boolean result = pacienteDAO.create(paciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class PacienteDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        Paciente paciente = new Paciente();
        paciente.setCpf("111.111.111-11");
        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente expResult = new Paciente();
        expResult.setCpf("111.111.111-11");
        Paciente result = pacienteDAO.read(paciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class PacienteDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Paciente paciente = new Paciente("José Pedro Barreto Santos", "M", "Pardo", "111.111.111-11", LocalDate.of(1999, 1, 1));
        PacienteDAO pacienteDAO = new PacienteDAO();
        boolean expResult = true;
        boolean result = pacienteDAO.update(paciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class PacienteDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Paciente paciente = new Paciente("José Pedro Barreto Santos", "M", "Pardo", "111.111.111-11", LocalDate.of(1998, 12, 1));
        PacienteDAO pacienteDAO = new PacienteDAO();
        boolean expResult = true;
        boolean result = pacienteDAO.delete(paciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAll method, of class PacienteDAO.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        PacienteDAO instance = new PacienteDAO();
        List<Paciente> result = instance.selectAll();
        assertEquals(1, result.size());
    }
}
