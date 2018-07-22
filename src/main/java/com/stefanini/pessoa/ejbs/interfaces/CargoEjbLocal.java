package com.stefanini.pessoa.ejbs.interfaces;

import com.stefanini.pessoa.entidades.Cargo;

public interface CargoEjbLocal  extends AbstractLocalEjb<Cargo>{

	boolean hasCargo(String cargo);


}
