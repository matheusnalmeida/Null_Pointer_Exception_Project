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
        this.emissaoPedidoExameDAO = new EmissaoPedidoExameDAO();
        this.medico = new Medico();
        this.medico.setMatricula("56021041031");
        this.paciente = new Paciente();
        this.paciente.setCpf("111.111.111-11");
        this.pedidoExame = new PedidoExame();
        this.pedidoExame.setCodigo(7);
        this.emissaoPedidoExame = new EmissaoPedidoExame(LocalDateTime.now().toString(), medico, paciente, pedidoExame);
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
        System.out.println("Criando uma emissão de pedido de exame.");
        boolean expResult = true;
        boolean result = this.emissaoPedidoExameDAO.create(emissaoPedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("Obtendo uma emissão de pedido de exame.");
        EmissaoPedidoExame expResult = new EmissaoPedidoExame();
        expResult.setCodigo(6);
        EmissaoPedidoExame result = this.emissaoPedidoExameDAO.read(this.emissaoPedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("Atualizando um pedido de exame");
        this.emissaoPedidoExame.setDataEmissao(LocalDateTime.now().toString());
        boolean expResult = true;
        boolean result = this.emissaoPedidoExameDAO.update(emissaoPedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testDelete() {
        System.out.println("Removendo uma emissão de pedido de exame.");
        boolean expResult = true;
        boolean result = this.emissaoPedidoExameDAO.delete(emissaoPedidoExame);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("Obtendo todas as emissões de pedido de exame. SELECT * FROM emissao_pedidos_exames.");
        List<EmissaoPedidoExame> result = this.emissaoPedidoExameDAO.selectAll();
        assertEquals(3, result.size());
    }

    @Test
    public void testDeletarPorPedidoExame() {
        System.out.println("Remove uma emissão de pedido de exame através do código de um pedido de exame.");
        boolean expResult = true;
        boolean result = this.emissaoPedidoExameDAO.deletarPorPedidoExame(pedidoExame);
        assertEquals(expResult, result);
    }
}
