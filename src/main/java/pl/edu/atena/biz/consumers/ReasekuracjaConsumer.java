package pl.edu.atena.biz.consumers;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;

import pl.edu.atena.dao.AudytDao;
import pl.edu.atena.entities.Polisa;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/PolisaTopic"), })
public class ReasekuracjaConsumer extends APolisaConsumer {

	private Logger log = Logger.getLogger("ReasekuracjaConsumer");

	@EJB
	private AudytDao audyt;
	
	@Override
	public void run(Polisa polisa) {
		log.info("Mam polise: " + polisa);
		audyt.loguj("Mam polise: " + polisa);
	}

}
