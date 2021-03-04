/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Cartao;
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



    @Test
    public void testSalvar() {
        System.out.println("Salvar");
        cartao = new Cartao(null, "1234-5678-9123-4567", "Visa", "12/24");
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.salvarOuAlterar(cartao, sessao);
        sessao.close();

        assertNotNull(cartao.getId());
    }
    
        // @Test
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

}
