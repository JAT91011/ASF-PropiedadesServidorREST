package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import entities.Cliente;

public class ClientResource {

	private int dni;

	public ClientResource(String dni) {
		this.dni = Integer.parseInt(dni);
		// Comprobar si se ha añadido en la BD
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente getCliente() {
		Cliente client = null;
		// Obtener cliente desde la BD
		return client;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putClient(@Context UriInfo uriInfo, Cliente client) {
		Response res;
		if (dni != client.getDni()) {
			res = Response.status(409).entity("Put: Todo with " + client.getDni() + " does not match with current client").build();
		} else {
			res = Response.noContent().build(); // Code: 204
			// Guardar o modificar en la BD
		}
		return res;
	}

	@DELETE
	public void deleteClient() {
		// Borrar en la BD
	}
}
