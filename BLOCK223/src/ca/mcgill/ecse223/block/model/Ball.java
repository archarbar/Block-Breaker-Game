/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 93 "main.ump"
public class Ball
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private float speed;
  private int diameter;
  private String color;

  //Ball Associations
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(float aSpeed, int aDiameter, String aColor, Level aLevel)
  {
    speed = aSpeed;
    diameter = aDiameter;
    color = aColor;
    if (aLevel == null || aLevel.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aLevel");
    }
    level = aLevel;
  }

  public Ball(float aSpeed, int aDiameter, String aColor, int aNumBlocsForLevel, int aNumLevelsForLevel, boolean aBlockRandomizerForLevel, Paddle aPaddleForLevel, Game aGameForLevel)
  {
    speed = aSpeed;
    diameter = aDiameter;
    color = aColor;
    level = new Level(aNumBlocsForLevel, aNumLevelsForLevel, aBlockRandomizerForLevel, aPaddleForLevel, aGameForLevel, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSpeed(float aSpeed)
  {
    boolean wasSet = false;
    speed = aSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiameter(int aDiameter)
  {
    boolean wasSet = false;
    diameter = aDiameter;
    wasSet = true;
    return wasSet;
  }

  public boolean setColor(String aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public float getSpeed()
  {
    return speed;
  }

  public int getDiameter()
  {
    return diameter;
  }

  public String getColor()
  {
    return color;
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
            "speed" + ":" + getSpeed()+ "," +
            "diameter" + ":" + getDiameter()+ "," +
            "color" + ":" + getColor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}