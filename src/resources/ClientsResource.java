package resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import entities.Cliente;

@Path("/clients")
public class ClientsResource {

	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getClients() {
		List<Cliente> clients = new ArrayList<Cliente>();
		// Obtener desde la BD
		clients.add(new Cliente(22756156, "Jordan", "Aranda", "jordan.aranda@me.com", "Calle...", 48902, 676909011));
		clients.add(new Cliente(22756156, "Jordan", "Aranda", "jordan.aranda@me.com", "Calle...", 48902, 676909011));
		clients.add(new Cliente(22756156, "Jordan", "Aranda", "jordan.aranda@me.com", "Calle...", 48902, 676909011));
		return clients;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newClient(Cliente client) {
		Response res = null;
		// Comprobar si existe o no en la BD
		// Si existe:
		// res = Response.status(409).entity("Post: Todo with " +
		// client.getDni() + " already exists").build();
		// Si no existe:
		URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(client.getDni())).build();
		res = Response.created(uri).entity(client).build();
		// Guardar en la BD
		return res;
	}

	@Path("{dni}")
	public ClientResource getTodo(@PathParam("dni") String dni) {
		return new ClientResource(dni);
	}
}
