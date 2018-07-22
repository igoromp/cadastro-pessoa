package com.stefanini.pessoa.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.stefanini.pessoa.entidades.interfaces.IEntity;

/**
 * @author GORILLA
 *
 */
@Entity
@NamedQuery(name="Linguagem.findAll", query ="SELECT l FROM Linguagem l")
public class Linguagem implements IEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_LINGUAGEM", nullable = false, unique= true)
	private Long id;
		
	@Column(name="DS_LINGUAGEM")
	private String dsLinguagem;
	
	@Column(name="SG_LINGUAGEM")
	private String sgLinguagem;
	
	

	public Linguagem() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsLinguagem() {
		return dsLinguagem;
	}

	public void setDsLinguagem(String dsLinguagem) {
		this.dsLinguagem = dsLinguagem;
	}

	public String getSgLinguagem() {
		return sgLinguagem;
	}

	public void setSgLinguagem(String sgLinguagem) {
		this.sgLinguagem = sgLinguagem;
	}

	@Override
	public String toString() {
		return "Linguagem [id=" + id + ", dsLinguagem=" + dsLinguagem + ", sgLinguagem=" + sgLinguagem + "]";
	}

	

}
