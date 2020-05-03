package app.model.dao;

import app.model.domain.Aluno;
import app.model.domain.Usuario;
import app.utilits.EncryptionPassword;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioDAOTest {

    public UsuarioDAOTest() {
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
    public void testAutenticar() throws Exception {
        System.out.println("autenticar");
        String matricula = "1204202018A";
        String senha = "75395145682";
        senha = EncryptionPassword.encrypt(senha);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "1204202018A", senha,
                1, LocalDate.of(1998, 12, 1).toString());
        Usuario result = usuarioDAO.autenticar(matricula, senha);
        assertEquals(aluno, result);
    }
}
