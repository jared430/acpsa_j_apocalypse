public class Game {

  // "APOCALYPSE OF THE DAMNED" created by MADISON VELEZ, AHMED DIABY, AND JARED CID

  private Grid grid;
  private int userRow;
  private int userCol;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private String player = "images\\jeremyHeere.gif";
  private String zPic ="images\\zombie1.gif";
  private WavPlayer backgroundMusic;
  private int rowLength;
  private int colLength;
  
  // MAIN CLASS
  
  public Game() {
    grid = new Grid(10, 20);
    userRow = 3;
    userCol = 5;
    rowLength = grid.getNumRows();
    colLength = grid.getNumCols();
    userRow = rowLength / 2;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, userCol), player);
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
    System.out.println(key);
    if(key == 38 || key == 87) {  // UP
     if(userRow <= 0) {
       return;
     }
     userRow--;
     Location loc = new Location(userRow, userCol);
     grid.setImage(loc, player);
     Location oldLoc = new Location(userRow + 1, userCol);
     grid.setImage(oldLoc, null);
    } else if(key == 40 || key == 83) { // DOWN
      if(userRow >= 9) {
        return;
      }
     userRow++;
     Location loc = new Location(userRow, userCol);
     grid.setImage(loc, player);
     Location oldLoc = new Location(userRow - 1, userCol);
     grid.setImage(oldLoc, null);
    } else if(key == 37 || key == 65) { // LEFT
     if(userCol <= 0) {
       return;
      }
     userCol--;
     Location loc = new Location(userRow, userCol);
     grid.setImage(loc, player);
     Location oldLoc = new Location(userRow, userCol + 1);
     grid.setImage(oldLoc, null);
    } else if(key == 39 || key == 68) { // RIGHT
     if(userCol >= 19) {
       return;
     }
     userCol++;
     Location loc = new Location(userRow, userCol);
     grid.setImage(loc, player);
     Location oldLoc = new Location(userRow, userCol - 1);
     grid.setImage(oldLoc, null);
    }
 }

  public void battleIntegration() {

  }

  
  
  public void populateRightEdge() {
    for(int i = 0; i < 1; i++){
      int place = (int)(Math.random()*grid.getNumRows());
      Location loc = new Location(place,grid.getNumCols()-1);
      grid.setImage(loc, zPic);
    }
  }
  
  public void scrollLeft() {
    
   for(int i = 0; i < grid.getNumRows(); i++){
      for(int j = 0; j < grid.getNumCols(); j++){
        Location loc = new Location(i, j);

        if( zPic.equals(grid.getImage(loc))){
          Location enemyLoc = new Location(i, j -1);
          grid.setImage(enemyLoc, zPic);
          Location oldLoc = new Location(i,j);
          grid.setImage(oldLoc, null);
       }
      }
    }
  }
  
  public void handleCollision(Location loc) {

  }

  public int getTime() {
    return 160;
  }
  
  public int getHealth() {
    return 100;
  }

  public int getScore() {
    return 0;
  }
  
  public void updateTitle() {
    grid.setTitle("Game:  " + getScore() + " Health:  " + getHealth() + " Time:  " + getTime());
  }
  
  public boolean isGameOver() {
    return false;
  }
    
  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }
}