package pl.edu.atena.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EP_RYZYKO")
public class Ryzyko {

	@Id
	@GeneratedValue
	private Long id;

	private BigDecimal skladka;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POL_ID", foreignKey = @ForeignKey(name = "FK_RYZYKO_TO_POLISA"))
	private Polisa polisa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSkladka() {
		return skladka;
	}

	public void setSkladka(BigDecimal skladka) {
		this.skladka = skladka;
	}

	public Polisa getPolisa() {
		return polisa;
	}

	public void setPolisa(Polisa polisa) {
		this.polisa = polisa;
	}

}
