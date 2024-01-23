package com.webapp.light.model.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contador_de_energia")
public class MedidorEnergia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column
	private Double preco;
	@Column
	private Double hora;
    @Column
    private Double totalPrecoPorHora;
	
    @JsonIgnore
	@OneToOne(mappedBy = "medidor")
    private Endereco endereco;

	
	public MedidorEnergia(Long id, Conta conta, Endereco endereco, Double preco, Double hora) {
		this.id = id;
		this.endereco = endereco;
		this.preco = preco;
		this.hora = hora;
	}

	public MedidorEnergia() {
		
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getHora() {
		return hora;
	}

	public void setHora(Double hora) {
		this.hora = hora;
	}

	public Long getId() {
		return id;
	}
	

	public Double getTotalPrecoPorHora() {
		return totalPrecoPorHora;
	}

	public void setTotalPrecoPorHora(Double totalPrecoPorHora) {
		this.totalPrecoPorHora = totalPrecoPorHora;
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
		MedidorEnergia other = (MedidorEnergia) obj;
		return Objects.equals(id, other.id);
	}
	
}
