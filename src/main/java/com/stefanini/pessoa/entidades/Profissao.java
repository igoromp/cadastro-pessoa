package com.stefanini.pessoa.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.stefanini.pessoa.entidades.interfaces.IEntity;


/**
 * The persistent class for the Profissao database table.
 * 
 */
@Entity
@NamedQuery(name="Profissao.findAll", query="SELECT p FROM Profissao p")
public class Profissao implements Serializable,IEntity {
	private static final long serialVersionUID = 1L;

	public Profissao() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROFISSAO", nullable = false, unique = true)
	private Long id;
	
	@Column(name="ID_PESSOA")
	private Long idPessoa;
	
	@Column(name="ID_CARGO")
	private Long idCargo;
	
	@Column(name="ID_LINGUAGEM")
	private Long idLinguage;

	public Long getId() {
		return id;
	}

	public void setId(Long idProfissao) {
		this.id = idProfissao;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public Long getIdLinguage() {
		return idLinguage;
	}

	public void setIdLinguage(Long idLinguage) {
		this.idLinguage = idLinguage;
	}
	

}