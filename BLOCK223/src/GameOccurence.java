/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 82 "Block223StateMachine.ump"
public class GameOccurence extends Game
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

  //GameOccurence State Machines
  public enum Status { Idle, InGame, Paused, GameEnded }
  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameOccurence(String aName, int aNrBlocksPerLevel, int aWidthPlayArea, int aHeightPlayArea, int aWidthHallOfFame, int aHeightHallOfFame, boolean aIsPublished, boolean aIsTested, Ball aBall, Paddle aPaddle, GameStatus aGameStatus, int aCurrentScore)
  {
    super(aName, aNrBlocksPerLevel, aWidthPlayArea, aHeightPlayArea, aWidthHallOfFame, aHeightHallOfFame, aIsPublished, aIsTested, aBall, aPaddle);
    gameStatus = aGameStatus;
    currentScore = aCurrentScore;
    gameId = nextGameId++;
    setStatus(Status.Idle);
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

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean startGame(Game currentGame)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Idle:
        if (isStartButtonPressed())
        {
        // line 89 "Block223StateMachine.ump"
          doStartGame(currentGame)
          setStatus(Status.InGame);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean removeBlock(BlockAssignment aBlock)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case InGame:
        if (!(blocksAlmostEmpty()))
        {
        // line 95 "Block223StateMachine.ump"
          doRemoveBlock(aBlock)
          setStatus(Status.InGame);
          wasEventProcessed = true;
          break;
        }
        if (blocksAlmostEmpty())
        {
        // line 97 "Block223StateMachine.ump"
          BlockAssignment aBlock = getBlockAssignment();
       doRemoveBlock(aBlock);
          setStatus(Status.GameEnded);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean removeBlockFromLevel(Block aBlock)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case InGame:
        if (levelBlocksAlmostEmpty())
        {
        // line 101 "Block223StateMachine.ump"
          BlockAssignment aBlock = getBlockAssignment();
       doRemoveBlock(aBlock);
          setStatus(Status.Idle);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean removeLife(Player aLife)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case InGame:
        if (lifesAlmostEmpty())
        {
        // line 105 "Block223StateMachine.ump"
          Player aLife = get
       doRemoveLife(aLife)
          setStatus(Status.GameEnded);
          wasEventProcessed = true;
          break;
        }
        if (!(lifesAlmostEmpty()))
        {
        // line 109 "Block223StateMachine.ump"
          doRemoveLife(aLife)
          setStatus(Status.Idle);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pauseGame(Block223Application currentGame)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case InGame:
        // line 112 "Block223StateMachine.ump"
        doPauseGame(currentGame)
        setStatus(Status.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean logOutFromGame(Block223Application currentGame)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Paused:
        if (isSpacebarPressed())
        {
        // line 119 "Block223StateMachine.ump"
          Game currentGame = Block223Application.getCurrentGame(); 
       doLogOut(currentGame)
          setStatus(Status.Idle);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean resumeGame(Block223Application currentGame)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Paused:
        if (isSpacebarPressed())
        {
        // line 123 "Block223StateMachine.ump"
          Game currentGame = Block223Application.getCurrentGame();
       doResume(currentGame)
          setStatus(Status.InGame);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;
  }

  public void delete()
  {
    super.delete();
  }

  // line 141 "Block223StateMachine.ump"
   private boolean blocksAlmostEmpty(){
    return numberofBlocks() == 1;
  }

  // line 145 "Block223StateMachine.ump"
   private boolean lifesAlmostEmpty(){
    return numberofLifes() == 1;
  }

  // line 149 "Block223StateMachine.ump"
   private boolean levelBlocksAlmostEmpty(){
    
  }

  // line 152 "Block223StateMachine.ump"
   private boolean isStartButtonPressed(){
    
  }

  // line 155 "Block223StateMachine.ump"
   private boolean isSpacebarPressed(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "gameId" + ":" + getGameId()+ "," +
            "currentScore" + ":" + getCurrentScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameStatus" + "=" + (getGameStatus() != null ? !getGameStatus().equals(this)  ? getGameStatus().toString().replaceAll("  ","    ") : "this" : "null");
  }
}