package com.mz.xpos.model;

public enum FormaPagamento {

	DINHEIRO("Cash"), 
	CARTAO_CREDITO("Cartão de crédito"), 
	CARTAO_DEBITO("Cartão de débito"), 
	CHEQUE("Cheque"), 
	DEPOSITO_BANCARIO("Depósito bancário");
	
	private String descricao;
	
	FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}