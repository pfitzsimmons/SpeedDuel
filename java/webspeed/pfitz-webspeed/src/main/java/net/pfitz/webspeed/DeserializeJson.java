package net.pfitz.webspeed;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.type.TypeReference;

public class DeserializeJson extends Runner {

	@Override
	protected void runIteration() {
		
		String path = "/Users/patrick/Dropbox/mygit/SpeedDuel/data/persons.json";
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		try {
			List<Person> persons = mapper.readValue(new File(path), new TypeReference<List<Person>>(){});
			//System.out.println("NumberOfPersons: " + persons.size());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
