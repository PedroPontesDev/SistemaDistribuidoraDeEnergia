package com.webapp.light.model.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reclamacoes")
public class Reclamacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonIgnore
	private Cliente cliente;

	private String titulo;
	private String conteudo;
	private LocalDate data;
	
	public Reclamacao(Long id, Cliente cliente, String titulo, String conteudo, LocalDate data) {
		this.id = id;
		this.cliente = cliente;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.data = data;
	}

	public Reclamacao() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
		Reclamacao other = (Reclamacao) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Reclamacao [id=" + id + ", cliente=" + cliente + ", titulo=" + titulo + ", conteudo=" + conteudo
				+ ", data=" + data + "]";
	}
	
	

	
	
}
