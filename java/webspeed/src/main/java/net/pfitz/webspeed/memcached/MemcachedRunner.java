package net.pfitz.webspeed.memcached;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import net.pfitz.webspeed.Constants;
import net.pfitz.webspeed.Person;
import net.pfitz.webspeed.Runner;
import net.spy.memcached.MemcachedClient;

public class MemcachedRunner extends Runner {
	private MemcachedClient client = null;
	private List<Person> persons = null;
	
	public MemcachedRunner() {
		try 
		{			
			String path = Constants.DATA_PATH + "/records.json";
			ObjectMapper mapper = new ObjectMapper(); 		
			this.persons = mapper.readValue(new File(path), new TypeReference<List<Person>>(){});			
			this.client = new MemcachedClient(
						new InetSocketAddress("127.0.0.1", 11211));
		} catch (Exception e) 
		{
			throw new RuntimeException(e);
		}		
	}
	
	public void runIteration()
	{
		String key = "thisisamemcachedkey";
		this.client.set(key, 1000, this.persons);
		List<Person> retrievedPersons = (List<Person>)this.client.get(key);
		assert retrievedPersons.size() == this.persons.size();
	}
	
	@Override
	public void shutdown() {
		this.client.shutdown();
	}
}

