package app.model.dao;

import app.model.domain.Medico;
import app.model.domain.Relatorio;
import app.utilits.EncryptionPassword;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RelatorioDAOTest {

    public RelatorioDAOTest() {
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
     * Test of create method, of class RelatorioDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE", 0);
        Relatorio relatorio = new Relatorio(LocalDate.of(2020, 4, 13), "Test", medico,
                LocalDate.of(2020, 4, 27), null);
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        boolean expResult = true;
        boolean result = relatorioDAO.create(relatorio);
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class RelatorioDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        Relatorio relatorio = new Relatorio();
        relatorio.setCodigo(0);
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        Relatorio expResult = new Relatorio();
        relatorio.setCodigo(0);
        Relatorio result = relatorioDAO.read(relatorio);
        assertEquals(expResult.getCodigo(), result.getCodigo());
    }

    /**
     * Test of update method, of class RelatorioDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE", 0);
        Relatorio relatorio = new Relatorio(LocalDate.of(2020, 4, 13), "Test", medico,
                LocalDate.of(2020, 4, 30), null);
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        boolean expResult = true;
        boolean result = relatorioDAO.update(relatorio);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class RelatorioDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Relatorio relatorio = new Relatorio();
        relatorio.setCodigo(0);
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        boolean expResult = true;
        boolean result = relatorioDAO.delete(relatorio);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAll method, of class RelatorioDAO.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        List<Relatorio> result = relatorioDAO.selectAll();
        assertEquals(1, result.size());
    }
}
