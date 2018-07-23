package com.stefanini.pessoa.entidades;

public class ProfissaoJson {

	private Pessoa pessoa;
	private Cargo cargo;
	private Linguagem linguagem;
	
	public ProfissaoJson() {
		super();
	}

	public ProfissaoJson(Pessoa pessoa, Cargo cargo, Linguagem linguagem) {
		super();
		this.pessoa = pessoa;
		this.cargo = cargo;
		this.linguagem = linguagem;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Linguagem getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}
	
	
}
