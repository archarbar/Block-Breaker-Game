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
   private BouncePoint calculateBouncePointWall(){
    BouncePoint bouncePoint = null;
	   Point2D.Double bouncePosition;
	   double ballRadius = 5.0;
	   double wallHeight = Game.PLAY_AREA_SIDE;
	   double xBall = this.currentBallX;
	   double yBall = this.currentBallY;
	   double xBallFuture = this.currentBallX + (this.ballDirectionX);
	   double yBallFuture = this.currentBallY + (this.ballDirectionY);
	   Line2D.Double leftWall = new Line2D.Double(ballRadius, ballRadius, ballRadius, wallHeight - ballRadius);
	   Line2D.Double topWall = new Line2D.Double(ballRadius, ballRadius, wallHeight - ballRadius, ballRadius);
	   Line2D.Double rightWall = new Line2D.Double(Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER / 2, Ball.BALL_DIAMETER / 2, Game.PLAY_AREA_SIDE - Ball.BALL_DIAMETER / 2, Game.PLAY_AREA_SIDE);
	   Line2D.Double lineBallPath = new Line2D.Double(xBall, yBall, xBallFuture, yBallFuture);
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
			   //System.out.println(bouncePoint);
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

  // line 93 "Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    BouncePoint bouncePoint = null;
	Point2D.Double bouncePosition;
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
	   Ellipse2D.Double ellipseE = new Ellipse2D.Double(paddleX - ballRadius, paddleY - ballRadius, ballRadius*2 , ballRadius*2);
	   Ellipse2D.Double ellipseF = new Ellipse2D.Double(paddleX - ballRadius + paddleLength, paddleY - ballRadius, ballRadius*2 , ballRadius*2);

	   Line2D.Double lineA = new Line2D.Double(getCurrentPaddleX() + getCurrentPaddleLength(), getCurrentPaddleY() - Ball.BALL_DIAMETER / 2,getCurrentPaddleX(), getCurrentPaddleY() - Ball.BALL_DIAMETER / 2);
	   Line2D.Double lineB = new Line2D.Double(getCurrentPaddleX() - Ball.BALL_DIAMETER / 2, getCurrentPaddleY(),getCurrentPaddleX() - Ball.BALL_DIAMETER / 2, getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
	   Line2D.Double lineC = new Line2D.Double(getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER / 2, getCurrentPaddleY(),getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER / 2,getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
	   Line2D.Double lineBallPath = new Line2D.Double(xBall, yBall, xBallFuture, yBallFuture);
	   if (!intersect) {

	   }
		   
	   if(lineA.intersectsLine(lineBallPath)) {
		   bouncePosition = getLineIntersection(lineBallPath, lineA);
		   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_Y);
	   }
	   if(lineB.intersectsLine(lineBallPath)){
		   bouncePosition = getLineIntersection(lineBallPath, lineB);
		   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
	   }
	   if(lineC.intersectsLine(lineBallPath)) {
		   bouncePosition = getLineIntersection(lineBallPath, lineC);
		   bouncePoint = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
	   }
	   if(ellipseE.contains(xBallFuture, yBallFuture)) {
		   if(xBall < xBallFuture) {

			   bouncePoint = getEllipseIntersection(ellipseE.getCenterX(), ellipseE.getCenterY(), BounceDirection.FLIP_X);
			   
		   }
		   else {
			   bouncePoint = getEllipseIntersection(ellipseE.getCenterX(), ellipseE.getCenterY(), BounceDirection.FLIP_Y);

		   }
	   }
	   if(ellipseF.contains(xBallFuture, yBallFuture)) {
		   if(xBall < xBallFuture) {
			   bouncePoint = new BouncePoint(xBall, yBall, BounceDirection.FLIP_Y);
		   }
		   else {
			   bouncePoint = new BouncePoint(xBall, yBall, BounceDirection.FLIP_X);
		   }
	   }

	   return bouncePoint;
  }

  // line 153 "Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment pblock){
    BouncePoint bouncePointBlock = null;
	   Point2D bouncePosition;
	   double ballRadius = Ball.BALL_DIAMETER/2;
	   double blockHeight = Block.SIZE;
	   double blockLength = Block.SIZE;
	   double blockX = pblock.getX();
	   double blockY = pblock.getY();
	   double xBall = this.currentBallX;
	   double yBall = this.currentBallY;
	   double xBallFuture = this.currentBallX + (this.ballDirectionX);
	   double yBallFuture = this.currentBallY + (this.ballDirectionY);
	   Rectangle2D.Double blockRect = new Rectangle2D.Double(blockX - ballRadius, blockY - ballRadius, blockLength + Ball.BALL_DIAMETER, blockHeight + Ball.BALL_DIAMETER);
	   boolean intersect = blockRect.intersectsLine(xBall, yBall, xBallFuture, yBallFuture);
	   ArrayList <BouncePoint> intersectionPoints = new ArrayList <BouncePoint>();
	   ArrayList <Double> intersectionDistances = new ArrayList <Double>();
		   Ellipse2D.Double ellipseTopLeftE = new Ellipse2D.Double(blockX - ballRadius, blockY - ballRadius, ballRadius*2 , ballRadius*2);
		   Ellipse2D.Double ellipseBottomLeftG = new Ellipse2D.Double(blockX - ballRadius, blockY - ballRadius + blockLength, ballRadius*2 , ballRadius*2);
		   Ellipse2D.Double ellipseBottomRightH = new Ellipse2D.Double(blockX - ballRadius + blockLength, blockY - ballRadius + blockLength, ballRadius*2 , ballRadius*2);
		   Ellipse2D.Double ellipseTopRightF = new Ellipse2D.Double(blockX - ballRadius + blockLength, blockY - ballRadius, ballRadius*2 , ballRadius*2);
		   Line2D.Double lineTop = new Line2D.Double(blockX, blockY - ballRadius, blockX + blockLength, blockY - ballRadius);
		   Line2D.Double lineLeft = new Line2D.Double(blockX - ballRadius, blockY, blockX - ballRadius, blockY + blockHeight);
		   Line2D.Double lineBottom = new Line2D.Double(blockX, blockY + blockHeight + ballRadius, blockX + blockLength, blockY + blockHeight + ballRadius);
		   Line2D.Double lineRight = new Line2D.Double(blockX + blockLength + ballRadius, blockY, blockX + blockLength + ballRadius, blockY + blockHeight);
		   Line2D.Double lineBallPath = new Line2D.Double(xBall, yBall, xBallFuture, yBallFuture);
		   if(lineTop.intersectsLine(lineBallPath)) {
			   bouncePosition = getLineIntersection(lineBallPath, lineTop);
			   //System.out.println(bouncePosition);
			   bouncePointBlock = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_Y);
			   bouncePointBlock.setHitBlock(pblock);
			   intersectionPoints.add(bouncePointBlock);
		   }
		   if(lineLeft.intersectsLine(lineBallPath)){
			   bouncePosition = getLineIntersection(lineBallPath, lineLeft);
			   bouncePointBlock = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
			   bouncePointBlock.setHitBlock(pblock);
			   intersectionPoints.add(bouncePointBlock);
		   }
		   if(lineBottom.intersectsLine(lineBallPath)){
			   bouncePosition = getLineIntersection(lineBallPath, lineBottom);
			   bouncePointBlock = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_Y);
			   bouncePointBlock.setHitBlock(pblock);
			   intersectionPoints.add(bouncePointBlock);
		   }
		   if(lineRight.intersectsLine(lineBallPath)){
			   bouncePosition = getLineIntersection(lineBallPath, lineRight);
			   bouncePointBlock = new BouncePoint(bouncePosition.getX(), bouncePosition.getY(), BounceDirection.FLIP_X);
			   bouncePointBlock.setHitBlock(pblock);
			   intersectionPoints.add(bouncePointBlock);
		   }
		   if(ellipseTopLeftE.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePointBlock = getEllipseIntersection(ellipseTopLeftE.getCenterX(), ellipseTopLeftE.getCenterY(), BounceDirection.FLIP_X);
				   bouncePointBlock.setHitBlock(pblock);
				   intersectionPoints.add(bouncePointBlock);
				   return bouncePointBlock;
			   }
			   else if(xBall > xBallFuture){
				   bouncePointBlock = getEllipseIntersection(ellipseTopLeftE.getCenterX(), ellipseTopLeftE.getCenterY(), BounceDirection.FLIP_Y);
				   bouncePointBlock.setHitBlock(pblock);
				   intersectionPoints.add(bouncePointBlock);
				   return bouncePointBlock;
			   }
		   }
		   if(ellipseBottomLeftG.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePointBlock = getEllipseIntersection(ellipseBottomLeftG.getCenterX(), ellipseBottomLeftG.getCenterY(), BounceDirection.FLIP_X);
				   bouncePointBlock.setHitBlock(pblock);
				   intersectionPoints.add(bouncePointBlock);
				   return bouncePointBlock;
			   }
			   else if(xBall > xBallFuture){
				   bouncePointBlock = getEllipseIntersection(ellipseBottomLeftG.getCenterX(), ellipseBottomLeftG.getCenterY(), BounceDirection.FLIP_Y);
				   bouncePointBlock.setHitBlock(pblock);
				   intersectionPoints.add(bouncePointBlock);
				   return bouncePointBlock;
			   }
		   }
		   if(ellipseBottomRightH.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePointBlock = getEllipseIntersection(ellipseBottomRightH.getCenterX(), ellipseBottomRightH.getCenterY(), BounceDirection.FLIP_Y);
				   bouncePointBlock.setHitBlock(pblock);
				   intersectionPoints.add(bouncePointBlock);
				   return bouncePointBlock;
			   }
			   else if(xBall > xBallFuture){
				   bouncePointBlock = getEllipseIntersection(ellipseBottomRightH.getCenterX(), ellipseBottomRightH.getCenterY(), BounceDirection.FLIP_X);
				   bouncePointBlock.setHitBlock(pblock);
				   intersectionPoints.add(bouncePointBlock);
				   return bouncePointBlock;
		
			   }
		   }
		   if(ellipseTopRightF.contains(xBallFuture, yBallFuture)) {
			   if(xBall < xBallFuture) {
				   bouncePointBlock = getEllipseIntersection(ellipseTopRightF.getCenterX(), ellipseTopRightF.getCenterY(), BounceDirection.FLIP_Y);
				   bouncePointBlock.setHitBlock(pblock);
				   intersectionPoints.add(bouncePointBlock);
				   return bouncePointBlock;
			   }
			   else if(xBall > xBallFuture){
				   bouncePointBlock = getEllipseIntersection(ellipseTopRightF.getCenterX(), ellipseTopRightF.getCenterY(), BounceDirection.FLIP_X);
				   bouncePointBlock.setHitBlock(pblock);
				   intersectionPoints.add(bouncePointBlock);
				   return bouncePointBlock;
			   }
		   }
		   for(BouncePoint closestPoint: intersectionPoints) {
			   double squareX = Math.pow(java.lang.Math.abs(xBall - (blockX + blockLength)), 2);
			   double squareY = Math.pow(java.lang.Math.abs(yBall - (blockY + blockLength)), 2);
			   double distanceIntersectionPointToBall = java.lang.Math.sqrt(squareX + squareY);
			   intersectionDistances.add(distanceIntersectionPointToBall);
		   }
		   if (!intersectionPoints.isEmpty()) {
			   int indexClosestPoint = intersectionDistances.indexOf(Collections.min(intersectionDistances));
			   BouncePoint closestPoint = intersectionPoints.get(indexClosestPoint);
			   return closestPoint;
		   }
		   else {
			   return null;
		   }
  }

  // line 277 "Block223States.ump"
   private void bounceBall(){
    double xBallDirection = this.getBallDirectionX();
	    double yBallDirection = this.getBallDirectionY();
	    double xNewDistance = xBallDirection - (bounce.getX() - this.getCurrentBallX());
	    double yNewDistance = yBallDirection - (bounce.getY() - this.getCurrentBallY());
	    double signX = Math.signum(xBallDirection);
	    double signY = Math.signum(yBallDirection);

	    if (bounce.getDirection().equals(BounceDirection.FLIP_Y)) {
	    	xBallDirection = (xBallDirection == 0) ? 0.1 * Math.abs(yBallDirection) : xBallDirection + signX * 0.1 * Math.abs(yBallDirection);
	    	yBallDirection *= -1.0;
	      if (yNewDistance == 0 || (getBounce().getX() != getCurrentBallX() && getBounce().getY() != getCurrentBallY())) {
	        setCurrentBallX(bounce.getX());
	        setCurrentBallY(bounce.getY());
	        if (getBounce().getHitBlock() != null) {
	          setBallDirectionX(xBallDirection);
	          setBallDirectionY(yBallDirection);
	        }
	        return;
	      } else {
	        setCurrentBallX(bounce.getX() + yNewDistance / getBallDirectionY() * xBallDirection);
	        setCurrentBallY(bounce.getY() + yNewDistance / getBallDirectionY() * yBallDirection);
	      }
	    } else if (bounce.getDirection().equals(BounceDirection.FLIP_X)) {
	     
	    	yBallDirection = (yBallDirection == 0) ? 0.1 * Math.abs(xBallDirection) : yBallDirection + signY * 0.1 * Math.abs(xBallDirection);
	      xBallDirection *= -1.0;
	      if (xNewDistance == 0 || (getBounce().getX() != getCurrentBallX() && getBounce().getY() != getCurrentBallY())) {
	        if (getBounce().getHitBlock() != null) {
	        		setBallDirectionX(xBallDirection);
	        		setBallDirectionY(yBallDirection);
	        }
	        	setCurrentBallX(bounce.getX());
	        setCurrentBallY(bounce.getY());
	        return;
	        
	      } else {
	        double nextX = (bounce.getX() + xNewDistance / getBallDirectionX() * xBallDirection);
	        double nextY = bounce.getY() + xNewDistance / getBallDirectionX() * yBallDirection;

	        if(nextX < 5) {
	          nextX = 5; 
	        }
	        
	        else if(nextX > 385){
	          nextX = 385;
	        }

	        if(nextY < 5) {
	          nextY = 5; 
	        }
	        
	        else if(nextY > 385){
	          nextY = 385;
	        }

	        setCurrentBallX(nextX);
	        setCurrentBallY(nextY);
	      }
	    } else {
	    	xBallDirection *= -1.0;
	    	yBallDirection *= -1.0;
	      if (xNewDistance == 0 && yNewDistance == 0) {
	        if (getBounce().getHitBlock() != null) {
	          setBallDirectionX(xBallDirection);
	          setBallDirectionY(yBallDirection);
	        }
	        setCurrentBallX(bounce.getX());
	        setCurrentBallY(bounce.getY());
	        return;
	      } else {
	        setCurrentBallX(bounce.getX() + (xBallDirection * (xNewDistance / getBallDirectionX())));
	        setCurrentBallY(bounce.getY() + (yBallDirection * (yNewDistance / getBallDirectionY())));
	      }
	    }

	    setBallDirectionX(xBallDirection);
	    setBallDirectionY(yBallDirection);
  }

  // line 357 "Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    
    if (lives == 1){
    	outOfBounds = this.isBallOutOfBounds();
    }	
    return outOfBounds;
  }

  // line 366 "Block223States.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = this.isBallOutOfBounds();
    return outOfBounds;
  }

  // line 371 "Block223States.ump"
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

  // line 387 "Block223States.ump"
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

  // line 399 "Block223States.ump"
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

  // line 415 "Block223States.ump"
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
  // line 426 "Block223States.ump"
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

  // line 479 "Block223States.ump"
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

  // line 496 "Block223States.ump"
   private void doHitPaddleOrWall(){
    this.bounceBall();
  }

  // line 500 "Block223States.ump"
   private void doOutOfBounds(){
    this.setLives(lives-1);
		this.resetCurrentBallX();
		this.resetCurrentBallY();
		this.resetBallDirectionX();
		this.resetBallDirectionY();
		this.resetCurrentPaddleX();
  }

  // line 509 "Block223States.ump"
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

  // line 520 "Block223States.ump"
   private void doHitBlockNextLevel(){
    this.doHitBlock();
    int level = this.getCurrentLevel();
    this.setCurrentPaddleLength(this.getGame().getPaddle().getMaxPaddleLength()-( (this.getGame().getPaddle().getMaxPaddleLength()
    							-this.getGame().getPaddle().getMinPaddleLength()) /( this.getGame().numberOfLevels() ) )*(this.getCurrentLevel() + 1));
     this.setWaitTime(INITIAL_WAIT_TIME*Math.pow(this.getGame().getBall().getBallSpeedIncreaseFactor(),(double)(this.getCurrentLevel())));
    this.setCurrentLevel(level+1);
  }

  // line 529 "Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
	double x = currentPlayedGame.getCurrentBallX();
	double y = currentPlayedGame.getCurrentBallY();
	double dx = currentPlayedGame.getBallDirectionX();
	double dy = currentPlayedGame.getBallDirectionY();
	currentPlayedGame.setCurrentBallX(x + dx);
	currentPlayedGame.setCurrentBallY(y + dy);
  }

  // line 539 "Block223States.ump"
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