package app.model.dao;

import app.model.domain.Medico;
import app.utilits.EncryptionPassword;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MedicoDAOTest {
    
    public MedicoDAOTest() {
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
     * Test of create method, of class MedicoDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE", 0);
        MedicoDAO medicoDAO = new MedicoDAO();
        boolean expResult = true;
        boolean result = medicoDAO.create(medico);
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class MedicoDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE", 0);
        MedicoDAO medicoDAO = new MedicoDAO();
        Medico expResult = new Medico();
        expResult.setMatricula("1204202018M");
        Medico result = medicoDAO.read(medico);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class MedicoDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        //Exemplo de atualização de senha
        String senha = "789963951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "1204202018M", senha, "0000/SE", 0);
        MedicoDAO medicoDAO = new MedicoDAO();
        boolean expResult = true;
        boolean result = medicoDAO.update(medico);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class MedicoDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Medico medico = new Medico();
        medico.setMatricula("1204202018M");
        MedicoDAO medicoDAO = new MedicoDAO();
        boolean expResult = true;
        boolean result = medicoDAO.delete(medico);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAll method, of class MedicoDAO.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        MedicoDAO medicoDAO = new MedicoDAO();
        List<Medico> result = medicoDAO.selectAll();
        assertEquals(1, result.size());
    }
}
