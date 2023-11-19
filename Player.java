import java.util.*;
/** Represents a Player
 * @author Aron Choe
 * @author Kenneth Borrero
 * @version 1.4
 * @since 1.0
*/
public class Player {
  public String[][] gameBoard;
  private String p1;
  private String p2;

  /** Creates two players with the specified name.
   * @param a The first player's name.
   * @param b The second player's name.
  */
  public Player (String a, String b) {
    p1 = a;
    p2 = b;
    gameBoard = new String[6][7];
  }

  /** Gets the first player's name.
   * @return A string representing the first player's name.
  */
  public String getP1() {
    return p1;
  }

  /** Gets the second player's name.
   * @return A string representing the second player's name.
  */
  public String getP2() {
    return p2;
  }

  /** Sets the first player's name.
   * @param name A String containing the first player's name.
  */
  public void setP1(String name) {
    p1 = name;
  }

  /** Sets the second player's name.
   * @param name A String containing the second player's name.
  */
  public void setP2(String name) {
    p2 = name;
  }

  /** Builds the Connect Four Board.
  */
  public void build() {
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        gameBoard[i][j] = "[ ]";
      }
    }
  }

  /** Prints out the Connect Four Board.
  */
  public void returnBoard() {
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        System.out.print(gameBoard[i][j]);
      }
      System.out.println("");
    }
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  }

  /** Checks if the player's move is valid.
   * @param col An integer containing the column that the player wants to input their "chip" (String "[R]").
   * @return A boolean value that determines if the move is valid.
  */
  public boolean playerMove(int col) {
    if (openPos(col - 1) != -1) {
      gameBoard[openPos(col - 1)][col - 1] = "[R]";
      return true;
    } else {
      return false;
    }
  }

  /** Checks if computer's move is valid.
   * @param max An integer containing the max amount of columns in the board.
   * @return A boolean value that determines if the move is valid.
  */
  public boolean botMove(int max) {
    int col = 0; 
    int row = 0;
    int start = 0;
    while (openPos(col) == -1 || start == 0) {
      start++;
      col = (int) (Math.random() * max); // 0 to 6
      row = openPos(col);
    }
    gameBoard[row][col] = "[B]";
    return true;
  }

  /** Checks if the second player's move is valid.
   * @param col An integer containing the column that the player wants to input their "chip" (String "[B]").
   * @return A boolean value that determines if the move is valid. Since the second player is not valid in this class, the boolean value returned (for this class) will be false.
  */
  public boolean twoMove(int col) {
    return false;
  }

  /** Checks if the position of the player's move is valid, determining if the column is full or not.
   * @param col An integer containing the column that the player wants to input their "chip."
   * @return An integer value that determines the row in which the chip would be placed. If the column is full, then -1 would be returned.
  */
  public int openPos(int col) {
    if (col < 0 || col > 7) {
      return -1;
    }
    for (int i = 5; i >= 0; i--) {
      if (!gameBoard[i][col].equals("[R]") && !gameBoard[i][col].equals("[B]")) {
        return i;
      }
    }
    return -1;
  }

  /** Checks if the Connect Four board is full.
   * @return A boolean value that determines if every slot in gameBoard is filled.
  */
  public boolean isFull(){
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        if(gameBoard[i][j].equals("[ ]")){
          return true;
        }
      }
    }
    return false;
  }

  /** Checks if a player has won.
   * @param color A string representing the player's chip.
   * @return A boolean value that determines if the specified player won.
  */
  public boolean checkWin(String color) {
    // Diagonally Up
    for (int i = 0; i < gameBoard[0].length - 3; i++) {
      for (int j = 3; j < gameBoard.length; j++) {
        if (gameBoard[j][i].equals(color) && gameBoard[j - 1][i + 1].equals(color)
            && gameBoard[j - 2][i + 2].equals(color) && gameBoard[j - 3][i + 3].equals(color)) {
          return true;
        }
      }
    }

    // Diagonally Down
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        if (gameBoard[j][i].equals(color) && gameBoard[j + 1][i + 1].equals(color)
            && gameBoard[j + 2][i + 2].equals(color) && gameBoard[j + 3][i + 3].equals(color)) {
          return true;
        }
      }
    }

    // Horizontal
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length - 3; j++) {
        if(gameBoard[i][j].equals(color) && gameBoard[i][j+1].equals(color) && gameBoard[i][j+2].equals(color) && gameBoard[i][j+3].equals(color)){
          return true;
        }
      }
    }

    // Vertical
    for(int i = 0; i < gameBoard[0].length; i++){
      for(int j = 0; j < gameBoard.length - 3; j++){
        if(gameBoard[j][i].equals(color) && gameBoard[j+1][i].equals(color) && gameBoard[j+2][i].equals(color) && gameBoard[j+3][i].equals(color)){
          return true;
        }
      }
    }
    return false;    
  }
}
