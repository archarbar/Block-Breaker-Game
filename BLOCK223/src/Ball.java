/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 39 "Block223StateMachine.ump"
public class Ball
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int minBallSpeedX;
  private int minBallSpeedY;
  private double ballSpeedIncreaseFactor;
  private int ballPositionX;
  private int ballPositionY;

  //Ball Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, int aBallPositionX, int aBallPositionY, Game aGame)
  {
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    ballPositionX = aBallPositionX;
    ballPositionY = aBallPositionY;
    if (aGame == null || aGame.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aGame");
    }
    game = aGame;
  }

  public Ball(int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, int aBallPositionX, int aBallPositionY, String aNameForGame, int aNrBlocksPerLevelForGame, int aWidthPlayAreaForGame, int aHeightPlayAreaForGame, int aWidthHallOfFameForGame, int aHeightHallOfFameForGame, boolean aIsPublishedForGame, boolean aIsTestedForGame, Paddle aPaddleForGame)
  {
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    ballPositionX = aBallPositionX;
    ballPositionY = aBallPositionY;
    game = new Game(aNameForGame, aNrBlocksPerLevelForGame, aWidthPlayAreaForGame, aHeightPlayAreaForGame, aWidthHallOfFameForGame, aHeightHallOfFameForGame, aIsPublishedForGame, aIsTestedForGame, this, aPaddleForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMinBallSpeedX(int aMinBallSpeedX)
  {
    boolean wasSet = false;
    minBallSpeedX = aMinBallSpeedX;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeedY(int aMinBallSpeedY)
  {
    boolean wasSet = false;
    minBallSpeedY = aMinBallSpeedY;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallSpeedIncreaseFactor(double aBallSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    wasSet = true;
    return wasSet;
  }

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

  public int getBallPositionX()
  {
    return ballPositionX;
  }

  public int getBallPositionY()
  {
    return ballPositionY;
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
            "minBallSpeedX" + ":" + getMinBallSpeedX()+ "," +
            "minBallSpeedY" + ":" + getMinBallSpeedY()+ "," +
            "ballSpeedIncreaseFactor" + ":" + getBallSpeedIncreaseFactor()+ "," +
            "ballPositionX" + ":" + getBallPositionX()+ "," +
            "ballPositionY" + ":" + getBallPositionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}