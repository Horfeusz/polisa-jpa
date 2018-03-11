package pl.edu.atena.rest.polisa;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.atena.biz.consumers.PolisaEvent;
import pl.edu.atena.biz.consumers.PolisaEvent.Typ;
import pl.edu.atena.biz.producers.PolicyNewProducer;
import pl.edu.atena.biz.producers.PolicyNewToTopicProducer;
import pl.edu.atena.biz.producers.PolisaKolejka;
import pl.edu.atena.biz.producers.PolisaProducer;
import pl.edu.atena.biz.timers.PolicyCountTimer;
import pl.edu.atena.dao.AudytDao;
import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.dao.PolisaDao2;
import pl.edu.atena.dao.UbezpieczonyDao;
import pl.edu.atena.entities.Polisa;
import pl.edu.atena.entities.StatusPolisy;

@Path("/polisa")
public class PolisaService {

	private Logger log = Logger.getLogger("PolisaService");

	@EJB
	private PolisaDao polisaDao;

	@EJB
	private PolisaDao2 polisaDao2;

	@EJB
	private UbezpieczonyDao ubezpieczonyDao;

	@EJB
	private PolicyNewProducer policyNewProducer;

	@EJB
	private PolicyCountTimer policyCountTimer;

	@EJB
	private PolicyNewToTopicProducer policyNewToTopicProducer;

	@EJB
	private AudytDao audyt;

	// @Inject
	// @PolisaKolejka
	// private PolisaProducer kolejkaProducer;

	// @Inject
	// @Named
	// private PolisaProducer tematProducer;

	@Inject
	@PolisaEvent(Typ.ZATWIERDZ)
	private Event<Polisa> eventZatwierdz;

	@Inject
	@PolisaEvent(Typ.USUN)
	private Event<Polisa> eventUsun;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Polisa polisa) {
		try {
			polisaDao.create(polisa);

			// if(StatusPolisy.ZATWIERDZONA.equals(polisa.getStatusPolisy())) {
			// eventZatwierdz.fire(polisa);
			// } else if(StatusPolisy.ROZWIAZANA.equals(polisa.getStatusPolisy())) {
			// eventUsun.fire(polisa);
			// }

		} catch (Exception e) {
			//audyt.loguj("Cos tam sie stalo: " + e.getMessage());
		}

		// policyNewProducer.sendPolicy(polisa);
		// policyNewToTopicProducer.send(polisa);

		// policyCountTimer.create();
		// policyCountTimer.timery();
		return Response.status(200).entity(polisa).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ubezpieczajacy")
	public Response updateUbezpieczajacy(Polisa polisa) {
		Polisa polisaDb = polisaDao.szukajPoNumerze(polisa.getNumerPolisy());
		polisaDb.setUbezpieczajacy(polisa.getUbezpieczajacy());
		polisaDb = polisaDao2.updateUbezpieczajacy(polisaDb);
		return Response.status(200).entity(polisaDb).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Polisa polisa) {
		Polisa result = polisaDao.update(polisa);
		// policyNewProducer.sendPolicy(polisa);
		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		polisaDao.delete(id);
		return Response.status(200).build();
	}

	@GET
	@Path("/szukaj/numer/{numer}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Polisa> poNumerze(@PathParam("numer") String numer) {
		Polisa polisa = polisaDao.szukajPoNumerze(numer);
		List<Polisa> polisy = new ArrayList<Polisa>();
		polisy.add(polisa);
		return polisy;
	}

	@GET
	@Path("/szukaj/status/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Polisa> poStatusie(@PathParam("status") StatusPolisy status) {
		return polisaDao.szukajPoStatusie(status);
	}

}
