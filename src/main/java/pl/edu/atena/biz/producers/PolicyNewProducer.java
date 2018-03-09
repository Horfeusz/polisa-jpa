package pl.edu.atena.biz.producers;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import pl.edu.atena.entities.Polisa;
import pl.edu.atena.kernel.Audit;

@Stateless
@ExcludeDefaultInterceptors
public class PolicyNewProducer {

	Logger log = Logger.getLogger("PolicyNewProducer");

	@Resource(mappedName = "java:/JmsXA")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/PolisaQueue")
	private Queue queue;

	@Audit
	public void sendPolicy(Polisa polisa) {
		Connection connection = null;
		MessageProducer publisher = null;
		Session session = null;
		try {
			log.info("Wysy³anie polisy: " + polisa);
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			publisher = session.createProducer(queue);
			connection.start();
			ObjectMessage message = session.createObjectMessage();
			message.setObjectProperty("POLISA", "ZATWIERDZONA");
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
