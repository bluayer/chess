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
	 * 0 = white, 1 = red, 2 = black, 3 = green.
	 */
  public static int[] stalemateFlag = {0, 0, 0, 0};
  public static int[] checkmateFlag = {0, 0, 0, 0};
  public static int[] checkFlag = {0, 0, 0, 0};

  public static Color teamToColor(int num) {
    Color color = null;
    
    switch(num) {
    case 0:
      color = Color.WHITE;
      break;
    case 1:
      color = Color.RED;
      break;
    case 2:
      color = Color.BLACK;
      break;
    case 3:
      color = Color.GREEN;
      break;
    default :
      System.out.println("teamToColor:Error");
    }
    
    return color;
  }
}
