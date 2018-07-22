package com.stefanini.pessoa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.stefanini.pessoa.ejbs.interfaces.CargoEjbLocal;
import com.stefanini.pessoa.entidades.Cargo;

@Stateless
@Path("cargo")
public class CargoController {

	@EJB
	private CargoEjbLocal service;	
	
	@GET
	public Response getPage() {
		return Response.ok("<pre>Adicionar :stefa/cargo/add/cargo={String}\n"
				+ "Atualizar :stefa/cargo/update/{id}/{mesmos campos se cima, caso queira mudar so um campo tmb pode.}\n "
				+ "Deletar :stefa/cargo/remove/{id}\n"
				+ "Buscar Todos: stefa/cargo/list</pre>").build();
	}
	
	@GET
	@Path("/list")
	public Response  getList() {
		return Response.ok(
				(new Gson()).toJson(service.findAll())
				).build();
	}
	
	private Cargo getCargoByUri(String content) {
		List<String> split = new ArrayList<>();
		split.addAll(Arrays.asList(content.split("&")));
		Cargo cargo = new Cargo();
		
		split.forEach(s->{
			if(s.indexOf("cargo=") > -1)
				cargo.setDescCargo(s.substring("cargo=".length(),s.length()).toLowerCase());
		});		
		return cargo;
	}
	
	
	@GET
	@Path("/add/{dados}")
	public Response addCargo(@PathParam("dados") String content) {
		Cargo  cargo = getCargoByUri(content);		
		System.out.println(cargo);
		if(service.salvar(cargo))
			return Response.ok("Cargo cadastrada com sucesso!").build();
		
		return Response.ok("Erro ao gravar").build();
	}
	
	
	@GET
	@Path("/update/{id}/{dados}")
	public Response updateCargo(@PathParam("id") Long id,@PathParam("dados") String content) {
		Cargo merge = service.getById(id);
		Cargo cargo = getCargoByUri(content);
	
		
		//VERIFICANDO QUAIS CAMPOS ESTÃO NULOS  E PREENCHENDO COM OS VALORES JA	
		if(cargo.getDescCargo()!= null)
			merge.setDescCargo(cargo.getDescCargo());
	

		
		if(service.salvar(merge))
			return Response.ok("Cargo atualizado com sucesso!").build();		
		return Response.ok("Erro ao atualizar").build();
	}
	
	@GET
	@Path("/remove/{id}")
	public Response removeCargo(@PathParam("id") Long id) {
		Cargo cargo = new Cargo();	
		cargo.setId(id);
		
		if(service.delete(cargo))
			return Response.ok("Cargo deletado com sucesso!").build();
		
		return Response.ok("Erro ao deletar").build();
	}
	
	@GET
	@Path("/search/{id}")
	public Response getCargoById(@PathParam("id") Long id) {
		
		return Response.ok(
				(new Gson()).toJson(service.getById(id))
				).build();
	}
	
}
