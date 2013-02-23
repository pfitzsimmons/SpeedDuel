package net.pfitz.webspeed.josephus;

public class Soldier {
  int count;
  private Soldier prev = null;
  private Soldier next = null;
  
  public Soldier(int count)
  {
      this.count = count;
  }
  
  public int shout(int shout, int deadif)
  {
      if (shout < deadif) return (shout + 1);
      this.getPrev().setNext(this.getNext());
      this.getNext().setPrev(this.getPrev());
      return 1;
  }
  
  public int getCount()
  {
      return this.count;
  }
  
  public Soldier getPrev()
  {
      return prev;
  }

  public void setPrev(Soldier prev)
  {
      this.prev = prev;
  }

  public Soldier getNext()
  {
      return next;
  }

  public void setNext(Soldier next)

  {
      this.next = next;
  }
}
