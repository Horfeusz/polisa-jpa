package pl.edu.atena.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EP_UBEZPIECZONY2")
public class Ubezpieczony {

	@Id
	@GeneratedValue
	private Long id;

	private String nazwa;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POL_ID")
	private Polisa polisa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Polisa getPolisa() {
		return polisa;
	}

	public void setPolisa(Polisa polisa) {
		this.polisa = polisa;
	}

}
