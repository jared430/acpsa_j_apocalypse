public class Game {

  // "APOCALYPSE OF THE DAMNED" created by MADISON VELEZ, AHMED DIABY, AND JARED CID

  private Grid grid;
  private int userRow;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  
  // MAIN CLASS
  
  public Game() {
    grid = new Grid(10, 20);
    userRow = grid.getNumRows()/2;  // USER IS ALWAYS IN THE MIDDLE OF THE GRID WHEN PARAMETERS
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, 0), "user.gif");
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
  }

  public void setUserRow() {
    int rowNum=grid.getNumRows();
    if (userRow < 0){
      userRow = 0;
    } else if(userRow>rowNum) {
    userRow = rowNum - 1;
    }
  }
  
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