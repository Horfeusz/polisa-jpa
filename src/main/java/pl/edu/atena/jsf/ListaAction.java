package pl.edu.atena.jsf;

import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.entities.Polisa;

@ManagedBean
@RequestScoped
public class ListaAction {

	@Inject
	private PolisaDao dao;
	
	private List<Polisa> polisy;

	public List<Polisa> getPolisy() {
		return polisy;
	}
	
	@PostConstruct
	private void init() {
		polisy = dao.select();
	}
	
	public String nowa() {
		return "nowa";
	}
	
	public String edytuj() {
		return "edytuj";
	}
	
}
