/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 59 "../../../../../main.ump"
public class Level
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Attributes
  private int numBlocs;
  private int numLevels;
  private boolean blockRandomizer;

  //Level Associations
  private Paddle paddle;
  private Game game;
  private List<Block> blocks;
  private Ball ball;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(int aNumBlocs, int aNumLevels, boolean aBlockRandomizer, Paddle aPaddle, Game aGame, Ball aBall)
  {
    numBlocs = aNumBlocs;
    numLevels = aNumLevels;
    blockRandomizer = aBlockRandomizer;
    if (aPaddle == null || aPaddle.getLevel() != null)
    {
      throw new RuntimeException("Unable to create Level due to aPaddle");
    }
    paddle = aPaddle;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create level due to game");
    }
    blocks = new ArrayList<Block>();
    if (aBall == null || aBall.getLevel() != null)
    {
      throw new RuntimeException("Unable to create Level due to aBall");
    }
    ball = aBall;
  }

  public Level(int aNumBlocs, int aNumLevels, boolean aBlockRandomizer, float aLengthForPaddle, Game aGame, float aSpeedForBall, int aDiameterForBall, String aColorForBall)
  {
    numBlocs = aNumBlocs;
    numLevels = aNumLevels;
    blockRandomizer = aBlockRandomizer;
    paddle = new Paddle(aLengthForPaddle, this);
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create level due to game");
    }
    blocks = new ArrayList<Block>();
    ball = new Ball(aSpeedForBall, aDiameterForBall, aColorForBall, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumBlocs(int aNumBlocs)
  {
    boolean wasSet = false;
    numBlocs = aNumBlocs;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumLevels(int aNumLevels)
  {
    boolean wasSet = false;
    numLevels = aNumLevels;
    wasSet = true;
    return wasSet;
  }

  public boolean setBlockRandomizer(boolean aBlockRandomizer)
  {
    boolean wasSet = false;
    blockRandomizer = aBlockRandomizer;
    wasSet = true;
    return wasSet;
  }

  public int getNumBlocs()
  {
    return numBlocs;
  }

  public int getNumLevels()
  {
    return numLevels;
  }

  public boolean getBlockRandomizer()
  {
    return blockRandomizer;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isBlockRandomizer()
  {
    return blockRandomizer;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
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

  public int indexOfBlock(Block aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to level
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (99)
    if (aGame.numberOfLevels() >= Game.maximumNumberOfLevels())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeLevel(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addLevel(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock(String aColor, int aPoints, int aCellNumber, int aBlockSideLength, Cell aCell)
  {
    return new Block(aColor, aPoints, aCellNumber, aBlockSideLength, this, aCell);
  }

  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    Level existingLevel = aBlock.getLevel();
    boolean isNewLevel = existingLevel != null && !this.equals(existingLevel);
    if (isNewLevel)
    {
      aBlock.setLevel(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a level
    if (!this.equals(aBlock.getLevel()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
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

  public boolean addOrMoveBlockAt(Block aBlock, int index)
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

  public void delete()
  {
    Paddle existingPaddle = paddle;
    paddle = null;
    if (existingPaddle != null)
    {
      existingPaddle.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeLevel(this);
    }
    for(int i=blocks.size(); i > 0; i--)
    {
      Block aBlock = blocks.get(i - 1);
      aBlock.delete();
    }
    Ball existingBall = ball;
    ball = null;
    if (existingBall != null)
    {
      existingBall.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numBlocs" + ":" + getNumBlocs()+ "," +
            "numLevels" + ":" + getNumLevels()+ "," +
            "blockRandomizer" + ":" + getBlockRandomizer()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null");
  }
}