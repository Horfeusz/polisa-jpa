package pl.edu.atena.biz.consumers;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.interceptor.ExcludeDefaultInterceptors;

import pl.edu.atena.biz.consumers.PolisaEvent.Typ;
import pl.edu.atena.entities.Polisa;

@Stateless
@ExcludeDefaultInterceptors
public class PolisaObserwator2 {

	private Logger log = Logger.getLogger("PolisaObserwator2");

	public void zatwierdzeniePolisy(@Observes @PolisaEvent(Typ.USUN) Polisa polisa) {
		log.info("Otrzyma³em polise: " + polisa);
	}

}
