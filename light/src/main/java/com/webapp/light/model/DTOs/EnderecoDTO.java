package com.webapp.light.model.DTOs;

import java.io.Serializable;
import java.util.Objects;

import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Conta;
import com.webapp.light.model.entities.MedidorEnergia;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String rua;
	private Integer numero;
	private String complemento;
	private boolean temUmaConta;

    private Long clienteId;
	private Conta conta;
	private MedidorEnergia medidor;

	public EnderecoDTO(Long id, String rua, Integer numero, String complemento, boolean temUmaConta, Cliente cliente,
			Conta conta, MedidorEnergia medidor) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.temUmaConta = temUmaConta;
		this.setClienteId(cliente.getId());
		this.conta = conta;
		this.medidor = medidor;
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

	public Conta getConta() {
		return conta;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}


	public MedidorEnergia getMedidor() {
		return medidor;
	}

	public void setMedidor(MedidorEnergia medidor) {
		this.medidor = medidor;
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
