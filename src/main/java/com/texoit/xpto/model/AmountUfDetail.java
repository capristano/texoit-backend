package com.texoit.xpto.model;

/**
 * Classe que armazena dados especificos da entidade City
 * @author Cleiton
 */
public class AmountUfDetail {
	
	private String uf;
	private int amount;
	
	public AmountUfDetail(String s){
		String[] values = s.split(",");
		setName(values[0]);
		setAmount(Integer.parseInt(values[1]));
	}
	
	public String getName() {
		return uf;
	}
	public void setName(String uf) {
		this.uf = uf;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
