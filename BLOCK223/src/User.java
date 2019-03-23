/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 1 "Block223 v3.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;

  //User Associations
  private List<UserRole> userRoles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUsername, UserRole... allUserRoles)
  {
    username = aUsername;
    userRoles = new ArrayList<UserRole>();
    boolean didAddUserRoles = setUserRoles(allUserRoles);
    if (!didAddUserRoles)
    {
      throw new RuntimeException("Unable to create User, must have 1 to 2 userRoles");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template association_GetMany */
  public UserRole getUserRole(int index)
  {
    UserRole aUserRole = userRoles.get(index);
    return aUserRole;
  }

  public List<UserRole> getUserRoles()
  {
    List<UserRole> newUserRoles = Collections.unmodifiableList(userRoles);
    return newUserRoles;
  }

  public int numberOfUserRoles()
  {
    int number = userRoles.size();
    return number;
  }

  public boolean hasUserRoles()
  {
    boolean has = userRoles.size() > 0;
    return has;
  }

  public int indexOfUserRole(UserRole aUserRole)
  {
    int index = userRoles.indexOf(aUserRole);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserRoles()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfUserRoles()
  {
    return 2;
  }
  /* Code from template association_AddUnidirectionalMN */
  public boolean addUserRole(UserRole aUserRole)
  {
    boolean wasAdded = false;
    if (userRoles.contains(aUserRole)) { return false; }
    if (numberOfUserRoles() < maximumNumberOfUserRoles())
    {
      userRoles.add(aUserRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeUserRole(UserRole aUserRole)
  {
    boolean wasRemoved = false;
    if (!userRoles.contains(aUserRole))
    {
      return wasRemoved;
    }

    if (numberOfUserRoles() <= minimumNumberOfUserRoles())
    {
      return wasRemoved;
    }

    userRoles.remove(aUserRole);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMN */
  public boolean setUserRoles(UserRole... newUserRoles)
  {
    boolean wasSet = false;
    ArrayList<UserRole> verifiedUserRoles = new ArrayList<UserRole>();
    for (UserRole aUserRole : newUserRoles)
    {
      if (verifiedUserRoles.contains(aUserRole))
      {
        continue;
      }
      verifiedUserRoles.add(aUserRole);
    }

    if (verifiedUserRoles.size() != newUserRoles.length || verifiedUserRoles.size() < minimumNumberOfUserRoles() || verifiedUserRoles.size() > maximumNumberOfUserRoles())
    {
      return wasSet;
    }

    userRoles.clear();
    userRoles.addAll(verifiedUserRoles);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserRoleAt(UserRole aUserRole, int index)
  {  
    boolean wasAdded = false;
    if(addUserRole(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserRoleAt(UserRole aUserRole, int index)
  {
    boolean wasAdded = false;
    if(userRoles.contains(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserRoleAt(aUserRole, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    userRoles.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "]";
  }
}