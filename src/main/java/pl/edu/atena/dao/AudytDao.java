package pl.edu.atena.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.atena.entities.Audyt;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AudytDao {

	@PersistenceContext(unitName = "PolisaPU")
	private EntityManager em;

	public void loguj(String message) {
		Audyt audyt = new Audyt();
		audyt.setMessage(message);
		em.persist(audyt);
	}

}
