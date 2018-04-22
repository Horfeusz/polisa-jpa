package pl.edu.atena.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.entities.Polisa;

@ManagedBean
public class ListaMBean {

	@Inject
	private PolisaDao polisaDao;

	private List<Polisa> polisy;

	@PostConstruct
	private void init() {
		polisy = polisaDao.select();
	}

	public List<Polisa> getPolisy() {
		return polisy;
	}

	public void setPolisy(List<Polisa> polisy) {
		this.polisy = polisy;
	}

	public String rejestruj() {
		return "rejestracja";
	}

}
