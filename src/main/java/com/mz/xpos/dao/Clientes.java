package com.mz.xpos.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.mz.xpos.model.Cliente;



public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Cliente guardar(Cliente cliente){
		return manager.merge(cliente);
	}
	
	public Cliente porId(Long id) {
		return this.manager.find(Cliente.class, id);
	}
	
	public List<Cliente> porNomes(String nome) {
		return this.manager.createQuery("from Cliente " +
				"where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	
	public Cliente porNome(String nome){
		try{
			return manager.createQuery("from Cliente where upper(nome) = :nome",Cliente.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e){
			return null;
		}
		                
	}
}