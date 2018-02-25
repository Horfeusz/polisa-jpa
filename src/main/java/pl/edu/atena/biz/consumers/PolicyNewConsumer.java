package pl.edu.atena.biz.consumers;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import pl.edu.atena.entities.Polisa;

@MessageDriven(activationConfig = {

		// Typ miejsca docelowego
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),

		// Adres JNDI Miejsca docelowego
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/PolisaQueue"),

		// Tryb potwierdzania
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),

		// Selektor komunikatów
		// @ActivationConfigProperty(propertyName = "messageSelector", propertyValue =
		// "POLISA = 'ZATWIERDZONA'"),

		// Trwa³oœæ subskrypcji
		@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable") })

@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PolicyNewConsumer implements MessageListener {

	Logger log = Logger.getLogger("PolicyNewConsumer");

	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage objMessage = (ObjectMessage) message;
			Polisa polisa = (Polisa) objMessage.getObject();
			log.info("Odebra³em polise:" + polisa);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	private void init() {
		log.info("Powsta³em");
	}

	@PreDestroy
	private void kill() {
		log.info("Umieram");
	}

}
