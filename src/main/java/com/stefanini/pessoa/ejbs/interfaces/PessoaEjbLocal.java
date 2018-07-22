package com.stefanini.pessoa.ejbs.interfaces;

import com.stefanini.pessoa.entidades.Pessoa;

public interface PessoaEjbLocal extends AbstractLocalEjb<Pessoa>{

	Pessoa getByCpf(String cpf);

}