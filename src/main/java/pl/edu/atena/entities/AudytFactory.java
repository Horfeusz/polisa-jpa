package pl.edu.atena.entities;

import java.util.Arrays;
import java.util.Date;

import javax.interceptor.InvocationContext;

public class AudytFactory {

	private AudytFactory() {
	}

	public static Audyt create(Date date, Long timeAsMilisecond, InvocationContext invocation) {
		Audyt audyt = new Audyt();
		audyt.setClazz(invocation.getTarget().getClass().getCanonicalName());
		audyt.setMethod(invocation.getMethod().getName());
		audyt.setTime(timeAsMilisecond);
		audyt.setWr(date);
		if (invocation.getParameters() != null && invocation.getParameters().length > 0) {
			audyt.setParameters(Arrays.asList(invocation.getParameters()).toString());
		}
		return audyt;
	}

}
