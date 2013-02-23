package net.pfitz.webspeed.josephus;

import net.pfitz.webspeed.Runner;

public class JosephusRunner extends Runner {
  public void runIteration()
  {
	  Chain chain = new Chain(40);
      chain.kill(3);	  
  }
}
