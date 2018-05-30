package board;

import chess.MouseClick;
import piece.Position;

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
}
