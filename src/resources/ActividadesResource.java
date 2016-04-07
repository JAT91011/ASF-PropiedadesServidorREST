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

import dao.Actividad;
import utilities.Database;

@Path("/actividades")
public class ActividadesResource {

	@Context
	UriInfo	uriInfo;

	@Context
	Request	request;

	@GET
	@Path("/todas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actividad> getActividades() {
		List<Actividad> actividades = new ArrayList<Actividad>();
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from actividad");
			while (rs.next()) {
				actividades.add(new Actividad(rs.getInt("idActividad"), rs.getString("nombre")));
			}
			Database.getInstance().disconnect();
			return actividades;
		} catch (SQLException e) {
			e.printStackTrace();
			return actividades;
		}
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Actividad getActividadById(@PathParam("id") String id) {
		Actividad actividad = null;
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult("select * from actividad where idActividad = " + Integer.parseInt(id));
			if (rs.next()) {
				actividad = new Actividad(rs.getInt("idActividad"), rs.getString("nombre"));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actividad;
	}

	@GET
	@Path("/propiedades/{idPropiedad}")
	@Produces(MediaType.APPLICATION_JSON)
	public Actividad getActividadByIdPropiedad(@PathParam("idPropiedad") String idPropiedad) {
		Actividad actividad = null;
		try {
			Database.getInstance().createConnection();
			ResultSet rs = Database.getInstance().consult(
					"select A.idActividad, A.nombre from actividad AS A INNER JOIN propiedad_actividad AS PA ON A.idActividad=PA.idActividad where PA.idPropiedad = "
							+ Integer.parseInt(idPropiedad));
			if (rs.next()) {
				actividad = new Actividad(rs.getInt("idActividad"), rs.getString("nombre"));
			}
			Database.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actividad;
	}
}