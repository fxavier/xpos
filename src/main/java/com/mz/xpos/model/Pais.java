/**
 * 
 */
package com.mz.xpos.model;

/**
 * @author Dell
 *
 */
public enum Pais {
	ANGOLA("Angola"),
	AFRICA_SUL("Africa do Sul"),
	AUSTRALIA("Australia"),
	BRASIL("Brasil"),
	CANADA("Canada"),
	CHINA("China"),
	EUA("EUA"),
	FRANCA("Franca"),
	INGLATERRA("Inglaterra"),
	ITALIA("Italia"),
	MOCAMBIQUE("Mocambique");
	
	private String descricao;
	
	Pais(String descricao){
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	
	

}
