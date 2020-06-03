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
        //Teste de autenticação de usuário
        System.out.println("autenticar");
        String matricula = "67841997433";
        String senha = "123456";
        senha = EncryptionPassword.encrypt(senha);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Aluno aluno = new Aluno("José Pedro Barreto Santos", "67841997433", senha,
                1, LocalDate.of(1998, 12, 1).toString());
        Usuario result = usuarioDAO.autenticar(matricula, senha);
        assertEquals(aluno, result);
    }
}
