package net.pfitz.webspeed.logparser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TsvParserHackyRunner extends TsvParserRunner {
	@Override
	protected void runIteration() throws Exception {
		// This version simply splits the date string to make a key, rather
		// than incurring the overhead of the datetime parser
		Map<String[], Integer> totals = new HashMap<String[], Integer>();
		for(String line: content.split("\n")) {
			String[] parts = line.split("\t");
			if (parts.length == 0 || parts[0].isEmpty()) {
				continue;
			}
			String[] dateParts = parts[0].split("\\-");			
			String[] key = new String[]{dateParts[0], dateParts[1]};
			Integer value = Integer.parseInt(parts[1]);
			if (!totals.containsKey(key)) {
				totals.put(key, value);
			} else {
				totals.put(key, totals.get(key) + value);
			}
		}
	}
}
