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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.atena.biz.consumers.PolisaEvent;
import pl.edu.atena.biz.consumers.PolisaEvent.Typ;
import pl.edu.atena.biz.file.ZapiszDoPliku;
import pl.edu.atena.biz.producers.PolicyNewProducer;
import pl.edu.atena.biz.producers.PolicyNewToTopicProducer;
import pl.edu.atena.biz.producers.PolisaProducer;
import pl.edu.atena.biz.timers.PolicyCountTimer;
import pl.edu.atena.dao.AudytDao;
import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.dao.PolisaDao2;
import pl.edu.atena.dao.UbezpieczonyDao;
import pl.edu.atena.entities.Polisa;
import pl.edu.atena.entities.StatusPolisy;
import pl.edu.atena.kernel.RequestTest;

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

	@Inject
	@Named
	private PolisaProducer kolejkaProducer;

	@Inject
	@Named("tematProducer")
	private PolisaProducer dupa;

	@Inject
	@PolisaEvent(Typ.ZATWIERDZ)
	private Event<Polisa> eventZatwierdz;

	@Inject
	@PolisaEvent(Typ.USUN)
	private Event<Polisa> eventUsun;

	@Inject
	@Named
	private ZapiszDoPliku xml;

	@Inject
	@Named
	private ZapiszDoPliku json;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response create(Polisa polisa, @Context HttpHeaders headers) {
		String contentType = headers.getRequestHeader("content-Type").iterator().next();

		try {
			polisaDao.create(polisa);

			if ("application/xml".equals(contentType)) {
				xml.zapisz(polisa);
			}

			if ("application/json".equals(contentType)) {
				json.zapisz(polisa);
			}

		} catch (Exception e) {
			e.printStackTrace();

			// audyt.loguj("Cos tam sie stalo: " + e.getMessage());
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
