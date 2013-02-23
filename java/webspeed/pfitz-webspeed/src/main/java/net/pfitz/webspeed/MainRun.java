package net.pfitz.webspeed;

import net.pfitz.webspeed.josephus.Chain;
import net.pfitz.webspeed.josephus.JosephusRunner;
import net.pfitz.webspeed.memcached.MemcachedRunner;
import net.pfitz.webspeed.memcached.MemcachedStringRunner;

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
		int numTimes = 100000;
		if (action.equals("josephus")) {
			runner = new JosephusRunner(); 
		} else if (action.equals("deserializejson")) {
			numTimes = 1000;
			runner = new DeserializeJson();
		} else if (action.equals("deserializetyped")) {
			numTimes = 1000;
			runner = new DeserializeTypedJson();
		} else if (action.equals("memcached")) {
			numTimes = 1000;
			runner = new MemcachedRunner();
		} else if (action.equals("memcachedstring")) {
			numTimes = 10000;
			runner = new MemcachedStringRunner();			
		} else {
			System.out.println("Invalid action: " + action);			
			return;
		}
		
		long start = System.nanoTime();
		for (int i = 0 ; i < numTimes ; i++)
		{
			runner.runIteration();
		  
		}
		long end = System.nanoTime();
		System.out.println("Time per iteration = " + ((end - start) / (numTimes )/1000.0) + " microseconds.");
		
		
	}

}
