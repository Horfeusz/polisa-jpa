package pl.edu.atena.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.atena.entities.Ubezpieczony;

@Stateless
public class UbezpieczonyDao {

	@PersistenceContext(unitName = "PolisaPU")
	private EntityManager em;

	public void create(Ubezpieczony ubezpieczony) {
		em.persist(ubezpieczony);
	}

	public Ubezpieczony find(Long id) {
		return em.find(Ubezpieczony.class, id);
	}

	public Ubezpieczony findWtihPolicy(Long id) {
		Ubezpieczony ubezp = em.find(Ubezpieczony.class, id);
		ubezp.getPolisa();
		return ubezp;
	}

	public Ubezpieczony update(Ubezpieczony ubezpieczony) {
		return em.merge(ubezpieczony);
	}

	public void delete(Long id) {
		Ubezpieczony ubezpieczony = find(id);
		if (ubezpieczony != null) {
			em.remove(ubezpieczony);
		}

	}

}
