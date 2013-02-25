package net.pfitz.webspeed;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class DeserializeTypedJson extends BaseRunner {

	@Override
	protected void runIteration() throws Exception {
		JsonFactory f = new JsonFactory();
		String path = Constants.DATA_PATH + "/persons.json";
		JsonParser jp = f.createJsonParser(new File(path));
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person();

		jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
		for (long x = 0; x < 100000000; x++) {
			// while(jp.nextToken() != JsonToken.END_ARRAY) {
			JsonToken token = jp.nextToken();
			if (token == JsonToken.END_ARRAY) {
				break;
			}
			if (token == JsonToken.START_OBJECT) {
				person = new Person();
				for (long y = 0; y < 10000000; y++) {
					token = jp.nextToken();
					if (token == JsonToken.END_OBJECT) {
						break;
					}
					String fieldname = jp.getCurrentName();
					jp.nextToken(); // move to value, or
									// START_OBJECT/START_ARRAY
					if (fieldname.equals("city")) {
						person.setCity(jp.getText());
					} else if (fieldname.equals("firstName")) {
						person.setFirstName(jp.getText());
					} else if (fieldname.equals("lastName")) {
						person.setLastName(jp.getText());
					} else if (fieldname.equals("email")) {
						person.setEmail(jp.getText());
					} else if (fieldname.equals("age")) {
						person.setAge(jp.getIntValue());
					} else if (fieldname.equals("id")) {
						person.setId(jp.getLongValue());
					} else if (fieldname.equals("accountId")) {
						person.setAccountId(jp.getLongValue());
					} else if (fieldname.equals("married")) {	
						person.setMarried(jp.getBooleanValue());
					} else if (fieldname.equals("country")) {	
						person.setCountry(jp.getText());
					}

				}
				persons.add(person);
			}

		}
		jp.close(); // ensure resources get cleaned up timely and properly


	}
}
