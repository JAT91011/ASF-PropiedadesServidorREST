package resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import dao.Propiedad;
import utilities.Database;

public class PropiedadesResource {

	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;

	@GET
	@Path("/todas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Propiedad> getPropiedades() {
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from propiedad");
			while (rs.next()) {
				propiedades.add(new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre")));
			}
			Database.getInstance().disconnect();
			return propiedades;
		} catch (SQLException e) {
			e.printStackTrace();
			return propiedades;
		}
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Propiedad getPropiedadById(@PathParam("id") String id) {
		Propiedad propiedad = null;
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from propiedad where idPropiedad = " + Integer.parseInt(id));
			if (rs.next()) {
				propiedad = new Propiedad(rs.getInt("idPropiedad"), rs.getString("nombre"));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return propiedad;
	}
}