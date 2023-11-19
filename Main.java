import java.util.Scanner;

/** Runs "Kennect4" -> the application
 * @author Aron Choe
 * @author Kenneth Borrero
 * @version 1.7
 * @since 1.0
*/
public class Main {

  /** Gets the program up and running.
  */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Single or Multiplayer [S/M]: ");
    String amtPlayer = in.nextLine();

    System.out.println("Enter your Name: ");
    String name1 = in.nextLine();
    
    if(amtPlayer.equals("S")) {
      Player play = new Player(name1, "bot");
      gameLoop(play, amtPlayer);
    }
    else if(amtPlayer.equals("M")){
      System.out.println("Enter Player Two Name: ");
      TwoPlayer play = new TwoPlayer(name1, in.nextLine());
      gameLoop(play, amtPlayer);
    }
    else {
      System.out.println("Wrong Input!");
    }
    
  }
  
  /** Continuously Loops the game. Also, prints out all parts of the game making sure it is up and running and easy to use.
   * @param play A player object used to properly enact the methods of the player class following the rules and regulations of the game
   * @param amt A String containing a S or M to determine the amount of players
  */
  public static void gameLoop(Player play, String amt){
    String winner = "";
    Scanner put = new Scanner(System.in);
    
    boolean end = false;
    play.build();

    while(!end){
      if(amt.equals("S")){
        System.out.println(play.getP1() + ", Enter the Column (1-7): ");
        int col = put.nextInt();
        play.playerMove(col);
        play.returnBoard();
        if(play.checkWin("[R]")){
          end = true;
          winner = play.getP1();
          break;
        }
          
        play.botMove(7);

        play.returnBoard();
        if(play.checkWin("[B]")){
          end = true;
          winner = "bot";
          break;
        }
        else if(!play.isFull()){
          end = true;
          winner = "nobody";
          break;
        }
      }
      else if(amt.equals("M")){
        System.out.println(play.getP1() + ", Enter the Column (1-7): ");
        int col = put.nextInt();
        while(!(play.playerMove(col))){
          System.out.println(play.getP1() + ", Enter a Different Column (1-7): ");
          col = put.nextInt();
        }
          
        play.returnBoard();
        if(play.checkWin("[R]")){
          end = true;
          winner = play.getP1();
          break;
        }

        System.out.println(play.getP2() + ", Enter the Column (1-7): ");
        int colu = put.nextInt();
        while(play instanceof TwoPlayer && !(play.twoMove(colu))){
          System.out.println(play.getP2() + ", Enter a Different Column (1-7): ");
          colu = put.nextInt();
        }
        play.returnBoard();
        if(play.checkWin("[B]")){
          end = true;
          winner = play.getP2();
          break;
        }
        else if(!play.isFull()){
          end = true;
          winner = "nobody";
          break;
        }
      }
    }

    System.out.println("The winner is: " + winner);
      
    Scanner again = new Scanner(System.in);

    System.out.println("Play Again? [Y/N] ");
    String playCheck = again.nextLine();
    if(playCheck.equals("Y")){
      System.out.println("Single or Multiplayer [S/M]: ");
      String amount = again.nextLine();

      System.out.println("Enter Player One Name: ");
      String one = again.nextLine();

      if(amount.equals("S")) {
        Player another = new Player(one, "bot");
        gameLoop(another, amount);
      }
      else if(amount.equals("M")){
        System.out.println("Enter Player Two Name: ");
        TwoPlayer another = new TwoPlayer(one, again.nextLine());
        gameLoop(another, amount);
      }
    }
  }
}
