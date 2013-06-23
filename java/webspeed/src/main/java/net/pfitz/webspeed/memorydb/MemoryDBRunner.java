package net.pfitz.webspeed.memorydb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.pfitz.webspeed.BaseRunner;

public class MemoryDBRunner extends BaseRunner {
	private List<PageVisit> visits;
	public MemoryDBRunner() {
		visits = new ArrayList<PageVisit>();
		for(int x = 0; x<1000000; x++) {
			visits.add(PageVisit.randomVisit());
		}
		for(int x = 0; x<10000; x++) {
			visits.add(PageVisit.randomVisit(2001));
		}
	}
	@Override
	protected void runIteration() throws Exception {
		this.findTop20PagesForAccount(2001);
		this.findTotalVisitsForAccount(2001);		
		this.findTop20VisitedAccountsOverall();
	}	
	
	public List<PageVisit> findTop20PagesForAccount(int accountId) {
		List<PageVisit> filtered = new ArrayList<PageVisit>();
		for(PageVisit visit: visits) {
			if (visit.getAccountId() == accountId) {
				filtered.add(visit);
			}
		}		
		Collections.sort(filtered);
		Collections.reverse(filtered);
		return filtered.subList(0, 20);
	}
	
	public int findTotalVisitsForAccount(int accountId) {
		List<PageVisit> filtered = new ArrayList<PageVisit>();
		for(PageVisit visit: visits) {
			if (visit.getAccountId() == accountId) {
				filtered.add(visit);
			}
		}	
		int total = 0;
		for(PageVisit visit: visits){
			total += visit.getVisits();
		}
		return total;
	}
	
	public List<AccountVisits> findTop20VisitedAccountsOverall() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(PageVisit visit: this.visits) {
			if (!map.containsKey(visit.getAccountId())) {
				map.put(visit.getAccountId(), 0);
			} 
			map.put(visit.getAccountId(), visit.getVisits() + map.get(visit.getAccountId()));
		}
		List<AccountVisits> avs = new ArrayList<AccountVisits>();
		for(int accountId: map.keySet()) {
			AccountVisits av = new AccountVisits(accountId, map.get(accountId));
			avs.add(av);
		}
		Collections.sort(avs);
		Collections.reverse(avs);
		return avs.subList(0, 20);
	}
	
	public List<PageVisit> findTop20VisitedPages() {
		Collections.sort(visits);
		Collections.reverse(visits);
		return visits;
	}
	
	
	
	
	
}
