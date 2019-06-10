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

    backgroundMusic = new WavPlayer("audio\\BMCTPG8BIT.wav");

    title = new Grid(10, 20/* , "images\\titleScreen.png" */);
    grid = new Grid(10, 20);
    userRow = grid.getNumRows() / 2; // USER IS ALWAYS IN THE MIDDLE OF THE GRID WHEN PARAMETERS CHANGE
    userCol = 0;
    rowLength = grid.getNumRows();
    colLength = grid.getNumCols();
    userRow = rowLength / 2;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    health = 3;
    time = 30000;
    updateTitle();
    grid.setBackground("images\\mainBackground.png");
    grid.fullscreen();
    // backgroundMusic.startSound();
    grid.setMovableBackground("images\\mainBackground.png", 0, 0, 1.0, 1.0);
    grid.setImage(new Location(userRow, userCol), player);
  }

  public void play() {

    backgroundMusic.startSound();
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

          } else if (zombies[z].equals(grid.getImage(oldLoc))) {
            grid.setImage(oldLoc, null);
            grid.setImage(newLoc, zombies[z]);

            handleCollision(newLoc, zombies[z]);

          }
          grid.setImage(new Location(userRow, userCol), player);
        }
      }
    }
  }

  public void handleCollision(Location zLoc, String zombie) {
    if (zLoc.equals(new Location(userRow, userCol + 1))) {

      // run the animoationikjl
      if (Math.random() > 0.50) {
        playerAttack(zombie);
      } else {
        enemyAttack(zombie);
        health--;
      }

      grid.setImage(zLoc, null);

    }
  }

  public int getTime() {
    return (time - msElapsed) / 1000;
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
    return (health == -1) || (getTime() < 0);
  }

  public void setUserRow() {
    int rowLength = grid.getNumRows();
    if (userRow > rowLength) {
      userRow = rowLength - 1;
    } else if (userRow < 0) {
      userRow = 0;
    }
  }

  private void attackStart(String zombie) {

    battle = new Grid(5, 5, "images\\battleBackground.png");
    battle.setTitle("Player Attack");

    // display
    battle.setImage(new Location(3, 1), player);
    battle.setImage(new Location(3, 3), zombie);
    battle.pause(500);
  }

  public void playerAttack(String zombie) {

    attackStart(zombie);

    // move Jeremy over
    battle.setImage(new Location(3, 1), null);
    battle.setImage(new Location(3, 2), player);
    battle.pause(500);

    // check which zombie we have

    String zombieSlash = "";

    if (zombie.equals("images\\zombie1.gif")) {
      zombieSlash = "images\\attackedZombie1.gif";
    }

    if (zombie.equals("images\\zombie2.gif")) {
      zombieSlash = "images\\attackedZombie2.gif";
    }

    if (zombie.equals("images\\zombie3.gif")) {
      zombieSlash = "images\\attackedZombie3.gif";
    }

    // slash zombie
    battle.setImage(new Location(3, 3), zombieSlash);
    battle.pause(500);
    // add sound effect
    battle.setImage(new Location(3, 3), null);
    battle.pause(500);

    // retreat Jereemy
    battle.setImage(new Location(3, 2), null);
    battle.setImage(new Location(3, 1), player);

    // blinky death

    battle.pause(1500);

    // close screen
    battle.close();
  }

  public void enemyAttack(String zombie) {

    attackStart(zombie);

    // move zombie over
    battle.setImage(new Location(3, 3), null);
    battle.setImage(new Location(3, 2), zombie);
    battle.pause(500);

    // check which zombie we have

    String playerSlash = "";

    if (player.equals("images\\jeremyHeere.gif")) {
      playerSlash = "images\\attackedJeremyHeere.gif";
    }

    if (player.equals("images\\michaelMell.gif")) {
      playerSlash = "images\\attackedMichaelMell.gif";
    }

    // slash player
    battle.setImage(new Location(3, 1), playerSlash);
    battle.pause(500);
    // add sound effect
    battle.setImage(new Location(3, 1), null);
    battle.pause(500);

    // blink player

    for (int i = 0; i < 5; i++) {
      battle.setImage(new Location(3, 1), null);
      battle.pause(100);
      battle.setImage(new Location(3, 1), playerSlash);
      battle.pause(100);
    }

    // retreat zombie
    battle.setImage(new Location(3, 2), null);
    battle.pause(1500);

    // close screen
    battle.close();
  }

  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }
}