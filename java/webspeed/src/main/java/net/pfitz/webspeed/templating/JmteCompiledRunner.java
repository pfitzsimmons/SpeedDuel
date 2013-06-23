package net.pfitz.webspeed.templating;

import java.util.HashMap;
import java.util.Map;

import com.floreysoft.jmte.Engine;
import com.floreysoft.jmte.template.Template;

public class JmteCompiledRunner extends JmteRunner {
	Template template;
	public JmteCompiledRunner() {
		loadPersons();
		loadTemplate();				
		template = Engine.createCompilingEngine().getTemplate(templateSource);
	}
	@Override
	protected void runIteration() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("persons", persons);
		String transformed = template.transform(model, null);		
	}	
}
