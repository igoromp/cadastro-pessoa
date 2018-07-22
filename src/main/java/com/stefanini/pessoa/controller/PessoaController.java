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
import com.stefanini.pessoa.ejbs.interfaces.PessoaEjbLocal;
import com.stefanini.pessoa.entidades.Pessoa;

@Stateless
@Path("pessoa")
public class PessoaController {
	
	@EJB
	private PessoaEjbLocal service;
	
	@GET
	public Response getPage() {
		return Response.ok("<pre>Adicionar :stefa/pessoa/add/nome={String}&email={String}&cpf={String}&telefone={String}\n"
				+ "Atualizar :stefa/pessoa/update/{id}/{mesmos campos se cima, caso queira mudar so um campo tmb pode.}\n "
				+ "Deletar :stefa/pessoa/remove/{id}\n"
				+ "Buscar Todos: stefa/pessoa/list</pre>").build();
	}
		
	@GET
	@Path("/list")
	public Response  getList() {
		return Response.ok(
				(new Gson()).toJson(service.findAll())
				).build();
	}
	
	private Pessoa getPessoaByUri(String content) {
		List<String> split = new ArrayList<>();
		split.addAll(Arrays.asList(content.split("&")));
		Pessoa p = new Pessoa();
		
		split.forEach(s->{
			if(s.indexOf("nome=") > -1)
				p.setNome(s.substring("nome=".length(),s.length()).toLowerCase());
			if(s.indexOf("email=") > -1)
				p.setEmail(s.substring("email=".length(),s.length()).toLowerCase() );
			if(s.indexOf("cpf=") > -1)
				p.setCpf(s.substring("cpf=".length() ,s.length()));
			if(s.indexOf("telefone=") > -1)
				p.setTelefone(s.substring("telefone=".length(),s.length()).toLowerCase());
		});
		
		return p;
	}
	
	
	
	@GET
	@Path("/add/{dados}")
	public Response addPessoa(@PathParam("dados") String content) {
		Pessoa  p = getPessoaByUri(content);	
		
		if(service.getByCpf(p.getCpf()) != null)
				return Response.ok("CPF ja cadastrado").build();
		
		if(service.salvar(p))
			return Response.ok("Pessoa cadastrada com sucesso!").build();
		
		return Response.ok("Erro ao grava").build();
	}
	
	
	@GET
	@Path("/update/{id}/{dados}")
	public Response updatePessoa(@PathParam("id") Long id,@PathParam("dados") String content) {
		Pessoa merge = service.getById(id);
		Pessoa pessoa = getPessoaByUri(content);
		
		System.out.println(pessoa);
		
		//VERIFICANDO QUAIS CAMPOS ESTÃO NULOS  E PREENCHENDO COM OS VALORES JA	
		if(pessoa.getNome() != null)
			merge.setNome(pessoa.getNome());
		if(pessoa.getEmail() != null)
			merge.setEmail(pessoa.getEmail());
		if(pessoa.getCpf() != null)
			merge.setCpf(pessoa.getCpf());
		if(pessoa.getTelefone() != null)
			merge.setTelefone(pessoa.getTelefone());
	
		System.out.println(merge);
		
		if(service.salvar(merge))
			return Response.ok("Pessoa atualizado com sucesso!").build();		
		return Response.ok("Erro ao atualizar").build();
	}
	
	@GET
	@Path("/remove/{id}")
	public Response removePessoa(@PathParam("id") Long id) {
		Pessoa pessoa = new Pessoa();	
		pessoa.setId(id);
		
		if(service.delete(pessoa))
			return Response.ok("Pessoa deletada com sucesso!").build();
		
		return Response.ok("Erro ao deletar").build();
	}
	
	@GET
	@Path("/search/{id}")
	public Response getPessoaById(@PathParam("id") Long id) {
		
		return Response.ok(
				(new Gson()).toJson(service.getById(id))
				).build();
	}
	
	
}
