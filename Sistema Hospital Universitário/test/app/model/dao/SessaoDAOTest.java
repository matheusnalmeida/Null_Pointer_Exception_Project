package app.model.dao;

import app.model.domain.Aluno;
import app.model.domain.Sessao;
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

public class SessaoDAOTest {

    public SessaoDAOTest() {
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
        Sessao sessao = new Sessao(aluno, LocalDateTime.now().toString(), null);
        SessaoDAO sessaoDAO = new SessaoDAO();
        boolean expResult = true;
        boolean result = sessaoDAO.create(sessao);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("read");
        String senha = "75395145682";
        senha = EncryptionPassword.encrypt(senha);
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1204202018A", senha,
                1, LocalDate.of(1998, 12, 1).toString());
        Sessao sessao = new Sessao(aluno, LocalDateTime.now().toString(), null);
        SessaoDAO sessaoDAO = new SessaoDAO();
        Sessao expResult = new Sessao();
        sessao.setCodigo(10);
        expResult.setCodigo(10);
        Sessao result = sessaoDAO.read(sessao);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        //Atualizando horário de término da sessão.
        String senha = "75395145682";
        senha = EncryptionPassword.encrypt(senha);
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1204202018A", senha,
                1, LocalDate.of(1998, 12, 1).toString());
        Sessao sessao = new Sessao(aluno, "2020-05-03 16:00:32", LocalDateTime.now().toString());
        sessao.setCodigo(12);
        SessaoDAO sessaoDAO = new SessaoDAO();
        boolean expResult = true;
        boolean result = sessaoDAO.update(sessao);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        SessaoDAO sessaoDAO = new SessaoDAO();
        List<Sessao> result = sessaoDAO.selectAll();
        assertEquals(1, result.size());
    }
}
