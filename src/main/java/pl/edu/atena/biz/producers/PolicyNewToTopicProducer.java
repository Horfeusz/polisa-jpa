package pl.edu.atena.biz.producers;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import pl.edu.atena.entities.Polisa;

@Stateless
public class PolicyNewToTopicProducer {

	Logger log = Logger.getLogger("PolicyNewToTopicProducer");

	@Resource(mappedName = "java:/JmsXA")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/topic/PolisaTopic")
	private Topic topic;

	public void send(Polisa polisa) {
		Connection connection = null;
		MessageProducer publisher = null;
		Session session = null;
		try {
			log.info("Wysy³anie polisy: " + polisa);
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			publisher = session.createProducer(topic);
			connection.start();
			ObjectMessage message = session.createObjectMessage();
			message.setObject(polisa);
			publisher.send(message);
			log.info("Wys³ano polisê");
		} catch (JMSException exc) {
			exc.printStackTrace();
		} finally {
			if (publisher != null)
				try {
					publisher.close();
				} catch (Exception ignore) {
				}
			if (session != null)
				try {
					session.close();
				} catch (Exception ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception ignore) {
				}
		}

	}

}
