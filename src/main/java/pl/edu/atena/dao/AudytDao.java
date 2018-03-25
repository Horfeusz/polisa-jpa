package pl.edu.atena.dao;

import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.atena.entities.AudytFactory;

@Stateless
@ExcludeDefaultInterceptors
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AudytDao {

	@PersistenceContext(unitName = "PolisaPU")
	private EntityManager em;

	public void loguj(Date date, Long timeAsMilisecond, InvocationContext invocation) {
		em.persist(AudytFactory.create(date, timeAsMilisecond, invocation));
	}

	public void loguj(Date date, Long timeAsMilisecond, String method) {
		em.persist(AudytFactory.create(date, timeAsMilisecond, method));
	}
	
}
