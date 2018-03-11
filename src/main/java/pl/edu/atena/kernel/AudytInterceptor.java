package pl.edu.atena.kernel;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import pl.edu.atena.dao.AudytDao;

public class AudytInterceptor {

	@EJB
	private AudytDao dao;

	@AroundInvoke
	public Object loguj(InvocationContext invocation) throws Exception {
		Instant start = Instant.now();
		try {
			return invocation.proceed();
		} finally {
			dao.loguj(Date.from(start), Duration.between(start, Instant.now()).toMillis(), invocation);
		}
	}

}
