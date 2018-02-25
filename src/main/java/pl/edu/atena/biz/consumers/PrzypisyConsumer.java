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
public class PrzypisyConsumer extends APolisaConsumer {

	private Logger log = Logger.getLogger("PrzypisyConsumer");

	@EJB
	private AudytDao audyt;
	
	@Override
	public void run(Polisa polisa) {
		log.info("Odebra³em polise:" + polisa);
		audyt.loguj("Odebra³em polise:" + polisa);
	}

}
