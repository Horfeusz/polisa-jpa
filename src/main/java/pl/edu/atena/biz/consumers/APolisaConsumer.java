package pl.edu.atena.biz.consumers;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import pl.edu.atena.entities.Polisa;

public abstract class APolisaConsumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage objMessage = (ObjectMessage) message;
			Polisa polisa = (Polisa) objMessage.getObject();
			run(polisa);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	abstract public void run(Polisa polisa);

}
