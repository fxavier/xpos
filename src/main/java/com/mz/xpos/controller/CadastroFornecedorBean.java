/**
 * 
 */
package com.mz.xpos.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mz.xpos.model.Fornecedor;
import com.mz.xpos.model.Pais;
import com.mz.xpos.service.CadastroFornecedorService;
import com.mz.xpos.util.jsf.FacesUtil;

/**
 * @author Dell
 *
 */
@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroFornecedorService cadastroFornecedorService;
	
	private Fornecedor fornecedor;
	
	public CadastroFornecedorBean(){
		limpar();
	}
	
	private void limpar() {
		this.fornecedor = new Fornecedor();		
	}

	public void salvar(){
		this.fornecedor = cadastroFornecedorService.salvar(fornecedor);
		FacesUtil.addInfoMessage("Fornecedor adicionado com sucesso");
		limpar();
	}
	
	
	public Pais[] getPaises(){
		return Pais.values();
	}
	
	public boolean isEditando(){
		return fornecedor.getId() != null;
	}

	/**
	 * @return the fornecedor
	 */
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	

}
