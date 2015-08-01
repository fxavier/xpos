package com.mz.xpos.model;

public enum StatusFactura {

	ORCAMENTO("Orçamento"), 
	EMITIDO("Emitido"), 
	CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusFactura(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
