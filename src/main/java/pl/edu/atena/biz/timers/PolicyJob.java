package pl.edu.atena.biz.timers;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;

@Startup
@Singleton
public class PolicyJob {

	private Logger log = Logger.getLogger("PolicyJob");

	@Schedule(second = "0", minute = "30", hour = "*", persistent = false)
	public void execute(Timer timer) {
		log.info("Odpale się znowu: " + timer.getNextTimeout());
	}

	@PostConstruct
	public void init() {
		log.info("START");
	}

}
