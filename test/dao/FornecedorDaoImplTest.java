/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.utilitario.UtilGerador;
import entidade.Fornecedor;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Titione
 */
public class FornecedorDaoImplTest {

    private Fornecedor fornecedor;
    private FornecedorDao fornecedorDao;
    private Session sessao;

    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    }

   @Test
    public void testSalvar() {
        System.out.println("Salvar");
        fornecedor = new Fornecedor(null, UtilGerador.gerarNome(), new Date(),"teste");
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        sessao.close();

        assertNotNull(fornecedor.getId());
    }

    // @Test
    public void testExcluir() {
        System.out.println("Excluir");
        buscarFornecedorBd();
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.excluir(fornecedor, sessao);
        
        Fornecedor fornecedorExcluido = fornecedorDao.pesquisarPorId(fornecedor.getId(), sessao);
        sessao.close();
        assertNull(fornecedorExcluido);
    }
    
    
    // @Test
    public void testAlterar() {
        System.out.println("Iniciando alterar");
        System.out.println("-----------------");
        buscarFornecedorBd();
        fornecedor.setNome(UtilGerador.gerarNome());
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        
        Fornecedor fornecedorAlterado = fornecedorDao.pesquisarPorId(fornecedor.getId(), sessao);
        sessao.close();
        
        assertEquals(fornecedor, fornecedorAlterado);
        
    }
  //  @Test

    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        Fornecedor fornecedorPesquisado = fornecedorDao.pesquisarPorId(Long.parseLong("1"), sessao);
        sessao.close();
        
        assertNotNull(fornecedorPesquisado);
    }

 //   @Test
    public void testListarTodo() {
        System.out.println("Listar todo");
        buscarFornecedorBd();
        sessao = HibernateUtil.abrirConexao();
        List<Fornecedor> fornecedores = fornecedorDao.listarTodo(sessao);
        sessao.close();
        
        System.out.println(fornecedores.toString());
        
        assertTrue(!fornecedores.isEmpty());
        
    }

    public Fornecedor buscarFornecedorBd() {

        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Fornecedor"); // HQL
        List<Fornecedor> fornecedores = consulta.list();
        sessao.close();
        if (fornecedores.isEmpty()) {
            testSalvar();
        } else {
            fornecedor = fornecedores.get(2);
        }

        return fornecedor;
    }

}
