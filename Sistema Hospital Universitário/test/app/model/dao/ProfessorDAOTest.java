package app.model.dao;

import app.model.domain.Medico;
import app.model.domain.Professor;
import app.utilits.EncryptionPassword;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfessorDAOTest {
    
    public ProfessorDAOTest() {
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
     * Test of create method, of class ProfessorDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Professor professor = new Professor("José Pedro Barreto Santos", "1204202021P", senha, "0000/SE", "Pós-Graduado");
        ProfessorDAO professorDAO = new ProfessorDAO();
        boolean expResult = true;
        boolean result = professorDAO.create(professor);
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class ProfessorDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Professor professor = new Professor("José Pedro Barreto Santos", "1204202021P", senha, "0000/SE", "Pós-Graduado");
        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor expResult = new Professor();
        expResult.setMatricula("1204202021P");
        Professor result = professorDAO.read(professor);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class ProfessorDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        //Exemplo de atualização de titulação
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Professor professor = new Professor("José Pedro Barreto Santos", "1204202021P", senha, "0000/SE", "Mestrando");
        ProfessorDAO professorDAO = new ProfessorDAO();
        boolean expResult = true;
        boolean result = professorDAO.update(professor);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class ProfessorDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Professor professor = new Professor();
        professor.setMatricula("1204202021P");
        ProfessorDAO professorDAO = new ProfessorDAO();
        boolean expResult = true;
        boolean result = professorDAO.delete(professor);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAll method, of class ProfessorDAO.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        ProfessorDAO professorDAO = new ProfessorDAO();
        List<Professor> result = professorDAO.selectAll();
        assertEquals(1, result.size());
    }
}
