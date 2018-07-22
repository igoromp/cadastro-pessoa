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

@Entity
@NamedQuery(name="Cargo.findAll",query = "SELECT c FROM Cargo c")
@Table(name="cargo")
public class Cargo implements Serializable,IEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CARGO",nullable = false, unique = true)
	private Long id;
		
	@Column(name="DS_CARGO")
	private String desc;


	public Cargo() {
		super();
	}


	public Cargo(Long id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescCargo() {
		return desc;
	}


	public void setDescCargo(String desc) {
		this.desc = desc;
	}


	@Override
	public String toString() {
		return "Cargo [id=" + id + ", desc=" + desc + "]";
	}
	
	
	
	
}
