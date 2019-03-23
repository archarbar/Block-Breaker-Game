/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 36 "Block223v3.ump"
public class Paddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private int maxPaddleLength;
  private int minPaddleLength;
  private int paddlePositionX;
  private int paddlePositionY;

  //Paddle Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(int aMaxPaddleLength, int aMinPaddleLength, int aPaddlePositionX, int aPaddlePositionY, Game aGame)
  {
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    paddlePositionX = aPaddlePositionX;
    paddlePositionY = aPaddlePositionY;
    if (aGame == null || aGame.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aGame");
    }
    game = aGame;
  }

  public Paddle(int aMaxPaddleLength, int aMinPaddleLength, int aPaddlePositionX, int aPaddlePositionY, int aWidthPlayAreaForGame, int aHeightPlayAreaForGame, int aWidthHallOfFameForGame, int aHeightHallOfFameForGame, boolean aIsPublishedForGame, boolean aIsTestedForGame, int aWaitTimeForGame, Ball aBallForGame)
  {
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    paddlePositionX = aPaddlePositionX;
    paddlePositionY = aPaddlePositionY;
    game = new Game(aWidthPlayAreaForGame, aHeightPlayAreaForGame, aWidthHallOfFameForGame, aHeightHallOfFameForGame, aIsPublishedForGame, aIsTestedForGame, aWaitTimeForGame, aBallForGame, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMaxPaddleLength(int aMaxPaddleLength)
  {
    boolean wasSet = false;
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinPaddleLength(int aMinPaddleLength)
  {
    boolean wasSet = false;
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

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

  public int getMaxPaddleLength()
  {
    return maxPaddleLength;
  }

  public int getMinPaddleLength()
  {
    return minPaddleLength;
  }

  public int getPaddlePositionX()
  {
    return paddlePositionX;
  }

  public int getPaddlePositionY()
  {
    return paddlePositionY;
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
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "," +
            "paddlePositionX" + ":" + getPaddlePositionX()+ "," +
            "paddlePositionY" + ":" + getPaddlePositionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}