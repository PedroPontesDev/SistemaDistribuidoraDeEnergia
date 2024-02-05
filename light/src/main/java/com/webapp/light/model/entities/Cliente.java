package com.webapp.light.model.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Cliente extends Usuario {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String password;
	private String email;

	@OneToOne(mappedBy = "cliente")
	private Endereco end;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	private Set<Reclamacao> reclamacoes = new HashSet<>();
	
	public Cliente(Long id, String username, String password, String email, Endereco endereco) {
		super(id, username, password, email, endereco);
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.end = endereco;
	}

	public Cliente() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return end;
	}

	public void setEndereco(Endereco endereco) {
		this.end = endereco;
	}

	public Long getId() {
		return id;
	}

	
	
	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public Set<Reclamacao> getReclamacoes() {
		return reclamacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(end, id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(end, other.end) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", endereco=" + end + "]";
	}

}
