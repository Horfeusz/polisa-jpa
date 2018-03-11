package pl.edu.atena.rest.ubepieczony;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.dao.UbezpieczonyDao;
import pl.edu.atena.entities.Ubezpieczony;

@Path("/ubezpieczony")
public class UbezpieczonyService {

	@EJB
	private UbezpieczonyDao dao;

	@Inject
	private PolisaDao polisaDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Ubezpieczony ubezpieczony) {
		dao.create(ubezpieczony);
		return Response.status(200).entity(ubezpieczony).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/polisa/{idPolisy}")
	public Response dodajDodPolisy(Ubezpieczony ubezpieczony, @PathParam("idPolisy") Long idPolisy) {
		//ubezpieczony.setPolisa(polisaDao.find(idPolisy));
		dao.update(ubezpieczony);
		return Response.status(200).entity(ubezpieczony).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response retrieve(@PathParam("id") Long id) {
		Ubezpieczony ubezpieczony = dao.findWtihPolicy4(id);
		return Response.status(200).entity(ubezpieczony).build();
	}

}
