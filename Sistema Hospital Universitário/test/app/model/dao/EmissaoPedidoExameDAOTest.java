package app.model.dao;

import app.model.domain.EmissaoPedidoExame;
import app.model.domain.Medico;
import app.model.domain.Paciente;
import app.model.domain.PedidoExame;
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

public class EmissaoPedidoExameDAOTest {
    
    private Medico medico;
    private Paciente paciente;
    private PedidoExame pedidoExame;
    private EmissaoPedidoExameDAO emissaoPedidoExameDAO;
    private EmissaoPedidoExame emissaoPedidoExame;
    
    public EmissaoPedidoExameDAOTest() {
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        this.medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE", 0);
        this.paciente = new Paciente("José Pedro Barreto Santos", "M", "Pardo", "111.111.111-11", LocalDate.of(1998, 12, 1));
        this.pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 04, 12), "Test", "Test");
        this.emissaoPedidoExame = new EmissaoPedidoExame(null, medico, paciente, pedidoExame);
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
     * Test of create method, of class EmissaoPedidoExameDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        boolean expResult = true;
        boolean result = this.emissaoPedidoExameDAO.create(emissaoPedidoExame);
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class EmissaoPedidoExameDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        EmissaoPedidoExame expResult = new EmissaoPedidoExame();
        expResult.setPedidoExame(pedidoExame);
        EmissaoPedidoExame result = this.emissaoPedidoExameDAO.read(emissaoPedidoExame);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class EmissaoPedidoExameDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        this.emissaoPedidoExame.setDataEmissao(LocalDateTime.of(2020, 5, 1, 15, 30));
        boolean expResult = true;
        boolean result = this.emissaoPedidoExameDAO.update(emissaoPedidoExame);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class EmissaoPedidoExameDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        boolean expResult = true;
        boolean result = this.emissaoPedidoExameDAO.delete(emissaoPedidoExame);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAll method, of class EmissaoPedidoExameDAO.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        List<EmissaoPedidoExame> result = this.emissaoPedidoExameDAO.selectAll();
        assertEquals(1, result.size());
    }
}
