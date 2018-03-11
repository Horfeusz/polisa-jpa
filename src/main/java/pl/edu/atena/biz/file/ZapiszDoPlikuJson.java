package pl.edu.atena.biz.file;

import java.io.File;
import java.io.IOException;

import javax.inject.Named;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import pl.edu.atena.entities.Polisa;

@Named("json")
public class ZapiszDoPlikuJson implements ZapiszDoPliku {

	@Override
	public void zapisz(Polisa polisa) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Convert object to JSON string and save into a file directly
			//mapper.writeValue(new File("c:\\" + polisa.getNumerPolisy() + ".json"), polisa);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(polisa);
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(polisa);
			System.out.println(jsonInString);
			
			
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("c:\\" + polisa.getNumerPolisy() + ".json"), polisa);
			

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
