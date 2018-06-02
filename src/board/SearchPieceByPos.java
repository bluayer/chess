package board;

import piece.Bishop;
import piece.GamePiece;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Position;
import piece.Queen;
import piece.Rook;


public class SearchPieceByPos {
	static Pawn searchPawn(Position pos, ChessBoard board) {
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 8; j++) {
	      if(board.pawn[i][j].getPosition().getX() == pos.getX() && board.pawn[i][j].getPosition().getY() == pos.getY()) {
	        if(board.pawn[i][j].isAlive()) {
	          return board.pawn[i][j];
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
	      if(board.bishop[i][j].getPosition().getX() == pos.getX() && board.bishop[i][j].getPosition().getY() == pos.getY()) {
	        if(board.bishop[i][j].isAlive()) {
	          return board.bishop[i][j];
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
	      if(board.knight[i][j].getPosition().getX() == pos.getX() && board.knight[i][j].getPosition().getY() == pos.getY()) {
	        if(board.knight[i][j].isAlive()) {
	          return board.knight[i][j];
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
	      if(board.rook[i][j].getPosition().getX() == pos.getX() && board.rook[i][j].getPosition().getY() == pos.getY()) {
	        if(board.rook[i][j].isAlive()) {
	          return board.rook[i][j];
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
	    if(board.queen[i].getPosition().getX() == pos.getX() && board.queen[i].getPosition().getY() == pos.getY()) {
	      if(board.queen[i].isAlive()) {
	        return board.queen[i];
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
	    if(board.king[i].getPosition().getX() == pos.getX() && board.king[i].getPosition().getY() == pos.getY()) {
	      if(board.king[i].isAlive()) {
	        return board.king[i];
	      }
	      else {
	        continue;
	      }
	    }
	  }
	  return null;
	}
	
	
	public static GamePiece searchPiece(Position pos, ChessBoard Board) {
	  if(Board.getcBoard()[pos.getX()][pos.getY()].isOnPiece()) {
	    
	    switch(Board.getcBoard()[pos.getX()][pos.getY()].getOccupyPiece()) {
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
	    }
	  }
	  return null;
	}
	

}
