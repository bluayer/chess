package gamestate;

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
	/*
	 * if Flag[i]  = 1, then player i is on that state.
	 * 0 = black, 1 = white, 2 = red, 3 = green.
	 */
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

  public int GameResult() {
    if(checkmateFlag[0] + checkmateFlag[1] == 2) {
      return 1; //Team1(Black, White) is win
    }
    else if(checkmateFlag[2] + checkmateFlag[3] == 2) {
      return 2; //Team2(Red, Green) is win
    }
    else if(stalemateFlag[0] + stalemateFlag[1] == 2) {
      return -1; //draw
    }
    else if(stalemateFlag[2] + stalemateFlag[3] == 2) {
      return -1;
    }
    
    else {
      return 0;
    }
  }
}
