package pl.edu.atena.kernel;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Interceptor Licz¹cy czas trwania metody
 * 
 * @author michalh
 *
 */
public class CzasTrwaniaMetodyLogger {

	@PersistenceContext(unitName = "PolisaPU")
	private EntityManager em;

	@AroundInvoke
	public Object liczCzas(InvocationContext invocation) throws Exception {
		Instant start = Instant.now();
		try {
			return invocation.proceed();
		} finally {
			long milis = Duration.between(start, Instant.now()).toMillis();
			String clazz = invocation.getTarget().getClass().getCanonicalName();
			String method = invocation.getMethod().getName();
			Logger log = Logger.getLogger(clazz);
			log.info(method + " time: " + milis + " milisekund");
		}
	}

}
