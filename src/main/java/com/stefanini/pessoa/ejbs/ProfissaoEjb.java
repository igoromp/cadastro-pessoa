package com.stefanini.pessoa.ejbs;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.stefanini.pessoa.ejbs.interfaces.ProfissaoEjbLocal;
import com.stefanini.pessoa.entidades.Cargo;
import com.stefanini.pessoa.entidades.Profissao;
import com.stefanini.pessoa.entidades.interfaces.IEntity;

@Stateless
public class ProfissaoEjb extends AbstractEjb<Profissao> implements ProfissaoEjbLocal {

	@Override
	public boolean salvar(Profissao profissao) {
		return super.salvar(profissao);
	}
	@Override
	public List<Profissao> findAll(){
		return super.findAll("Profissao.findAll", Profissao.class);
	}
	@Override
	public Profissao getById(Long profissaoId) {
		return super.getById(Profissao.class, profissaoId);
	}
	@Override
	public boolean delete(Profissao profissao) {
		return super.delete(profissao, "DELETE FROM Profissao l WHERE ID_Profissao = ?");
	}
	

	
}
