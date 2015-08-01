package com.mz.xpos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mz.xpos.dao.Facturas;
import com.mz.xpos.dao.filter.FacturaFilter;
import com.mz.xpos.model.Factura;
import com.mz.xpos.model.StatusFactura;

@Named
@ViewScoped
public class PesquisaFacturasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Facturas facturas;
	
	private FacturaFilter filtro;
	private List<Factura> facturasFiltrados;
	
	public PesquisaFacturasBean() {
		filtro = new FacturaFilter();
		facturasFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
	     facturasFiltrados = facturas.filtrados(filtro);
	}
	
	public StatusFactura[] getStatuses() {
		return StatusFactura.values();
	}
	
	public List<Factura> getFacturasFiltrados() {
		return facturasFiltrados;
	}

	public FacturaFilter getFiltro() {
		return filtro;
	}
	
}