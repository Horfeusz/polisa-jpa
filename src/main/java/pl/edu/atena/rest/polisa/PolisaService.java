package pl.edu.atena.rest.polisa;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.dao.UbezpieczonyDao;
import pl.edu.atena.entities.Polisa;

@Path("/polisa")
public class PolisaService {

	@EJB
	private PolisaDao polisa2Dao;

	@EJB
	private UbezpieczonyDao ubezpieczonyDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Polisa polisa) {
		polisa2Dao.create(polisa);
		return Response.status(200).entity(polisa).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Polisa polisa) {
		Polisa result = polisa2Dao.update(polisa);
		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		polisa2Dao.delete(id);
		return Response.status(200).build();
	}

}
