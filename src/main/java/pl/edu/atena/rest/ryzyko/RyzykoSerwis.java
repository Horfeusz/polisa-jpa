package pl.edu.atena.rest.ryzyko;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RyzykoSerwis {

	@GET
	@Path("/polisa/{numerPolisy}/ryzyko/{symbol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dajRyzyko(@PathParam(value = "numerPolisy") String numerPolisy,
			@PathParam(value = "symbol") String symbol) {
		return Response.status(200).build();
	}

	@GET
	@Path("/polisa/{numerPolisy}/ryzyko/{symbol}")
	@Produces({ MediaType.APPLICATION_XML + ";charset=utf-8", MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public Response filtrujRyzyka(
			@DefaultValue("") @QueryParam("numerPolisy") String numerPolisy,
			@DefaultValue("") @QueryParam("cepikId") String systemIdentifier,
			@DefaultValue("") @QueryParam("vin") String vin,
			@DefaultValue("") @QueryParam("nrRejestracyjny") String registrationNumber,
			@Context HttpHeaders httpHeaders) {
		return null;
	};

	@POST
	@Path("/polisa/{numerPolisy}/ryzyko")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dodajRyzykoDoPolisy(@PathParam(value = "numerPolisy") String numerPolisy) {
		return Response.status(200).build();
	}

	@PUT
	@Path("/polisa/{numerPolisy}/ryzyko/{symbol}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response aktualizujRyzykoNaPolisie(@PathParam(value = "numerPolisy") String numerPolisy,
			@PathParam(value = "symbol") String symbol) {
		return Response.status(200).build();
	}

	@DELETE
	@Path("/polisa/{numerPolisy}/ryzyko/{symbol}")
	public Response usunRyzykoZPolisy(@PathParam(value = "numerPolisy") String numerPolisy,
			@PathParam(value = "symbol") String symbol) {
		return Response.status(200).build();
	}

}
