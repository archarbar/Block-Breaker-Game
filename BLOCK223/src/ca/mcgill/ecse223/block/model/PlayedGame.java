/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import ca.mcgill.ecse223.block.application.*;
import ca.mcgill.ecse223.block.model.BouncePoint.BounceDirection;

import java.util.*;
import java.awt.Point;
import java.awt.geom.*;

// line 11 "../../../../../Block223PlayMode.ump"
// line 104 "../../../../../Block223Persistence.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 1000;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to Game and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to Game and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 1;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 62 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top right corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 13 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 14 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 15 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 16 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 17 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 19 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 20 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 8 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 26 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
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
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 47 "../../../../../Block223PlayMode.ump"
   public boolean isBallOutOfBounds(){
    double xball = this.currentBallX;
   		double yball = this.currentBallY;
   		double xballfuture = this.currentBallX + (this.ballDirectionX)*getWaitTime();
   		double yballfuture = this.currentBallY + (this.ballDirectionY)*getWaitTime();
   		double xwallleft= 0;
   		double ywallleft = Game.WALL_PADDING + Game.PLAY_AREA_SIDE;
   		double xwallright = Game.PLAY_AREA_SIDE;
   		double ywallright = Game.WALL_PADDING + Game.PLAY_AREA_SIDE;
   		
   		return java.awt.geom.Line2D.linesIntersect(xball, yball, xballfuture, yballfuture, xwallleft, ywallleft, xwallright, ywallright);
  }
   private static Point getLineIntersection(Line2D pLine1, Line2D pLine2) {
	   Point result = null;
	   double s1_x = pLine1.getX2() - pLine1.getX1();
	   double s1_y = pLine1.getY2() - pLine1.getY1();
	   double s2_x = pLine2.getX2() - pLine2.getX1();
	   double s2_y = pLine2.getY2() - pLine2.getY1();
	   double s = (-s1_y * (pLine1.getX1() - pLine2.getX1()) + s1_x * (pLine1.getY1() - pLine2.getY1())) / (-s2_x * s1_y + s1_x * s2_y);
	   double t = ( s2_x * (pLine1.getY1() - pLine2.getY1()) - s2_y * (pLine1.getX1() - pLine2.getX1())) / (-s2_x * s1_y + s1_x * s2_y);
	   
	   if(s >= 0 && s <= 1 && t >= 0 && t <= 1) {
		   //This is only true if there is the two lines intersect
		   result = new Point((int) (pLine1.getX1() + (t * s1_x)), (int) (pLine1.getY1() + (t * s1_y)));
	   }
	   return result;
   }
   private BouncePoint calculateBouncePointPaddle() {
	   BouncePoint bouncePoint = null;
	   Point bouncePosition;
	   double ballRadius = 5.0;
	   double paddleHeight = 5.0;
	   double paddleLength = this.currentPaddleLength;
	   double paddleX = this.currentPaddleX;
	   double paddleY = this.currentPaddleY;
	   double xBall = this.currentBallX;
	   double yBall = this.currentBallY;
	   double xBallFuture = this.currentBallX + (this.ballDirectionX)*getWaitTime();
	   double yBallFuture = this.currentBallY + (this.ballDirectionY)*getWaitTime();
	   Rectangle2D.Double paddleRect = new Rectangle2D.Double(paddleX, paddleY, paddleLength, paddleHeight+ballRadius);
	   boolean intersect = paddleRect.intersectsLine(xBall, yBall, xBallFuture, yBallFuture);
	   if (!intersect) {
		   return bouncePoint;
	   }
	   else {
		   Ellipse2D.Double ellipseE = new Ellipse2D.Double(ballRadius, ballRadius, paddleX - ballRadius, paddleY - ballRadius );
		   Ellipse2D.Double ellipseF = new Ellipse2D.Double(ballRadius, ballRadius, paddleX + paddleLength + ballRadius, paddleY - ballRadius);
		   Line2D.Double lineA = new Line2D.Double(paddleX, paddleY, paddleX + paddleLength, paddleY);
		   Line2D.Double lineB = new Line2D.Double(paddleX - ballRadius, paddleY, paddleX - ballRadius, paddleY + ballRadius);
		   Line2D.Double lineC = new Line2D.Double(paddleX + paddleLength + ballRadius, paddleY, paddleX + paddleLength + ballRadius, paddleY + ballRadius);
		   Line2D.Double lineBallPath = new Line2D.Double(xBall, yBall, xBallFuture, yBallFuture);
		   if(lineA.intersectsLine(lineBallPath)) {
			   bouncePosition = getLineIntersection(lineBallPath, lineA);
			   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_Y);
		   }
		   else if(lineB.intersectsLine(lineBallPath)){
			   bouncePosition = getLineIntersection(lineBallPath, lineB);
			   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
		   }
		   else if(lineC.intersectsLine(lineBallPath)) {
			   bouncePosition = getLineIntersection(lineBallPath, lineC);
			   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
		   }
		   else if(ellipseE.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePoint = new BouncePoint(xBall, yBall, BounceDirection.FLIP_X);
			   }
			   else {
				   bouncePoint = new BouncePoint(xBall, yBall, BounceDirection.FLIP_Y);
			   }
		   }
		   else if(ellipseF.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePoint = new BouncePoint(xBall, yBall, BounceDirection.FLIP_Y);
			   }
			   else {
				   bouncePoint = new BouncePoint(xBall, yBall, BounceDirection.FLIP_X);
			   }
		   } 
	   }
	   
	   return bouncePoint;
   }
  /**
   * Guards
   */
  // line 34 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
	   BouncePoint bp = calculateBouncePointPaddle();
	   if(bp != null) {
		   setBounce(bp);
		   return true;
	   }
	   return false;
  }

  // line 38 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    
    if (lives == 1){
    	outOfBounds = this.isBallOutOfBounds();
    }	
    return outOfBounds;
  }

  // line 47 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = this.isBallOutOfBounds();
    return outOfBounds;
  }

  // line 52 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    // TODO implement
    return false;
  }

  // line 57 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    // TODO implement
    return false;
  }

  // line 62 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    // TODO implement
    return false;
  }

  // line 67 "../../../../../Block223States.ump"
   private boolean hitWall(){
    // TODO implement
	   BouncePoint bp = calculateBouncePointWall();
	   if(bp != null) {
		   setBounce(bp);
		   return true;
	   }
	   return false;
  }
   private BouncePoint calculateBouncePointWall() {
	   BouncePoint bouncePoint = null;
	   Point bouncePosition;
	   double ballRadius = 5.0;
	   double wallHeight = Game.PLAY_AREA_SIDE;
	   double xBall = this.currentBallX;
	   double yBall = this.currentBallY;
	   double xBallFuture = this.currentBallX + (this.ballDirectionX)*getWaitTime();
	   double yBallFuture = this.currentBallY + (this.ballDirectionY)*getWaitTime();
	   Line2D.Double leftWall = new Line2D.Double(ballRadius, ballRadius, ballRadius, wallHeight - ballRadius);
	   Line2D.Double topWall = new Line2D.Double(ballRadius, ballRadius, wallHeight - ballRadius, ballRadius);
	   Line2D.Double rightWall = new Line2D.Double(ballRadius, wallHeight - ballRadius, wallHeight - ballRadius, wallHeight - ballRadius);
	   Line2D.Double lineBallPath = new Line2D.Double(xBall, yBall, xBallFuture, yBallFuture);
	   //If it doesn't intersect with any wall, return null
	   if (!(lineBallPath.intersectsLine(leftWall) || lineBallPath.intersectsLine(topWall) || lineBallPath.intersectsLine(rightWall))) {
		   return bouncePoint;
	   }
	   else {
		   if(lineBallPath.intersectsLine(leftWall)) {
			   bouncePosition = getLineIntersection(lineBallPath, leftWall);
			   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
			   if( (bouncePosition.getX() == 5 && bouncePosition.getY() == 5) || (bouncePosition.getX() == 385 && bouncePosition.getY() == 5) ) {
				   bouncePoint.setDirection(BounceDirection.FLIP_BOTH);
			   } 
		   }
		   else if(lineBallPath.intersectsLine(topWall)){
			   bouncePosition = getLineIntersection(lineBallPath, topWall);
			   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_Y);
			   if( (bouncePosition.getX() == 5 && bouncePosition.getY() == 5) || (bouncePosition.getX() == 385 && bouncePosition.getY() == 5) ) {
				   bouncePoint.setDirection(BounceDirection.FLIP_BOTH);
			   } 
		   }
		   else if(lineBallPath.intersectsLine(rightWall)) {
			   bouncePosition = getLineIntersection(lineBallPath, rightWall);
			   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
			   if( (bouncePosition.getX() == 5 && bouncePosition.getY() == 5) || (bouncePosition.getX() == 385 && bouncePosition.getY() == 5) ) {
				   bouncePoint.setDirection(BounceDirection.FLIP_BOTH);
			   } 
		   }
	   }
	   return bouncePoint;
   }

