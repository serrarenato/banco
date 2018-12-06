package br.com.bancos.controller.AccessRequest;

import java.io.Serializable;

/**
 * Classe de entrada para a API de login
 * 
 *
 */
public class AccessRequest implements Serializable {
	String agencia;
	String password;
	String conta;
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	
	
	
}
