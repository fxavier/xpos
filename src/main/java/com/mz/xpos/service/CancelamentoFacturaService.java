package com.mz.xpos.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.mz.xpos.dao.Facturas;
import com.mz.xpos.exception.NegocioException;
import com.mz.xpos.model.Factura;
import com.mz.xpos.model.StatusFactura;
import com.mz.xpos.util.jpa.Transactional;



public class CancelamentoFacturaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Facturas facturas;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Factura cancelar(Factura factura) {
		factura = this.facturas.porId(factura.getId());
		
		if (factura.isNaoCancelavel()) {
			throw new NegocioException("Factura não pode ser cancelado no status "
					+ factura.getStatus().getDescricao() + ".");
		}
		
		if (factura.isEmitido()) {
			this.estoqueService.retornarItensEstoque(factura);
		}
		
		factura.setStatus(StatusFactura.CANCELADO);
		
		factura = this.facturas.guardar(factura);
		
		return factura;
	}

}
