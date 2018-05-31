package board;

import piece.Bishop;
import piece.CreatePiece;
import piece.GamePiece.PieceType;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Position;
import piece.Queen;
import piece.Rook;

/**
 * @author ChangminYi
 * Class about chess board, and moving pieces
 */
public class ChessBoard extends Tile{
	/**
	 * Board is two-dimensional Tile Object array
	 */
	private static Tile[][] cBoard = new Tile[14][14];
  
	/**
	 * arrays about initial pieces
	 * can access by using cvtTeam()
	 * Pawn is not built yet
	 * king, queen is only one per team, so one-dimensional
	 * pawn is 5-column
	 * else are 2-column
	 */
	public static Pawn[][] pawn;
	public static Knight[][] knight;
	public static Bishop[][] bishop;
	public static Rook[][] rook;
	public static Queen[] queen;
	public static King[] king;
	
	/**
	 * black team: top side, 0
	 * white team: bottom side, 1
	 * red team: left side, 2
	 * green team: right side, 3
	 * by cvtTeam(TEAM) method in Tile class
	 */
	public ChessBoard() {
	  pawn = new Pawn[4][8];
	  knight = new Knight[4][2];
	  bishop = new Bishop[4][2];
	  rook = new Rook[4][2];
	  queen = new Queen[4];
	  king = new King[4];
	  
	  
	  
	  for(int i = 0; i < 4; i++) {
	    switch(i) {
	    case 0:	//black
	      for(int j = 0; j < 8; j++) {
	        pawn[i][j] = (Pawn) CreatePiece.BPawn(new Position(1, 3 + j));
				}
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = (Bishop) CreatePiece.BBishop(new Position(0, 5 + 3 * j));
					knight[i][j] = (Knight) CreatePiece.BKnight(new Position(0, 4 + 5 * j));
					rook[i][j] = (Rook) CreatePiece.BRook(new Position(0, 3 + 7 * j));

				}
				queen[i] = (Queen) CreatePiece.BQueen(new Position(0, 7));
				king[i] = (King) CreatePiece.BKing(new Position(0, 6));
				break;
				
			case 1:	//white
				for(int j = 0; j < 8; j++) {
					pawn[i][j] = (Pawn) CreatePiece.WPawn(new Position(12, 3 + j));
				}
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = (Bishop) CreatePiece.WBishop(new Position(13, 5 + 3 * j));
					knight[i][j] = (Knight) CreatePiece.WKnight(new Position(13, 4 + 5 * j));
					rook[i][j] = (Rook) CreatePiece.WRook(new Position(13, 3 + 7 * j));

				}
				queen[i] = (Queen) CreatePiece.WQueen(new Position(13, 6));
				king[i] = (King) CreatePiece.WKing(new Position(13, 7));				
				break;
				
			case 2:	//red
				for(int j = 0; j < 8; j++) {
					pawn[i][j] = (Pawn) CreatePiece.RPawn(new Position(3 + j, 1));
				}
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = (Bishop) CreatePiece.RBishop(new Position(5 + 3 * j, 0));
					knight[i][j] = (Knight) CreatePiece.RKnight(new Position(4 + 5 * j, 0));
					rook[i][j] = (Rook) CreatePiece.RRook(new Position(3 + 7 * j, 0));

				}
				queen[i] = (Queen) CreatePiece.RQueen(new Position(7, 0));
				king[i] = (King) CreatePiece.RKing(new Position(6, 0));
				break;
			case 3:	//green
			  
