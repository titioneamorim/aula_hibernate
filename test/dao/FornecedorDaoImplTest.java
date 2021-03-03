/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author felipe.souza2
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
        fornecedor = new Fornecedor();
        fornecedor.setNome("Fornecedor 1");
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        sessao.close();
        
        assertNotNull(fornecedor.getId());
    }
//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
    }

//    @Test
    public void testListarTodo() {
        System.out.println("listarTodo");
    }
    
}
