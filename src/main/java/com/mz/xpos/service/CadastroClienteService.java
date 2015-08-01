/**
 * 
 */
package com.mz.xpos.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mz.xpos.dao.Clientes;
import com.mz.xpos.exception.NegocioException;
import com.mz.xpos.model.Cliente;
import com.mz.xpos.util.jpa.Transactional;

/**
 * @author Xavier Francisco
 *
 */
public class CadastroClienteService implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	@Transactional
	public Cliente Salvar(Cliente cliente){
		Cliente clienteExistente = clientes.porNome(cliente.getNome());
		
		if(clienteExistente != null && !clienteExistente.equals(cliente.getNome())){
			throw new NegocioException("Ja Existe um cliente com o nome informado");
		}
		
		return clientes.guardar(cliente);
	}

}
