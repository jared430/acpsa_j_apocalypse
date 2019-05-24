public class Game {

  // "APOCALYPSE OF THE DAMNED" created by MADISON VELEZ, AHMED DIABY, AND JARED CID

  private Grid grid;
  private int userRow;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  
  // main class 
  public Game() {

    grid = new Grid(10, 20);
<<<<<<< HEAD
    userRow = grid.getNumRows()/2;
=======
    userRow = 0;
>>>>>>> bd63adb50724aa1cc2da4122036cf8729cd0d2f4
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, 0), "user.gif");
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
  
  public void handleKeyPress(){
    int key = grid.checkLastKeyPressed();
  }
  
  public void populateRightEdge(){

  }
  
  public void scrollLeft(){

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
  public void setUserRow(){
    int rowLength=grid.getNumRows();
    if(userRow>rowLength){
      userRow=rowLength-1;
    }
    if(userRow<0){
      userRow=0;
    }
  }
    
  public static void main(String[] args) {


    Game game = new Game();
    
    game.play();
  }
}