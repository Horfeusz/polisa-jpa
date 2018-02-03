package pl.edu.atena.dao;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.atena.entities.Polisa;

@Stateless
public class PolisaDao {

	@PersistenceContext(unitName = "PolisaPU")
	private EntityManager em;

	public void create(Polisa polisa) {
		em.persist(polisa);
	}

	public Polisa find(Long id) {
		return em.find(Polisa.class, id);
	}

	public Polisa update(Polisa polisa) {
		System.out.println(em.contains(polisa));
		return em.merge(polisa);
	}

	public void delete(Long id) {
		Polisa polisa = find(id);
		System.out.println(em.contains(polisa));
		if (polisa != null) {
			em.remove(polisa);
			System.out.println(em.contains(polisa));
		}

	}

	public Polisa updateUbezpieczajact(Long id, String ubezpieczajacy) {
		Polisa polisaUpdate = find(id);
		polisaUpdate.setUbezpieczajacy(ubezpieczajacy);
		polisaUpdate.setSkladka(BigDecimal.ONE);
		return polisaUpdate;
	}

}
