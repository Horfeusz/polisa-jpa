package pl.edu.atena.rest.ws;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;

@Stateless
@WebService(name = "polisa", targetNamespace = "http://pl.atena.edu.ws.polisa/", serviceName = "ServicePolisa")
public class PolisaWService {

	private Logger log = Logger.getAnonymousLogger();

	@WebResult(name = "rezultatio", targetNamespace = "http://pl.atena.edu.ws.polisa/")
	@WebMethod(operationName = "przywitanieSie")
	public String hello(@WebParam(name = "nazwa", targetNamespace = "http://pl.atena.edu.ws.polisa/") String name,
			@WebParam(name = "holderek", mode = Mode.INOUT, targetNamespace = "http://pl.atena.edu.ws.polisa/") Holder<String> marka) {

		log.info("DZiałam: " + name + " z holderka: " + marka.value);

		marka.value = "Zwracam jakąś tam wartość";

		return "Jak się masz " + name;

	}

}
