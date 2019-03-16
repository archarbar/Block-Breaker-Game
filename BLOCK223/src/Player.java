/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 17 "main.ump"
public class Player extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int life;

  //Player Associations
  private List<Entry> entries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, int aLife)
  {
    super(aPassword);
    life = aLife;
    entries = new ArrayList<Entry>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLife(int aLife)
  {
    boolean wasSet = false;
    life = aLife;
    wasSet = true;
    return wasSet;
  }

  public int getLife()
  {
    return life;
  }
  /* Code from template association_GetMany */
  public Entry getEntry(int index)
  {
    Entry aEntry = entries.get(index);
    return aEntry;
  }

  public List<Entry> getEntries()
  {
    List<Entry> newEntries = Collections.unmodifiableList(entries);
    return newEntries;
  }

  public int numberOfEntries()
  {
    int number = entries.size();
    return number;
  }

  public boolean hasEntries()
  {
    boolean has = entries.size() > 0;
    return has;
  }

  public int indexOfEntry(Entry aEntry)
  {
    int index = entries.indexOf(aEntry);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Entry addEntry(int aFinalScore, String aName, Game aGame)
  {
    return new Entry(aFinalScore, aName, aGame, this);
  }

  public boolean addEntry(Entry aEntry)
  {
    boolean wasAdded = false;
    if (entries.contains(aEntry)) { return false; }
    Player existingPlayer = aEntry.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aEntry.setPlayer(this);
    }
    else
    {
      entries.add(aEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEntry(Entry aEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aEntry, as it must always have a player
    if (!this.equals(aEntry.getPlayer()))
    {
      entries.remove(aEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEntryAt(Entry aEntry, int index)
  {  
    boolean wasAdded = false;
    if(addEntry(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEntryAt(Entry aEntry, int index)
  {
    boolean wasAdded = false;
    if(entries.contains(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEntryAt(aEntry, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=entries.size(); i > 0; i--)
    {
      Entry aEntry = entries.get(i - 1);
      aEntry.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "life" + ":" + getLife()+ "]";
  }
}