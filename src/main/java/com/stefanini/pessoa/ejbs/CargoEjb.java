package com.stefanini.pessoa.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.stefanini.pessoa.ejbs.interfaces.CargoEjbLocal;
import com.stefanini.pessoa.entidades.Cargo;

@Stateless
public class CargoEjb  extends AbstractEjb<Cargo> implements CargoEjbLocal {

	@Override
	public boolean salvar(Cargo cargo) {
		return super.salvar(cargo);
	}
	
	@Override
	public List<Cargo> findAll(){
		return super.findAll("Cargo.findAll", Cargo.class);
	}
	
	@Override
	public Cargo getById(Long cargoId) {
		return super.getById(Cargo.class, cargoId);
	}
	@Override
	public boolean delete(Cargo cargo) {
		return super.delete(cargo, "DELETE FROM Cargo l WHERE ID_CARGO = ?");
	}

	@Override
	public boolean hasCargo(String cargo) {
		Query query = this.manager.createQuery("SELECT c FROM Cargo WHERE c.desc = :cargo");
		
		try {
			query.setParameter("cargo", cargo.toLowerCase());
			if(query.getSingleResult() != null)
				return true;
		}catch (NoResultException e) {
			System.out.println("Nenhum resultado encontrado");
			e.printStackTrace();
		}
		
		return false;
	}
}
