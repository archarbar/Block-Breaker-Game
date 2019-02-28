/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 14 "Block223Persistence.ump"
public class Block implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 20 "Block223Persistence.ump"
   public static  void reinitializeBlockuniqueId(List<Block> blocks){
    nextId = 0; 
    for (Block block : blocks) {
      if (block.getId() > nextId) {
        nextId = block.getId();
      }
    }
    nextId++;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 17 "Block223Persistence.ump"
  private static final long serialVersionUID =002 ;

  
}