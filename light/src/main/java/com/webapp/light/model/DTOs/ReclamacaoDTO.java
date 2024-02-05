package com.webapp.light.model.DTOs;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.webapp.light.model.entities.Cliente;

public class ReclamacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Cliente cliente;

	private String titulo;
	private String conteudo;
	private LocalDate data;

	public ReclamacaoDTO(Long id, Cliente cliente, String titulo, String conteudo, LocalDate data) {
		this.id = id;
		this.cliente = cliente;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.data = data;
	}

	public ReclamacaoDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		ReclamacaoDTO other = (ReclamacaoDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ReclamacaoDTO [id=" + id + ", client=" + cliente + ", titulo=" + titulo + ", conteudo=" + conteudo
				+ ", data=" + data + "]";
	}

}
