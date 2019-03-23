/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 55 "Block223v3.ump"
public class Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Associations
  private Game game;
  private List<BlockAssignment> blockAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(Game aGame)
  {
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create block due to game");
    }
    blockAssignments = new ArrayList<BlockAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public BlockAssignment getBlockAssignment(int index)
  {
    BlockAssignment aBlockAssignment = blockAssignments.get(index);
    return aBlockAssignment;
  }

  public List<BlockAssignment> getBlockAssignments()
  {
    List<BlockAssignment> newBlockAssignments = Collections.unmodifiableList(blockAssignments);
    return newBlockAssignments;
  }

  public int numberOfBlockAssignments()
  {
    int number = blockAssignments.size();
    return number;
  }

  public boolean hasBlockAssignments()
  {
    boolean has = blockAssignments.size() > 0;
    return has;
  }

  public int indexOfBlockAssignment(BlockAssignment aBlockAssignment)
  {
    int index = blockAssignments.indexOf(aBlockAssignment);
    return index;
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
      existingGame.removeBlock(this);
    }
    game.addBlock(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockAssignment addBlockAssignment(Level aLevel, Game aGame)
  {
    return new BlockAssignment(aLevel, aGame, this);
  }

  public boolean addBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasAdded = false;
    if (blockAssignments.contains(aBlockAssignment)) { return false; }
    Block existingBlock = aBlockAssignment.getBlock();
    boolean isNewBlock = existingBlock != null && !this.equals(existingBlock);
    if (isNewBlock)
    {
      aBlockAssignment.setBlock(this);
    }
    else
    {
      blockAssignments.add(aBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockAssignment, as it must always have a block
    if (!this.equals(aBlockAssignment.getBlock()))
    {
      blockAssignments.remove(aBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAssignmentAt(BlockAssignment aBlockAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addBlockAssignment(aBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockAssignments()) { index = numberOfBlockAssignments() - 1; }
      blockAssignments.remove(aBlockAssignment);
      blockAssignments.add(index, aBlockAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAssignmentAt(BlockAssignment aBlockAssignment, int index)
  {
    boolean wasAdded = false;
    if(blockAssignments.contains(aBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockAssignments()) { index = numberOfBlockAssignments() - 1; }
      blockAssignments.remove(aBlockAssignment);
      blockAssignments.add(index, aBlockAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAssignmentAt(aBlockAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlock(this);
    }
    for(int i=blockAssignments.size(); i > 0; i--)
    {
      BlockAssignment aBlockAssignment = blockAssignments.get(i - 1);
      aBlockAssignment.delete();
    }
  }

}