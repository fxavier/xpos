/**
 * 
 */
package com.mz.xpos.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mz.xpos.dao.filter.FornecedorFilter;
import com.mz.xpos.exception.NegocioException;
import com.mz.xpos.model.Fornecedor;
import com.mz.xpos.util.jpa.Transactional;


/**
 * @author Dell
 *
 */
public class Fornecedores implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Fornecedor guardar(Fornecedor fornecedor){
		return manager.merge(fornecedor);
	}
	
	public Fornecedor porNome(String nome){
		try{
		return manager.createQuery("from Fornecedor where upper(nome) = :nome",Fornecedor.class)
				.setParameter("nome", nome.toUpperCase()).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> listFornecedor(){
		return manager.createQuery("from Fornecedor",Fornecedor.class)
				.getResultList();
		
	}
	
	@Transactional
	public void remover(Fornecedor fornecedor){
		try{
			fornecedor = porId(fornecedor.getId());
			manager.remove(fornecedor);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("O fornecedor nao pode ser removido");
		}
	}

	public Fornecedor porId(Long id) {
		return manager.find(Fornecedor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> filtrados(FornecedorFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Fornecedor.class);
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

}
