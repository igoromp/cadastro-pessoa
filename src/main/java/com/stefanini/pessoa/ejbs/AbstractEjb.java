package com.stefanini.pessoa.ejbs;

import java.util.List;

import javax.ejb.EJBTransactionRequiredException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.stefanini.pessoa.entidades.interfaces.IEntity;

@Stateless
public  class AbstractEjb<T extends IEntity>{

	@PersistenceContext(unitName = "hackathonDS")
	protected EntityManager manager;
	

	public boolean salvar(T entity) {
				
		try {
			if(entity.getId() == null) {
				this.manager.persist(entity);				
			}else {				
				this.manager.merge(entity);	
			}
			return true;
		}catch(EJBTransactionRolledbackException | EJBTransactionRequiredException e){
			System.out.println("ACONTECEU ALGUM ERRO NO PROCESSO DE GRAVAÇÃO");
			e.printStackTrace();
		}
		
		return false;		
	}

	public boolean delete(T entity,String sql) {
		if(entity.getId() == null) 
			return false;			
		//String sql = "DELETE FROM Profissao p WHERE p.id =? ";
		try {
			Query query = this.manager.createQuery(sql);
			query.setParameter(1, entity.getId());
			query.executeUpdate();
			return true;
		}catch(EJBTransactionRolledbackException e) {
			e.printStackTrace();
		}
		
		return false;		
	}
	
	public List<T> findAll(String sql,Class<T> entity) {
		TypedQuery<T> query = this.manager.createNamedQuery(sql,entity);
		return query.getResultList();
	}

	public T getById(Class<T> entity,Long id) {
		return this.manager.find(entity , id);
	}


	
}
