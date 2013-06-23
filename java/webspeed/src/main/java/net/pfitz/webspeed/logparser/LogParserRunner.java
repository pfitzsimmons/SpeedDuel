package net.pfitz.webspeed.logparser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.io.Files;

import net.pfitz.webspeed.BaseRunner;
import net.pfitz.webspeed.Constants;

public class LogParserRunner extends BaseRunner {
	protected String content;
    Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[([^]]+) \\-\\d+\\] \"(GET|POST) [^\"]+ HTTP/1.1\" \\d+ (\\d+)");	
	public LogParserRunner() {
		loadContent();
	}
	
	public void loadContent() {
		String path = Constants.DATA_PATH + "/webserver.log";
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
		Map<String, Long> map = new HashMap<String, Long>();
		for(String line: content.split("\n")) {
			if (line.trim().isEmpty()){
				continue;
			}
			Matcher m = pattern.matcher(line);
			Boolean matches = m.matches();
			if (!matches) {
				System.out.println("skipping");				
			}
			String date = m.group(2);
			Long mils = Long.parseLong(m.group(4));
			String[] dateParts = date.split("/");
			String dateKey = dateParts[1] + "-" + dateParts[2];			
			if (!map.containsKey(dateKey)) {
				map.put(dateKey, mils);
			} else {
				map.put(dateKey, map.get(dateKey) + mils);
			}
			assert 30 == map.size();
			assert 1694718298 == map.get("2012-January");
		}
	}
}
