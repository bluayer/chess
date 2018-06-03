package board;

import chess.ChessGui;
import piece.Bishop;
import piece.GamePiece;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Position;
import piece.Queen;
import piece.Rook;

/**
 * @author ¿Ã√¢πŒ
 * class about searching piece in chessboard by position
 */
public class SearchPieceByPos {
	static Pawn searchPawn(Position pos, ChessBoard board) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 8; j++) {
	      if(ChessGui.b.pawn[i][j].getPosition().getX() == pos.getX() && ChessGui.b.pawn[i][j].getPosition().getY() == pos.getY()) {
	        if(ChessGui.b.pawn[i][j].isAlive()) {
	          return ChessGui.b.pawn[i][j];
	        }
	        else {
	          continue;
	        }
	      }
	    }
	  }
	  return null;
	}
	static Bishop searchBishop(Position pos, ChessBoard board) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 2; j++) {
	      if(ChessGui.b.bishop[i][j].getPosition().getX() == pos.getX() && ChessGui.b.bishop[i][j].getPosition().getY() == pos.getY()) {
	        if(ChessGui.b.bishop[i][j].isAlive()) {
	          return ChessGui.b.bishop[i][j];
	        }
	        else {
	          continue;
	        }
	      }
	    }
	  }
	  return null;
	}	
	static Knight searchKnight(Position pos, ChessBoard board) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 2; j++) {
	      if(ChessGui.b.knight[i][j].getPosition().getX() == pos.getX() && ChessGui.b.knight[i][j].getPosition().getY() == pos.getY()) {
	        if(ChessGui.b.knight[i][j].isAlive()) {
	          return ChessGui.b.knight[i][j];
	        }
	        else {
	          continue;
	        }
	      }
	    }
	  }
	  return null;
	}
	static Rook searchRook(Position pos, ChessBoard board) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 2; j++) {
	      if(ChessGui.b.rook[i][j].getPosition().getX() == pos.getX() && ChessGui.b.rook[i][j].getPosition().getY() == pos.getY()) {
	        if(ChessGui.b.rook[i][j].isAlive()) {
	          return ChessGui.b.rook[i][j];
	        }
	        else {
	          continue;
	        }
	      }
	    }
	  }
	  return null;
	}	
	static Queen searchQueen(Position pos, ChessBoard board) {
	  for(int i = 0; i < 4; i++) {
	    if(ChessGui.b.queen[i].getPosition().getX() == pos.getX() && ChessGui.b.queen[i].getPosition().getY() == pos.getY()) {
	      if(ChessGui.b.queen[i].isAlive()) {
	        return ChessGui.b.queen[i];
	      }
	      else {
	        continue;
	      }
	    }
	  }
	  return null;
	}
	static King searchKing(Position pos, ChessBoard board) {
	  for(int i = 0; i < 4; i++) {
	    if(ChessGui.b.king[i].getPosition().getX() == pos.getX() && ChessGui.b.king[i].getPosition().getY() == pos.getY()) {
	      if(ChessGui.b.king[i].isAlive()) {
	        return ChessGui.b.king[i];
	      }
	      else {
	        continue;
	      }
	    }
	  }
	  return null;
	}
	
	
	public static GamePiece searchPiece(Position pos, ChessBoard Board) {
	  if(ChessGui.b.getcBoard()[pos.getX()][pos.getY()].isOnPiece()) {
	    
	    switch(ChessGui.b.getcBoard()[pos.getX()][pos.getY()].getOccupyPiece()) {
	    case PAWN:
	      return (GamePiece) searchPawn(pos, Board);
      case BISHOP:
	      return (GamePiece) searchBishop(pos, Board);
	    case KNIGHT:
	      return (GamePiece) searchKnight(pos, Board);
	    case ROOK:
	      return (GamePiece) searchRook(pos, Board);
	    case QUEEN:
	      return (GamePiece) searchQueen(pos, Board);
	    case KING:
	      return (GamePiece) searchKing(pos, Board);
      default:
        System.out.println("searchPiece: nothing found");
        break;
	    }
	  }
	  return null;
	}
	

}
