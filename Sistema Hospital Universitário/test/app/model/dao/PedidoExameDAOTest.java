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
        System.out.println("Criando um pedido de exame");
        PedidoExame pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 06, 03).toString(), "Test", "Test");
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoExameDAO.create(pedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("Obtendo um pedido de exame do banco.");
        PedidoExame pedidoExame = new PedidoExame();
        pedidoExame.setCodigo(6);
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        PedidoExame expResult = new PedidoExame("Test", LocalDate.of(2020, 06, 03).toString(), "Test", "Test");
        expResult.setCodigo(6);
        PedidoExame result = pedidoExameDAO.read(pedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("Atualizando um pedido de exame");
        PedidoExame pedidoExame = new PedidoExame("Test", LocalDate.of(2020, 06, 06).toString(), "Test", "Test");
        pedidoExame.setCodigo(6);
        PedidoExameDAO pedidoDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoDAO.update(pedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("Obtendo todos os pedidos de exame do banco. SELECT * FROM pedidos_exames.");
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        List<PedidoExame> result = pedidoExameDAO.selectAll();
        assertEquals(3, result.size());
    }

    @Test
    public void testDelete() {
        System.out.println("Removendo um pedido de exame do banco.");
        PedidoExame pedidoExame = new PedidoExame();
        pedidoExame.setCodigo(6);
        PedidoExameDAO pedidoExameDAO = new PedidoExameDAO();
        boolean expResult = true;
        boolean result = pedidoExameDAO.delete(pedidoExame);
        assertEquals(expResult, result);
    }
}
