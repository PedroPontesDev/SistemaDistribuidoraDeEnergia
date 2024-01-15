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
	private boolean estaEmAberto;
	private Double precoTotal;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataDeVencimento;

	public ContaDTO(Long id, LocalDate dataDeVencimento, boolean estaEmAberto, Double precoTotal) {
		this.id = id;
		this.dataDeVencimento = dataDeVencimento;
		this.estaEmAberto = estaEmAberto;
		this.precoTotal = precoTotal;
	}

	public ContaDTO() {
		
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

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
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
		return "ContaDTO [id=" + id + ", dataDeVencimento=" + dataDeVencimento + ", estaEmAberto=" + estaEmAberto;
	}

}