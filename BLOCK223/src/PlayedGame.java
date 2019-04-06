/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.awt.geom.*;
import math.geom2d.conic.*;
import java.awt.Point;
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
        // line 17 "Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 18 "Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 19 "Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 20 "Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 21 "Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 22 "Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 23 "Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 24 "Block223States.ump"
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
        // line 12 "Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 30 "Block223States.ump"
        doGameOver();
        break;
    }
  }

  public void delete()
  {}


  /**
   * Guards
   */
  // line 37 "Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
	if(bp != null) {
		setBounce(bp);
		return true;
	}
    return false;
  }

  // line 46 "Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    Rectangle2D paddleRect = new Rectangle2D.Double();
		Line2D l = new Line2D.Double();
		ArrayList<BouncePoint> intersect = new ArrayList<>();
		int counter = 0;
		l.setLine(getCurrentBallX(), getCurrentBallY(), getCurrentBallX() + ballDirectionX,
				getCurrentBallY() + ballDirectionY);
		math.geom2d.line.Line2D l1 = new math.geom2d.line.Line2D(getCurrentBallX(), getCurrentBallY(),
				getCurrentBallX() + ballDirectionX, getCurrentBallY() + ballDirectionY);
		paddleRect.setFrame(getCurrentPaddleX() - Ball.BALL_DIAMETER / 2, getCurrentPaddleY() - Ball.BALL_DIAMETER / 2,
				getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER / 2,
				getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
		if (paddleRect.intersectsLine(l)) {
			Line2D A = new Line2D.Double();
			A.setLine(getCurrentPaddleX() + getCurrentPaddleLength(), getCurrentPaddleY() - Ball.BALL_DIAMETER / 2,
					getCurrentPaddleX(), getCurrentPaddleY() - Ball.BALL_DIAMETER / 2);
			Line2D B = new Line2D.Double();
			B.setLine(getCurrentPaddleX() - Ball.BALL_DIAMETER / 2, getCurrentPaddleY(),
					getCurrentPaddleX() - Ball.BALL_DIAMETER / 2, getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
			Line2D C = new Line2D.Double();
			C.setLine(getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER / 2, getCurrentPaddleY(),
					getCurrentPaddleX() + getCurrentPaddleLength() + Ball.BALL_DIAMETER / 2,
					getCurrentPaddleY() + Paddle.PADDLE_WIDTH);
			CircleArc2D F = new CircleArc2D(
					new math.geom2d.Point2D(getCurrentPaddleX() + getCurrentPaddleLength(), getCurrentPaddleY()),
					Ball.BALL_DIAMETER / 2., 0, Math.PI / 2, false);
			CircleArc2D E = new CircleArc2D(new math.geom2d.Point2D(getCurrentPaddleX(), getCurrentPaddleY()),
					Ball.BALL_DIAMETER / 2., Math.PI / 2, Math.PI, false);
			ArrayList<math.geom2d.Point2D> EIntersections = E.intersections(l1);
			ArrayList<math.geom2d.Point2D> FIntersections = F.intersections(l1);
			if (C.intersectsLine(l) && getBallDirectionX() <= 0) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(C, l).x, calculateIntersectionPoint(C, l).y,
						BounceDirection.FLIP_X));
				counter++;
			}
			if (A.intersectsLine(l) && getBallDirectionY() >= 0) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(A, l).x, calculateIntersectionPoint(A, l).y,
						BounceDirection.FLIP_Y));
				counter++;
			}
			if (B.intersectsLine(l) && getBallDirectionX() >= 0) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(B, l).x, calculateIntersectionPoint(B, l).y,
						BounceDirection.FLIP_X));
				
				counter++;
			}

			if (EIntersections.size() > 0 ) {
				for (int a = 0; a < EIntersections.size(); a++) {
					if (ballDirectionX > 0) {
						BouncePoint x = new BouncePoint(EIntersections.get(a).getX(), EIntersections.get(a).getY(),
								BounceDirection.FLIP_X);
						if(EIntersections.get(a).getX()<=getCurrentPaddleX())
						intersect.add(x);}
					else
					{
						BouncePoint x = new BouncePoint(EIntersections.get(a).getX(), EIntersections.get(a).getY(),
								BounceDirection.FLIP_Y);
						if(EIntersections.get(a).getX()<=getCurrentPaddleX())
						intersect.add(x);}
					counter++;
				}
			}

			if (FIntersections.size() > 0) {
				for (int a = 0; a < FIntersections.size(); a++) {
					if (ballDirectionX > 0) {
						BouncePoint x = new BouncePoint(FIntersections.get(a).getX(), FIntersections.get(a).getY(),
								BounceDirection.FLIP_Y);
						if(FIntersections.get(a).getX()>getCurrentPaddleX())
							intersect.add(x);}
					else
					{
						BouncePoint x = new BouncePoint(FIntersections.get(a).getX(), FIntersections.get(a).getY(),
								BounceDirection.FLIP_X);
						if(FIntersections.get(a).getX()>getCurrentPaddleX())
							intersect.add(x);}
					counter++;
				}
			}

			EIntersections.clear();
			FIntersections.clear();
			BouncePoint closest = null;
			if (counter == 1) {
				closest = intersect.get(0);
			} else {
				for (int a = 0; a < counter - 1; a++) {
					if (isCloser(intersect.get(a), intersect.get(a + 1))) {
						closest = intersect.get(a);
					} else {
						closest = intersect.get(a + 1);
					}
				}
			}
			if (closest != null && getCurrentBallX() + ballDirectionX == closest.getX() && getCurrentBallY() + ballDirectionY == closest.getY()){
				return null;
			}
			return closest;

		} else {
			return null;
		}
  }

  // line 151 "Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment pblock){
    Rectangle2D blockRect = new Rectangle2D.Double();
		Line2D l = new Line2D.Double();
		ArrayList<BouncePoint> intersect = new ArrayList<>();
		int counter = 0;
		l.setLine(getCurrentBallX(), getCurrentBallY(), getCurrentBallX() + ballDirectionX,
				getCurrentBallY() + ballDirectionY);
		math.geom2d.line.Line2D l1= new math.geom2d.line.Line2D(getCurrentBallX(), getCurrentBallY(), getCurrentBallX() + ballDirectionX,
				getCurrentBallY() + ballDirectionY);

		blockRect.setFrame(pblock.getX()- Ball.BALL_DIAMETER / 2, pblock.getY() - Ball.BALL_DIAMETER / 2,  Block.SIZE+pblock.getX() + Ball.BALL_DIAMETER/2,
				pblock.getY() + Ball.BALL_DIAMETER/2+Block.SIZE);

		if (blockRect.intersectsLine(l)) {
			Line2D B = new Line2D.Double();
			B.setLine(pblock.getX()-Ball.BALL_DIAMETER/2, pblock.getY(), pblock.getX()-Ball.BALL_DIAMETER/2, pblock.getY()+Block.SIZE);
			Line2D A = new Line2D.Double();
			A.setLine(pblock.getX(), pblock.getY()-Ball.BALL_DIAMETER/2, pblock.getX()+Block.SIZE, pblock.getY()-Ball.BALL_DIAMETER/2);
			Line2D C = new Line2D.Double();
			C.setLine(pblock.getX()+Block.SIZE+Ball.BALL_DIAMETER/2, pblock.getY(), pblock.getX()+Block.SIZE+Ball.BALL_DIAMETER/2, pblock.getY()+Block.SIZE);
			Line2D D = new Line2D.Double();
			D.setLine(pblock.getX(), pblock.getY()+Block.SIZE+Ball.BALL_DIAMETER/2, pblock.getX()+Block.SIZE, pblock.getY()+Block.SIZE+Ball.BALL_DIAMETER/2);


			CircleArc2D E = new CircleArc2D(new math.geom2d.Point2D(pblock.getX(),pblock.getY()),
					Ball.BALL_DIAMETER/2.,Math.PI/2,Math.PI,false);
			CircleArc2D F = new CircleArc2D(new math.geom2d.Point2D(pblock.getX()+Block.SIZE,pblock.getY()),
					Ball.BALL_DIAMETER/2.,0,Math.PI/2,false);
			CircleArc2D G = new CircleArc2D(new math.geom2d.Point2D(pblock.getX(),pblock.getY()+Block.SIZE),
					Ball.BALL_DIAMETER/2.,Math.PI,1.5*Math.PI,false);
			CircleArc2D H = new CircleArc2D(new math.geom2d.Point2D(pblock.getX()+Block.SIZE,pblock.getY()+Block.SIZE),
					Ball.BALL_DIAMETER/2.,1.5*Math.PI,0,false);

			ArrayList<math.geom2d.Point2D> EIntersections = E.intersections(l1);
			ArrayList<math.geom2d.Point2D> FIntersections = F.intersections(l1);
			ArrayList<math.geom2d.Point2D> GIntersections = G.intersections(l1);
			ArrayList<math.geom2d.Point2D> HIntersections = H.intersections(l1);

			if (A.intersectsLine(l)) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(A, l).x, calculateIntersectionPoint(A, l).y,
						BounceDirection.FLIP_Y));
				counter++;
			}
			if (B.intersectsLine(l)) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(B, l).x, calculateIntersectionPoint(B, l).y,
						BounceDirection.FLIP_X));
				counter++;
			}
			if (C.intersectsLine(l)) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(C, l).x, calculateIntersectionPoint(C, l).y,
						BounceDirection.FLIP_X));
				counter++;
			}
			if (D.intersectsLine(l)) {
				intersect.add(new BouncePoint(calculateIntersectionPoint(D, l).x, calculateIntersectionPoint(D, l).y,
						BounceDirection.FLIP_Y));
				counter++;
			}

			for(int a = 0; a<EIntersections.size();a++) {
				if(ballDirectionX<0)
					intersect.add(new BouncePoint(EIntersections.get(a).getX(), EIntersections.get(a).getY(), BounceDirection.FLIP_Y));
				else intersect.add(new BouncePoint(EIntersections.get(a).getX(), EIntersections.get(a).getY(), BounceDirection.FLIP_X));
				counter++;
			}	
			for(int a = 0; a<FIntersections.size();a++) {
				if(ballDirectionX<=0)
					intersect.add(new BouncePoint(FIntersections.get(a).getX(), FIntersections.get(a).getY(), BounceDirection.FLIP_X));
				else intersect.add(new BouncePoint(FIntersections.get(a).getX(), FIntersections.get(a).getY(), BounceDirection.FLIP_Y));
				counter++;
			}
			for(int a = 0; a<GIntersections.size();a++) {
				if(ballDirectionX<0)
					intersect.add(new BouncePoint(GIntersections.get(a).getX(), GIntersections.get(a).getY(), BounceDirection.FLIP_Y));
				else intersect.add(new BouncePoint(GIntersections.get(a).getX(), GIntersections.get(a).getY(), BounceDirection.FLIP_X));
				counter++;
			}
			for(int a = 0; a<HIntersections.size();a++) {
				if(ballDirectionX<0)
					intersect.add(new BouncePoint(HIntersections.get(a).getX(), HIntersections.get(a).getY(), BounceDirection.FLIP_X));
				else intersect.add(new BouncePoint(HIntersections.get(a).getX(), HIntersections.get(a).getY(), BounceDirection.FLIP_Y));
				counter++;
			}


			BouncePoint closest = null;
			if (counter == 1) {
				closest = intersect.get(0);
	    		closest.setHitBlock(pblock);

			} else {
				for (int a = 0; a < counter-1; a++) {
					if (isCloser(intersect.get(a), intersect.get(a + 1))) {
						closest = intersect.get(a);
						closest.setHitBlock(pblock);
					} else {
						closest = intersect.get(a + 1);
						closest.setHitBlock(pblock);
					}
				}
			}
			if (closest != null && getCurrentBallX() + ballDirectionX == closest.getX() && getCurrentBallY() + ballDirectionY == closest.getY()){
				return null;
			}
			return closest;	
		}
		
		return null;
  }

  // line 262 "Block223States.ump"
   private void bounceBall(){
    double distanceX = getBallDirectionX();
	   double distanceY = getBallDirectionY();
	   double positionX = getCurrentBallX();
	   double positionY = getCurrentBallY();
	   double bouncePointX = getBounce().getX();
	   double bouncePointY = getBounce().getY();
	   double distanceOutgoingX = (distanceX) - Math.abs(bouncePointX - positionX);
	   double distanceOutgoingY = (distanceY) - Math.abs(bouncePointY - positionY);

	   BounceDirection bounceDirection = getBounce().getDirection();
		   if(bounceDirection.equals(BounceDirection.FLIP_BOTH)) {
			   ballDirectionX *= -1;
			   ballDirectionY *= -1;
			   currentBallX = bouncePointX + distanceOutgoingX / distanceX * ballDirectionX;
			   currentBallY = bouncePointY + distanceOutgoingY / distanceY * ballDirectionY;
		   }
		   if(bounceDirection.equals(BounceDirection.FLIP_X)) {
			   ballDirectionX *= -1;
			   if(ballDirectionY==0) {
				   ballDirectionY=0.1*Math.abs(ballDirectionX);
			   }
			   else
			   ballDirectionY += Math.signum(ballDirectionY) * 0.1 * Math.abs(ballDirectionX);
			   currentBallX = bouncePointX + distanceOutgoingX / distanceX * ballDirectionX;
			   currentBallY = bouncePointY + distanceOutgoingX / distanceX * ballDirectionY;
		   }
		   if(bounceDirection.equals(BounceDirection.FLIP_Y)) {
			   if(ballDirectionX==0) {
				   ballDirectionX=0.1*Math.abs(ballDirectionY);
			   }
			   else
			   ballDirectionX += Math.signum(ballDirectionX) * 0.1 * Math.abs(ballDirectionY);
			   ballDirectionY *= -1;
			   currentBallX = bouncePointX + distanceOutgoingY / distanceY * ballDirectionX;
			   currentBallY = bouncePointY + distanceOutgoingY / distanceY * ballDirectionY;
		   }
	   
	   setBounce(null);
  }

  // line 303 "Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    
    if (lives == 1){
    	outOfBounds = this.isBallOutOfBounds();
    }	
    return outOfBounds;
  }

  // line 312 "Block223States.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = this.isBallOutOfBounds();
    return outOfBounds;
  }

  // line 317 "Block223States.ump"
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

  // line 333 "Block223States.ump"
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

  // line 345 "Block223States.ump"
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

  // line 361 "Block223States.ump"
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
  // line 372 "Block223States.ump"
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
	   int maxHorizontalizontal = (1+(Game.PLAY_AREA_SIDE-2*Game.WALL_PADDING-Block.SIZE)/(Block.SIZE+Game.COLUMNS_PADDING));
	   int maxVertical = (1+(Game.PLAY_AREA_SIDE-Paddle.VERTICAL_DISTANCE-Game.WALL_PADDING-Paddle.PADDLE_WIDTH-Ball.BALL_DIAMETER-Block.SIZE)/(Block.SIZE+Game.ROW_PADDING));
	   int x;
	   int y;
	   
	   while(numberOfBlocks < game.getNrBlocksPerLevel()) {
		   Random rand = new Random();
		   x = rand.nextInt(maxHorizontalizontal);
		   y = rand.nextInt(maxVertical);
		   PlayedBlockAssignment foundAssignment = this.findPlayedBlockAssignment(x , y);
		   while(foundAssignment != null) {
			   if(y < maxVer) {
				   if(x <= maxHorizontal) x=x+1;
				   if(x > maxHorizontal) {
					   x = 1;
					   y=y+1;
				   }
			   }
			   
			   else if(y >= maxVer) {
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

  // line 422 "Block223States.ump"
   private void doHitPaddleOrWall(){
    this.bounceBall();
  }

  // line 426 "Block223States.ump"
   private void doOutOfBounds(){
    this.setLives(lives-1);
		this.resetCurrentBallX();
		this.resetCurrentBallY();
		this.resetBallDirectionX();
		this.resetBallDirectionY();
		this.resetCurrentPaddleX();
  }

  // line 435 "Block223States.ump"
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

  // line 446 "Block223States.ump"
   private void doHitBlockNextLevel(){
    this.doHitBlock();
    int level = this.getCurrentLevel();
    this.setCurrentPaddleLength(this.getGame().getPaddle().getMaxPaddleLength()-(this.getGame().getPaddle().getMaxPaddleLength()
    							-this.getGame().getPaddle().getMinPaddleLength())/(this.getGame().numberOfLevels())*(this.getCurrentLevel()));
    this.setWaitTime(INITIAL_WAIT_TIME*Math.pow(this.getGame().getBall().getBallSpeedIncreaseFactor(),(double)(this.getCurrentLevel())));
    this.setCurrentLevel(level+1);
  }

  // line 455 "Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    PlayedGame currentPlayedGame = Block223Application.getCurrentPlayableGame();
	double x = currentPlayedGame.getCurrentBallX();
	double y = currentPlayedGame.getCurrentBallY();
	double dx = currentPlayedGame.getBallDirectionX();
	double dy = currentPlayedGame.getBallDirectionY();
	currentPlayedGame.setCurrentBallX(x + dx);
	currentPlayedGame.setCurrentBallY(y + dy);
  }

  // line 465 "Block223States.ump"
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