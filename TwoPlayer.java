import java.util.*;
/** Represents a Second Player
 * @author Aron Choe
 * @author Kenneth Borrero
 * @version 1.2
 * @since 1.0
*/
public class TwoPlayer extends Player {

  /** Creates two players with the specified name.
   * @param a The first player's name.
   * @param b The second player's name.
  */
  public TwoPlayer(String a, String b) {
    super(a, b);
  }

  /** Checks if the second player's move is valid.
   * @param col An integer containing the column that the player wants to input their "chip" (String "[B]").
   * @return A boolean value that determines if the move is valid.
  */
  public boolean twoMove(int col) {
    if (openPos(col - 1) != -1) {
      gameBoard[openPos(col - 1)][col - 1] = "[B]";
      return true;
    } else {
      return false;
    }
  }
}
