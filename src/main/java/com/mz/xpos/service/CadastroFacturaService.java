package com.mz.xpos.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mz.xpos.dao.Facturas;
import com.mz.xpos.exception.NegocioException;
import com.mz.xpos.model.Factura;
import com.mz.xpos.model.StatusFactura;
import com.mz.xpos.util.jpa.Transactional;



public class CadastroFacturaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Facturas facturas;
	
	@Transactional
	public Factura salvar(Factura factura) {
		if (factura.isNovo()) {
			factura.setDataCriacao(new Date());
			factura.setStatus(StatusFactura.ORCAMENTO);
		}
		
		factura.recalcularValorTotal();
		
		if (factura.isNaoAlteravel()) {
			throw new NegocioException("A Factura não pode ser alterado no status "
					+ factura.getStatus().getDescricao() + ".");
		}
		
		if (factura.getItens().isEmpty()) {
			throw new NegocioException("A Factura deve possuir pelo menos um item.");
		}
		
		if (factura.isValorTotalNegativo()) {
			throw new NegocioException("Valor total da factura não pode ser negativo.");
		}
		
		factura = this.facturas.guardar(factura);
		return factura;
	}
	
}
