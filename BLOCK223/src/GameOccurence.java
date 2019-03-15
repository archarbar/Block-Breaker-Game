/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 83 "main.ump"
// line 163 "main.ump"
public class GameOccurence
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum GameStatus { Idle, InGame, Paused, Ended }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextGameId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameOccurence Attributes
  private GameStatus gameStatus;
  private int currentScore;

  //Autounique Attributes
  private int gameId;

  //GameOccurence Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameOccurence(GameStatus aGameStatus, int aCurrentScore, Game aGame)
  {
    gameStatus = aGameStatus;
    currentScore = aCurrentScore;
    gameId = nextGameId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create gameOccurence due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGameStatus(GameStatus aGameStatus)
  {
    boolean wasSet = false;
    gameStatus = aGameStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentScore(int aCurrentScore)
  {
    boolean wasSet = false;
    currentScore = aCurrentScore;
    wasSet = true;
    return wasSet;
  }

  public GameStatus getGameStatus()
  {
    return gameStatus;
  }

  public int getCurrentScore()
  {
    return currentScore;
  }

  public int getGameId()
  {
    return gameId;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeGameOccurence(this);
    }
    game.addGameOccurence(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeGameOccurence(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gameId" + ":" + getGameId()+ "," +
            "currentScore" + ":" + getCurrentScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameStatus" + "=" + (getGameStatus() != null ? !getGameStatus().equals(this)  ? getGameStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}