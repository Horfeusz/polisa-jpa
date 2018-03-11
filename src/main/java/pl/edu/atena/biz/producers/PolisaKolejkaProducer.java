package pl.edu.atena.biz.producers;

import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import pl.edu.atena.entities.Agent;
import pl.edu.atena.entities.Polisa;

@Named("kolejkaProducer")
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
