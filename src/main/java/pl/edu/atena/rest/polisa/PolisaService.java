package pl.edu.atena.rest.polisa;

import java.util.Objects;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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

import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.entities.Polisa;

@Path("/polisa")
public class PolisaService {

	@Inject
	private PolisaDao polisaDao;

	private Logger log = Logger.getLogger("PolisaService");

	/**
	 * Filtrowanie polis
	 * 
	 * @param mode
	 * @param statusPolisy
	 * @param ubezpieczajacy
	 * @param httpHeaders
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML + ";charset=utf-8", MediaType.APPLICATION_JSON + ";charset=utf-8" })
	public Response filtrujPolisy(@DefaultValue("") @QueryParam("numerPolisy") String numerPolisy,
			@DefaultValue("") @QueryParam("statusPolisy") String statusPolisy,
			@DefaultValue("") @QueryParam("ubezpieczajacy") String ubezpieczajacy, @Context HttpHeaders httpHeaders) {

		// FIXME -- filtrowanie polisy

		log.info(numerPolisy);
		log.info(statusPolisy);
		log.info(ubezpieczajacy);

		httpHeaders.getRequestHeaders().forEach((klucz, lista) -> {
			StringBuilder sb = new StringBuilder(klucz);
			sb.append(":").append(lista);
			log.info(sb.toString());
		});

		Polisa polisa = polisaDao.szukajPoNumerze(numerPolisy);

		JsonObject result = Json.createObjectBuilder().add("numerPolisyZlocitki", polisa.getNumerPolisy()).build();


		return Response.status(200).entity(result).build();

	};

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Polisa polisa, @Context HttpHeaders headers) {
		Objects.nonNull(polisa);
		polisaDao.create(polisa);
		return Response.status(200).entity(polisa).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Polisa polisa) {
		Polisa result = polisaDao.update(polisa);
		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		polisaDao.delete(id);
		return Response.status(200).build();
	}

}
