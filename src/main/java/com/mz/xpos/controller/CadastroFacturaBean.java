package com.mz.xpos.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.mz.xpos.dao.Clientes;
import com.mz.xpos.dao.Produtos;
import com.mz.xpos.dao.Usuarios;
import com.mz.xpos.model.Cliente;
import com.mz.xpos.model.EnderecoEntrega;
import com.mz.xpos.model.Factura;
import com.mz.xpos.model.FormaPagamento;
import com.mz.xpos.model.ItemFactura;
import com.mz.xpos.model.Produto;
import com.mz.xpos.model.Usuario;
import com.mz.xpos.service.CadastroFacturaService;
import com.mz.xpos.util.jsf.FacesUtil;
import com.mz.xpos.validation.SKU;



@Named
@ViewScoped
public class CadastroFacturaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Produtos produtos;
	
	@Inject
	private CadastroFacturaService cadastroFacturaService;
	
	private String sku;
	
	@Produces
	@FacturaEdicao
	private Factura factura;
	
	private List<Usuario> vendedores;
	
	private Produto produtoLinhaEditavel;
	
	public CadastroFacturaBean() {
		limpar();
	
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.vendedores = this.usuarios.vendedores();
			
			this.factura.adicionarItemVazio();
			
			this.recalcularFactura();
		}
	}
	
	private void limpar() {
		factura = new Factura();
		factura.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void facturaAlterado(@Observes FacturaAlteradoEvent event) {
		this.factura = event.getFactura();
	}
	
	public void salvar() {
		this.factura.removerItemVazio();
		
		try {
			this.factura = this.cadastroFacturaService.salvar(this.factura);
		
			FacesUtil.addInfoMessage("Factura salvo com sucesso!");
		} finally {
			this.factura.adicionarItemVazio();
		}
	}
	
	public void recalcularFactura() {
		if (this.factura != null) {
			this.factura.recalcularValorTotal();
		}
	}
	
	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	public void carregarProdutoLinhaEditavel() {
		ItemFactura item = this.factura.getItens().get(0);
		
		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item na factura com o produto informado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.factura.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				
				this.factura.recalcularValorTotal();
			}
		}
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for (ItemFactura item : this.getFactura().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}

	public List<Produto> completarProduto(String nome) {
		return this.produtos.porNome(nome);
	}
	
	public void atualizarQuantidade(ItemFactura item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getFactura().getItens().remove(linha);
			}
		}
		
		this.factura.recalcularValorTotal();
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome) {
		return clientes.porNomes(nome);
	}

	public Factura getFactura() {
		return factura;
	}
	
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
	public boolean isEditando() {
		return this.factura.getId() != null;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}