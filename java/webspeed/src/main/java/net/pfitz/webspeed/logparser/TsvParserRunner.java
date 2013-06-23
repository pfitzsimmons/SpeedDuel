package net.pfitz.webspeed.logparser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.common.io.Files;

import net.pfitz.webspeed.BaseRunner;
import net.pfitz.webspeed.Constants;

public class TsvParserRunner extends BaseRunner {
	protected String content;	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	
	public TsvParserRunner() {
		loadTsv();
	}
	
	public void loadTsv() {
		String path = Constants.DATA_PATH + "/data.csv";
		File file = new File(path);
		try {
			content = Files.toString(file, Charset.forName("utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	protected void runIteration() throws Exception {
		Map<Integer[], Integer> totals = new HashMap<Integer[], Integer>();
		for(String line: content.split("\n")) {
			String[] parts = line.split("\t");
			if (parts.length == 0 || parts[0].isEmpty()) {
				continue;
			}
			Date date = dateFormat.parse(parts[0]);
			Integer[] key = new Integer[]{date.getYear(), date.getMonth()};
			Integer value = Integer.parseInt(parts[1]);
			if (!totals.containsKey(key)) {
				totals.put(key, value);
			} else {
				totals.put(key, totals.get(key) + value);
			}
		}
	}
}
