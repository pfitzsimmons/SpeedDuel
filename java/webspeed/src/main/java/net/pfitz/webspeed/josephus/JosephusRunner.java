package net.pfitz.webspeed.josephus;

import net.pfitz.webspeed.BaseRunner;
import net.pfitz.webspeed.Runner;

public class JosephusRunner extends BaseRunner {
  public void runIteration()
  {
	  Chain chain = new Chain(40);
      chain.kill(3);	  
  }
}
