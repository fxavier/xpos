/**
 * 
 */
package com.mz.xpos.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mz.xpos.dao.Fornecedores;
import com.mz.xpos.exception.NegocioException;
import com.mz.xpos.model.Fornecedor;
import com.mz.xpos.util.jpa.Transactional;

/**
 * @author Dell
 *
 */
public class CadastroFornecedorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Fornecedores fornecedores;
	
	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor){
		Fornecedor fornecedorExistente = fornecedores.porNome(fornecedor.getNome());
		
		if(fornecedorExistente != null && !fornecedorExistente.equals(fornecedor)){
			throw new NegocioException("Fornecedor com esse nome ja foi cadastrado");
		} 
		
		 return fornecedores.guardar(fornecedor);
	}

}
