/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 84 "../../../../../main.ump"
public class Paddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private float length;

  //Paddle Associations
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(float aLength, Level aLevel)
  {
    length = aLength;
    if (aLevel == null || aLevel.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aLevel");
    }
    level = aLevel;
  }

  public Paddle(float aLength, int aNumBlocsForLevel, int aNumLevelsForLevel, boolean aBlockRandomizerForLevel, Game aGameForLevel, Ball aBallForLevel)
  {
    length = aLength;
    level = new Level(aNumBlocsForLevel, aNumLevelsForLevel, aBlockRandomizerForLevel, this, aGameForLevel, aBallForLevel);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLength(float aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public float getLength()
  {
    return length;
  }
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
  }

  public void delete()
  {
    Level existingLevel = level;
    level = null;
    if (existingLevel != null)
    {
      existingLevel.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "length" + ":" + getLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}