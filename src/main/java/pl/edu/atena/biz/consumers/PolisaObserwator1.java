package pl.edu.atena.biz.consumers;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.interceptor.ExcludeDefaultInterceptors;

import pl.edu.atena.biz.consumers.PolisaEvent.Typ;
import pl.edu.atena.entities.Polisa;

@Stateless
@ExcludeDefaultInterceptors
public class PolisaObserwator1 {

	private Logger log = Logger.getLogger("PolisaObserwator1");

	public void inProgressPrzypisy(@Observes(during = TransactionPhase.IN_PROGRESS) @PolisaEvent(Typ.ZATWIERDZ) Polisa polisa) {
		log.info("Generuje przypisy: " + polisa);
	}

	public void inProgressReasekuracja(@Observes @PolisaEvent(Typ.ZATWIERDZ) Polisa polisa) {
		log.info("Przeka¿e polise do rea: " + polisa);
	}
	
	
	public void afterSuccess(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @PolisaEvent(Typ.ZATWIERDZ) Polisa polisa) {
		log.info("afterSuccess: " + polisa);
	}

	public void afterFailure(
			@Observes(during = TransactionPhase.AFTER_FAILURE) @PolisaEvent(Typ.ZATWIERDZ) Polisa polisa) {
		log.info("afterFailure: " + polisa);
	}

	public void afterCompletion(
			@Observes(during = TransactionPhase.AFTER_COMPLETION) @PolisaEvent(Typ.ZATWIERDZ) Polisa polisa) {
		log.info("afterCompletion: " + polisa);
	}

	public void beforeCompletion(
			@Observes(during = TransactionPhase.BEFORE_COMPLETION) @PolisaEvent(Typ.ZATWIERDZ) Polisa polisa) {
		log.info("beforeCompletion: " + polisa);
	}

}
