/**
 * 
 */
package com.mz.xpos.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mz.xpos.dao.Fornecedores;
import com.mz.xpos.dao.filter.FornecedorFilter;
import com.mz.xpos.model.Fornecedor;
import com.mz.xpos.util.jsf.FacesUtil;



/**
 * @author Dell
 *
 */
@Named
@ViewScoped
public class PesquisaFornecedorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Fornecedores fornecedores;
	
	private FornecedorFilter filtro;
	
	private List<Fornecedor> fornecedoresFiltrados;
	
	private Fornecedor fornecedorselecionado;
	
	public PesquisaFornecedorBean(){
		filtro = new FornecedorFilter();
	//	fornecedoresFiltrados = fornecedores.filtrados(filtro);
	}
	
	public void excluir(){
		fornecedores.remover(fornecedorselecionado);
		fornecedoresFiltrados.remove(fornecedorselecionado);
		FacesUtil.addInfoMessage("Fornecedor   "+ fornecedorselecionado.getNome()+
				     "Excluido com sucesso");
	}
	
	
	public void pesquisar(){
		fornecedoresFiltrados = fornecedores.filtrados(filtro);
	}

	/**
	 * @return the filtro
	 */
	public FornecedorFilter getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(FornecedorFilter filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return the fornecedoresFiltrados
	 */
	public List<Fornecedor> getFornecedoresFiltrados() {
		return fornecedoresFiltrados;
	}

	/**
	 * @param fornecedoresFiltrados the fornecedoresFiltrados to set
	 */
	public void setFornecedoresFiltrados(List<Fornecedor> fornecedoresFiltrados) {
		this.fornecedoresFiltrados = fornecedoresFiltrados;
	}

	/**
	 * @return the fornecedorselecionado
	 */
	public Fornecedor getFornecedorselecionado() {
		return fornecedorselecionado;
	}

	/**
	 * @param fornecedorselecionado the fornecedorselecionado to set
	 */
	public void setFornecedorselecionado(Fornecedor fornecedorselecionado) {
		this.fornecedorselecionado = fornecedorselecionado;
	}
	
	

}
