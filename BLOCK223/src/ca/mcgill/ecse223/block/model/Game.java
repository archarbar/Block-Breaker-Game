/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 21 "main.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int numLives;
  private int playerScore;
  private float width;
  private float length;
  private String name;
  private float minPaddleLength;
  private float maxPaddleLength;
  private int numLevels;
  private float speedFactor;

  //Game Associations
  private HallOfFame hallOfFame;
  private PlayArea playArea;
  private Header header;
  private List<Level> levels;
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aNumLives, int aPlayerScore, float aWidth, float aLength, String aName, float aMinPaddleLength, float aMaxPaddleLength, int aNumLevels, float aSpeedFactor, HallOfFame aHallOfFame, PlayArea aPlayArea, Header aHeader, Admin aAdmin)
  {
    numLives = aNumLives;
    playerScore = aPlayerScore;
    width = aWidth;
    length = aLength;
    name = aName;
    minPaddleLength = aMinPaddleLength;
    maxPaddleLength = aMaxPaddleLength;
    numLevels = aNumLevels;
    speedFactor = aSpeedFactor;
    if (aHallOfFame == null || aHallOfFame.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aHallOfFame");
    }
    hallOfFame = aHallOfFame;
    if (aPlayArea == null || aPlayArea.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aPlayArea");
    }
    playArea = aPlayArea;
    if (aHeader == null || aHeader.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aHeader");
    }
    header = aHeader;
    levels = new ArrayList<Level>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
  }

  public Game(int aNumLives, int aPlayerScore, float aWidth, float aLength, String aName, float aMinPaddleLength, float aMaxPaddleLength, int aNumLevels, float aSpeedFactor, float aWidthForHallOfFame, float aLengthForHallOfFame, float aWidthForPlayArea, float aLengthForPlayArea, GridSystem aGridSystemForPlayArea, float aLengthForHeader, float aWidthForHeader, Admin aAdmin)
  {
    numLives = aNumLives;
    playerScore = aPlayerScore;
    width = aWidth;
    length = aLength;
    name = aName;
    minPaddleLength = aMinPaddleLength;
    maxPaddleLength = aMaxPaddleLength;
    numLevels = aNumLevels;
    speedFactor = aSpeedFactor;
    hallOfFame = new HallOfFame(aWidthForHallOfFame, aLengthForHallOfFame, this);
    playArea = new PlayArea(aWidthForPlayArea, aLengthForPlayArea, this, aGridSystemForPlayArea);
    header = new Header(aLengthForHeader, aWidthForHeader, this);
    levels = new ArrayList<Level>();
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumLives(int aNumLives)
  {
    boolean wasSet = false;
    numLives = aNumLives;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayerScore(int aPlayerScore)
  {
    boolean wasSet = false;
    playerScore = aPlayerScore;
    wasSet = true;
    return wasSet;
  }

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

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinPaddleLength(float aMinPaddleLength)
  {
    boolean wasSet = false;
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxPaddleLength(float aMaxPaddleLength)
  {
    boolean wasSet = false;
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumLevels(int aNumLevels)
  {
    boolean wasSet = false;
    numLevels = aNumLevels;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpeedFactor(float aSpeedFactor)
  {
    boolean wasSet = false;
    speedFactor = aSpeedFactor;
    wasSet = true;
    return wasSet;
  }

  public int getNumLives()
  {
    return numLives;
  }

  public int getPlayerScore()
  {
    return playerScore;
  }

  public float getWidth()
  {
    return width;
  }

  public float getLength()
  {
    return length;
  }

  public String getName()
  {
    return name;
  }

  public float getMinPaddleLength()
  {
    return minPaddleLength;
  }

  public float getMaxPaddleLength()
  {
    return maxPaddleLength;
  }

  public int getNumLevels()
  {
    return numLevels;
  }

  public float getSpeedFactor()
  {
    return speedFactor;
  }
  /* Code from template association_GetOne */
  public HallOfFame getHallOfFame()
  {
    return hallOfFame;
  }
  /* Code from template association_GetOne */
  public PlayArea getPlayArea()
  {
    return playArea;
  }
  /* Code from template association_GetOne */
  public Header getHeader()
  {
    return header;
  }
  /* Code from template association_GetMany */
  public Level getLevel(int index)
  {
    Level aLevel = levels.get(index);
    return aLevel;
  }

  public List<Level> getLevels()
  {
    List<Level> newLevels = Collections.unmodifiableList(levels);
    return newLevels;
  }

  public int numberOfLevels()
  {
    int number = levels.size();
    return number;
  }

  public boolean hasLevels()
  {
    boolean has = levels.size() > 0;
    return has;
  }

  public int indexOfLevel(Level aLevel)
  {
    int index = levels.indexOf(aLevel);
    return index;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfLevelsValid()
  {
    boolean isValid = numberOfLevels() >= minimumNumberOfLevels() && numberOfLevels() <= maximumNumberOfLevels();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfLevels()
  {
    return 99;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Level addLevel(int aNumBlocs, int aNumLevels, boolean aBlockRandomizer, Paddle aPaddle, Ball aBall)
  {
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return null;
    }
    else
    {
      return new Level(aNumBlocs, aNumLevels, aBlockRandomizer, aPaddle, this, aBall);
    }
  }

  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return wasAdded;
    }

    Game existingGame = aLevel.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfLevels() <= minimumNumberOfLevels())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aLevel.setGame(this);
    }
    else
    {
      levels.add(aLevel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    //Unable to remove aLevel, as it must always have a game
    if (this.equals(aLevel.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (1)
    if (numberOfLevels() <= minimumNumberOfLevels())
    {
      return wasRemoved;
    }
    levels.remove(aLevel);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLevelAt(Level aLevel, int index)
  {  
    boolean wasAdded = false;
    if(addLevel(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLevelAt(Level aLevel, int index)
  {
    boolean wasAdded = false;
    if(levels.contains(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLevelAt(aLevel, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeGame(this);
    }
    admin.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    HallOfFame existingHallOfFame = hallOfFame;
    hallOfFame = null;
    if (existingHallOfFame != null)
    {
      existingHallOfFame.delete();
    }
    PlayArea existingPlayArea = playArea;
    playArea = null;
    if (existingPlayArea != null)
    {
      existingPlayArea.delete();
    }
    Header existingHeader = header;
    header = null;
    if (existingHeader != null)
    {
      existingHeader.delete();
    }
    while (levels.size() > 0)
    {
      Level aLevel = levels.get(levels.size() - 1);
      aLevel.delete();
      levels.remove(aLevel);
    }
    
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeGame(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numLives" + ":" + getNumLives()+ "," +
            "playerScore" + ":" + getPlayerScore()+ "," +
            "width" + ":" + getWidth()+ "," +
            "length" + ":" + getLength()+ "," +
            "name" + ":" + getName()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "," +
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "," +
            "numLevels" + ":" + getNumLevels()+ "," +
            "speedFactor" + ":" + getSpeedFactor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFame = "+(getHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getHallOfFame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playArea = "+(getPlayArea()!=null?Integer.toHexString(System.identityHashCode(getPlayArea())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "header = "+(getHeader()!=null?Integer.toHexString(System.identityHashCode(getHeader())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}