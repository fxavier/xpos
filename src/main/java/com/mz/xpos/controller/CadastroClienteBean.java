/**
 * 
 */
package com.mz.xpos.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mz.xpos.model.Cliente;
import com.mz.xpos.service.CadastroClienteService;
import com.mz.xpos.util.jsf.FacesUtil;

/**
 * @author Xavier Francisco
 *
 */
@Named
@ViewScoped
public class CadastroClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	private Cliente cliente;
	
	public CadastroClienteBean(){
		limpar();
	}

	private void limpar() {
		cliente = new Cliente();
		
	}
	
	
	public void salvar(){
		this.cliente = cadastroClienteService.Salvar(cliente);	
		limpar();
		FacesUtil.addInfoMessage("Cliente Salvo com sucesso");
	
	}
	
	
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
