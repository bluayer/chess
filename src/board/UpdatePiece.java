package board;

import chess.ChessGui;
import chess.MouseClick;
import piece.GamePiece;
import piece.Position;

/**
 * @author Yi Changmin
 * class about methods update piece in Chess Board
 */
public class UpdatePiece {
  public static void updatePawn(Position prev, Position togo) {
    SearchPieceByPos.searchPawn(prev, MouseClick.board).move(togo);
  }
  
  public static void updateKnight(Position prev, Position togo) {
    SearchPieceByPos.searchKnight(prev, MouseClick.board).move(togo);
  }
  
  public static void updateBishop(Position prev, Position togo) {
    SearchPieceByPos.searchBishop(prev, MouseClick.board).move(togo);
  }
    
  public static void updateRook(Position prev, Position togo) {
    SearchPieceByPos.searchRook(prev, MouseClick.board).move(togo);
  }
  
  public static void updateQueen(Position prev, Position togo) {
    SearchPieceByPos.searchQueen(prev, MouseClick.board).move(togo);
  }
  
  public static void updateKing(Position prev, Position togo) {
    SearchPieceByPos.searchKing(prev, MouseClick.board).move(togo);
  }
  
  public static void updateDead(Position togo) {
    if( !ChessGui.b.getcBoard()[togo.getX()][togo.getY()].isOnPiece() ) {
      return;
    }
    else {
      GamePiece toBeDead = null;
      
      switch(ChessGui.b.getcBoard()[togo.getX()][togo.getY()].getOccupyPiece()) {
      case PAWN:
        toBeDead = SearchPieceByPos.searchPawn(togo, ChessGui.b);
        break;
      case KNIGHT:
        toBeDead = SearchPieceByPos.searchKnight(togo, ChessGui.b);
        break;
      case BISHOP:
        toBeDead = SearchPieceByPos.searchBishop(togo, ChessGui.b);
        break;
      case ROOK:
        toBeDead = SearchPieceByPos.searchRook(togo, ChessGui.b);
        break;
      case QUEEN:
        toBeDead = SearchPieceByPos.searchQueen(togo, ChessGui.b);
        break;
      case KING:
        toBeDead = SearchPieceByPos.searchKing(togo, ChessGui.b);
        break;
      default:
        System.out.println("updateDead: Can't search any piece");
        return;
      }
      
      toBeDead.setAlive(false);
      
    }
    
    return;
  }
}
