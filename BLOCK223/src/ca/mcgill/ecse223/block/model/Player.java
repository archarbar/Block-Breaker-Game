/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 17 "../../../../../main.ump"
public class Player extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String playerPassword;

  //Player Associations
  private HallOfFame hallOfFame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aUserName, String aPlayerPassword, HallOfFame aHallOfFame)
  {
    super(aUserName);
    playerPassword = aPlayerPassword;
    boolean didAddHallOfFame = setHallOfFame(aHallOfFame);
    if (!didAddHallOfFame)
    {
      throw new RuntimeException("Unable to create player due to hallOfFame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPlayerPassword(String aPlayerPassword)
  {
    boolean wasSet = false;
    playerPassword = aPlayerPassword;
    wasSet = true;
    return wasSet;
  }

  public String getPlayerPassword()
  {
    return playerPassword;
  }
  /* Code from template association_GetOne */
  public HallOfFame getHallOfFame()
  {
    return hallOfFame;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHallOfFame(HallOfFame aHallOfFame)
  {
    boolean wasSet = false;
    if (aHallOfFame == null)
    {
      return wasSet;
    }

    HallOfFame existingHallOfFame = hallOfFame;
    hallOfFame = aHallOfFame;
    if (existingHallOfFame != null && !existingHallOfFame.equals(aHallOfFame))
    {
      existingHallOfFame.removePlayer(this);
    }
    hallOfFame.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    HallOfFame placeholderHallOfFame = hallOfFame;
    this.hallOfFame = null;
    if(placeholderHallOfFame != null)
    {
      placeholderHallOfFame.removePlayer(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "playerPassword" + ":" + getPlayerPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFame = "+(getHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getHallOfFame())):"null");
  }
}