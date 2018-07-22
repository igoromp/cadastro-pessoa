package com.stefanini.pessoa.ejbs;

import java.util.List;

import javax.ejb.Stateless;

import com.stefanini.pessoa.ejbs.interfaces.LinguagemEjbLocal;
import com.stefanini.pessoa.entidades.Linguagem;

@Stateless
public class LinguagemEjb extends AbstractEjb<Linguagem> implements LinguagemEjbLocal {

	public boolean salvar(Linguagem linguagem) {
		return super.salvar(linguagem);
	}
	
	public List<Linguagem> findAll(){
		return super.findAll("Linguagem.findAll", Linguagem.class);
	}
	
	public Linguagem getById(Long linguagemId) {
		return super.getById(Linguagem.class, linguagemId);
	}
	
	public boolean delete(Linguagem linguagem) {
		return super.delete(linguagem, "DELETE FROM Linguagem l WHERE ID_LINGUAGEM = ?");
	}
}
