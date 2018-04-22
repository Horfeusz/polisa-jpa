package pl.edu.atena.jsf;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.entities.Polisa;
import pl.edu.atena.entities.StatusPolisy;

@ManagedBean
@ViewScoped
public class PolisaAction {

	@Inject
	private PolisaDao dao;

	private Polisa polisa;

	private StatusPolisy[] statusy = StatusPolisy.values();

	public StatusPolisy[] getStatusy() {
		return statusy;
	}

	public Polisa getPolisa() {
		return polisa;
	}

	public void setPolisa(Polisa polisa) {
		this.polisa = polisa;
	}

	@PostConstruct
	private void init() {
		String idPolisy = FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("idPolisy");
		if (idPolisy == null) {
			polisa = new Polisa();
		} else {
			polisa = dao.find(Long.valueOf(idPolisy));
		}
	}

	public void zapisz() {
		if (polisa.getId() == null) {
			dao.create(polisa);
		} else {
			dao.update(polisa);
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Zapisano polise o id:", polisa.getId().toString()));

		// polisa = new Polisa();
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Wybrano date", format.format(event.getObject())));
	}

	public String lista() {
		return "lista";
	}
}
