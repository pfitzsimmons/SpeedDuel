package net.pfitz.webspeed.memorydb;

public class AccountVisits implements Comparable<AccountVisits>  {
	public int accountId;
	public int visits;
	public AccountVisits(int accountId, int visits) {
		this.accountId = accountId;
		this.visits = visits;
	}
	@Override
	public int compareTo(AccountVisits o) {
		return Integer.compare(this.visits, o.visits);
	}
	
}
