package net.pfitz.webspeed.templating;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import net.pfitz.webspeed.BaseRunner;
import net.pfitz.webspeed.Constants;
import net.pfitz.webspeed.Person;

public class VelocityRunner extends BaseRunner {
	List<Person> persons;
	
	public VelocityRunner() {
		Properties props = new Properties();
		props.put("resource.loader", "file");
		props.put("resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		props.put("file.resource.loader.path", Constants.DATA_PATH);
		Velocity.init(props);	
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
		VelocityContext context = new VelocityContext();
		context.put("persons", persons);
		Template template = Velocity.getTemplate("velocity.html");			
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writer.toString();		
	}
}
