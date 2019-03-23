/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;

// line 45 "../../../../../Block223Persistence.ump"
// line 43 "../../../../../Block223v3.ump"
// line 142 "../../../../../Block223v2.ump"
public class BlockAssignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockAssignment Attributes
  private int gridHorizontalPosition;
  private int gridVerticalPosition;
  private int maxVerticalGridPosition;
  private int maxHorizontalGridPosition;

  //BlockAssignment Associations
  private Game game;
  private Level level;
  private Block block;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockAssignment(int aGridHorizontalPosition, int aGridVerticalPosition, Game aGame, Level aLevel, Block aBlock)
  {
    // line 148 "../../../../../Block223v2.ump"
    if (aGridHorizontalPosition <= 0 || aGridHorizontalPosition > getMaxHorizontalGridPosition()) {
    	    	throw new RuntimeException("gridHorizontalPosition can't be negative or greater than " + getMaxHorizontalGridPosition());
    	    }
    	   if (aGridVerticalPosition <= 0 || aGridVerticalPosition > getMaxVerticalGridPosition()) {
    	    	throw new RuntimeException("GridVerticalPosition can't be negative or greater than " + getMaxVerticalGridPosition());
    	    }
    // END OF UMPLE BEFORE INJECTION
    gridHorizontalPosition = aGridHorizontalPosition;
    gridVerticalPosition = aGridVerticalPosition;
    maxVerticalGridPosition = 15;
    maxHorizontalGridPosition = 15;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create blockAssignment due to game");
    }
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create blockAssignment due to level");
    }
    boolean didAddBlock = setBlock(aBlock);
    if (!didAddBlock)
    {
      throw new RuntimeException("Unable to create blockAssignment due to block");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGridHorizontalPosition(int aGridHorizontalPosition)
  {
    boolean wasSet = false;
    // line 156 "../../../../../Block223v2.ump"
    if (aGridHorizontalPosition <= 0 || aGridHorizontalPosition > getMaxHorizontalGridPosition()) {
    	    	throw new RuntimeException("gridHorizontalPosition can't be negative or greater than " + getMaxHorizontalGridPosition());
    	    }
    // END OF UMPLE BEFORE INJECTION
    gridHorizontalPosition = aGridHorizontalPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setGridVerticalPosition(int aGridVerticalPosition)
  {
    boolean wasSet = false;
    // line 161 "../../../../../Block223v2.ump"
    if (aGridVerticalPosition <= 0 || aGridVerticalPosition > getMaxVerticalGridPosition()) {
    	    	throw new RuntimeException("GridVerticalPosition can't be negative or greater than " + getMaxVerticalGridPosition());
    	    }
    // END OF UMPLE BEFORE INJECTION
    gridVerticalPosition = aGridVerticalPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxVerticalGridPosition(int aMaxVerticalGridPosition)
  {
    boolean wasSet = false;
    maxVerticalGridPosition = aMaxVerticalGridPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxHorizontalGridPosition(int aMaxHorizontalGridPosition)
  {
    boolean wasSet = false;
    maxHorizontalGridPosition = aMaxHorizontalGridPosition;
    wasSet = true;
    return wasSet;
  }

  public int getGridHorizontalPosition()
  {
    return gridHorizontalPosition;
  }

  public int getGridVerticalPosition()
  {
    return gridVerticalPosition;
  }

  public int getMaxVerticalGridPosition()
  {
    return maxVerticalGridPosition;
  }

  public int getMaxHorizontalGridPosition()
  {
    return maxHorizontalGridPosition;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_game()
  {
    game = null;
  }
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
  }
  /* Code from template association_GetOne_relatedSpecialization */
  public Block getBlock_OneBlock()
  {
    return (Block)block;
  } 
  /* Code from template association_GetOne */
  public Block getBlock()
  {
    return block;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_block()
  {
    block = null;
  }
  /* Code from template association_GetOne_relatedSpecialization */
  public Game getGame_OneGame()
  {
    return (Game)game;
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
      existingGame.removeBlockAssignment(this);
    }
    game.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLevel(Level aLevel)
  {
    boolean wasSet = false;
    if (aLevel == null)
    {
      return wasSet;
    }

    Level existingLevel = level;
    level = aLevel;
    if (existingLevel != null && !existingLevel.equals(aLevel))
    {
      existingLevel.removeBlockAssignment(this);
    }
    level.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_SetOneToMany_relatedSpecialization */
  public boolean setBlock_Block(Block aBlock)
  {
    boolean wasSet = false;
    if (aBlock == null)
    {
      return wasSet;
    }

    Block existingBlock = (Block)block;
    block = aBlock;
    if (existingBlock != null && !existingBlock.equals(aBlock))
    {
      existingBlock.removeBlockAssignment(this);
    }
    block.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock(Block aBlock)
  {
    boolean wasSet = false;
    if (aBlock == null)
    {
      return wasSet;
    }

    Block existingBlock = block;
    block = aBlock;
    if (existingBlock != null && !existingBlock.equals(aBlock))
    {
      existingBlock.removeBlockAssignment(this);
    }
    block.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_SetOneToMany_relatedSpecialization */
  public boolean setGame_Game(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = (Game)game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeBlockAssignment(this);
    }
    game.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlockAssignment(this);
    }
    Level placeholderLevel = level;
    this.level = null;
    if(placeholderLevel != null)
    {
      placeholderLevel.removeBlockAssignment(this);
    }
    Block placeholderBlock = block;
    this.block = null;
    if(placeholderBlock != null)
    {
      placeholderBlock.removeBlockAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gridHorizontalPosition" + ":" + getGridHorizontalPosition()+ "," +
            "gridVerticalPosition" + ":" + getGridVerticalPosition()+ "," +
            "maxVerticalGridPosition" + ":" + getMaxVerticalGridPosition()+ "," +
            "maxHorizontalGridPosition" + ":" + getMaxHorizontalGridPosition()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 48 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID =004 ;

  
}