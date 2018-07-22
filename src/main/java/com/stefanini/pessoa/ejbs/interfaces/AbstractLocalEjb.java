package com.stefanini.pessoa.ejbs.interfaces;

import java.util.List;

import com.stefanini.pessoa.entidades.interfaces.IEntity;

public interface AbstractLocalEjb<T extends IEntity> extends IEJB{
	
	public boolean delete(T t);
	public List<T> findAll();
	public T getById(Long id);
	public boolean salvar(T t);
	
	
}
