/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 158 "Block223StateMachine.ump"
public class Entry
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Entry Attributes
  private int finalScore;

  //Entry Associations
  private Game game;
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Entry(int aFinalScore, Game aGame, Player aPlayer)
  {
    finalScore = aFinalScore;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create entry due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create entry due to player");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFinalScore(int aFinalScore)
  {
    boolean wasSet = false;
    finalScore = aFinalScore;
    wasSet = true;
    return wasSet;
  }

  public int getFinalScore()
  {
    return finalScore;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeEntry(this);
    }
    game.addEntry(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    if (aPlayer == null)
    {
      return wasSet;
    }

    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removeEntry(this);
    }
    player.addEntry(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeEntry(this);
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeEntry(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "finalScore" + ":" + getFinalScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}