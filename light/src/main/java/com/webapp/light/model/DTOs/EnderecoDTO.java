package com.webapp.light.model.DTOs;

import java.io.Serializable;
import java.util.Objects;

import com.webapp.light.model.entities.Cliente;

public class EnderecoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String rua;
	private Integer numero;
	private String complemento;
	private boolean temUmaConta;
	
	private Cliente cliente;
	
	public EnderecoDTO(Long id, String rua, Integer numero, String complemento, boolean temUmaConta) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.temUmaConta = temUmaConta;
	}
	
	public EnderecoDTO() {
		
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isTemUmaConta() {
		return temUmaConta;
	}

	public void setTemUmaConta(boolean temUmaConta) {
		this.temUmaConta = temUmaConta;
	}

	public Long getId() {
		return id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoDTO other = (EnderecoDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EnderecoDTO [id=" + id + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento
				+ ", temUmaConta=" + temUmaConta + "]";
	}
	
	

	
}
