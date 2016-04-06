package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import entities.Cliente;

//Sets the path to base URL + /hello
@Path("/client")
public class ClientHandler {

	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;

	@GET
	@Path("/one")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente getClient() {
		return new Cliente(22756156, "Jordan", "Aranda", "jordan.aranda@me.com", "Calle...", 48902, 676909011);
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getAllClients() {
		List<Cliente> listadoClientes = new ArrayList<Cliente>();
		listadoClientes.add(new Cliente(22756156, "Jordan", "Aranda", "jordan.aranda@me.com", "Calle...", 48902, 676909011));
		listadoClientes.add(new Cliente(22756156, "Jordan", "Aranda", "jordan.aranda@me.com", "Calle...", 48902, 676909011));
		listadoClientes.add(new Cliente(22756156, "Jordan", "Aranda", "jordan.aranda@me.com", "Calle...", 48902, 676909011));
		return listadoClientes;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCliente(Cliente client) {
		client.setNombre("Jordan");
		return Response.status(201).entity(client).build();

		/*
		 * if (TodoDao.instance().getModel().containsKey(todo.getId())) { res =
		 * Response.status(409).entity("Post: Todo with " + todo.getId() +
		 * " already exists").build(); } else { URI uri =
		 * uriInfo.getAbsolutePathBuilder().path(todo.getId()).build(); res =
		 * Response.created(uri).entity(todo).build(); // Code: 201
		 * TodoDao.instance().getModel().put(todo.getId(), todo); }
		 */
	}
}