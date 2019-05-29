public class Game {

  // "APOCALYPSE OF THE DAMNED" created by MADISON VELEZ, AHMED DIABY, AND JARED CID

  private Grid grid;
  private int userRow;
  private int userCol;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private WavPlayer backgroundMusic;
  private String player;
  private int rowLength;
  private int colLength;
  
  // MAIN CLASS
  
  public Game() {
    
    grid = new Grid(10, 20);
    rowLength=grid.getNumRows();
    colLength=grid.getNumCols();
    userRow = rowLength/ 2;
    userCol = 0;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, userCol), "user.gif");
  }
  
  public void play() {
    while (!isGameOver()) {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0) {
        scrollLeft();
        populateRightEdge();
      }
      updateTitle();
      msElapsed += 100;
    }
  }
  
  public void handleKeyPress() {
    int key = grid.checkLastKeyPressed();
    Location loc;
    Location oldLoc;
    System.out.println(key);
    if(key == 38 )
   {
     if(userRow <= 0){
       return;
     }
     userRow--;
      loc = new Location(userRow, userCol);
     grid.setImage(loc, "user.gif");

     oldLoc = new Location(userRow + 1, userCol);
     grid.setImage(oldLoc, null);
   }
   else if(key == 40)
   {
   if(userRow >= 9){
     return;
   }
     userRow++;
     loc = new Location(userRow, userCol);
     grid.setImage(loc, "user.gif");

      oldLoc = new Location(userRow - 1, userCol);
     grid.setImage(oldLoc, null);
   }
   else if(key == 37)
   {
     if(userCol <= 0)
     {
       return;
     }
     userCol--;
      loc = new Location(userRow, userCol);
     grid.setImage(loc, "user.gif");

      oldLoc = new Location(userRow, userCol+1);
     grid.setImage(oldLoc, null);
   }
   else if(key == 39)
   {
     if(userCol >= colLength - 1){
       return;
     }
     userCol++;
      loc = new Location(userRow, userCol);
     grid.setImage(loc, "user.gif");

      oldLoc = new Location(userRow, userCol - 1);
     grid.setImage(oldLoc, null);
   }
 }
  

  public void battleIntegration() {

  }

  public void setUserLocation() {
    int rowNum = grid.getNumRows();
    int colNum=grid.getNumCols();
    if (userRow < 0){
      userRow = 0;
    } else if(userRow > rowNum) {
      userRow = rowNum - 1;
    }
    if(userCol<0){
      userCol = 0;
    }
    else if(userCol > colNum){
     userCol = colNum - 1;
    }
  }
  
  public void populateRightEdge() {

  }
  
  public void scrollLeft() {

  }
  
  public void handleCollision(Location loc) {

  }
  
  public int getHealth() {
    return 0;
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