package pl.edu.atena.biz.file;

import java.io.File;

import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import pl.edu.atena.entities.Polisa;

@Named("xml")
public class ZapiszDoPlikuXml implements ZapiszDoPliku {

	@Override
	public void zapisz(Polisa polisa) {
		try {
			File file = new File("C:\\" + polisa.getNumerPolisy() + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Polisa.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(polisa, file);
			jaxbMarshaller.marshal(polisa, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
