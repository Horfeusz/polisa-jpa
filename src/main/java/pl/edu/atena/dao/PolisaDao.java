package pl.edu.atena.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.ExcludeClassInterceptors;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Query;

import pl.edu.atena.biz.consumers.PolisaEvent;
import pl.edu.atena.biz.consumers.PolisaEvent.Typ;
import pl.edu.atena.entities.Polisa;
import pl.edu.atena.entities.StatusPolisy;
import pl.edu.atena.kernel.RequestTest;

@Stateless
public class PolisaDao {

	private Logger log = Logger.getLogger("PolisaDao");

	@PersistenceContext(unitName = "PolisaPU")
	private EntityManager em;

	@Inject
	private RyzykaDao ryzykoDao;

	@Inject
	@PolisaEvent(Typ.ZATWIERDZ)
	private Event<Polisa> eventZatwierdz;

	@Inject
	private RequestTest requestTest;

	public PolisaDao() {
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void create(Polisa polisa) {
		log.info("Polisa przed persist");
		em.persist(polisa);

		// eventZatwierdz.fire(polisa);
		requestTest.add("Po odpaleniu");

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Polisa find(Long id) {
		EntityGraph<Polisa> eg = (EntityGraph<Polisa>) em.createEntityGraph(Polisa.class);
		eg.addSubgraph("ryzyka");
		eg.addSubgraph("agenci");
		Map hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", eg);
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

	@ExcludeClassInterceptors
	public Polisa updateUbezpieczajac(Long id, String ubezpieczajacy) {
		Polisa polisaUpdate = find(id);
		em.lock(polisaUpdate, LockModeType.WRITE);
		polisaUpdate.setUbezpieczajacy(ubezpieczajacy);
		polisaUpdate.setSkladka(BigDecimal.ONE);
		return polisaUpdate;
	}

	public Polisa szukajPoNumerze(String numer) {
		Query query = em.createQuery(
				"select p from Polisa p LEFT JOIN FETCH p.ryzyka r LEFT JOIN p.agenci a where p.numerPolisy = ?1");
		query.setParameter(1, numer);
		if (query.getFirstResult() == 0) {
			return null;
		}
		return (Polisa) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Polisa> szukajPoStatusie(StatusPolisy status) {
		Query query = em.createQuery("select p from Polisa p where p.statusPolisy = ?1");
		query.setParameter(1, status);
		return query.getResultList();
	}

	public List<Polisa> select() {
		Query query = em.createQuery("select p from Polisa p");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public void ileRyzyk(String numer) {
		Query query = em.createNativeQuery(
				"select p.id, p.NR_POLISY, count(r.id) as ile from EP_POLISA p left join EP_RYZYKO r on r.pol_id = p.id where p.NR_POLISY = ?1 group by p.id, p.NR_POLISY",
				"polisaIleRyzyk");
		query.setParameter(1, numer);
		query.getResultList().forEach(System.out::println);
		// query.getResultList().forEach(item -> {
		// Object[] obj = (Object[]) item;
		// System.out.println(obj[0] + " " + obj[1]);
		// });
	}

	@PrePersist
	private void beforeCreate(Polisa object) {
		System.out.println("Walidacja ...." + object);
	}

	@PostLoad
	private void postLoad(Polisa polisa) {
		polisa.setRyzyka(ryzykoDao.ryzykaPolisy(polisa.getId()));
		polisa.getAgenci().size();
		System.out.println(polisa);
	}

}
