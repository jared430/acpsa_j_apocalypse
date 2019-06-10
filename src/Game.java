public class Game {

  // "APOCALYPSE OF THE DAMNED" created by MADISON VELEZ, AHMED DIABY, AND JARED
  // CID

  private Grid title;
  private Grid grid;
  private Grid battle;
  private int userRow;
  private int userCol;
  private int rowLength;
  private int colLength;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private WavPlayer backgroundMusic;
  private int health;
  private String player = "images\\jeremyHeere.gif";
  private String zPic = "images\\zombie2.gif";
  private String[] zombies = { "images\\zombie1.gif", "images\\zombie2.gif", "images\\zombie3.gif" };
  private int time;

  // MAIN CLASS

  public Game() {
    grid = new Grid(10, 20);
    userRow = grid.getNumRows() / 2; // USER IS ALWAYS IN THE MIDDLE OF THE GRID WHEN PARAMETERS CHANGE
    userCol = 0;
    rowLength = grid.getNumRows();
    colLength = grid.getNumCols();
    userRow = rowLength / 2;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    health = 100;
    time = 160;
    updateTitle();
    grid.setBackground("images\\mainBackground.png");
    grid.setMovableBackground("images\\mainBackground.png", 0, 0, 1.0, 1.0);
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
    if (key == 38 || key == 87) { // UP
      if (userRow <= 3) {
        return;
      }
      userRow--;
      Location loc = new Location(userRow, userCol);
      grid.setImage(loc, player);
      Location oldLoc = new Location(userRow + 1, userCol);
      grid.setImage(oldLoc, null);
    } else if (key == 40 || key == 83) { // DOWN
      if (userRow >= 9) {
        return;
      }
      userRow++;
      Location loc = new Location(userRow, userCol);
      grid.setImage(loc, player);
      Location oldLoc = new Location(userRow - 1, userCol);
      grid.setImage(oldLoc, null);
    } else if (key == 37 || key == 65) { // LEFT
      if (userCol <= 0) {
        return;
      }
      userCol--;
      Location loc = new Location(userRow, userCol);
      grid.setImage(loc, player);
      Location oldLoc = new Location(userRow, userCol + 1);
      grid.setImage(oldLoc, null);
    } else if (key == 39 || key == 68) { // RIGHT
      if (userCol >= 19) {
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
    if (Math.random() < 0.50) {
      int place = 3 + (int) (Math.random() * (grid.getNumRows() - 3));
      Location loc = new Location(place, grid.getNumCols() - 1);
      zPic = zombies[(int) (Math.random() * zombies.length)];
      grid.setImage(loc, zPic);
    }
  }

  public void scrollLeft() {
    for (int i = 0; i < grid.getNumRows(); i++) {
      for (int j = 0; j < grid.getNumCols(); j++) {
        Location oldLoc = new Location(i, j);
        Location end = new Location(i, 0);
        Location newLoc = new Location(i, j - 1);
        for (int z = 0; z < zombies.length; z++) {
          if (j == 0) {
            grid.setImage(end, null);
          } else if (zombies[z].equals(grid.getImage(oldLoc)) && player.equals(grid.getImage(newLoc))) {
            battleIntegration();
          } else if (zombies[z].equals(grid.getImage(oldLoc))) {
            grid.setImage(oldLoc, null);
            grid.setImage(newLoc, zombies[z]);
            handleCollision(newLoc);
          }
          grid.setImage(new Location(userRow, userCol), player);
        }
      }
    }
  }

  public void handleCollision(Location zLoc) {
    if (zLoc.equals(new Location(userRow, userCol + 1))) {
      health--;
    }
  }

  public int getTime() {
    return time;
  }

  public int getHealth() {
    return health;
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

  public void setUserRow() {
    int rowLength = grid.getNumRows();
    if (userRow > rowLength) {
      userRow = rowLength - 1;
    } else if (userRow < 0) {
      userRow = 0;
    }
  }

  public void playerAttack(String zombie) {
    battle = new Grid(5, 5, "battleBackground.png");
    battle.setTitle("Player Attack");
    battle.setImage(new Location(3, 1), player);
    battle.setImage(new Location(3, 3), zombie);
    battle.pause(500);
    battle.setImage(new Location(3, 1), null);
    battle.setImage(new Location(3, 2), zombie);
    battle.pause(500);
    battle.setImage(new Location(3, 3), zombie.substring(0, zombie.length() - 4) + "Attack.gif");
    battle.pause(500);
    battle.setImage(new Location(3, 3), null);
    battle.pause(500);
    battle.setImage(new Location(3, 2), null);
    battle.setImage(new Location(3, 1), player);
    battle.pause(500);
    // battle.stop();
  }

  public void enemyAttack(String zombie) {
    battle = new Grid(5, 5, "battleBackground.png");
    battle.setTitle("Enemy Attack");
    battle.setImage(new Location(3, 1), player);
    battle.setImage(new Location(3, 3), zombie);
    battle.pause(500);
    battle.setImage(new Location(3, 1), null);
    battle.setImage(new Location(3, 2), zombie);
    battle.pause(500);
    if (health <= 0) {
      battle.setImage(new Location(3, 1), player.substring(0, player.length() - 4) + "Attack.gif");
      battle.pause(500);
      battle.setImage(new Location(3, 1), null);
    } else {
      battle.setImage(new Location(3, 1), player.substring(0, player.length() - 4) + "Attack.gif");
      battle.pause(500);
      battle.setImage(new Location(3, 1), player);
    }
    battle.pause(500);
    battle.setImage(new Location(3, 2), null);
    battle.setImage(new Location(3, 3), zombie);
    battle.pause(500);
    // battle.close;
    }
  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }
}