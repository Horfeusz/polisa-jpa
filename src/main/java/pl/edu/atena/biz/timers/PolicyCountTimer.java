package pl.edu.atena.biz.timers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Stateless
public class PolicyCountTimer {

	private Logger log = Logger.getLogger("PolicyCountTimer");

	@Resource
	private TimerService ts;

	public void create() {
		LocalDateTime ldt = LocalDateTime.now().plusSeconds(10);
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		ts.createTimer(date, "Mój Timer");
	}

	public void timery() {
		ts.getTimers().forEach(timer -> {
			StringBuilder sb = new StringBuilder();
			sb.append("Timer: ").append((String) timer.getInfo()).append(" czas: ").append(timer.getNextTimeout());
			log.info(sb.toString());
			timer.cancel();
		});
	}

	@Timeout
	public void runTimer(Timer timer) {
		log.info((String) timer.getInfo());
	}

}
