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
import com.stefanini.pessoa.ejbs.interfaces.LinguagemEjbLocal;
import com.stefanini.pessoa.ejbs.interfaces.PessoaEjbLocal;
import com.stefanini.pessoa.ejbs.interfaces.ProfissaoEjbLocal;
import com.stefanini.pessoa.entidades.Cargo;
import com.stefanini.pessoa.entidades.Linguagem;
import com.stefanini.pessoa.entidades.Pessoa;
import com.stefanini.pessoa.entidades.Profissao;

@Stateless
@Path("profissao")
public class ProfissaoController {

	@EJB
	private ProfissaoEjbLocal service;
	
	@EJB
	private CargoEjbLocal cargoService;
	
	@EJB
	private PessoaEjbLocal pessoaService;
	
	@EJB
	private LinguagemEjbLocal linguagemService;
	
	
	@GET
	public Response getPage() {
		return Response.ok("<pre>Adicionar :stefa/profissao/add/pessoa={id}&cargo={id}&linguagem={id}\n"
				+ "Atualizar :stefa/profissao/update/{id}/{mesmos campos se cima, caso queira mudar so um campo tmb pode.}\n "
				+ "Deletar :stefa/profissao/remove/{id}\n"
				+ "Buscar Todos: stefa/profissao/list</pre>").build();
	}
	
	@GET
	@Path("/list")
	public Response  getList() {
		List<Profissao> list = service.findAll();
		StringBuffer sb = new StringBuffer("<pre>");		
		
		list.forEach(l->{
			Cargo cargo = cargoService.getById(l.getIdCargo());
			Pessoa pessoa = pessoaService.getById(l.getIdPessoa());
			Linguagem linguagem =linguagemService.getById(l.getIdLinguage());
			
			sb.append("NOME:"+pessoa.getNome()+"  "
					+ "CPF:"+pessoa.getCpf()
					+"  EMAIL:"+pessoa.getEmail() 
					+"  TELEFONE: "+pessoa.getTelefone() 
					+ " CARGO:"+cargo.getDescCargo()
					+" LINGUAGEM:"+linguagem.getDsLinguagem() );
			sb.append("<br/>");	
		});
		sb.append("</pre>");	
		
		return Response.ok(
				sb
				).build();
	}
	
	private Profissao getProfissaoByUri(String content) {
		List<String> split = new ArrayList<>();
		split.addAll(Arrays.asList(content.split("&")));
		Profissao p = new Profissao();
		
		split.forEach(s->{
			if(s.indexOf("cargo=") > -1)
				p.setIdCargo(Long.parseLong(s.substring("cargo=".length(),s.length())));
			if(s.indexOf("linguagem=") > -1)
				p.setIdLinguage(Long.parseLong(s.substring("linguagem=".length(),s.length())));
			if(s.indexOf("pessoa=") > -1)
				p.setIdPessoa(Long.parseLong(s.substring("pessoa=".length() ,s.length())));			
		});
		
		return p;
	}
	
	
	
	@GET
	@Path("/add/{dados}")
	public Response addProfissao(@PathParam("dados") String content) {
		Profissao  p = getProfissaoByUri(content);		
		System.out.println(p);
		if(service.salvar(p))
			return Response.ok("Profissao cadastrada com sucesso!").build();
		
		return Response.ok("Erro ao grava").build();
	}
	
	
	@GET
	@Path("/update/{id}/{dados}")
	public Response updateProfissao(@PathParam("id") Long id,@PathParam("dados") String content) {
		Profissao merge = service.getById(id);
		Profissao profissao = getProfissaoByUri(content);
	
		
		//VERIFICANDO QUAIS CAMPOS ESTÃO NULOS  E PREENCHENDO COM OS VALORES JA	
		if(profissao.getIdCargo() != null)
			merge.setIdCargo(profissao.getIdCargo());
		if(profissao.getIdLinguage() != null)
			merge.setIdLinguage(profissao.getIdLinguage());
		if(profissao.getIdPessoa() != null)
			merge.setIdPessoa(profissao.getIdPessoa());

		
		if(service.salvar(merge))
			return Response.ok("Profissao atualizado com sucesso!").build();		
		return Response.ok("Erro ao atualizar").build();
	}
	
	@GET
	@Path("/remove/{id}")
	public Response removeProfissao(@PathParam("id") Long id) {
		Profissao profissao = new Profissao();	
		profissao.setId(id);
		
		if(service.delete(profissao))
			return Response.ok("Profissao deletada com sucesso!").build();
		
		return Response.ok("Erro ao deletar").build();
	}
	
	@GET
	@Path("/search/{id}")
	public Response getProfissaoById(@PathParam("id") Long id) {
		
		return Response.ok(
				(new Gson()).toJson(service.getById(id))
				).build();
	}
}
