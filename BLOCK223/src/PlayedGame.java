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
  // line 40 "Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
	if(bp != null) {
		setBounce(bp);
		return true;
	}
    return false;
  }

  // line 49 "Block223States.ump"
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
	   double xBallFuture = this.currentBallX + (this.ballDirectionX);
	   double yBallFuture = this.currentBallY + (this.ballDirectionY);
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

  // line 108 "Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
    BouncePoint bouncePointBlock = null;
	   Point bouncePosition;
	   double ballRadius = Ball.BALL_DIAMETER/2;
	   double blockHeight = Block.SIZE;
	   double blockLength = Block.SIZE;
	   double blockX = this.currentBlockLength;
	   double blockY = this.currentBlockHeight;
	   double xBall = this.currentBallX;
	   double yBall = this.currentBallY;
	   double xBallFuture = this.currentBallX + (this.ballDirectionX);
	   double yBallFuture = this.currentBallY + (this.ballDirectionY);
	   Rectangle2D.Double blockRect = new Rectangle2D.Double(blockX, blockY, blockLength, blockHeight);
	   boolean intersect = blockRect.intersectsLine(xBall, yBall, xBallFuture, yBallFuture);
	   if(!intersect) {
		   return bouncePointBlock;
	   }
	   else{
		   Ellipse2D.Double ellipseTopLeftE = new Ellipse2D.Double(ballRadius, ballRadius, blockX - ballRadius, blockY - ballRadius);
		   Ellipse2D.Double ellipseBottomLeftG = new Ellipse2D.Double(ballRadius, ballRadius, blockX - ballRadius, blockY + blockHeight + ballRadius);
		   Ellipse2D.Double ellipseBottomRightH = new Ellipse2D.Double(ballRadius, ballRadius, blockX + blockLength + ballRadius, blockY + blockHeight + ballRadius);
		   Ellipse2D.Double ellipseTopRightF = new Ellipse2D.Double(ballRadius, ballRadius, blockX +blockLength + ballRadius, blockY - ballRadius);
		   Line2D.Double lineTop = new Line2D.Double(blockX - ballRadius, blockY - ballRadius, blockX + blockLength + ballRadius, blockY - ballRadius);
		   Line2D.Double lineLeft = new Line2D.Double(blockX - ballRadius, blockY - ballRadius, blockX - ballRadius, blockY + blockHeight + ballRadius);
		   Line2D.Double lineBottom = new Line2D.Double(blockX - ballRadius, blockY - blockHeight - ballRadius, blockX + blockLength + ballRadius, blockY - blockHeight - ballRadius);
		   Line2D.Double lineRight = new Line2D.Double(blockX + blockLength + ballRadius, blockY - ballRadius, blockX + blockLength + ballRadius, blockY + blockHeight + ballRadius);
		   Line2D.Double lineBallPath = new Line2D.Double(xBall, yBall, xBallFuture, yBallFuture);
		   if(lineTop.intersectsLine(lineBallPath)) {
			   bouncePosition = getLineIntersection(lineBallPath, lineTop);
			   bouncePointBlock = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
		   }
		   else if(lineLeft.intersectsLine(lineBallPath)){
			   bouncePosition = getLineIntersection(lineBallPath, lineLeft);
			   bouncePointBlock = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
		   }
		   else if(lineBottom.intersectsLine(lineBallPath)){
			   bouncePosition = getLineIntersection(lineBallPath, lineBottom);
			   bouncePointBlock = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
		   }
		   else if(lineRight.intersectsLine(lineBallPath)){
			   bouncePosition = getLineIntersection(lineBallPath, lineRight);
			   bouncePointBlock = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
		   }
		   else if(ellipseTopLeftE.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePointBlock = new BouncePoint(xBall, yBall, BounceDirection.FLIP_X);
			   }
			   else {
				   bouncePointBlock = new BouncePoint(xBall, yBall, BounceDirection.FLIP_Y);
			   }
		   }
		   else if(ellipseBottomLeftG.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePointBlock = new BouncePoint(xBall, yBall, BounceDirection.FLIP_X);
			   }
			   else {
				   bouncePointBlock = new BouncePoint(xBall, yBall, BounceDirection.FLIP_Y);
			   }
		   }
		   else if(ellipseBottomRightH.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePointBlock = new BouncePoint(xBall, yBall, BounceDirection.FLIP_Y);
			   }
			   else {
				   bouncePointBlock = new BouncePoint(xBall, yBall, BounceDirection.FLIP_X);
			   }
		   }
		   else if(ellipseTopRightF.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePointBlock = new BouncePoint(xBall, yBall, BounceDirection.FLIP_Y);
			   }
			   else {
				   bouncePointBlock = new BouncePoint(xBall, yBall, BounceDirection.FLIP_X);
			   }
		   }
	   }
	   return bouncePointBlock;
  }

  // line 189 "Block223States.ump"
   private void bounceBall(){
    double x = this.getBallDirectionX();
	    double y = this.getBallDirectionY();
	    double outgoingDistanceX = x - (bounce.getX() - this.getCurrentBallX());
	    double outgoingDistanceY = y - (bounce.getY() - this.getCurrentBallY());
	    double signX = Math.signum(x);
	    double signY = Math.signum(y);

	    if (bounce.getDirection().equals(BounceDirection.FLIP_Y)) {
	      x = (x == 0) ? 0.1 * Math.abs(y) : x + signX * 0.1 * Math.abs(y);
	      y *= -1.0;
	      if (outgoingDistanceY == 0 || (getBounce().getX() != getCurrentBallX() && getBounce().getY() != getCurrentBallY())) {
	        setCurrentBallX(bounce.getX());
	        setCurrentBallY(bounce.getY());
	        if (getBounce().getHitBlock() != null) {
	          setBallDirectionX(x);
	          setBallDirectionY(y);
	        }
	        return;
	      } else {
	        setCurrentBallX(bounce.getX() + outgoingDistanceY / getBallDirectionY() * x);
	        setCurrentBallY(bounce.getY() + outgoingDistanceY / getBallDirectionY() * y);
	      }
	    } else if (bounce.getDirection().equals(BounceDirection.FLIP_X)) {
	      y = (y == 0) ? 0.1 * Math.abs(x) : y + signY * 0.1 * Math.abs(x);
	      x *= -1.0;
	      if (outgoingDistanceX == 0 || (getBounce().getX() != getCurrentBallX() && getBounce().getY() != getCurrentBallY())) {
	        if (getBounce().getHitBlock() != null) {
	          setBallDirectionX(x);
	          setBallDirectionY(y);
	        }
	        setCurrentBallX(bounce.getX());
	        setCurrentBallY(bounce.getY());
	        return;
	      } else {
	        double nextX = (bounce.getX() + outgoingDistanceX / getBallDirectionX() * x);
	        double nextY = bounce.getY() + outgoingDistanceX / getBallDirectionX() * y;

	        if(nextX < 5) {
	          nextX = 5; 
	        }else if(nextX > 385){
	          nextX = 385;
	        }

	        if(nextY < 5) {
	          nextY = 5; 
	        }else if(nextY > 385){
	          nextY = 385;
	        }

	        setCurrentBallX(nextX);
	        setCurrentBallY(nextY);
	      }
	    } else {
	      x *= -1.0;
	      y *= -1.0;
	      if (outgoingDistanceX == 0 && outgoingDistanceY == 0) {
	        if (getBounce().getHitBlock() != null) {
	          setBallDirectionX(x);
	          setBallDirectionY(y);
	        }
	        setCurrentBallX(bounce.getX());
	        setCurrentBallY(bounce.getY());
	        return;
	      } else {
	        setCurrentBallX(bounce.getX() + outgoingDistanceX / getBallDirectionX() * x);
	        setCurrentBallY(bounce.getY() + outgoingDistanceY / getBallDirectionY() * y);
	      }
	    }

	    if (waitTime != 0) {
	      double threshold = waitTime / 10.0;
	      if (Math.abs(x) >= threshold || Math.abs(y) >= threshold) {
	        x /= threshold;
	        y /= threshold;
	      }
	    }
	    setBallDirectionX(x);
	    setBallDirectionY(y);
  }

  // line 270 "Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    
    if (lives == 1){
    	outOfBounds = this.isBallOutOfBounds();
    }	
    return outOfBounds;
  }

  // line 279 "Block223States.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = this.isBallOutOfBounds();
    return outOfBounds;
  }

  // line 284 "Block223States.ump"
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

  // line 300 "Block223States.ump"
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

  // line 312 "Block223States.ump"
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

  // line 328 "Block223States.ump"
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
  // line 339 "Block223States.ump"
   private void doSetup(){
    // TODO implement
	   this.resetCurrentBallX();
	   this.resetCurrentBallY();
	   this.resetBallDirectionX();
	   this.resetBallDirectionY();
	   this.resetCurrentPaddleX();
	   Game game = this.getGame();
	   Level level = game.getLevel(currentLevel - 1);
	   List<BlockAssignment> assignments = level.getBlockAssignments();
	   for(BlockAssignment a : assignments) {
			 PlayedBlockAssignment pblock = new PlayedBlockAssignment(
					 Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (a.getGridHorizontalPosition() - 1),
					 Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (a.getGridVerticalPosition() - 1), a.getBlock(),this);
	   }
	   int numberOfBlocks = assignments.size();
	   int maxHorizontal = (Game.PLAY_AREA_SIDE-2*Game.WALL_PADDING-Block.SIZE)/(Block.SIZE+Game.COLUMNS_PADDING);
	   int maxVertical = (Game.PLAY_AREA_SIDE-Paddle.VERTICAL_DISTANCE-Game.WALL_PADDING-Paddle.PADDLE_WIDTH-Ball.BALL_DIAMETER-Block.SIZE)/(Block.SIZE+Game.ROW_PADDING);
	   int x;
	   int y;
	   while(numberOfBlocks < game.getNrBlocksPerLevel() - 1) {
		   Random rand = new Random();
		   x = rand.nextInt(maxHorizontal);
		   y = rand.nextInt(maxVertical);
		   PlayedBlockAssignment foundAssignment = this.findPlayedBlockAssignment(x , y);
		   while(foundAssignment != null) {
			   if(y < maxVertical) {
				   if(x < maxHorizontal) {
					   x=x+1;
				   }
				   if(x >= maxHorizontal) {
					   x = 1;
					   y=y+1;
				   }
			   }
			   else if(y >= maxVertical) {
				   if(x < maxHorizontal) {
					   x=x+1;
				   }
				   if(x >= maxHorizontal) {
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

  // line 392 "Block223States.ump"
   private PlayedBlockAssignment findPlayedBlockAssignment(int x, int y){
    Game game = this.getGame();
	  Level level = game.getLevel(currentLevel - 1);
	  List<BlockAssignment> assignments = level.getBlockAssignments();
	  for(BlockAssignment a : assignments) {
		 PlayedBlockAssignment pblock = new PlayedBlockAssignment(
				 Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (a.getGridHorizontalPosition() - 1),
				 Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (a.getGridVerticalPosition() - 1), a.getBlock(),this);
		 int h = pblock.getX();
		 int v = pblock.getY();
		 if(h == x && v == y) {
			 return pblock;
		 }
	  }
		 return null;
  }

  // line 409 "Block223States.ump"
   private void doHitPaddleOrWall(){
    this.bounceBall();
  }

  // line 413 "Block223States.ump"
   private void doOutOfBounds(){
    this.setLives(lives-1);
		this.resetCurrentBallX();
		this.resetCurrentBallY();
		this.resetBallDirectionX();
		this.resetBallDirectionY();
		this.resetCurrentPaddleX();
  }

  // line 422 "Block223States.ump"
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

  // line 433 "Block223States.ump"
   private void doHitBlockNextLevel(){
    this.doHitBlock();
    int level = this.getCurrentLevel();
    this.setCurrentPaddleLength(this.getGame().getPaddle().getMaxPaddleLength()-(this.getGame().getPaddle().getMaxPaddleLength()
    							-this.getGame().getPaddle().getMinPaddleLength())/(this.getGame().numberOfLevels())*(this.getCurrentLevel()));
    this.setWaitTime(INITIAL_WAIT_TIME*Math.pow(this.getGame().getBall().getBallSpeedIncreaseFactor(),(double)(this.getCurrentLevel())));
    this.setCurrentLevel(level+1);
  }

  // line 442 "Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
	double x = currentPlayedGame.getCurrentBallX();
	double y = currentPlayedGame.getCurrentBallY();
	double dx = currentPlayedGame.getBallDirectionX();
	double dy = currentPlayedGame.getBallDirectionY();
	currentPlayedGame.setCurrentBallX(x + dx);
	currentPlayedGame.setCurrentBallY(y + dy);
  }

  // line 452 "Block223States.ump"
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
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 105 "Block223States.ump"
  private double currentBlockLength ;
// line 106 "Block223States.ump"
  private double currentBlockHeight ;

  
}