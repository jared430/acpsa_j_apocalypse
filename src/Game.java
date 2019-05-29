public class Game {

  // "APOCALYPSE OF THE DAMNED" created by MADISON VELEZ, AHMED DIABY, AND JARED CID

  private Grid grid;
  private int userRow;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private int userCol;
  
  // MAIN CLASS
  
  public Game() {

    grid = new Grid(10, 20);
    userRow = 3;
    userCol = 5;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, userCol), "user.gif");
  }
  
  public void play() {
    while (!isGameOver()) {
      grid.pause(100);  // MODIFY GAME SPEED
      handleKeyPress();
      if (msElapsed % 300 == 0) { // YOU CAN MOVE THREE TIMES AND THE OBJECT MOVES ONCE EVERY THREE TENTHS OF A SECOND
        scrollLeft();
        populateRightEdge();
      }
      updateTitle();  // SCORE, HEALTH, ETC
      msElapsed += 100; // KEEP TRACK OF GAME TIMING
    }
  }
  
  public void handleKeyPress() {
    int key = grid.checkLastKeyPressed();
    //check for out of bounds
    
    if(key == 38 )
    {
      if(userRow <= 0){
        return;
      }
      userRow--;
      Location loc = new Location(userRow, userCol);
      grid.setImage(loc, "user.gif");

      Location oldLoc = new Location(userRow+1, userCol);
      grid.setImage(oldLoc, null);
    }
    else if(key == 40)
    {
    if(userRow >= 10){
      return;
    }
      userRow++;
      Location loc = new Location(userRow, userCol);
      grid.setImage(loc, "user.gif");

      Location oldLoc = new Location(userRow-1, userCol);
      grid.setImage(oldLoc, null);
    }
    else if(key == 37)
    {
      if(userCol <= 0)
      {
        return;
      }
      userCol--;
      Location loc = new Location(userRow, userCol);
      grid.setImage(loc, "user.gif");

      Location oldLoc = new Location(userRow, userCol+1);
      grid.setImage(oldLoc, null);
    }
    else if(key == 39)
    {
      if(userCol >= 20){
        return;
      }
      userCol++;
      Location loc = new Location(userRow, userCol);
      grid.setImage(loc, "user.gif");

      Location oldLoc = new Location(userRow, userCol-1);
      grid.setImage(oldLoc, null);
    }
  }

  /*public void setUserRow() {
    int rowNum=grid.getNumRows();
    if (userRow < 0){
      userRow = 0;
    } else if(userRow>rowNum) {
    userRow = rowNum - 1;
    }
  }
  */
  
  public void populateRightEdge() {

  }
  
  public void scrollLeft() {

  }
  
  public void handleCollision(Location loc) {

  }
  
  public int getScore() {
    return 0;
  }
  
  public void updateTitle() {
    grid.setTitle("Game:  " + getScore());
  }
  
  public boolean isGameOver() {
    return false;
  }
    
  public static void main(String[] args) {


    Game game = new Game();
    
    game.play();
  }
}