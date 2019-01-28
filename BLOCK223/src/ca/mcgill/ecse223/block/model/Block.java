/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 72 "../../../../../main.ump"
public class Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Attributes
  private String color;
  private int points;
  private int cellNumber;
  private int blockSideLength;

  //Block Associations
  private Level level;
  private Cell cell;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(String aColor, int aPoints, int aCellNumber, int aBlockSideLength, Level aLevel, Cell aCell)
  {
    color = aColor;
    points = aPoints;
    cellNumber = aCellNumber;
    blockSideLength = aBlockSideLength;
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create block due to level");
    }
    if (aCell == null || aCell.getBlock() != null)
    {
      throw new RuntimeException("Unable to create Block due to aCell");
    }
    cell = aCell;
  }

  public Block(String aColor, int aPoints, int aCellNumber, int aBlockSideLength, Level aLevel, int aNumberForCell, float aXPosForCell, float aYPosForCell, GridSystem aGridSystemForCell)
  {
    color = aColor;
    points = aPoints;
    cellNumber = aCellNumber;
    blockSideLength = aBlockSideLength;
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create block due to level");
    }
    cell = new Cell(aNumberForCell, aXPosForCell, aYPosForCell, this, aGridSystemForCell);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setColor(String aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setCellNumber(int aCellNumber)
  {
    boolean wasSet = false;
    cellNumber = aCellNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setBlockSideLength(int aBlockSideLength)
  {
    boolean wasSet = false;
    blockSideLength = aBlockSideLength;
    wasSet = true;
    return wasSet;
  }

  public String getColor()
  {
    return color;
  }

  public int getPoints()
  {
    return points;
  }

  public int getCellNumber()
  {
    return cellNumber;
  }

  public int getBlockSideLength()
  {
    return blockSideLength;
  }
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
  }
  /* Code from template association_GetOne */
  public Cell getCell()
  {
    return cell;
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
      existingLevel.removeBlock(this);
    }
    level.addBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Level placeholderLevel = level;
    this.level = null;
    if(placeholderLevel != null)
    {
      placeholderLevel.removeBlock(this);
    }
    Cell existingCell = cell;
    cell = null;
    if (existingCell != null)
    {
      existingCell.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "color" + ":" + getColor()+ "," +
            "points" + ":" + getPoints()+ "," +
            "cellNumber" + ":" + getCellNumber()+ "," +
            "blockSideLength" + ":" + getBlockSideLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "cell = "+(getCell()!=null?Integer.toHexString(System.identityHashCode(getCell())):"null");
  }
}