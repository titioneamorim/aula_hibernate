/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.utilitario.UtilGerador;
import entidade.Cartao;
import entidade.Correntista;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Titione
 */
public class CorrentistaDaoImplTest {
    
    private Correntista correntista;
    private CorrentistaDao correntistaDao;
    private Session sessao;
    
    public CorrentistaDaoImplTest() {
        correntistaDao = new CorrentistaDaoImpl();
    }
    
    
  //  @Test
    public void testSalvar() {
        System.out.println("Salvar");
        CartaoDaoImplTest cartaoTeste = new CartaoDaoImplTest();
        
        
        correntista = new Correntista(null, 
                UtilGerador.gerarNome(),
                new Date(),
                UtilGerador.gerarNumero(3) + "-" + UtilGerador.gerarNumero(3) +"-"
                        + UtilGerador.gerarNumero(3) + "-"
                        + UtilGerador.gerarNumero(2) ,
                UtilGerador.gerarEmail(), 
                BigDecimal.TEN,
                "historico");
                Cartao cartao = cartaoTeste.buscarCartaoBd();
                correntista.setCartao(cartao);
        sessao = HibernateUtil.abrirConexao();
        correntistaDao.salvarOuAlterar(correntista, sessao);
        sessao.close();

        assertNotNull(correntista.getId());
        
    }

    @Test
    public void testAlterar() {
        System.out.println("Alterar");
        buscarCorrentistaBd();
        correntista.setNome(UtilGerador.gerarNome());
        sessao = HibernateUtil.abrirConexao();
        correntistaDao.salvarOuAlterar(correntista, sessao);
        Correntista correntistaAlt = correntistaDao.pesquisarPorId(correntista.getId(), sessao);
        assertEquals(correntista.getNome(), correntistaAlt.getNome());
        sessao.close();
        
    }
 //   @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        Long id = null;
        Session sessao = null;
        CorrentistaDaoImpl instance = new CorrentistaDaoImpl();
        Correntista expResult = null;
        Correntista result = instance.pesquisarPorId(id, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testPessquisarPorNome() {
        System.out.println("pessquisarPorNome");
        String nome = "";
        Session sessao = null;
        CorrentistaDaoImpl instance = new CorrentistaDaoImpl();
        List<Correntista> expResult = null;
        List<Correntista> result = instance.pessquisarPorNome(nome, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
    
    public Correntista buscarCorrentistaBd(){
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Correntista"); // HQL
        List<Correntista> correntistas = consulta.list();
        sessao.close();
        
        if (correntistas.isEmpty()) {
            testSalvar();
        } else{
            correntista = correntistas.get(0);
        }
        
        return correntista;
    }
    
}
