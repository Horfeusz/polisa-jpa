package pl.edu.atena.rest.ws;

import java.util.Objects;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.entities.Polisa;

@WebService(name = "polisa", targetNamespace = "http://pl.atena.edu.ws.polisa/", serviceName = "ServicePolisa")
public class PolisaWService {

	private Logger log = Logger.getAnonymousLogger();

	@Inject
	private PolisaDao polisaDao;

	@WebResult(name = "polisaId", targetNamespace = "http://pl.atena.edu.ws.polisa/")
	@WebMethod
	public Polisa dodajPolise(@WebParam(targetNamespace = "http://pl.atena.edu.ws.polisa/") Polisa polisa) {
		log.info(polisa.toString());
		Objects.nonNull(polisa);
		polisaDao.create(polisa);
		return polisa;
	}

	@WebResult(name = "polisa", targetNamespace = "http://pl.atena.edu.ws.polisa/")
	@WebMethod
	public Polisa szukajPoNumerze(@WebParam(targetNamespace = "http://pl.atena.edu.ws.polisa/") String numerPolisy) {
		log.info("Szukam polisy o numerze: " + numerPolisy);
		Objects.nonNull(numerPolisy);
		return polisaDao.szukajPoNumerze(numerPolisy);
	}

}
