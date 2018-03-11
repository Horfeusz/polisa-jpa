package pl.edu.atena.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import pl.edu.atena.entities.Ubezpieczony;

@Stateless
public class UbezpieczonyDao {

	@PersistenceContext(unitName = "PolisaPU")
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void create(Ubezpieczony ubezpieczony) {
		em.persist(ubezpieczony);
		throw new NullPointerException("Srututut");
	}

	public Ubezpieczony find(Long id) {
		return em.find(Ubezpieczony.class, id);
	}

	/**
	 * Pobranie danych w relacji Lazy sposób pierwszy
	 * 
	 * @param id
	 * @return
	 */
	public Ubezpieczony findWtihPolicy1(Long id) {
		Query q = this.em.createQuery("SELECT o FROM Ubezpieczony o " + "JOIN FETCH o.polisa i WHERE o.id = :id");
		q.setParameter("id", id);
		return (Ubezpieczony) q.getSingleResult();
	}

	
	//FIXME - popraw to bo sprawdzaliœmy a tam nie ma relacji
	//public Ubezpieczony findWtihPolicy(Long id) {
	//	Ubezpieczony ubezp = em.find(Ubezpieczony.class, id);
	//	ubezp.getPolisa().getId();
	//	return ubezp;
	//}	
	
	/**
	 * Pobranie danych w relacji Lazy sposób drugi
	 * 
	 * @param id
	 * @return
	 */
	public Ubezpieczony findWtihPolicy2(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ubezpieczony> q = cb.createQuery(Ubezpieczony.class);
		Root<Ubezpieczony> o = q.from(Ubezpieczony.class);
		o.fetch("polisa", JoinType.INNER);
		q.select(o);
		q.where(cb.equal(o.get("id"), id));
		return em.createQuery(q).getSingleResult();
	}

	/**
	 * Pobranie danych w relacji Lazy sposób trzeci
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Ubezpieczony findWtihPolicy3(Long id) {
		EntityGraph graph = em.getEntityGraph("graph.Ubezpieczony.polisa");
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		return em.find(Ubezpieczony.class, id, hints);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Ubezpieczony findWtihPolicy4(Long id) {
		EntityGraph<Ubezpieczony> graph = em.createEntityGraph(Ubezpieczony.class);
		graph.addSubgraph("polisa");
		Map hints = new HashMap();
		hints.put("javax.persistence.loadgraph", graph);
		return em.find(Ubezpieczony.class, id, hints);
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
