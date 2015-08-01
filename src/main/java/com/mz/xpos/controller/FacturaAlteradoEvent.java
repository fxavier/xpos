package com.mz.xpos.controller;

import com.mz.xpos.model.Factura;



public class FacturaAlteradoEvent {

	private Factura factura;
	
	public FacturaAlteradoEvent(Factura factura) {
		this.factura = factura;
	}

	public Factura getFactura() {
		return factura;
	}
	
}
