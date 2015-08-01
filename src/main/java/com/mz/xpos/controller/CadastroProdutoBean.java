package com.mz.xpos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.mz.xpos.dao.Categorias;
import com.mz.xpos.dao.Fornecedores;
import com.mz.xpos.model.Categoria;
import com.mz.xpos.model.Fornecedor;
import com.mz.xpos.model.Produto;
import com.mz.xpos.service.CadastroProdutoService;
import com.mz.xpos.util.jsf.FacesUtil;


@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;
	
	@Inject
	private Fornecedores fornecedores;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	private Categoria categoriaPai;
	private Fornecedor fornecedor;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	private List<Fornecedor> listFornecedores;
	
	public CadastroProdutoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			categoriasRaizes = categorias.raizes();
			listFornecedores = fornecedores.listFornecedor();
			
			if (this.categoriaPai != null) {
				carregarSubcategorias();
				
			}
		}
	}
	
	
	public void carregarSubcategorias() {
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}
	
	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
		listFornecedores = new ArrayList<>();
	}
	
	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();
		
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
		
		if(this.fornecedor != null){
			this.fornecedor = this.produto.getFornecedor();
		}
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	
	public boolean isEditando() {
		return this.produto.getId() != null;
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

	/**
	 * @return the listFornecedores
	 */
	public List<Fornecedor> getListFornecedores() {
		return listFornecedores;
	}

	/**
	 * @param listFornecedores the listFornecedores to set
	 */
	public void setListFornecedores(List<Fornecedor> listFornecedores) {
		this.listFornecedores = listFornecedores;
	}
	
	

}