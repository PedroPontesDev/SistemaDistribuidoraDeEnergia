package com.webapp.light.model.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_enderecos")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String rua;
	@Column(nullable = false, length = 10)
	private Integer numero;
	@Column(nullable = true, length = 50)
	private String complemento;

	@OneToOne(mappedBy = "endereco")
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "contador_id")
	MedidorEnergia medidor;

	@JsonIgnore
	@OneToOne
	Conta conta;
	
	private boolean temUmaConta;

	public Endereco(Long id, String rua, Integer numero, String complemento, boolean temUmaConta, Cliente cliente, Conta conta) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.temUmaConta = temUmaConta;
		this.cliente = cliente;
		this.conta = conta;
	}

	public Endereco() {

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

	public MedidorEnergia getMedidor() {
		return medidor;
	}

	public void setMedidor(MedidorEnergia medidor) {
		this.medidor = medidor;
	}

	public boolean temUmaConta() {
		return temUmaConta;
	}
	

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void setTemUmaConta(boolean temUmaConta) {
		this.temUmaConta = temUmaConta;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento
				+ ", temUmaConta=" + temUmaConta + ", cliente=" + cliente + "]";
	}
}
