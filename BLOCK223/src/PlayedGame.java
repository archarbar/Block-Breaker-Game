/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.awt.geom.*;
import java.awt.geom.Ellipse2D;
import math.geom2d.conic.*;
import math.geom2d.*;
import java.awt.Point;
import ca.mcgill.ecse223.block.model.BouncePoint.BounceDirection;
import ca.mcgill.ecse223.block.application.Block223Application;

// line 1 "Block223States.ump"
public class PlayedGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame()
  {
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

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
        // line 20 "Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 21 "Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 22 "Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 23 "Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 24 "Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 25 "Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 26 "Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 27 "Block223States.ump"
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
        // line 15 "Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 33 "Block223States.ump"
        doGameOver();
        break;
    }
  }

  public void delete()
  {}


  /**
   * Guards
   */
  // line 41 "Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
	if(bp != null) {
		setBounce(bp);
		return true;
	}
    return false;
  }

  // line 50 "Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
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

		} else {
			return null;
		}
  }

  // line 111 "Block223States.ump"
   private void bounceBall(){
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

  // line 160 "Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    
    if (lives == 1){
    	outOfBounds = this.isBallOutOfBounds();
    }	
    return outOfBounds;
  }

  // line 169 "Block223States.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = this.isBallOutOfBounds();
    return outOfBounds;
  }

  // line 174 "Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    Game game = this.getGame();
    	int nrLevels = game.numberOfLevels();
    	this.setBounce(null);
    	if(nrLevels==currentLevel){
    		int nrBlocks = this.numberOfBlocks();
    		if(nrBlocks ==1){
    			PlayedBlockAssignment block = this.getBlock(0);
    			BouncePoint bp = this.calculateBouncePointBlock(block);
    			this.setBounce(bp);
    			return bp != null;
    		}
    	}
    return false;
  }

  // line 190 "Block223States.ump"
   private boolean hitLastBlock(){
    int nrBlocks = this.numberOfBlocks();
    	this.setBounce(null);
    	if(nrBlocks == 1){
    		PlayedBlockAssignment block = this.getBlock(0);
    		BouncePoint bp = this.calculateBouncePointBlock(block);
    		this.setBounce(bp);
    		return bp != null;	
    	}
    return false;
  }

  // line 202 "Block223States.ump"
   private boolean hitBlock(){
    int nrBlocks = this.numberOfBlocks();
    	this.setBounce(null);
    	for(int i=0; i<nrBlocks; i++){
    		PlayedBlockAssignment block = getBlock(i);
    		BouncePoint bp = this.calculateBouncePointBlock(block);
    		BouncePoint bounce = this.getBounce();
    		boolean closer = this.isCloser(bp, bounce);
    		
    		if(closer){
    			this.setBounce(bp);
    		}   		
    	}
    	return this.getBounce() != null;
  }

  // line 218 "Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
	if(bp != null) {
		setBounce(bp);
		return true;
	}
	return false;
  }


  /**
   * Actions
   */
  // line 229 "Block223States.ump"
   private void doSetup(){
    this.resetCurrentBallX();
	   this.resetCurrentBallY();
	   this.resetBallDirectionX();
	   this.resetBallDirectionY();	   
	   this.resetCurrentPaddleX();
	   Game game = this.getGame();
	   Level level = game.getLevel(currentLevel - 1);
	   List<BlockAssignment> assignments = level.getBlockAssignments();
	   
	   for(BlockAssignment a : assignments) {
		   PlayedBlockAssignment pblock = new PlayedBlockAssignment(Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (a.getGridHorizontalPosition() - 1),Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (a.getGridVerticalPosition() - 1), a.getBlock(),this);
	   }
	   int numberOfBlocks = assignments.size();
	   int maxHorizontal = (1+(Game.PLAY_AREA_SIDE-2*Game.WALL_PADDING-Block.SIZE)/(Block.SIZE+Game.COLUMNS_PADDING));
	   int maxVertical = (1+(Game.PLAY_AREA_SIDE-Paddle.VERTICAL_DISTANCE-Game.WALL_PADDING-Paddle.PADDLE_WIDTH-Ball.BALL_DIAMETER-Block.SIZE)/(Block.SIZE+Game.ROW_PADDING));
	   int x;
	   int y;
	   
	   while(numberOfBlocks < game.getNrBlocksPerLevel()) {
		   Random rand = new Random();
		   x = rand.nextInt(maxHorizontal);
		   y = rand.nextInt(maxVertical);
		   PlayedBlockAssignment foundAssignment = this.findPlayedBlockAssignment(x , y);
		   while(foundAssignment != null) {
			   if(y < maxVertical) {
				   if(x <= maxHorizontal) x=x+1;
				   if(x > maxHorizontal) {
					   x = 1;
					   y=y+1;
				   }
			   }
			   
			   else if(y >= maxVertical) {
				   if(x <= maxHorizontal) x=x+1;
				   if(x > maxHorizontal) {
					   x = 1;
					   y = 1;
				   }
			   }  
			   foundAssignment = this.findPlayedBlockAssignment(x , y);
		   }
		   x = Game.WALL_PADDING + (x-1)*(Game.COLUMNS_PADDING+Block.SIZE);
		   y = Game.WALL_PADDING + (y-1)*(Game.ROW_PADDING+Block.SIZE);
		   
		   new PlayedBlockAssignment(x,y,game.getRandomBlock(),this);
			numberOfBlocks++;
	   }
  }

  // line 279 "Block223States.ump"
   private void doHitPaddleOrWall(){
    this.bounceBall();
  }

  // line 283 "Block223States.ump"
   private void doOutOfBounds(){
    this.setLives(lives-1);
		this.resetCurrentBallX();
		this.resetCurrentBallY();
		this.resetBallDirectionX();
		this.resetBallDirectionY();
		this.resetCurrentPaddleX();
  }

  // line 292 "Block223States.ump"
   private void doHitBlock(){
    int currentscore = this.getScore();
    BouncePoint bouncepoint = this.getBounce();
    PlayedBlockAssignment playedblock = bouncepoint.getHitBlock();
    Block block = playedblock.getBlock();
    int blockscore = block.getPoints();
    this.setScore(currentscore + blockscore);
    playedblock.delete();
    this.bounceBall();
  }

  // line 303 "Block223States.ump"
   private void doHitBlockNextLevel(){
    this.doHitBlock();
    int level = this.getCurrentLevel();
    this.setCurrentPaddleLength(this.getGame().getPaddle().getMaxPaddleLength()-(this.getGame().getPaddle().getMaxPaddleLength()
    							-this.getGame().getPaddle().getMinPaddleLength())/(this.getGame().numberOfLevels())*(this.getCurrentLevel()));
    this.setWaitTime(INITIAL_WAIT_TIME*Math.pow(this.getGame().getBall().getBallSpeedIncreaseFactor(),(double)(this.getCurrentLevel())));
    this.setCurrentLevel(level+1);
  }

  // line 312 "Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
	double x = currentPlayedGame.getCurrentBallX();
	double y = currentPlayedGame.getCurrentBallY();
	double dx = currentPlayedGame.getBallDirectionX();
	double dy = currentPlayedGame.getBallDirectionY();
	currentPlayedGame.setCurrentBallX(x + dx);
	currentPlayedGame.setCurrentBallY(y + dy);
  }

  // line 322 "Block223States.ump"
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

}