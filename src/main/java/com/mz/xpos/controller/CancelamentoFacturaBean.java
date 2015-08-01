package com.mz.xpos.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.mz.xpos.model.Factura;
import com.mz.xpos.service.CancelamentoFacturaService;
import com.mz.xpos.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelamentoFacturaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoFacturaService cancelamentoFacturaService;
	
	@Inject
	private Event<FacturaAlteradoEvent> facturaAlteradoEvent;
	
	@Inject
	@FacturaEdicao
	private Factura factura;
	
	public void cancelarFactura() {
		this.factura = this.cancelamentoFacturaService.cancelar(this.factura);
		this.facturaAlteradoEvent.fire(new FacturaAlteradoEvent(this.factura));
		
		FacesUtil.addInfoMessage("Pedido cancelado com sucesso!");
	}
	
}
