/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 16 "../../../../../main.ump"
public class Player extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String playerPassword;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aUserName, String aPlayerPassword)
  {
    super(aUserName);
    playerPassword = aPlayerPassword;
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

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "playerPassword" + ":" + getPlayerPassword()+ "]";
  }
}