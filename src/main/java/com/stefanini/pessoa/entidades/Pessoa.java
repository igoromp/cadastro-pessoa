package com.stefanini.pessoa.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.stefanini.pessoa.entidades.interfaces.IEntity;

/**
 * The persistence class for the entity Pessoas
 * @author GORILLA
 *
 */

@Entity
@NamedQuery(name="Pessoa.findAll",query ="SELECT p from Pessoa p")
@Table(name="pessoa")
public class Pessoa  implements Serializable, IEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PESSOA", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "DS_NOME")
	private String nome;
	
	@Column(name = "DS_EMAIL")
	private String email;

	@Column(name = "DS_CPF")
	private String cpf;

	@Column(name = "DS_TELEFONE")
	private String telefone;
	
	
	

	public Pessoa() {
		super();
	}

	public Pessoa(String nome, String email, String cpf, String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", telefone=" + telefone;
	}
	
	
	
	
	
	
}
