package com.webapp.light.model.DTOs;

import java.io.Serializable;
import java.util.Objects;

import com.webapp.light.model.entities.Cliente;
import com.webapp.light.model.entities.Conta;
import com.webapp.light.model.entities.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;

public class MedidorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Double preco;
	private Double hora;
	private Double totalPrecoPorHora;

	public MedidorDTO(Long id, Double preco, Double hora, Double totalPrecoPorHora) {
		this.id = id;
		this.preco = preco;
		this.hora = hora;
		this.totalPrecoPorHora = totalPrecoPorHora;
	}

	public MedidorDTO() {

	}

	public Double getPreco() {
		return preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		MedidorDTO other = (MedidorDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "MedidorDTO [id=" + id + ", preco=" + preco + ", hora=" + hora + ", totalPrecoPorHora="
				+ totalPrecoPorHora + ", endereco=";
	}

}
