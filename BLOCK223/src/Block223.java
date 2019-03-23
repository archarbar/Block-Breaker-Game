/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 29 "Block223Persistence.ump"
public class Block223 implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block223()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 35 "Block223Persistence.ump"
   public void reinitialize(){
    User.reinitializeUseruniqueUsername(this.getUsers());
    Game.reinitializeGameuniqueName(this.getGames());
    List<Game> games = this.getGames();
    for (Game game : games) {
    	Block.reinitializeBlockuniqueId(game.getBlocks());
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 32 "Block223Persistence.ump"
  private static final long serialVersionUID =003 ;

  
}