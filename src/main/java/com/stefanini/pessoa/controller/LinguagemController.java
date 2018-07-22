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
import com.stefanini.pessoa.ejbs.interfaces.LinguagemEjbLocal;
import com.stefanini.pessoa.entidades.Linguagem;

@Stateless
@Path("linguagem")
public class LinguagemController {
	 
	@EJB
	private LinguagemEjbLocal service;
	
	@GET
	public Response getPage() {
		return Response.ok("<pre>Adicionar :stefa/linguagem/add/linguagem={String}&sg={String}\n"
				+ "Atualizar :stefa/linguagem/update/{id}/{mesmos campos se cima, caso queira mudar so um campo tmb pode.}\n "
				+ "Deletar :stefa/linguagem/remove/{id}\n"
				+ "Buscar Todos: stefa/linguagem/list</pre>").build();
	}
	
	@GET
	@Path("/list")
	public Response  getList() {
		return Response.ok(
				(new Gson()).toJson(service.findAll())
				).build();
	}
	
	private Linguagem getLinguagemByUri(String content) {
		List<String> split = new ArrayList<>();
		split.addAll(Arrays.asList(content.split("&")));
		Linguagem linguagem = new Linguagem();
		
		split.forEach(s->{
			if(s.indexOf("linguagem=") > -1)
				linguagem.setDsLinguagem(s.substring("linguagem=".length(),s.length()).toLowerCase());
			if(s.indexOf("sg=") > -1)
				linguagem.setSgLinguagem(s.substring("sg=".length(),s.length()).toLowerCase());
		});		
		return linguagem;
	}
	
	
	@GET
	@Path("/add/{dados}")
	public Response addLinguagem(@PathParam("dados") String content) {
		Linguagem  linguagem = getLinguagemByUri(content);		
		System.out.println(linguagem);
		if(service.salvar(linguagem))
			return Response.ok("Cargo cadastrada com sucesso!").build();
		
		return Response.ok("Erro ao gravar").build();
	}
	
	
	@GET
	@Path("/update/{id}/{dados}")
	public Response updateLinguagem(@PathParam("id") Long id,@PathParam("dados") String content) {
		Linguagem merge = service.getById(id);
		Linguagem linguagem = getLinguagemByUri(content);
	
		
		//VERIFICANDO QUAIS CAMPOS ESTÃO NULOS  E PREENCHENDO COM OS VALORES JA	
		if(linguagem.getDsLinguagem() == null)
			merge.setDsLinguagem(linguagem.getDsLinguagem());
		if(linguagem.getSgLinguagem() == null)
			merge.setSgLinguagem(linguagem.getSgLinguagem());
			
		if(service.salvar(merge))
			return Response.ok("Cargo atualizado com sucesso!").build();		
		return Response.ok("Erro ao atualizar").build();
	}
	
	@GET
	@Path("/remove/{id}")
	public Response removeLinguagem(@PathParam("id") Long id) {
		Linguagem linguagem = new Linguagem();	
		linguagem.setId(id);
		
		if(service.delete(linguagem))
			return Response.ok("Cargo deletado com sucesso!").build();
		
		return Response.ok("Erro ao deletar").build();
	}
	
	@GET
	@Path("/search/{id}")
	public Response getLinguagemById(@PathParam("id") Long id) {
		
		return Response.ok(
				(new Gson()).toJson(service.getById(id))
				).build();
	}
}
