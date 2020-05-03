package app.model.dao;

import app.model.domain.Medico;
import app.model.domain.Relatorio;
import app.utilits.EncryptionPassword;
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

    @Test
    public void testCreate() {
        System.out.println("create");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE");
        Relatorio relatorio = new Relatorio(LocalDate.of(2020, 4, 13).toString(), "Test", medico,
                LocalDate.of(2020, 4, 27).toString(), null);
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        boolean expResult = true;
        boolean result = relatorioDAO.create(relatorio);
        assertEquals(expResult, result);
    }

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

    @Test
    public void testUpdate() {
        System.out.println("update");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE");
        Relatorio relatorio = new Relatorio(LocalDate.of(2020, 4, 13).toString(), "Test", medico,
                LocalDate.of(2020, 4, 30).toString(), null);
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        boolean expResult = true;
        boolean result = relatorioDAO.update(relatorio);
        assertEquals(expResult, result);
    }

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

    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        List<Relatorio> result = relatorioDAO.selectAll();
        assertEquals(1, result.size());
    }
}
