/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 68 "../../../../../Block223Persistence.ump"
// line 32 "../../../../../Block223v3.ump"
// line 197 "../../../../../Block223v2.ump"
public class Paddle implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int PADDLE_WIDTH = 5;
  public static final int VERTICAL_DISTANCE = 30;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private int paddlePositionX;
  private int paddlePositionY;
  private int maxPaddleLength;
  private int minPaddleLength;

  //Paddle Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(int aPaddlePositionX, int aPaddlePositionY, int aMaxPaddleLength, int aMinPaddleLength, Game aGame)
  {
    // line 202 "../../../../../Block223v2.ump"
    if (aMaxPaddleLength <= 0 || aMaxPaddleLength > 400) {
         	throw new RuntimeException("The maximum length of the paddle must be greater than zero and less than or equal to 400");
         }
    // END OF UMPLE BEFORE INJECTION
    // line 208 "../../../../../Block223v2.ump"
    if (aMinPaddleLength <= 0) {
         	throw new RuntimeException("The minimum length of the paddle must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    paddlePositionX = aPaddlePositionX;
    paddlePositionY = aPaddlePositionY;
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    if (aGame == null || aGame.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aGame");
    }
    game = aGame;
  }

  public Paddle(int aPaddlePositionX, int aPaddlePositionY, int aMaxPaddleLength, int aMinPaddleLength, int aWidthPlayAreaForGame, int aHeightPlayAreaForGame, int aWidthHallOfFameForGame, int aHeightHallOfFameForGame, boolean aIsPublishedForGame, boolean aIsTestedForGame, int aWaitTimeForGame, String aNameForGame, int aNrBlocksPerLevelForGame, Ball aBallForGame, Block223 aBlock223ForGame)
  {
    // line 202 "../../../../../Block223v2.ump"
    if (aMaxPaddleLength <= 0 || aMaxPaddleLength > 400) {
         	throw new RuntimeException("The maximum length of the paddle must be greater than zero and less than or equal to 400");
         }
    // END OF UMPLE BEFORE INJECTION
    // line 208 "../../../../../Block223v2.ump"
    if (aMinPaddleLength <= 0) {
         	throw new RuntimeException("The minimum length of the paddle must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    paddlePositionX = aPaddlePositionX;
    paddlePositionY = aPaddlePositionY;
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    game = new Game(aWidthPlayAreaForGame, aHeightPlayAreaForGame, aWidthHallOfFameForGame, aHeightHallOfFameForGame, aIsPublishedForGame, aIsTestedForGame, aWaitTimeForGame, aNameForGame, aNrBlocksPerLevelForGame, this, aBallForGame, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPaddlePositionX(int aPaddlePositionX)
  {
    boolean wasSet = false;
    paddlePositionX = aPaddlePositionX;
    wasSet = true;
    return wasSet;
  }

  public boolean setPaddlePositionY(int aPaddlePositionY)
  {
    boolean wasSet = false;
    paddlePositionY = aPaddlePositionY;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxPaddleLength(int aMaxPaddleLength)
  {
    boolean wasSet = false;
    // line 202 "../../../../../Block223v2.ump"
    if (aMaxPaddleLength <= 0 || aMaxPaddleLength > 400) {
         	throw new RuntimeException("The maximum length of the paddle must be greater than zero and less than or equal to 400");
         }
    // END OF UMPLE BEFORE INJECTION
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinPaddleLength(int aMinPaddleLength)
  {
    boolean wasSet = false;
    // line 208 "../../../../../Block223v2.ump"
    if (aMinPaddleLength <= 0) {
         	throw new RuntimeException("The minimum length of the paddle must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public int getPaddlePositionX()
  {
    return paddlePositionX;
  }

  public int getPaddlePositionY()
  {
    return paddlePositionY;
  }

  public int getMaxPaddleLength()
  {
    return maxPaddleLength;
  }

  public int getMinPaddleLength()
  {
    return minPaddleLength;
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
            "paddlePositionX" + ":" + getPaddlePositionX()+ "," +
            "paddlePositionY" + ":" + getPaddlePositionY()+ "," +
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 71 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID =007 ;

  
}