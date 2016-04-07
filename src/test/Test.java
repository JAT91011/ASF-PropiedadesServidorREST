package test;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import dao.Actividad;
import dao.Cliente;
import dao.Propiedad;

public class Test {

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/PropiedadesServidorREST/").build();
	}

	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		// INSERTAMOS UNOS CLIENTES DE PRUEBA
		System.out.println("INSERTAMOS TRES CLIENTES DE PRUEBA");
		Cliente cl1 = new Cliente(78952922, "Endika", "Salgueiro", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		Cliente cl2 = new Cliente(12457845, "Jordan", "Aranda", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		Cliente cl3 = new Cliente(12345678, "Prueba", "Prueba", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		ClientResponse response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, cl1);
		if (response.getStatus() == 201) {
			System.out.println("Cliente insertado: " + response.getEntity(Cliente.class).toString());
		} else {
			System.out.println("Ya existe");
		}
		response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, cl2);
		if (response.getStatus() == 201) {
			System.out.println("Cliente insertado: " + response.getEntity(Cliente.class).toString());
		} else {
			System.out.println("Ya existe");
		}
		response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, cl3);
		if (response.getStatus() == 201) {
			System.out.println("Cliente insertado: " + response.getEntity(Cliente.class).toString());
		} else {
			System.out.println("Ya existe");
		}

		// OBTENEMOS TODOS LOS CLIENTES DE LA BASE DE DATOS Y LOS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODOS LOS CLIENTES DE LA BASE DE DATOS");
		Cliente[] arrayClientes = service.path("rest").path("clientes").get(Cliente[].class);
		for (Cliente c : arrayClientes) {
			System.out.println(c.toString());
		}

		// EDITAMOS EL CLIENTE LLAMADO ENDIKA
		System.out.println("\nEDITAMOS EL CLIENTE 'ENDIKA'");
		cl1.setNombre("Endika Editado");
		response = service.path("rest").path("clientes").path(Integer.toString(cl1.getDni())).type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, cl1);
		if (response.getStatus() == 204) {
			System.out.println("Cliente editado.");
		} else {
			System.out.println("No se ha podido editar el cliente");
		}

		// OBTENEMOS EL CLIENTE EDITADO Y LO MOSTRAMOS
		System.out.println("\nMOSTRAMOS LOS DATOS DEL CLIENTE 'ENDIKA' TRAS LA EDICION");
		cl1 = service.path("rest").path("clientes").path(Integer.toString(cl1.getDni())).get(Cliente.class);
		System.out.println(cl1.toString());

		// BORRAMOS EL CLIENTE 3 (EL DE PRUEBA)
		System.out.println("\nELIMINAMOS EL CLIENTE DE PRUEBA");
		service.path("rest").path("clientes").path(Integer.toString(cl3.getDni())).delete();
		System.out.println("Cliente '" + cl3.getNombre() + "' borrado correctamente de la base de datos.");

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS Y LAS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS");
		Actividad[] arrayActividades = service.path("rest").path("actividades").get(Actividad[].class);
		for (Actividad a : arrayActividades) {
			System.out.println(a.toString());
		}

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1");
		Actividad[] arrayActividadesPorProp = service.path("rest").path("actividades").path("1").get(Actividad[].class);
		for (Actividad a : arrayActividadesPorProp) {
			System.out.println(a.toString());
		}

		// OBTENEMOS TODAS LAS PROPIEDADES Y LAS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODAS LAS PROPIEDADES DE LA BASE DE DATOS");
		Propiedad[] arrayPropiedades = service.path("rest").path("propiedades").get(Propiedad[].class);
		for (Propiedad p : arrayPropiedades) {
			System.out.println(p.toString());
		}

		// OBTENEMOS LA PROPIEDAD CON ID 1 Y LA MOSTRAMOS
		System.out.println("\nVISUALIZAMOS LA PROPIEDAD CON ID 1");
		Propiedad propiedad = service.path("rest").path("propiedades").path("1").get(Propiedad.class);
		System.out.println(propiedad.toString());
	}
}
