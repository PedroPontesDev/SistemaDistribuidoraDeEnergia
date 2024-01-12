package com.webapp.light.model.DTOs;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.MedidorEnergia;
import com.webapp.light.model.entities.Endereco;

public class ContaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Cliente cliente;
	private boolean estaEmAberto;
	private MedidorEnergia contador;
	private Endereco endereco;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataDeVencimento;

	public ContaDTO(Long id, Cliente cliente, LocalDate dataDeVencimento, boolean estaEmAberto) {
		this.id = id;
		this.cliente = cliente;
		this.dataDeVencimento = dataDeVencimento;
		this.estaEmAberto = estaEmAberto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataDeVencimento() {
		return dataDeVencimento;
	}

	public void setDataDeVencimento(LocalDate dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}

	public boolean EstaEmAberto() {
		return estaEmAberto;
	}

	public void setEstaEmAberto(boolean estaEmAberto) {
		this.estaEmAberto = estaEmAberto;
	}

	public Long getId() {
		return id;
	}
	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public MedidorEnergia getContador() {
		return contador;
	}

	public void setContador(MedidorEnergia contador) {
		this.contador = contador;
	}


	@Override
	public int hashCode() {
		return Objects.hash(dataDeVencimento, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaDTO other = (ContaDTO) obj;
		return Objects.equals(dataDeVencimento, other.dataDeVencimento) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ContaDTO [id=" + id + ", cliente=" + cliente + ", dataDeVencimento=" + dataDeVencimento
				+ ", estaEmAberto=" + estaEmAberto + ", contador=" + contador + "]";
	}
	
}
