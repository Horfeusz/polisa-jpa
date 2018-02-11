package pl.edu.atena.entities;

//@Entity
public class PolisaIleRyzykVO {

	//@Id
	//@GeneratedValue
	private Long id;
	
	private String nrPolisy;

	private int ile;

	public String getNrPolisy() {
		return nrPolisy;
	}

	public void setNrPolisy(String nrPolisy) {
		this.nrPolisy = nrPolisy;
	}

	public int getIle() {
		return ile;
	}

	public void setIle(int ile) {
		this.ile = ile;
	}

	@Override
	public String toString() {
		return String.format("PolisaIleRyzykVO [nrPolisy=%s, ile=%s]", nrPolisy, ile);
	}

}
