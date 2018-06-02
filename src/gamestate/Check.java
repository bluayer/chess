package gamestate;

import board.ChessBoard;
import board.SearchPieceByPos;
import chess.ChessGui;
import piece.GamePiece;
import piece.GamePiece.Color;
import piece.GamePiece.PieceType;
import piece.King;
import piece.Position;


/**
 * 
 * @author SongJeongWoo
 *
 */
public class Check {
  
  public Check(){}

  private ChessBoard board = ChessGui.b;
  
  public boolean isCheck(King king) {
    Color color = king.getColor();
    Color oppositeColor1 = null, oppositeColor2 = null;

    int kingX = king.getPosition().getX();
    int kingY = king.getPosition().getY();
    
    switch (color) {
    case BLACK:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.GREEN;
      break;
    case WHITE:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.GREEN;
      break;
    case RED:
      oppositeColor1 = Color.BLACK;
      oppositeColor2 = Color.WHITE;
      break;
    case GREEN:
      oppositeColor1 = Color.RED;
      oppositeColor2 = Color.WHITE;
      break;
    default:
    }

    Position nowPos = new Position(0, 0);
    for (int i = 0; i < 14; i++) {
      for (int j = 0; j < 14; j++) {
        nowPos.setX(i);
        nowPos.setY(j);

        if (board.getcBoard()[i][j].getActive() == true) {
          if (board.getcBoard()[i][j].isOnPiece() == true) {
            if (SearchPieceByPos.searchPiece(nowPos, board).getPieceType() != PieceType.KING) {
              GamePiece piece = SearchPieceByPos.searchPiece(nowPos, board);
              if (piece.getColor() == oppositeColor1 || piece.getColor() == oppositeColor2) {
                if (piece.getCanMoves() != null) {
                  for (int k = 0; k < piece.getCanMoves().length; k++) {               
                      if (piece.getCanMoves()[k].getX() == kingX
                          && piece.getCanMoves()[k].getY() == kingY) {
                        return true;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }

    return false;
  }
  
}
