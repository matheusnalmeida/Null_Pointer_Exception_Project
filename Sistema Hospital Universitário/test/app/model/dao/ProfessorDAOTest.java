package app.model.dao;

import app.model.domain.Aluno;
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

    @Test
    public void testCreate() {
        System.out.println("Criando um professor no banco de dados.");
        String senha = "123456";
        senha = EncryptionPassword.encrypt(senha);
        Professor professor = new Professor("Teste Professor", "02500529222", senha, "1010/SE", "Pós-Graduado");
        ProfessorDAO professorDAO = new ProfessorDAO();
        boolean expResult = true;
        boolean result = professorDAO.create(professor);
        assertEquals(expResult, result);
    }

    @Test
    public void testRead() {
        System.out.println("Obtendo um professor do banco de dados");
        Professor professor = new Professor();
        professor.setMatricula("02500529222");
        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor expResult = new Professor();
        expResult.setMatricula("02500529222");
        Professor result = professorDAO.read(professor);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdate() {
        System.out.println("Atualizando os dados de um professor.");
        String senha = "741753951";
        senha = EncryptionPassword.encrypt(senha);
        Professor professor = new Professor("Teste Professor", "02500529222", senha, "1010/SE", "Mestrando");
        ProfessorDAO professorDAO = new ProfessorDAO();
        boolean expResult = true;
        boolean result = professorDAO.update(professor);
        assertEquals(expResult, result);
    }

    @Test
    public void testSelectAll() {
        System.out.println("Obtendo todos os professores do banco. SELECT * FROM professores");
        ProfessorDAO professorDAO = new ProfessorDAO();
        List<Professor> result = professorDAO.selectAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testDelete() {
        System.out.println("Removendo um professor do banco de dados.");
        Professor professor = new Professor();
        professor.setMatricula("02500529222");
        ProfessorDAO professorDAO = new ProfessorDAO();
        boolean expResult = true;
        boolean result = professorDAO.delete(professor);
        assertEquals(expResult, result);
    }
        
    @Test
    public void testGetAlunos(){
        System.out.println("Obtendo todos os alunos de um professor.");
        Professor professor = new Professor();
        professor.setMatricula("02310529222");
        ProfessorDAO professorDAO = new ProfessorDAO();
        List<Aluno> alunos = professorDAO.getAlunos(professor);
        assertEquals(1, alunos.size());
    }
    
    @Test
    public void testGetAlunosSemProfessor(){
        System.out.println("Obtendo todos os alunos que não possuem um professor");
        ProfessorDAO professorDAO = new ProfessorDAO();
        List<Aluno> alunos = professorDAO.getAlunosSemProfessor();
        assertEquals(1, alunos.size());
    }
}
