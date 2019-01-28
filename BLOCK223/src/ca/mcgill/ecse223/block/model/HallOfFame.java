/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 39 "../../../../../main.ump"
public class HallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HallOfFame Attributes
  private float width;
  private float length;

  //HallOfFame Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HallOfFame(float aWidth, float aLength, Game aGame)
  {
    width = aWidth;
    length = aLength;
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
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }

  public void delete()
  {
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