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

    @Test
    public void testCreate() {
        System.out.println("create");
        PedidoExame pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 04, 12).toString(), "Test", "Test");
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoExameDAO.create(pedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("read");
        PedidoExame pedidoExame = new PedidoExame();
        pedidoExame.setCodigo(1);
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        PedidoExame expResult = new PedidoExame("Test", LocalDate.of(2020, 04, 12).toString(), "Test", "Test");
        expResult.setCodigo(1);
        PedidoExame result = pedidoExameDAO.read(pedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        PedidoExame pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 04, 20).toString(), "Test", "Test");
        pedidoExame.setCodigo(1);
        PedidoExameDAO pedidoDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoDAO.update(pedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        List<PedidoExame> result = pedidoExameDAO.selectAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        PedidoExame pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 04, 20).toString(), "Test", "Test");
        pedidoExame.setCodigo(1);
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoExameDAO.delete(pedidoExame);
        assertEquals(expResult, result);
    }
}
