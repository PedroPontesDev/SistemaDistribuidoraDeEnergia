package com.webapp.light.model.entities;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Cliente extends Usuario {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = true, length = 50)
	private String username;
	@Column(name = "password", nullable = true, length = 50)
	private String password;
	@Column(name = "email", nullable = true, length = 50)
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;

	public Cliente(Long id, String username, String password, String email, Endereco endereco) {
		super(id, username, password, email, endereco);
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.endereco = endereco;
	}

	public Cliente() {
		// TODO Auto-generated constructor stub
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
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(endereco, id);
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
		return Objects.equals(endereco, other.endereco) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", endereco=" + endereco + "]";
	}

}
