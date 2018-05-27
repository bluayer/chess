package ChangminYi;

import piece.Pawn;
import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Position;
import piece.Queen;
import piece.Rook;
import piece.CreatePiece;

/**
 * @author ¿Ã√¢πŒ
 * Class about chess board, and moving pieces
 */
public class ChessBoard extends Tile{
	/**
	 * Board is two-dimensional Tile Object array
	 */
	static Tile[][] cBoard;
	
	/**
	 * arrays about initial pieces
	 * can access by using cvtTeam()
	 * Pawn is not built yet
	 * king, queen is only one per team, so one-dimensional
	 * pawn is 5-column
	 * else are 2-column
	 */
	static Pawn[][] pawn = new Pawn[4][5];
	static Knight[][] knight = new Knight[4][2];
	static Bishop[][] bishop = new Bishop[4][2];
	static Rook[][] rook = new Rook[4][2];
	static Queen[] queen = new Queen[4];
	static King[] king = new King[4];
	
	/**
	 * black team: top side, 0
	 * white team: bottom side, 1
	 * red team: left side, 2
	 * green team: right side, 3
	 * by cvtTeam(TEAM) method in Tile class
	 */
	public ChessBoard() {
		for(int i = 0; i < 4; i++) {
			switch(i) {
			case 0:	//black
				for(int j = 0; j < 5; j++) {
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
				for(int j = 0; j < 5; j++) {
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
				for(int j = 0; j < 5; j++) {
					pawn[i][j] = (Pawn) CreatePiece.RPawn(new Position(3 + j, 1));
				}
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = (Bishop) CreatePiece.RBishop(new Position(5 + 3 * j, 0));
					knight[i][j] = (Knight) CreatePiece.RKnight(new Position(4 + 5 * j, 0));
					rook[i][j] = (Rook) CreatePiece.RRook(new Position(3 + 7 * j, 0));

				}
				queen[i] = (Queen) CreatePiece.RQueen(new Position(6, 0));
				king[i] = (King) CreatePiece.RKing(new Position(7, 0));
				break;
			case 3:	//green
				for(int j = 0; j < 5; j++) {
					pawn[i][j] = (Pawn) CreatePiece.GPawn(new Position(3 + j, 12));
				}
				for(int j = 0; j < 2; j++) {
					bishop[i][j] = (Bishop) CreatePiece.GBishop(new Position(5 + 3 * j, 13));
					knight[i][j] = (Knight) CreatePiece.GKnight(new Position(4 + 5 * j, 13));
					rook[i][j] = (Rook) CreatePiece.GRook(new Position(3 + 7 * j, 13));
				}
				queen[i] = (Queen) CreatePiece.GQueen(new Position(7, 13));
				king[i] = (King) CreatePiece.GKing(new Position(6, 13));
			}
		}
		
		
		for(int i = 0; i < cBoard.length; i++) {
			for(int j = 0; j < cBoard.length; j++) {
				//inactive tile creation
				if(((0 <= i && i <= 2) || (11 <= i && i <= 13)) && ((0 <= j && j <= 2) || (11 <= j && j <= 13))) {
					cBoard[i][j] = new Tile(false);
				}
				else {	//active tile creation
					cBoard[i][j] = new Tile(true);
				}
			}
		}
		
		//initializing BLACK, RED team's onPiece
		for(int i = 0; i <= 1; i++) {
			for(int j = 3; j <= 10; j++) {
				cBoard[i][j].onPiece = true;
				cBoard[j][i].onPiece = true;
			}
		}
		//initializing WHITE, GREEN team's onPiece
		for(int i = 12; i <= 13; i++) {
			for(int j = 3; j <= 10; j++) {
				cBoard[i][j].onPiece = true;
				cBoard[j][i].onPiece = true;
			}
		}
	}
	
	/** removeFromBoard
	 * 
	 * set cBoard[i][j].onPiece to false
	 * use when piece is dead
	 * 
	 * @param Position pos
	 * @param TEAM t
	 * @param PIECE p
	 * @param int index for rook, knight, bishop, pawn. use 0 when queen or king
	 */
	public static void removeFromBoard(Position pos) {
		cBoard[pos.getX()][pos.getY()].onPiece = false;
		//removePiece(t, p, index);
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
		cBoard[current.getX()][current.getY()].onPiece = false;
		cBoard[goal.getX()][goal.getY()].onPiece = true;
		return;
	}
	
	/** removePiece
	 * 
	 * erasing specific piece when attacked
	 * used by removeFromBoard
	 * @param TEAM t
	 * @param PIECE p
	 * @param int index for rook, knight, bishop, pawn. use 0 when queen.
	 * there's no king cause' king never dies.
	 */
	private static void removePiece(TEAM t, PIECE p, int index) {
		int rowIndex = cvtTeam(t);
		
		switch(p) {
		case PAWN:
			pawn[rowIndex][index] = null;
			break;
		case KNIGHT:
			knight[rowIndex][index] = null;
			break;
		case BISHOP:
			bishop[rowIndex][index] = null;
			break;
		case ROOK:
			rook[rowIndex][index] = null;
			break;
		case QUEEN:
			queen[rowIndex] = null;
			break;
		default:		
		}
		
		return;
	}

}
