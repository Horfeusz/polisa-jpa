package pl.edu.atena.jsf;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import pl.edu.atena.dao.PolisaDao;
import pl.edu.atena.entities.Polisa;
import pl.edu.atena.entities.StatusPolisy;

@ManagedBean
public class PolisaMBean {

	private Logger log = Logger.getLogger("PolisaMBean");

	@Inject
	private PolisaDao polisaDao;

	private Polisa polisa = new Polisa();

	private List<String> statusy = Arrays.asList("ZATWIERDZONA", "ZAWIESZONA", "ROZWIAZANA");

	private String statusPolisy;
	
	boolean isSave = false;

	@PostConstruct
	private void init() {
		String idPolisy = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPolisy");
		log.info(idPolisy);
	}
	
	public Polisa getPolisa() {
		return polisa;
	}

	public void setPolisa(Polisa polisa) {
		this.polisa = polisa;
	}

	public String getStatusPolisy() {
		return statusPolisy;
	}

	public void setStatusPolisy(String statusPolisy) {
		this.statusPolisy = statusPolisy;
	}
	
	public List<String> getStatusy() {
		return statusy;
	}

	public void setStatusy(List<String> statusy) {
		this.statusy = statusy;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Wybrano date", format.format(event.getObject())));
	}

	public void zapisz() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		try {
			if(!Objects.isNull(statusPolisy)) {
				polisa.setStatusPolisy(StatusPolisy.valueOf(statusPolisy));
			}
			polisaDao.create(polisa);
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Zapisano polise",
					"Z identyfikatorem: " + polisa.getId());
			isSave = true;
		} catch (Exception e) {
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd zapisu polisy: ", e.getMessage());
			isSave = false;
		} finally {
			facesContext.addMessage(null, facesMessage);
		}
	}

	public String polisy() {
		if(!isSave) {
			return "";
		}
		return "polisy";
	}
	
	
}
