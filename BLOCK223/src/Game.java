/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 29 "Block223Persistence.ump"
public class Game implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 35 "Block223Persistence.ump"
   public static  void reinitializeGameuniqueID(List<Game> games){
    nextId = 0; 
    for (Game game : games) {
      if (game.getId() > nextId) {
        nextId = driver.getId();
      }
    }
    nextId++;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 32 "Block223Persistence.ump"
  private static final long serialVersionUID = ;

  
}