package net.pfitz.webspeed.josephus;

import net.pfitz.webspeed.Runner;

public class Chain {

  private Soldier first = null;
  
  public Chain(int size)
  {
      Soldier last = null;
      Soldier current = null;
      for (int i = 0 ; i < size ; i++)
      {
          current = new Soldier(i);
          if (first == null) first = current;
          if (last != null)
          {
              last.setNext(current);
              current.setPrev(last);
          }
          last = current;
      }
      first.setPrev(last);
      last.setNext(first);     
  }

  public Soldier kill(int nth)
  {
      Soldier current = first;
      int shout = 1;
      while(current.getNext() != current)
      {
          shout = current.shout(shout, nth);
          current = current.getNext();
      }
      first = current;
      return current;
  }
  
  public Soldier getFirst()
  {
      return first;
  }

}