private void bounceBall() {
	double xBallFuture = this.currentBallX + (this.ballDirectionX)*getWaitTime();
	double yBallFuture = this.currentBallY + (this.ballDirectionY)*getWaitTime();
	double xBouncePosition = bounce.getX();
	double yBouncePosition = bounce.getY();
	double distanceX = Math.abs(xBallFuture - xBouncePosition);
	double distanceY = Math.abs(yBallFuture - yBouncePosition);
	if(xBouncePosition == xBallFuture && yBouncePosition == yBallFuture) {
		this.setCurrentBallX(xBallFuture);
		this.setCurrentBallY(yBallFuture);
	}
	else {
		if(bounce.getDirection() == BounceDirection.FLIP_BOTH) {
			if(Math.signum(this.getBallDirectionX() ) > 0) {
				this.setCurrentBallX(xBouncePosition - distanceX);
				this.setCurrentBallY(yBouncePosition + distanceY);
			}
			else {
				this.setCurrentBallX(xBouncePosition + distanceX);
				this.setCurrentBallY(yBouncePosition + distanceY);
			}
			this.setBallDirectionX(-1 * (ballDirectionX + Math.signum(ballDirectionX) * 0.1 * Math.abs(ballDirectionY)) );
			this.setBallDirectionY(-1 * (ballDirectionY + Math.signum(ballDirectionY) * 0.1 * Math.abs(ballDirectionX)) );
		}
		else if(bounce.getDirection() == BounceDirection.FLIP_X){
			if(Math.signum(this.getBallDirectionX() ) > 0) {
				this.setCurrentBallX(xBouncePosition - distanceX);
			}
			else {
				this.setCurrentBallX(xBouncePosition + distanceX);
			}
			this.setCurrentBallY(yBallFuture);
			this.setBallDirectionX(this.getBallDirectionX() * -1);
			this.setBallDirectionY(this.getBallDirectionY() + Math.signum(this.getBallDirectionY() * 0.1 + Math.abs(this.getBallDirectionX())));
		}
		else if(bounce.getDirection() == BounceDirection.FLIP_Y){
			if(Math.signum(this.getBallDirectionY() ) > 0) {
				this.setCurrentBallY(yBouncePosition - distanceY);
			}
			else {
				this.setCurrentBallY(yBouncePosition + distanceY);
			}
			this.setCurrentBallX(xBallFuture);
			this.setBallDirectionY(this.getBallDirectionY() * -1);
			this.setBallDirectionX(this.getBallDirectionX() + Math.signum(this.getBallDirectionX() * 0.1 + Math.abs(this.getBallDirectionY())));
		}
   }
}

  /**
   * Actions
   */
  // line 74 "../../../../../Block223States.ump"
   private void doSetup(){
    // TODO implement
  }

  // line 78 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    // TODO implement
	   this.bounceBall();
  }

  // line 82 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    this.setLives(lives-1);
		this.resetCurrentBallX();
		this.resetCurrentBallY();
		this.resetBallDirectionX();
		this.resetBallDirectionY();
		this.resetCurrentPaddleX();
  }

  // line 91 "../../../../../Block223States.ump"
   private void doHitBlock(){
    // TODO implement
  }

  // line 95 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    // TODO implement
  }

  // line 99 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    // TODO implement
    PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
	double x = currentPlayedGame.getCurrentBallX();
	double y = currentPlayedGame.getCurrentBallY();
	double dx = currentPlayedGame.getBallDirectionX();
	double dy = currentPlayedGame.getBallDirectionY();
	currentPlayedGame.setCurrentBallX(x + dx);
	currentPlayedGame.setCurrentBallY(y + dy);
  }

  // line 110 "../../../../../Block223States.ump"
   private void doGameOver(){
    Block223 block223 = this.getBlock223();
    Player p = this.getPlayer();
    if (p != null){
    	game = this.getGame();
    	HallOfFameEntry hof = new HallOfFameEntry(score, playername, p, game, block223);
    	game.setMostRecentEntry(hof);
    }
    this.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 107 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}