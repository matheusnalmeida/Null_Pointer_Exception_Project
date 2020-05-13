/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    /**
     * Test of create method, of class ImagemRelatorioDAO.
//     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        ImagemRelatorio imagemRelatorio = null;
//        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
//        boolean expResult = false;
//        boolean result = instance.create(imagemRelatorio);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of read method, of class ImagemRelatorioDAO.
     */
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

    /**
     * Test of update method, of class ImagemRelatorioDAO.
     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        ImagemRelatorio imagemRelatorio = null;
//        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
//        boolean expResult = false;
//        boolean result = instance.update(imagemRelatorio);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of delete method, of class ImagemRelatorioDAO.
     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        ImagemRelatorio imagemRelatorio = null;
//        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
//        boolean expResult = false;
//        boolean result = instance.delete(imagemRelatorio);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of selectAll method, of class ImagemRelatorioDAO.
     */
//    @Test
//    public void testSelectAll() {
//        System.out.println("selectAll");
//        PacienteAlunoRelatorio pacientealunoRelatorio = null;
//        ImagemRelatorioDAO instance = new ImagemRelatorioDAO();
//        List<ImagemRelatorio> expResult = null;
//        List<ImagemRelatorio> result = instance.selectAll(pacientealunoRelatorio);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
