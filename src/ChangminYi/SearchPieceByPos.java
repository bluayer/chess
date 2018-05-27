package ChangminYi;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Position;
import piece.Queen;
import piece.Rook;

public class SearchPieceByPos {
	public static Pawn searchPawn(Position pos) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 5; j++) {
	      if(ChessBoard.pawn[i][j].getPosition() == pos) {
	        return ChessBoard.pawn[i][j];
	      }
	    }
	  }
	  return null;
	}
	
	public static Bishop searchBishop(Position pos) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 2; j++) {
	      if(ChessBoard.bishop[i][j].getPosition() == pos) {
	        return ChessBoard.bishop[i][j];
	      }
	    }
	  }
	  return null;
	}
	
	public static Knight searchKnight(Position pos) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 2; j++) {
	      if(ChessBoard.knight[i][j].getPosition() == pos) {
	        return ChessBoard.knight[i][j];
	      }
	    }
	  }
	  return null;
	}
	
	public static Rook searchRook(Position pos) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 2; j++) {
	      if(ChessBoard.rook[i][j].getPosition() == pos) {
	        return ChessBoard.rook[i][j];
	      }
	    }
	  }
	  return null;
	}
	
	public static Queen searchQueen(Position pos) {
	  for(int i = 0; i < 4; i++) {
	    if(ChessBoard.queen[i].getPosition() == pos) {
	      return ChessBoard.queen[i];
	    }
	  }
	  return null;
	}
	  
	public static King searchKing(Position pos) {
	  for(int i = 0; i < 4; i++) {
	    if(ChessBoard.king[i].getPosition() == pos) {
	      return ChessBoard.king[i];
	    }
	  }
	  return null;
	}
}
