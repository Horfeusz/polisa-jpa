package pl.edu.atena.kernel;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RequestTest {

	private List<String> lista = new ArrayList<>();

	public void add(String tekst) {
		lista.add(tekst);
	}

	public void wyswietla() {
		lista.forEach(System.out::println);
	}
}
