/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 65 "../../../../../main.ump"
public class Cell
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Cell Attributes
  private int number;
  private float xPos;
  private float yPos;

  //Cell Associations
  private Block block;
  private GridSystem gridSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Cell(int aNumber, float aXPos, float aYPos, Block aBlock, GridSystem aGridSystem)
  {
    number = aNumber;
    xPos = aXPos;
    yPos = aYPos;
    if (aBlock == null || aBlock.getCell() != null)
    {
      throw new RuntimeException("Unable to create Cell due to aBlock");
    }
    block = aBlock;
    boolean didAddGridSystem = setGridSystem(aGridSystem);
    if (!didAddGridSystem)
    {
      throw new RuntimeException("Unable to create cell due to gridSystem");
    }
  }

  public Cell(int aNumber, float aXPos, float aYPos, String aColorForBlock, int aPointsForBlock, int aCellNumberForBlock, int aBlockSideLengthForBlock, Level aLevelForBlock, GridSystem aGridSystem)
  {
    number = aNumber;
    xPos = aXPos;
    yPos = aYPos;
    block = new Block(aColorForBlock, aPointsForBlock, aCellNumberForBlock, aBlockSideLengthForBlock, aLevelForBlock, this);
    boolean didAddGridSystem = setGridSystem(aGridSystem);
    if (!didAddGridSystem)
    {
      throw new RuntimeException("Unable to create cell due to gridSystem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setXPos(float aXPos)
  {
    boolean wasSet = false;
    xPos = aXPos;
    wasSet = true;
    return wasSet;
  }

  public boolean setYPos(float aYPos)
  {
    boolean wasSet = false;
    yPos = aYPos;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public float getXPos()
  {
    return xPos;
  }

  public float getYPos()
  {
    return yPos;
  }
  /* Code from template association_GetOne */
  public Block getBlock()
  {
    return block;
  }
  /* Code from template association_GetOne */
  public GridSystem getGridSystem()
  {
    return gridSystem;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGridSystem(GridSystem aGridSystem)
  {
    boolean wasSet = false;
    if (aGridSystem == null)
    {
      return wasSet;
    }

    GridSystem existingGridSystem = gridSystem;
    gridSystem = aGridSystem;
    if (existingGridSystem != null && !existingGridSystem.equals(aGridSystem))
    {
      existingGridSystem.removeCell(this);
    }
    gridSystem.addCell(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block existingBlock = block;
    block = null;
    if (existingBlock != null)
    {
      existingBlock.delete();
    }
    GridSystem placeholderGridSystem = gridSystem;
    this.gridSystem = null;
    if(placeholderGridSystem != null)
    {
      placeholderGridSystem.removeCell(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "xPos" + ":" + getXPos()+ "," +
            "yPos" + ":" + getYPos()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gridSystem = "+(getGridSystem()!=null?Integer.toHexString(System.identityHashCode(getGridSystem())):"null");
  }
}