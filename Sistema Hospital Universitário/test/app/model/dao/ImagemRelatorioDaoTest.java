package app.model.dao;

import app.model.domain.ImagemRelatorio;
import app.model.domain.Paciente;
import app.model.domain.PacienteAlunoRelatorio;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matheus Nunes
 */
public class ImagemRelatorioDaoTest {

    public ImagemRelatorioDaoTest() {
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
        ImagemRelatorio imagemRelatorio = null;
        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
        boolean expResult = false;
        boolean result = instance.create(imagemRelatorio);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("read");
        PacienteAlunoRelatorio par = new PacienteAlunoRelatorio();
        par.setCodigo(1);
        ImagemRelatorio imagemRelatorio = new ImagemRelatorio();
        imagemRelatorio.setCodigo(1);
        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
        ImagemRelatorio expResult = null;
        ImagemRelatorio result = instance.read(imagemRelatorio);
        System.out.println(result.getArquivo().length);

        assertEquals(imagemRelatorio, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        ImagemRelatorio imagemRelatorio = null;
        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
        boolean expResult = false;
        boolean result = instance.update(imagemRelatorio);
        assertEquals(expResult, result);
    }

    
    @Test
    public void testDelete() {
        System.out.println("delete");
        ImagemRelatorio imagemRelatorio = null;
        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
        boolean expResult = false;
        boolean result = instance.delete(imagemRelatorio);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        PacienteAlunoRelatorio pacientealunoRelatorio = null;
        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
        List<ImagemRelatorio> expResult = null;
        List<ImagemRelatorio> result = instance.selectAll(pacientealunoRelatorio);
        assertEquals(expResult, result);
    }
}
