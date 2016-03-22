package test;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Test {
	public static void main(String[] args) {
		// Creamos una configuración para el Cliente
		ClientConfig config = new DefaultClientConfig();
		
		// Creamos el Cliente y lo inicializamos con la configuración del paso previo
		Client client = Client.create(config);
		
		// Creamos un recurso web inicializado con la url base del proyecto
		// (http://localhost:8080/de.vogella.jersey.first)
		WebResource service = client.resource(getBaseURI());
		
		// Hacemos la invocación GET solicitando formato TEXT_PLAIN a la uri /rest/hello y
		// pedimos explícitamente que nos devuelva todo el response para hacerle un toString()
		System.out.println(service.path("rest").path("hello").accept(
				MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
		
		// Hacemos la invocación GET solicitando formato TEXT_PLAIN a la uri /rest/hello y
		// pedimos explícitamente que nos devuelva el String del response
		System.out.println(service.path("rest").path("hello").accept(
				MediaType.TEXT_PLAIN).get(String.class));

		// Hacemos la invocación GET solicitando formato TEXT_XML a la uri /rest/hello y
		// pedimos explícitamente que nos devuelva el String del response
		System.out.println(service.path("rest").path("hello").accept(
				MediaType.TEXT_XML).get(String.class));

		// Hacemos la invocación GET solicitando formato TEXT_HTML a la uri /rest/hello y
		// pedimos explícitamente que nos devuelva el String del response
		System.out.println(service.path("rest").path("hello").accept(
				MediaType.TEXT_HTML).get(String.class));

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/PropiedadesREST").build();
	}

}