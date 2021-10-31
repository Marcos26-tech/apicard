package br.com.fiap.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.resource.bo.UltimaCeiaBO;
import br.com.fiap.resource.to.UltimaCeiaTO;

@Path("/menu")
public class UltimaCeiaResource {

	private UltimaCeiaBO ucbo = new UltimaCeiaBO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UltimaCeiaTO> buscar() {
		return ucbo.listarTodos();
	}
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UltimaCeiaTO> buscarPorVotos(){
		return ucbo.buscarOrdemVotacao();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UltimaCeiaTO buscar(@PathParam("id") int id) {
		return ucbo.listarPorId(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(UltimaCeiaTO prato, @Context UriInfo uriInfo) {
		ucbo.inserir(prato);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(prato.getId()));
		return Response.created(builder.build()).build();
	}
	
	
	@PUT
	@Path("/votar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response votar(@PathParam("id") int id) {
		ucbo.votar(id);
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualiza(UltimaCeiaTO prato, @PathParam("id") int id) {
		prato.setId(id);
		ucbo.editar(prato);
		return Response.ok().build();
	}
	

}
