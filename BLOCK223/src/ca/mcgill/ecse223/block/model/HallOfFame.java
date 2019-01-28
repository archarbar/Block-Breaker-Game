/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 42 "../../../../../main.ump"
public class HallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HallOfFame Attributes
  private float width;
  private float length;

  //HallOfFame Associations
  private List<Player> players;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HallOfFame(float aWidth, float aLength, Game aGame)
  {
    width = aWidth;
    length = aLength;
    players = new ArrayList<Player>();
    if (aGame == null || aGame.getHallOfFame() != null)
    {
      throw new RuntimeException("Unable to create HallOfFame due to aGame");
    }
    game = aGame;
  }

  public HallOfFame(float aWidth, float aLength, int aNumLivesForGame, int aPlayerScoreForGame, float aWidthForGame, float aLengthForGame, String aNameForGame, float aMinPaddleLengthForGame, float aMaxPaddleLengthForGame, int aNumLevelsForGame, float aSpeedFactorForGame, PlayArea aPlayAreaForGame, Header aHeaderForGame, Admin aAdminForGame)
  {
    width = aWidth;
    length = aLength;
    players = new ArrayList<Player>();
    game = new Game(aNumLivesForGame, aPlayerScoreForGame, aWidthForGame, aLengthForGame, aNameForGame, aMinPaddleLengthForGame, aMaxPaddleLengthForGame, aNumLevelsForGame, aSpeedFactorForGame, this, aPlayAreaForGame, aHeaderForGame, aAdminForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWidth(float aWidth)
  {
    boolean wasSet = false;
    width = aWidth;
    wasSet = true;
    return wasSet;
  }

  public boolean setLength(float aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public float getWidth()
  {
    return width;
  }

  public float getLength()
  {
    return length;
  }
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Player addPlayer(String aUserName, String aPlayerPassword, int aScore)
  {
    return new Player(aUserName, aPlayerPassword, aScore, this);
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    HallOfFame existingHallOfFame = aPlayer.getHallOfFame();
    boolean isNewHallOfFame = existingHallOfFame != null && !this.equals(existingHallOfFame);
    if (isNewHallOfFame)
    {
      aPlayer.setHallOfFame(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a hallOfFame
    if (!this.equals(aPlayer.getHallOfFame()))
    {
      players.remove(aPlayer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=players.size(); i > 0; i--)
    {
      Player aPlayer = players.get(i - 1);
      aPlayer.delete();
    }
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "width" + ":" + getWidth()+ "," +
            "length" + ":" + getLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}