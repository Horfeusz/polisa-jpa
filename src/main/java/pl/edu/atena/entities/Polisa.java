package pl.edu.atena.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EP_POLISA", indexes = {
		@Index(columnList = "NR_POLISY", name = "IDX_NR_POLISY") }, uniqueConstraints = {
				@UniqueConstraint(columnNames = { "NR_POLISY" }) }, schema = "public")
public class Polisa {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "NR_POLISY", nullable = false, length = 20)
	private String numerPolisy;

	@Transient
	private Long idTemp;

	@Temporal(TemporalType.DATE)
	private Date dataPodpisania;

	@Temporal(TemporalType.TIMESTAMP)
	private Date wr = Date.from(Instant.now());

	@Enumerated(EnumType.STRING)
	private StatusPolisy statusPolisy;

	private String ubezpieczajacy;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "AGENCI_POLISY", foreignKey = @ForeignKey(name = "FK_AGENT_TO_POLISA"), joinColumns = {
			@JoinColumn(name = "AGD_ID") }, inverseJoinColumns = { @JoinColumn(name = "POL_ID") })
	private List<Agent> agenci;

	@OneToMany(mappedBy = "polisa", fetch = FetchType.EAGER)
	private List<Ryzyko> ryzyka;

	private BigDecimal skladka;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumerPolisy() {
		return numerPolisy;
	}

	public void setNumerPolisy(String numerPolisy) {
		this.numerPolisy = numerPolisy;
	}

	public String getUbezpieczajacy() {
		return ubezpieczajacy;
	}

	public void setUbezpieczajacy(String ubezpieczajacy) {
		this.ubezpieczajacy = ubezpieczajacy;
	}

	public BigDecimal getSkladka() {
		return skladka;
	}

	public void setSkladka(BigDecimal skladka) {
		this.skladka = skladka;
	}

	public Long getIdTemp() {
		return idTemp;
	}

	public void setIdTemp(Long idTemp) {
		this.idTemp = idTemp;
	}

	public Date getDataPodpisania() {
		return dataPodpisania;
	}

	public void setDataPodpisania(Date dataPodpisania) {
		this.dataPodpisania = dataPodpisania;
	}

	public Date getWr() {
		return wr;
	}

	public void setWr(Date wr) {
		this.wr = wr;
	}

	public StatusPolisy getStatusPolisy() {
		return statusPolisy;
	}

	public void setStatusPolisy(StatusPolisy statusPolisy) {
		this.statusPolisy = statusPolisy;
	}

	public List<Agent> getAgenci() {
		return agenci;
	}

	public void setAgenci(List<Agent> agenci) {
		this.agenci = agenci;
	}

	public List<Ryzyko> getRyzyka() {
		return ryzyka;
	}

	public void setRyzyka(List<Ryzyko> ryzyka) {
		this.ryzyka = ryzyka;
	}

}
