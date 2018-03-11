package pl.edu.atena.biz.producers;

import javax.inject.Inject;
import javax.inject.Named;

import pl.edu.atena.entities.Polisa;

//@Named("tematProducer")
public class PolisaTematProducer implements PolisaProducer {

	@Inject
	private PolicyNewToTopicProducer policyNewToTopicProducer;

	@Override
	public void send(Polisa polisa) {
		policyNewToTopicProducer.send(polisa);
	}

}
