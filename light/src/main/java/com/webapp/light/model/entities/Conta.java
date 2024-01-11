package com.webapp.light.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.light.services.Counter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_contas")
public class Conta implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataDeVencimento;
	private Double price;
	private boolean estaEmAberto;
	
	@Autowired
	private Counter counter;
	
	public Conta(Long id, Cliente cliente, LocalDate dataDeVencimento, Double price, boolean estaEmAberto) {
      	this.id = id;
		this.cliente = cliente;
		this.dataDeVencimento = dataDeVencimento;
		this.price = price;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isEstaEmAberto() {
		return estaEmAberto;
	}

	public void setEstaEmAberto(boolean estaEmAberto) {
		this.estaEmAberto = estaEmAberto;
	}

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public Long getId() {
		return id;
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
		return "Conta [id=" + id + ", cliente=" + cliente + ", dataDeVencimento=" + dataDeVencimento + ", price="
				+ price + ", estaEmAberto=" + estaEmAberto + ", counter=" + counter + "]";
	}
	
	
	
	
	
	
	
}
