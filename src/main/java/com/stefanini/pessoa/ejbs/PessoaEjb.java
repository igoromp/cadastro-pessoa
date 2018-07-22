package com.stefanini.pessoa.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.stefanini.pessoa.ejbs.interfaces.PessoaEjbLocal;
import com.stefanini.pessoa.entidades.Pessoa;

@Stateless
public class PessoaEjb extends AbstractEjb<Pessoa> implements PessoaEjbLocal {
	@Override
	public boolean salvar(Pessoa pessoa) {
		return super.salvar(pessoa);
	}
	@Override
	public List<Pessoa> findAll(){
		return super.findAll("Pessoa.findAll", Pessoa.class);
	}
	
	@Override
	public Pessoa getById(Long pessoaId) {
		return super.getById(Pessoa.class, pessoaId);
	}
	@Override
	public boolean delete(Pessoa pessoa) {
		return super.delete(pessoa, "DELETE FROM Pessoa l WHERE ID_PESSOA = ?");
	}

	@Override
	public Pessoa getByCpf(String cpf) {
		String sql = "SELECT p FROM Pessoa p WHERE p.cpf =:cpf";
		TypedQuery<Pessoa> query = this.manager.createQuery(sql, Pessoa.class);
		query.setParameter("cpf", cpf);
		System.out.println("buscando pessoa");
		Pessoa result = null;
		try {
			result = query.getSingleResult();
		}catch (NoResultException e) {
			System.out.println("nenhum resultado encontrado");
		}
		
		return result;
	}
	
}