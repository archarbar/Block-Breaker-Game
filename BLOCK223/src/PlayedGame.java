/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.awt.geom.*;
import math.geom2d.conic.*;
import java.awt.Point;

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
        // line 16 "Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 17 "Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 18 "Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 19 "Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 20 "Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 21 "Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 22 "Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 23 "Block223States.ump"
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
        // line 11 "Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 29 "Block223States.ump"
        doGameOver();
        break;
    }
  }

  public void delete()
  {}


  /**
   * Guards
   */
  // line 36 "Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
	if(bp != null) {
		setBounce(bp);
		return true;
	}
    return false;
  }

  // line 45 "Block223States.ump"
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
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    
    if (lives == 1){
    	outOfBounds = this.isBallOutOfBounds();
    }	
    return outOfBounds;
  }

  // line 160 "Block223States.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = this.isBallOutOfBounds();
    return outOfBounds;
  }

  // line 165 "Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    // TODO implement
    return false;
  }

  // line 170 "Block223States.ump"
   private boolean hitLastBlock(){
    // TODO implement
    return false;
  }

  // line 175 "Block223States.ump"
   private boolean hitBlock(){
    // TODO implement
    return false;
  }

  // line 180 "Block223States.ump"
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
  // line 191 "Block223States.ump"
   private void doSetup(){
    // TODO implement
  }

  // line 195 "Block223States.ump"
   private void doHitPaddleOrWall(){
    // TODO implement
    this.bounceBall();
  }

  // line 200 "Block223States.ump"
   private void doOutOfBounds(){
    this.setLives(lives-1);
		this.resetCurrentBallX();
		this.resetCurrentBallY();
		this.resetBallDirectionX();
		this.resetBallDirectionY();
		this.resetCurrentPaddleX();
  }

  // line 209 "Block223States.ump"
   private void doHitBlock(){
    // TODO implement
  }

  // line 213 "Block223States.ump"
   private void doHitBlockNextLevel(){
    // TODO implement
  }

  // line 217 "Block223States.ump"
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

  // line 228 "Block223States.ump"
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