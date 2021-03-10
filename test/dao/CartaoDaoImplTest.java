/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.utilitario.UtilGerador;
import entidade.Cartao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Titione
 */
public class CartaoDaoImplTest {

    private Cartao cartao;
    private CartaoDao cartaoDao;
    private Session sessao;

    public CartaoDaoImplTest() {
        cartaoDao = new CartaoDaoImpl();
    }

 //   @Test
    public void testSalvar() {
        System.out.println("Salvar");
        cartao = new Cartao(null, UtilGerador.gerarCaracter(4)+"-" 
                + UtilGerador.gerarCaracter(4) + "-" 
                + UtilGerador.gerarCaracter(4)+ "-" 
                + UtilGerador.gerarCaracter(4), "Visa", "12/24");
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.salvarOuAlterar(cartao, sessao);
        sessao.close();

        assertNotNull(cartao.getId());
    }
    
 // @Test
     public void testExcluir() {
        System.out.println("Excluir");
        buscarCartaoBd();
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.excluir(cartao, sessao);
        
        Cartao cartaoExcluido = cartaoDao.pesquisarPorId(cartao.getId(), sessao);
        sessao.close();
        assertNull(cartaoExcluido);
    }
    
   //  @Test
    public void testAlterar() {
        System.out.println("Iniciando alterar");
        System.out.println("-----------------");
        buscarCartaoBd();
        cartao.setNumero(UtilGerador.gerarNumero(16));
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.salvarOuAlterar(cartao, sessao);
        
        Cartao cartaoAlterado = cartaoDao.pesquisarPorId(cartao.getId(), sessao);
        sessao.close();
        
        assertEquals(cartao, cartaoAlterado);
        
    }
    

  //   @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        Long id = null;
        Session sessao = null;
        CartaoDaoImpl instance = new CartaoDaoImpl();
        Cartao expResult = null;
        Cartao result = instance.pesquisarPorId(id, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public Cartao buscarCartaoBd() {

        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Cartao"); // HQL
        List<Cartao> cartoes = consulta.list();
        sessao.close();
        if (cartoes.isEmpty()) {
            testSalvar();
        } else {
            cartao = cartoes.get(0);
        }

        return cartao;
    }

}
