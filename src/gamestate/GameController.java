package gamestate;

import board.Status.TEAM;
import piece.GamePiece.Color;

/**
 * This class manages checkFlag, stalemateFlag, checkmateFlag.
 * @see Check
 * @see Stalemate
 * @see Checkmate
 * @author Yeoilgoo
 * @since 2018-06-01
 */

public class GameController {
  public static int[] stalemateFlag = {0, 0, 0, 0};
  public static int[] checkmateFlag = {0, 0, 0, 0};
  public static int[] checkFlag = {0, 0, 0, 0};

  public static Color teamToColor(int num) {
    Color color = null;
    
    switch(num) {
    case 0:
      color = Color.BLACK;
      break;
    case 1:
      color = Color.WHITE;
      break;
    case 2:
      color = Color.RED;
      break;
    case 3:
      color = Color.GREEN;
      break;
    default :
      System.out.println("teamToColor:Error");
    }
    
    return color;
  }
  
  public static TEAM colorToTeam(Color color) {
    TEAM team = null;
    
    switch(color) {
    case WHITE:
      team = TEAM.WHITE;
      break;
    case BLACK:
      team = TEAM.BLACK;
      break;
    case RED:
      team = TEAM.RED;
      break;
    case GREEN:
      team = TEAM.GREEN;
      break;
    default:
        System.out.println("coloToTeam:Error");
    }
    
    return team;
  }
  
  public int GameResult() {
    if(checkmateFlag[0] + checkmateFlag[1] == 2) {
      return 1; //Team1(Black, White) is win
    }
    else if(this.checkmateFlag[2] + checkmateFlag[3] == 2) {
      return 2; //Team2(Red, Green) is win
    }
    else if(stalemateFlag[0] + stalemateFlag[1] == 2) {
      return -1; //draw
    }
    else if(stalemateFlag[2] + stalemateFlag[3] == 2) {
      return -1;
    }
    
    //add code here about skip some player's turn and other draw condition.
    
    else {
      return 0;
    }
  }
}
