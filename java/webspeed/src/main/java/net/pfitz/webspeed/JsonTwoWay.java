package net.pfitz.webspeed;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.type.TypeReference;

public class JsonTwoWay extends BaseRunner {

	@Override
	public void runIteration() {
		List<Person> persons = this.deserialize();
		assert persons.size() == 1000;
		String personsJson = serialize(persons);		
		assert personsJson.length() == 251572;
	}
	
	public List<Person> deserialize() {
		String path = Constants.DATA_PATH + "/persons.json";
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		List<Person> persons = new ArrayList<Person>();
		
		try {
			persons = mapper.readValue(new File(path), new TypeReference<List<Person>>(){});
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return persons;
	}
	
	public String serialize(List<Person> persons) {
		String personsJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			personsJson =  mapper.writeValueAsString(persons);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personsJson;
	}

}
