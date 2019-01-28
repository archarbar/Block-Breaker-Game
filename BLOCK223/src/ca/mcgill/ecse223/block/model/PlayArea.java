/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 54 "../../../../../main.ump"
public class PlayArea
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayArea Attributes
  private float width;
  private float length;

  //PlayArea Associations
  private Game game;
  private GridSystem gridSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayArea(float aWidth, float aLength, Game aGame, GridSystem aGridSystem)
  {
    width = aWidth;
    length = aLength;
    if (aGame == null || aGame.getPlayArea() != null)
    {
      throw new RuntimeException("Unable to create PlayArea due to aGame");
    }
    game = aGame;
    if (aGridSystem == null || aGridSystem.getPlayArea() != null)
    {
      throw new RuntimeException("Unable to create PlayArea due to aGridSystem");
    }
    gridSystem = aGridSystem;
  }

  public PlayArea(float aWidth, float aLength, int aNumLivesForGame, int aPlayerScoreForGame, float aWidthForGame, float aLengthForGame, String aNameForGame, float aMinPaddleLengthForGame, float aMaxPaddleLengthForGame, int aNumLevelsForGame, float aSpeedFactorForGame, HallOfFame aHallOfFameForGame, Header aHeaderForGame, Admin aAdminForGame)
  {
    width = aWidth;
    length = aLength;
    game = new Game(aNumLivesForGame, aPlayerScoreForGame, aWidthForGame, aLengthForGame, aNameForGame, aMinPaddleLengthForGame, aMaxPaddleLengthForGame, aNumLevelsForGame, aSpeedFactorForGame, aHallOfFameForGame, this, aHeaderForGame, aAdminForGame);
    gridSystem = new GridSystem(this);
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
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public GridSystem getGridSystem()
  {
    return gridSystem;
  }

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
    GridSystem existingGridSystem = gridSystem;
    gridSystem = null;
    if (existingGridSystem != null)
    {
      existingGridSystem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "width" + ":" + getWidth()+ "," +
            "length" + ":" + getLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gridSystem = "+(getGridSystem()!=null?Integer.toHexString(System.identityHashCode(getGridSystem())):"null");
  }
}