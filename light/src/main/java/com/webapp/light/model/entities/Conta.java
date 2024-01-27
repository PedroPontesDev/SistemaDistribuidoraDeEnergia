package com.webapp.light.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contas")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataDeVencimento;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataDeEmissao;
	
	private boolean estaEmAberto;

	@Column
	private Double precoTotal;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	public Conta(Long id, LocalDate dataDeVencimento, boolean estaEmAberto, Endereco endereco, Double precoTotal) {
		this.id = id;
		this.dataDeVencimento = dataDeVencimento;
		this.estaEmAberto = estaEmAberto;
		this.endereco = endereco;
		this.precoTotal = precoTotal;
	}

	public Conta() {

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

	public boolean EstaEmAberto() {
		return estaEmAberto;
	}

	public void setEstaEmAberto(boolean estaEmAberto) {
		this.estaEmAberto = estaEmAberto;
	}
	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Conta other = (Conta) obj;
		return Objects.equals(dataDeVencimento, other.dataDeVencimento) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", dataDeVencimento=" + dataDeVencimento + ", estaEmAberto=" + estaEmAberto
				+ ", contador=" + "]";
	}

}
