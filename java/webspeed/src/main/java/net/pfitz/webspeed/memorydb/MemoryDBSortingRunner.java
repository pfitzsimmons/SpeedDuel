package net.pfitz.webspeed.memorydb;

public class MemoryDBSortingRunner extends MemoryDBRunner {
	@Override
	protected void runIteration() throws Exception {		
		this.findTop20VisitedPages();
	}	
		
}
