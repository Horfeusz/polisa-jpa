package pl.edu.atena.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EP_UBEZPIECZONY")
@NamedEntityGraph(name = "graph.Ubezpieczony.ryzyko", attributeNodes = { @NamedAttributeNode("ryzyko") })
@XmlRootElement(name = "INSURER")
public class Ubezpieczony {

	@Id
	@GeneratedValue
	private Long id;

	private String nazwa;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RYZ_ID", foreignKey = @ForeignKey(name = "FK_UBEZP_TO_RYZYKO"))
	private Ryzyko ryzyko;

	public Long getId() {
		return id;
	}

	@XmlElement(name = "ID")
	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	@XmlElement(name = "NAME")
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Ryzyko getRyzyko() {
		return ryzyko;
	}

	public void setRyzyko(Ryzyko ryzyko) {
		this.ryzyko = ryzyko;
	}

}
