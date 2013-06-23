package net.pfitz.webspeed.logparser;

import java.util.HashMap;
import java.util.Map;

public class LogParserNoRegExRunner extends LogParserRunner {

	@Override
	protected void runIteration() throws Exception {
		Map<String, Long> map = new HashMap<String, Long>();
		for(String line: content.split("\n")) {
			if (line.trim().isEmpty()){
				continue;
			}			
			String[] parts = line.split(" ");			
			
			if (parts.length < 3) {
				continue;
			}
            String[] dateParts = parts[3].split("/");
            String year = dateParts[2].split(":")[0];
            String dateKey = year + "-" + dateParts[1];            
            Long mils = Long.parseLong(parts[parts.length-1]);
			if (!map.containsKey(dateKey)) {
				map.put(dateKey, mils);
			} else {
				map.put(dateKey, map.get(dateKey) + mils);
			}			
		}
		assert 30 == map.size();
		assert 1694718298 == map.get("2012-January");
	}	
}
