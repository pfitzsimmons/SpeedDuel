package net.pfitz.webspeed.memorydb;

import java.util.Comparator;
import java.util.Random;

public class PageVisit implements Comparable<PageVisit> {
	private String url;
	private int visits;
	private int accountId;
	private String source;
	private static String[] SOURCES = new String[]{"google", "yahoo", "bing", "link", "twitter", "facebook"};	
	
	public PageVisit(String url, int visits, int accountId, String source) {
		this.url = url;
		this.visits = visits;
		this.accountId = accountId;
		this.source = source;
	}	
	
	public static PageVisit randomVisit() {
		return randomVisit(null);
	}
	
	public static PageVisit randomVisit(Integer accountId) {
		Random rand = new Random();
		if (accountId == null) {
			accountId = rand.nextInt(2000);
		}
		int pageId = rand.nextInt(100);
		return new PageVisit(
			String.format("http://site-%s.sites.com/page/%s", accountId, pageId),
			rand.nextInt(100),
			(int)accountId,
			SOURCES[rand.nextInt(SOURCES.length-1)]
			);
		
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	@Override
	public int compareTo(PageVisit o) {
		return Integer.compare(this.getVisits(), o.getVisits());
	}


	

}
