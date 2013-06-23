package net.pfitz.webspeed.templating;

import java.io.File;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import net.pfitz.webspeed.BaseRunner;
import net.pfitz.webspeed.Constants;
import net.pfitz.webspeed.Person;

public class StringBuilderRunner extends BaseRunner {
	protected List<Person> persons;

	public StringBuilderRunner() {
		loadPersons();
	}
	
	public void loadPersons() {
		String path = Constants.DATA_PATH + "/persons.json";
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally		
		try {
			persons = mapper.readValue(new File(path), new TypeReference<List<Person>>(){});
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		persons = persons.subList(0, 30);		
	}	
	
	@Override
	protected void runIteration() throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table>\n");
		for(Person p : persons) {
			buffer.append("<tr>\n");
			buffer.append(String.format("<td>%s %s</td>\n", p.getFirstName(), p.getLastName()));
			if (p.isMarried()) {
				buffer.append("<td>married</td>\n");
			} else {
				buffer.append("<td>single</td>\n");
			}
			buffer.append(String.format("<td>%s</td>\n", p.getId()));
			buffer.append(String.format("<td>%s</td>\n", p.getAge()));
			buffer.append("</tr>\n");
		}
		buffer.append("</table>");
		String output = buffer.toString();
		//System.out.println(output);
	}	

}
