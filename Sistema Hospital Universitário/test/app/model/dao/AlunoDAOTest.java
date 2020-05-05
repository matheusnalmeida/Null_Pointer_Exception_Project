package app.model.dao;

import app.model.domain.Aluno;
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

    @Test
    public void testCreate() {
        System.out.println("create");
        String senha = "75395145682";
        senha = EncryptionPassword.encrypt(senha);
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1204202018A", senha,
                1, LocalDate.of(1998, 12, 1).toString());
        AlunoDAO alunoDAO = new AlunoDAO();
        boolean expResult = true;
        boolean result = alunoDAO.create(aluno);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("read");
        Aluno aluno = new Aluno();
        aluno.setMatricula("1204202018A");
        AlunoDAO alunoDAO = new AlunoDAO();
        String senha = "75395145682";
        senha = EncryptionPassword.encrypt(senha);
        Aluno expResult = new Aluno("José Pedro Barreto Santos", "1204202018A", senha,
                1, LocalDate.of(1998, 12, 1).toString());
        Aluno result = alunoDAO.read(aluno);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        //Atualizando ano de residência
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1204202018A", "8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92",
                2, LocalDate.of(1998, 12, 1).toString());
        AlunoDAO alunoDAO = new AlunoDAO();
        boolean expResult = true;
        boolean result = alunoDAO.update(aluno);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        AlunoDAO alunoDAO = new AlunoDAO();
        List<Aluno> result = alunoDAO.selectAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1204202018A", "8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92",
                2, LocalDate.of(1998, 12, 1).toString());
        AlunoDAO alunoDAO = new AlunoDAO();
        boolean expResult = true;
        boolean result = alunoDAO.delete(aluno);
        assertEquals(expResult, result);
    }

    @Test
    public void getRelatorios() {
        Aluno aluno = new Aluno();
        aluno.setMatricula("81149163393");
        AlunoDAO alunoDAO = new AlunoDAO();
        List<Relatorio> relatorios = alunoDAO.getRelatorios(aluno);
        assertNotEquals(null, relatorios);
    }
}
