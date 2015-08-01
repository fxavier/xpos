package com.mz.xpos.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mz.xpos.dao.Facturas;
import com.mz.xpos.model.Factura;
import com.mz.xpos.model.ItemFactura;
import com.mz.xpos.util.jpa.Transactional;



public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Facturas facturas;
	
	@Transactional
	public void baixarItensEstoque(Factura factura) {
		factura = this.facturas.porId(factura.getId());
		
		for (ItemFactura item : factura.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Factura factura) {
		factura = this.facturas.porId(factura.getId());
		
		for (ItemFactura item : factura.getItens()) {
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
	}
	
}
