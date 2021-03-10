/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Correntista;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Titione
 */
public class CorrentistaDaoImpl extends BaseDaoImpl<Correntista, Long> 
        implements CorrentistaDao, Serializable {

    @Override
    public Correntista pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Correntista) sessao.get(Correntista.class, id);
    }

    @Override
    public List<Correntista> pessquisarPorNome(String nome, Session sessao) throws HibernateException {
        
        Query consulta = sessao.createQuery("FROM correntista WHERE nome LIKE :nomehql");
        consulta.setParameter("nomeHql", "%" + nome + "%");
        
        return consulta.list();
    }
    
    
}
