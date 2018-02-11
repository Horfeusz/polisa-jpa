package pl.edu.atena.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.edu.atena.entities.Ryzyko;

@Stateless
public class RyzykaDao {

	@PersistenceContext(unitName = "PolisaPU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Ryzyko> ryzykaPolisy(Long polisaId) {
		Query query = em.createQuery("from Ryzyko r where r.polisa.id = ?1", Ryzyko.class);
		query.setParameter(1, polisaId);
		return query.getResultList();
	}

}
