/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 20 "Block223Persistence.ump"
public class User implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 26 "Block223Persistence.ump"
   public static  void reinitializeUseruniqueUsername(List<User> users){
    usersByUsername = new HashMap<String, User>();
    for (User user : users) {
        usersByUsername.put(user.getUsername(), user);
     }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 23 "Block223Persistence.ump"
  private static final long serialVersionUID = 4267485601061759914L ;

  
}