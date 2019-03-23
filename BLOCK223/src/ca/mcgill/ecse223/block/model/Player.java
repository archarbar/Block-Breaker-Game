/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 73 "../../../../../Block223Persistence.ump"
// line 6 "../../../../../Block223v3.ump"
// line 46 "../../../../../Block223v2.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int life;

  //Player Associations
  private List<Entry> entries;
  private List<PlayedGame> playedGames;
  private List<HallOfFameEntry> hallOfFameEntries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223, int aLife)
  {
    super(aPassword, aBlock223);
    life = aLife;
    entries = new ArrayList<Entry>();
    playedGames = new ArrayList<PlayedGame>();
    hallOfFameEntries = new ArrayList<HallOfFameEntry>();
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
  /* Code from template association_GetMany */
  public PlayedGame getPlayedGame(int index)
  {
    PlayedGame aPlayedGame = playedGames.get(index);
    return aPlayedGame;
  }

  public List<PlayedGame> getPlayedGames()
  {
    List<PlayedGame> newPlayedGames = Collections.unmodifiableList(playedGames);
    return newPlayedGames;
  }

  public int numberOfPlayedGames()
  {
    int number = playedGames.size();
    return number;
  }

  public boolean hasPlayedGames()
  {
    boolean has = playedGames.size() > 0;
    return has;
  }

  public int indexOfPlayedGame(PlayedGame aPlayedGame)
  {
    int index = playedGames.indexOf(aPlayedGame);
    return index;
  }
  /* Code from template association_GetMany */
  public HallOfFameEntry getHallOfFameEntry(int index)
  {
    HallOfFameEntry aHallOfFameEntry = hallOfFameEntries.get(index);
    return aHallOfFameEntry;
  }

  public List<HallOfFameEntry> getHallOfFameEntries()
  {
    List<HallOfFameEntry> newHallOfFameEntries = Collections.unmodifiableList(hallOfFameEntries);
    return newHallOfFameEntries;
  }

  public int numberOfHallOfFameEntries()
  {
    int number = hallOfFameEntries.size();
    return number;
  }

  public boolean hasHallOfFameEntries()
  {
    boolean has = hallOfFameEntries.size() > 0;
    return has;
  }

  public int indexOfHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    int index = hallOfFameEntries.indexOf(aHallOfFameEntry);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayedGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addPlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasAdded = false;
    if (playedGames.contains(aPlayedGame)) { return false; }
    Player existingPlayer = aPlayedGame.getPlayer();
    if (existingPlayer == null)
    {
      aPlayedGame.setPlayer(this);
    }
    else if (!this.equals(existingPlayer))
    {
      existingPlayer.removePlayedGame(aPlayedGame);
      addPlayedGame(aPlayedGame);
    }
    else
    {
      playedGames.add(aPlayedGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasRemoved = false;
    if (playedGames.contains(aPlayedGame))
    {
      playedGames.remove(aPlayedGame);
      aPlayedGame.setPlayer(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayedGameAt(PlayedGame aPlayedGame, int index)
  {  
    boolean wasAdded = false;
    if(addPlayedGame(aPlayedGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayedGames()) { index = numberOfPlayedGames() - 1; }
      playedGames.remove(aPlayedGame);
      playedGames.add(index, aPlayedGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayedGameAt(PlayedGame aPlayedGame, int index)
  {
    boolean wasAdded = false;
    if(playedGames.contains(aPlayedGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayedGames()) { index = numberOfPlayedGames() - 1; }
      playedGames.remove(aPlayedGame);
      playedGames.add(index, aPlayedGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayedGameAt(aPlayedGame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHallOfFameEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public HallOfFameEntry addHallOfFameEntry(int aScore, String aPlayername, Game aGame, Block223 aBlock223)
  {
    return new HallOfFameEntry(aScore, aPlayername, this, aGame, aBlock223);
  }

  public boolean addHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    boolean wasAdded = false;
    if (hallOfFameEntries.contains(aHallOfFameEntry)) { return false; }
    Player existingPlayer = aHallOfFameEntry.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aHallOfFameEntry.setPlayer(this);
    }
    else
    {
      hallOfFameEntries.add(aHallOfFameEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aHallOfFameEntry, as it must always have a player
    if (!this.equals(aHallOfFameEntry.getPlayer()))
    {
      hallOfFameEntries.remove(aHallOfFameEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHallOfFameEntryAt(HallOfFameEntry aHallOfFameEntry, int index)
  {  
    boolean wasAdded = false;
    if(addHallOfFameEntry(aHallOfFameEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFameEntries()) { index = numberOfHallOfFameEntries() - 1; }
      hallOfFameEntries.remove(aHallOfFameEntry);
      hallOfFameEntries.add(index, aHallOfFameEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHallOfFameEntryAt(HallOfFameEntry aHallOfFameEntry, int index)
  {
    boolean wasAdded = false;
    if(hallOfFameEntries.contains(aHallOfFameEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFameEntries()) { index = numberOfHallOfFameEntries() - 1; }
      hallOfFameEntries.remove(aHallOfFameEntry);
      hallOfFameEntries.add(index, aHallOfFameEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHallOfFameEntryAt(aHallOfFameEntry, index);
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
    while( !playedGames.isEmpty() )
    {
      playedGames.get(0).setPlayer(null);
    }
    for(int i=hallOfFameEntries.size(); i > 0; i--)
    {
      HallOfFameEntry aHallOfFameEntry = hallOfFameEntries.get(i - 1);
      aHallOfFameEntry.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "life" + ":" + getLife()+ "]";
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 76 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID =-8 ;

  
}