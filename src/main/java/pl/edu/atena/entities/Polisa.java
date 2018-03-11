package pl.edu.atena.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import pl.edu.atena.dao.PolisaDao;

@Entity
@Table(name = "EP_POLISA", indexes = { @Index(columnList = "NR_POLISY", name = "IDX_NR_POLISY") }, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "NR_POLISY" }) }, schema = "public")
@NamedEntityGraph(name = "graph.Polisa.agenci", attributeNodes = { @NamedAttributeNode("agenci"),
		@NamedAttributeNode("ryzyka") })

@SqlResultSetMapping(name = "polisaIleRyzyk", entities = {
		@EntityResult(entityClass = PolisaIleRyzykVO.class, fields = { @FieldResult(name = "id", column = "id"),
				@FieldResult(name = "nrPolisy", column = "NR_POLISY"), @FieldResult(name = "ile", column = "ile") }) })
@EntityListeners({ PolisaDao.class })
public class Polisa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "NR_POLISY", nullable = false, length = 20)
	private String numerPolisy;

	@Version
	private Long wer;

	@Transient
	private Long idTemp;

	@Temporal(TemporalType.DATE)
	private Date dataPodpisania;

	@Temporal(TemporalType.TIMESTAMP)
	private Date wr = Date.from(Instant.now());

	@Enumerated(EnumType.STRING)
	private StatusPolisy statusPolisy;

	private String ubezpieczajacy;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "AGENCI_POLISY", foreignKey = @ForeignKey(name = "FK_AGENT_TO_POLISA"), joinColumns = {
			@JoinColumn(name = "AGD_ID") }, inverseJoinColumns = { @JoinColumn(name = "POL_ID") })
	private List<Agent> agenci;

	@OneToMany(mappedBy = "polisa", fetch = FetchType.LAZY)
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

	@Override
	public String toString() {
		return String.format(
				"Polisa [id=%s, numerPolisy=%s, idTemp=%s, dataPodpisania=%s, wr=%s, statusPolisy=%s, ubezpieczajacy=%s, agenci=%s, ryzyka=%s, skladka=%s]",
				id, numerPolisy, idTemp, dataPodpisania, wr, statusPolisy, ubezpieczajacy, agenci, ryzyka, skladka);
	}

	@PostPersist
	private void afterCreate() {
		System.out.println("Zapisa³em siê");
	}

}