				for(int j = 0; j < 8; j++) {
					pawn[i][j] = (Pawn) CreatePiece.GPawn(new Position(3 + j, 12));
				}
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = (Bishop) CreatePiece.GBishop(new Position(5 + 3 * j, 13));
					knight[i][j] = (Knight) CreatePiece.GKnight(new Position(4 + 5 * j, 13));
					rook[i][j] = (Rook) CreatePiece.GRook(new Position(3 + 7 * j, 13));
				}
				queen[i] = (Queen) CreatePiece.GQueen(new Position(6, 13));
				king[i] = (King) CreatePiece.GKing(new Position(7, 13));
			}
		}
		
		
		
		for(int i = 0; i < 14; i++) {
			for(int j = 0; j < 14; j++) {
				//inactive tile creation
				if(((0 <= i && i <= 2) || (11 <= i && i <= 13)) && ((0 <= j && j <= 2) || (11 <= j && j <= 13))) {
					getcBoard()[i][j] = new Tile(false, PieceType.NOPE);
				}
				else {	//active tile creation
					getcBoard()[i][j] = new Tile(true, PieceType.NOPE);
				}
			}
		}
		
		//initializing BLACK, RED team's onPiece
		for(int i = 0; i <= 1; i++) {
			for(int j = 3; j <= 10; j++) {
				getcBoard()[i][j].setOnPiece(true);
				getcBoard()[j][i].setOnPiece(true);
			}
		}
		//initializing WHITE, GREEN team's onPiece
		for(int i = 12; i <= 13; i++) {
			for(int j = 3; j <= 10; j++) {
				getcBoard()[i][j].setOnPiece(true);
				getcBoard()[j][i].setOnPiece(true);
			}
		}
		
		for(int i = 3; i < 11; i++) {
		  getcBoard()[1][i].setOccupyPiece(PieceType.PAWN);
		  getcBoard()[12][i].setOccupyPiece(PieceType.PAWN);
		  getcBoard()[i][1].setOccupyPiece(PieceType.PAWN);
		  getcBoard()[i][12].setOccupyPiece(PieceType.PAWN);
		}
		for(int i = 3; i < 7; i++) {
		  switch (i) {
		  case 3:   //rook
		    getcBoard()[0][i].setOccupyPiece(PieceType.ROOK);
		    getcBoard()[0][13 - i].setOccupyPiece(PieceType.ROOK);
		    getcBoard()[13][i].setOccupyPiece(PieceType.ROOK);
		    getcBoard()[13][13 - i].setOccupyPiece(PieceType.ROOK);
		    getcBoard()[i][0].setOccupyPiece(PieceType.ROOK);
		    getcBoard()[13 - i][0].setOccupyPiece(PieceType.ROOK);
		    getcBoard()[i][13].setOccupyPiece(PieceType.ROOK);
		    getcBoard()[13 - i][13].setOccupyPiece(PieceType.ROOK);
		    break;
		    
		  case 4:   //knight
        getcBoard()[0][i].setOccupyPiece(PieceType.KNIGHT);
        getcBoard()[0][13 - i].setOccupyPiece(PieceType.KNIGHT);
        getcBoard()[13][i].setOccupyPiece(PieceType.KNIGHT);
        getcBoard()[13][13 - i].setOccupyPiece(PieceType.KNIGHT);
        getcBoard()[i][0].setOccupyPiece(PieceType.KNIGHT);
        getcBoard()[13 - i][0].setOccupyPiece(PieceType.KNIGHT);
        getcBoard()[i][13].setOccupyPiece(PieceType.KNIGHT);
        getcBoard()[13 - i][13].setOccupyPiece(PieceType.KNIGHT);
		    break;
		    
		  case 5:   //bishop
        getcBoard()[0][i].setOccupyPiece(PieceType.BISHOP);
        getcBoard()[0][13 - i].setOccupyPiece(PieceType.BISHOP);
        getcBoard()[13][i].setOccupyPiece(PieceType.BISHOP);
        getcBoard()[13][13 - i].setOccupyPiece(PieceType.BISHOP);
        getcBoard()[i][0].setOccupyPiece(PieceType.BISHOP);
        getcBoard()[13 - i][0].setOccupyPiece(PieceType.BISHOP);
        getcBoard()[i][13].setOccupyPiece(PieceType.BISHOP);
        getcBoard()[13 - i][13].setOccupyPiece(PieceType.BISHOP);
		    break;
		    
		  case 6:   //king, queen
        getcBoard()[0][13 - i].setOccupyPiece(PieceType.QUEEN);
        getcBoard()[0][i].setOccupyPiece(PieceType.KING);
        getcBoard()[13][13 - i].setOccupyPiece(PieceType.KING);
        getcBoard()[13][i].setOccupyPiece(PieceType.QUEEN);
        getcBoard()[13 - i][0].setOccupyPiece(PieceType.QUEEN);
        getcBoard()[i][0].setOccupyPiece(PieceType.KING);
        getcBoard()[13 - i][13].setOccupyPiece(PieceType.KING);
        getcBoard()[i][13].setOccupyPiece(PieceType.QUEEN);
		    break;
		  }
		}
	}
	
	/** removeFromBoard
	 * 
	 * set cBoard[i][j].onPiece to false
	 * use when piece is dead
	 * 
	 * @param Position pos
	 */
	public static void removeFromBoard(Position pos) {
		getcBoard()[pos.getX()][pos.getY()].setOnPiece(false);
		return;
	}
	
	/** updateTile
	 * 
	 * set cBoard[goalX][goalY].onPiece to true, and set cBoard[currnetX][currentY].onPiece to false
	 * use when piece move or attack
	 * 
	 * @param Position goal: to-move position
	 * @param Position current: current position
	 */
	
	public static void updateTile(Position current, Position goal) {
		getcBoard()[current.getX()][current.getY()].setOnPiece(false);
		getcBoard()[goal.getX()][goal.getY()].setOccupyPiece(getcBoard()[current.getX()][current.getY()].getOccupyPiece());
		getcBoard()[current.getX()][current.getY()].setOccupyPiece(PieceType.NOPE);
		getcBoard()[goal.getX()][goal.getY()].setOnPiece(true);
		System.out.println("updateTile: " + current.getX() + ", " + current.getY() + " to " + goal.getX() + ", " + goal.getY());
		return;
	}

  public static Tile[][] getcBoard() {
    return cBoard;
  }

  public static void setcBoard(Tile[][] cBoard) {
    ChessBoard.cBoard = cBoard;
  }
	

	
}
