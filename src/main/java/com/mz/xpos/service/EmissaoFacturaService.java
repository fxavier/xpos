package com.mz.xpos.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mz.xpos.dao.Facturas;
import com.mz.xpos.exception.NegocioException;
import com.mz.xpos.model.Factura;
import com.mz.xpos.model.StatusFactura;
import com.mz.xpos.util.jpa.Transactional;



public class EmissaoFacturaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFacturaService cadastroFacturaService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private Facturas facturas;
	
	@Transactional
	public Factura emitir(Factura factura) {
		factura = this.cadastroFacturaService.salvar(factura);
		
		if (factura.isNaoEmissivel()) {
			throw new NegocioException("Factura não pode ser emitido com status "
					+ factura.getStatus().getDescricao() + ".");
		}
		
		this.estoqueService.baixarItensEstoque(factura);
		
		factura.setStatus(StatusFactura.EMITIDO);
		
		factura = this.facturas.guardar(factura);
		
		return factura;
	}
	
}
