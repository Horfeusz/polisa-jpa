package pl.edu.atena.rest.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import pl.edu.atena.entities.Ubezpieczony;

@WebService(serviceName = "Akademia", targetNamespace = "http://pl.edu.atena.akademia")
public class WebSerwisTest {

	@WebResult(name = "rezultacik", targetNamespace = "http://pl.edu.atena.akademia")
	@WebMethod(operationName = "welcome")
	public Ubezpieczony przywitajSie(
			@WebParam(name = "name", targetNamespace = "http://pl.edu.atena.akademia") String nazwa,
			@WebParam(name = "test", targetNamespace = "http://pl.edu.atena.akademia", mode = Mode.OUT) Holder<Integer> test) {

		Ubezpieczony ubezp = new Ubezpieczony();
		ubezp.setNazwa(nazwa);
		ubezp.setId(Long.valueOf(1));

		test.value = 10;

		return ubezp;
	}
}
