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
	
	private String rua;
	private Integer numero;
	private String complemento;

	@JsonIgnore
	@OneToOne
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "medidor_id")
	private MedidorEnergia medidor;

	@JsonIgnore
	@OneToMany(mappedBy = "endereco")
	private Set<Conta> conta = new HashSet<>();

	private boolean temUmaConta;

	public Endereco(Long id, String rua, Integer numero, String complemento, Cliente cliente, MedidorEnergia medidor,
			Set<Conta> conta, boolean temUmaConta) {
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cliente = cliente;
		this.medidor = medidor;
		this.conta = conta;
		this.temUmaConta = temUmaConta;
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

	
	
	public Set<Conta> getConta() {
		return conta;
	}

	public void setConta(Set<Conta> conta) {
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
