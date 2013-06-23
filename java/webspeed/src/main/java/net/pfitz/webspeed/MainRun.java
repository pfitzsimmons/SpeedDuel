package net.pfitz.webspeed;

import net.pfitz.webspeed.josephus.Chain;
import net.pfitz.webspeed.josephus.JosephusRunner;
import net.pfitz.webspeed.logparser.LogParserNoRegExRunner;
import net.pfitz.webspeed.logparser.LogParserRunner;
import net.pfitz.webspeed.logparser.TsvParserHackyRunner;
import net.pfitz.webspeed.logparser.TsvParserRunner;
import net.pfitz.webspeed.memcached.MemcachedRunner;
import net.pfitz.webspeed.memcached.MemcachedStringRunner;
import net.pfitz.webspeed.memorydb.MemoryDBRunner;
import net.pfitz.webspeed.memorydb.MemoryDBSortingRunner;
import net.pfitz.webspeed.templating.JmteCompiledRunner;
import net.pfitz.webspeed.templating.JmteRunner;
import net.pfitz.webspeed.templating.StringBuilderRunner;
import net.pfitz.webspeed.templating.VelocityRunner;

public class MainRun {
	////mvn exec:java -Dexec.mainClass="net.pfitz.webspeed.MainRun" -Dexec.args="arg" 
	public static void main(String [] args) throws Exception
	{
		if (args.length == 0) {
			System.out.println("First argument should be the test you want to run");
			return;
		}
		String action = args[0];
		System.out.println("Running the main method, action: " + action);
		Runner runner = null;
		int numTimes = 10;
		if (action.equals("josephus")) {
			numTimes = 10000;
			runner = new JosephusRunner(); 
		} else if (action.equals("json-deserialize")) {
			numTimes = 1000;
			runner = new DeserializeJson();
		} else if (action.equals("json-two-way")) {
			numTimes = 1000;
			runner = new JsonTwoWay();		
		} else if (action.equals("json-typed")) {
			numTimes = 1000;
			runner = new DeserializeTypedJson();
		} else if (action.equals("json-typed-two-way")) {
			numTimes = 1000;
			runner = new DeserializeTwoWayTypedJson();
		} else if (action.equals("log-parser")) {
			numTimes = 10;
			runner = new LogParserRunner();
		} else if (action.equals("log-parser-nore")) {
			numTimes = 10;
			runner = new LogParserNoRegExRunner();
		} else if (action.equals("memdb")) {
			numTimes = 10;
			runner = new MemoryDBRunner();
		} else if (action.equals("memdb-sorting")) {
			numTimes = 10;
			runner = new MemoryDBSortingRunner();
		} else if (action.equals("memcached")) {
			numTimes = 10000;
			runner = new MemcachedRunner();
		} else if (action.equals("memcachedstring")) {
			numTimes = 10000;
			runner = new MemcachedStringRunner();
		} else if (action.equals("templating-velocity")) {
			numTimes = 10;
			runner = new VelocityRunner();			
		} else if (action.equals("templating-jmte")) {
			numTimes = 10;
			runner = new JmteRunner();
		} else if (action.equals("templating-string-builder")) {
			numTimes = 100;
			runner = new StringBuilderRunner();
		} else if (action.equals("templating-jmte-compiled")) {
			numTimes = 10;
			runner = new JmteCompiledRunner();
		} else if (action.equals("tsv")) {
			numTimes = 10;
			runner = new TsvParserRunner();
		} else if (action.equals("tsv-hacky")) {
			numTimes = 10;
			runner = new TsvParserHackyRunner();
		} else {
			System.out.println("Invalid action: " + action);			
			return;
		}
		
		long start = System.nanoTime();
		for (int i = 0 ; i < numTimes ; i++)
		{
			runner.runIteration();
		  
		}
		String postfix = " microseconds";
		long end = System.nanoTime();
		long elapsed = end - start;
		double perIteration = elapsed / numTimes / 1000.0;
		if (perIteration >= 10000) {
			perIteration = perIteration/1000.0;
			postfix = " milliseconds";
					
		}
		System.out.println("Time per iteration = " + perIteration + postfix + ".");
		runner.shutdown();
		
	}

}
