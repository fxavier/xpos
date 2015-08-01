package com.mz.xpos.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.mz.xpos.model.Factura;
import com.mz.xpos.service.EmissaoFacturaService;
import com.mz.xpos.util.jsf.FacesUtil;


@Named
@RequestScoped
public class EmissaoFacturaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmissaoFacturaService emissaoFacturaService;
	
	@Inject
	@FacturaEdicao
	private Factura factura;
	
	@Inject
	private Event<FacturaAlteradoEvent> facturaAlteradoEvent;
	
	public void emitirFactura() {
		this.factura.removerItemVazio();
		
		try {
			this.factura = this.emissaoFacturaService.emitir(this.factura);
			this.facturaAlteradoEvent.fire(new FacturaAlteradoEvent(this.factura));
			
			FacesUtil.addInfoMessage("Factura emitida com sucesso!");
		} finally {
			this.factura.adicionarItemVazio();
		}
	}
	
}
