package app.model.dao;

import app.model.domain.PedidoExame;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PedidoExameDAOTest {

    public PedidoExameDAOTest() {
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
     * Test of create method, of class PedidoExameDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        PedidoExame pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 04, 12), "Test", "Test");
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoExameDAO.create(pedidoExame);
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class PedidoExameDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        PedidoExame pedidoExame = new PedidoExame();
        pedidoExame.setCodigo(0);
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        PedidoExame expResult = new PedidoExame("Test", LocalDate.of(2020, 04, 12), "Test", "Test");
        expResult.setCodigo(0);
        PedidoExame result = pedidoExameDAO.read(pedidoExame);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class PedidoExameDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        PedidoExame pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 04, 20), "Test", "Test");
        pedidoExame.setCodigo(0);
        PedidoExameDAO pedidoDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoDAO.update(pedidoExame);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class PedidoExameDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        PedidoExame pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 04, 20), "Test", "Test");
        pedidoExame.setCodigo(0);
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoExameDAO.delete(pedidoExame);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAll method, of class PedidoExameDAO.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        List<PedidoExame> result = pedidoExameDAO.selectAll();
        assertEquals(1, result.size());
    }
}
