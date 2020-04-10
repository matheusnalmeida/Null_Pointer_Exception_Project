package app.model.dao;

import app.model.domain.Aluno;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlunoDAOTest {

    public AlunoDAOTest() {
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
     * Test of create method, of class AlunoDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1172113332A",
                "123456", 1, LocalDate.of(1998, 12, 1));
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.create(aluno);
        assertEquals(alunoDAO.read(aluno), aluno);
    }

    @Test
    public void testRead() {
        System.out.println("read");
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1172113332A",
                "123456", 1, LocalDate.of(1998, 12, 1));
        AlunoDAO alunoDAO = new AlunoDAO();
        assertEquals(alunoDAO.read(aluno), aluno);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1172113332A",
                "789456", 1, LocalDate.of(1998, 12, 1));
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.update(aluno);
        assertEquals(alunoDAO.read(aluno), aluno);
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1172113332A",
                "123456", 1, LocalDate.of(1998, 12, 1));
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.delete(aluno);
        assertEquals(alunoDAO.read(aluno), null);
    }

    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        AlunoDAO alunoDAO = new AlunoDAO();
        List<Aluno> result = alunoDAO.selectAll();
        System.out.println(result);
    }
}
