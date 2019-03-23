/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 49 "Block223v3.ump"
public class BlockAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockAssignment Associations
  private Level level;
  private Game game;
  private Block block;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockAssignment(Level aLevel, Game aGame, Block aBlock)
  {
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create blockAssignment due to level");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create blockAssignment due to game");
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
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Block getBlock()
  {
    return block;
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

  public void delete()
  {
    Level placeholderLevel = level;
    this.level = null;
    if(placeholderLevel != null)
    {
      placeholderLevel.removeBlockAssignment(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlockAssignment(this);
    }
    Block placeholderBlock = block;
    this.block = null;
    if(placeholderBlock != null)
    {
      placeholderBlock.removeBlockAssignment(this);
    }
  }

}