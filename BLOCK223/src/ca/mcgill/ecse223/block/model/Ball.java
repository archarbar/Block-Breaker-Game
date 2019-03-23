/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 9 "../../../../../Block223Persistence.ump"
// line 26 "../../../../../Block223v3.ump"
// line 175 "../../../../../Block223v2.ump"
public class Ball implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int BALL_DIAMETER = 10;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int ballPositionX;
  private int ballPositionY;
  private int minBallSpeedX;
  private int minBallSpeedY;
  private double ballSpeedIncreaseFactor;

  //Ball Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aBallPositionX, int aBallPositionY, int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, Game aGame)
  {
    // line 179 "../../../../../Block223v2.ump"
    if (aMinBallSpeedX <= 0) {
         	throw new RuntimeException("The minimum speed of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    // line 185 "../../../../../Block223v2.ump"
    if (aMinBallSpeedY <= 0) {
         	throw new RuntimeException("The minimum speed of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    // line 191 "../../../../../Block223v2.ump"
    if (aBallSpeedIncreaseFactor <= 0) {
         	throw new RuntimeException("The speed increase factor of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    ballPositionX = aBallPositionX;
    ballPositionY = aBallPositionY;
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    if (aGame == null || aGame.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aGame");
    }
    game = aGame;
  }

  public Ball(int aBallPositionX, int aBallPositionY, int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, int aWidthPlayAreaForGame, int aHeightPlayAreaForGame, int aWidthHallOfFameForGame, int aHeightHallOfFameForGame, boolean aIsPublishedForGame, boolean aIsTestedForGame, int aWaitTimeForGame, String aNameForGame, int aNrBlocksPerLevelForGame, Paddle aPaddleForGame, Block223 aBlock223ForGame)
  {
    // line 179 "../../../../../Block223v2.ump"
    if (aMinBallSpeedX <= 0) {
         	throw new RuntimeException("The minimum speed of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    // line 185 "../../../../../Block223v2.ump"
    if (aMinBallSpeedY <= 0) {
         	throw new RuntimeException("The minimum speed of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    // line 191 "../../../../../Block223v2.ump"
    if (aBallSpeedIncreaseFactor <= 0) {
         	throw new RuntimeException("The speed increase factor of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    ballPositionX = aBallPositionX;
    ballPositionY = aBallPositionY;
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    game = new Game(aWidthPlayAreaForGame, aHeightPlayAreaForGame, aWidthHallOfFameForGame, aHeightHallOfFameForGame, aIsPublishedForGame, aIsTestedForGame, aWaitTimeForGame, aNameForGame, aNrBlocksPerLevelForGame, aPaddleForGame, this, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBallPositionX(int aBallPositionX)
  {
    boolean wasSet = false;
    ballPositionX = aBallPositionX;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallPositionY(int aBallPositionY)
  {
    boolean wasSet = false;
    ballPositionY = aBallPositionY;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeedX(int aMinBallSpeedX)
  {
    boolean wasSet = false;
    // line 179 "../../../../../Block223v2.ump"
    if (aMinBallSpeedX <= 0) {
         	throw new RuntimeException("The minimum speed of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    minBallSpeedX = aMinBallSpeedX;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeedY(int aMinBallSpeedY)
  {
    boolean wasSet = false;
    // line 185 "../../../../../Block223v2.ump"
    if (aMinBallSpeedY <= 0) {
         	throw new RuntimeException("The minimum speed of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    minBallSpeedY = aMinBallSpeedY;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallSpeedIncreaseFactor(double aBallSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    // line 191 "../../../../../Block223v2.ump"
    if (aBallSpeedIncreaseFactor <= 0) {
         	throw new RuntimeException("The speed increase factor of the ball must be greater than zero");
         }
    // END OF UMPLE BEFORE INJECTION
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    wasSet = true;
    return wasSet;
  }

  public int getBallPositionX()
  {
    return ballPositionX;
  }

  public int getBallPositionY()
  {
    return ballPositionY;
  }

  public int getMinBallSpeedX()
  {
    return minBallSpeedX;
  }

  public int getMinBallSpeedY()
  {
    return minBallSpeedY;
  }

  public double getBallSpeedIncreaseFactor()
  {
    return ballSpeedIncreaseFactor;
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
            "ballPositionX" + ":" + getBallPositionX()+ "," +
            "ballPositionY" + ":" + getBallPositionY()+ "," +
            "minBallSpeedX" + ":" + getMinBallSpeedX()+ "," +
            "minBallSpeedY" + ":" + getMinBallSpeedY()+ "," +
            "ballSpeedIncreaseFactor" + ":" + getBallSpeedIncreaseFactor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 12 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID =001 ;

  
}