package net.pfitz.webspeed.templating;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.floreysoft.jmte.Engine;
import com.google.common.io.Files;

import net.pfitz.webspeed.BaseRunner;
import net.pfitz.webspeed.Constants;
import net.pfitz.webspeed.Person;

public class JmteRunner extends BaseRunner {
	protected String templateSource;
	protected List<Person> persons;
	
	
	public JmteRunner() {
		loadPersons();
		loadTemplate();
				
		Engine.createCompilingEngine().getTemplate(templateSource);
	}
	
	public void loadTemplate() {
		String templatePath = Constants.DATA_PATH + "/jmte.html";
		File file = new File(templatePath);
		try {
			templateSource = Files.toString(file, Charset.forName("utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("persons", persons);
		Engine engine = new Engine();
		String transformed = engine.transform(templateSource, model);		
	}
}
