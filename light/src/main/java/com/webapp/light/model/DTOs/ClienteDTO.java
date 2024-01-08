package com.webapp.light.model.DTOs;

import java.io.Serializable;
import java.util.Objects;

import com.webapp.light.model.entities.Endereco;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private String email;
	private Endereco endereco;

	public ClienteDTO(Long id, String username, String password, String email, Endereco endereco) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.endereco = endereco;
	}

	public ClienteDTO() {

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
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endereco, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteDTO other = (ClienteDTO) obj;
		return Objects.equals(endereco, other.endereco) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", endereco=" + endereco + "]";
	}

	
}
