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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataDeEmissao;

	private Endereco endereco;

	public ContaDTO(Long id, boolean estaEmAberto, Double precoTotal, LocalDate dataDeVencimento,
			LocalDate dataDeEmissao, Endereco endereco) {
		this.id = id;
		this.estaEmAberto = estaEmAberto;
		this.precoTotal = precoTotal;
		this.dataDeVencimento = dataDeVencimento;
		this.dataDeEmissao = dataDeEmissao;
		this.endereco = endereco;
	}

	public ContaDTO() {

	}

	public LocalDate getDataDeVencimento() {
		return dataDeVencimento;
	}

	public void setDataDeVencimento(LocalDate dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}

	public LocalDate getDataDeEmissao() {
		return dataDeEmissao;
	}

	public void setDataDeEmissao(LocalDate dataDeEmissao) {
		this.dataDeEmissao = dataDeEmissao;
	}

	public void setId(Long id) {
		this.id = id;
	}

    
	
	public boolean isEstaEmAberto() {
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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