package pl.edu.atena.jsf;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class MyApplication {

	private String applicationTitle = "Moja pierwsza apka w JSF";

	private String tekst;

	public String getApplicationTitle() {
		return applicationTitle;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

}
