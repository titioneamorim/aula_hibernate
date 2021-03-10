/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author felipe.souza2
 */
public class FornecedorDaoImpl extends BaseDaoImpl<Fornecedor, Long> implements FornecedorDao {

    @Override
    public Fornecedor pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Fornecedor) sessao.get(Fornecedor.class, id); // aqui faz o casting e envia o Object convertido em Fornecedor
        
    }

    @Override
    public List<Fornecedor> listarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("FROM Fornecedor f");
        return consulta.list();
    }

}
