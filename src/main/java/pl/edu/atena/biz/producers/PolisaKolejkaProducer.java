package pl.edu.atena.biz.producers;

import javax.enterprise.inject.New;
import javax.inject.Inject;

import pl.edu.atena.entities.Agent;
import pl.edu.atena.entities.Polisa;

@PolisaKolejka
public class PolisaKolejkaProducer implements PolisaProducer {

	@Inject
	private PolicyNewProducer policyNewProducer;

	@Inject @New
	private Agent agent;
	
	@Override
	public void send(Polisa polisa) {
		policyNewProducer.sendPolicy(polisa);
		
		System.out.println(agent);
	}

}
