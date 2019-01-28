/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 48 "../../../../../main.ump"
public class Header
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Header Attributes
  private float length;
  private float width;

  //Header Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Header(float aLength, float aWidth, Game aGame)
  {
    length = aLength;
    width = aWidth;
    if (aGame == null || aGame.getHeader() != null)
    {
      throw new RuntimeException("Unable to create Header due to aGame");
    }
    game = aGame;
  }

  public Header(float aLength, float aWidth, int aNumLivesForGame, int aPlayerScoreForGame, float aWidthForGame, float aLengthForGame, String aNameForGame, float aMinPaddleLengthForGame, float aMaxPaddleLengthForGame, int aNumLevelsForGame, float aSpeedFactorForGame, HallOfFame aHallOfFameForGame, PlayArea aPlayAreaForGame, Admin aAdminForGame)
  {
    length = aLength;
    width = aWidth;
    game = new Game(aNumLivesForGame, aPlayerScoreForGame, aWidthForGame, aLengthForGame, aNameForGame, aMinPaddleLengthForGame, aMaxPaddleLengthForGame, aNumLevelsForGame, aSpeedFactorForGame, aHallOfFameForGame, aPlayAreaForGame, this, aAdminForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLength(float aLength)
  {
    boolean wasSet = false;
    length = aLength;
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

  public float getLength()
  {
    return length;
  }

  public float getWidth()
  {
    return width;
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
            "length" + ":" + getLength()+ "," +
            "width" + ":" + getWidth()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}