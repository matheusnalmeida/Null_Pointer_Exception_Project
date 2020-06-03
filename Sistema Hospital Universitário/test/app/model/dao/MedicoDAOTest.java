package app.model.dao;

import app.model.domain.Medico;
import app.model.domain.PacienteAlunoRelatorio;
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

    @Test
    public void testCreate() {
        System.out.println("Criando um médico no banco de dados");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("Teste Médico", "70021041031", senha, "2020/SE");
        MedicoDAO medicoDAO = new MedicoDAO();
        boolean expResult = true;
        boolean result = medicoDAO.create(medico);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("Obtendo um médico do banco de dados");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("Teste Médico", "70021041031", senha, "2020/SE");
        MedicoDAO medicoDAO = new MedicoDAO();
        Medico expResult = new Medico();
        expResult.setMatricula("70021041031");
        Medico result = medicoDAO.read(medico);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("Atualizando os dados de um médico no banco de dados. Atualizando senha.");
        String senha = "123456";
        senha = EncryptionPassword.encrypt(senha);
        Medico medico = new Medico("José Pedro Barreto Santos", "70021041031", senha, "2020/SE");
        MedicoDAO medicoDAO = new MedicoDAO();
        boolean expResult = true;
        boolean result = medicoDAO.update(medico);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("Obtendo todos os médicos do banco de dados. SELECT * FROM medicos.");
        MedicoDAO medicoDAO = new MedicoDAO();
        List<Medico> result = medicoDAO.selectAll();
        assertEquals(3, result.size());
    }

    @Test
    public void testDelete() {
        System.out.println("Removendo um médico do banco de dados.");
        Medico medico = new Medico();
        medico.setMatricula("70021041031");
        MedicoDAO medicoDAO = new MedicoDAO();
        boolean expResult = true;
        boolean result = medicoDAO.delete(medico);
        assertEquals(expResult, result);
    }

    @Test
    public void getRelatorios() {
        System.out.println("Obtendo todos os relatórios autorizados pelo médico: 56021041031");
        MedicoDAO medicoDAO = new MedicoDAO();
        Medico medico = new Medico();
        medico.setMatricula("56021041031");
        List<PacienteAlunoRelatorio> relatorios = medicoDAO.getRelatorios(medico);
        assertEquals(1, relatorios.size());
    }
}
