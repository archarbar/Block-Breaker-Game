/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 71 "main.ump"
public class Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Attributes
  private Color color;
  private Int points;
  private Int cellNumber;
  private Int blockSideLength;

  //Block Associations
  private Level level;
  private Cell cell;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(Color aColor, Int aPoints, Int aCellNumber, Int aBlockSideLength, Level aLevel, Cell aCell)
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

  public Block(Color aColor, Int aPoints, Int aCellNumber, Int aBlockSideLength, Level aLevel, Int aNumberForCell, float aXPosForCell, float aYPosForCell, GridSystem aGridSystemForCell)
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

  public boolean setColor(Color aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoints(Int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setCellNumber(Int aCellNumber)
  {
    boolean wasSet = false;
    cellNumber = aCellNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setBlockSideLength(Int aBlockSideLength)
  {
    boolean wasSet = false;
    blockSideLength = aBlockSideLength;
    wasSet = true;
    return wasSet;
  }

  public Color getColor()
  {
    return color;
  }

  public Int getPoints()
  {
    return points;
  }

  public Int getCellNumber()
  {
    return cellNumber;
  }

  public Int getBlockSideLength()
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
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "color" + "=" + (getColor() != null ? !getColor().equals(this)  ? getColor().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "points" + "=" + (getPoints() != null ? !getPoints().equals(this)  ? getPoints().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "cellNumber" + "=" + (getCellNumber() != null ? !getCellNumber().equals(this)  ? getCellNumber().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "blockSideLength" + "=" + (getBlockSideLength() != null ? !getBlockSideLength().equals(this)  ? getBlockSideLength().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "cell = "+(getCell()!=null?Integer.toHexString(System.identityHashCode(getCell())):"null");
  }
}