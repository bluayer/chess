package ChangminYi;

import piece.*;

/**
 * @author ¿Ã√¢πŒ
 * Class about chess board, and moving pieces
 */
public class ChessBoard extends Tile{
	/**
	 * Board is two-dimensional Tile Object array
	 */
	static Tile[][] board;
	
	/**
	 * black team: top side
	 * white team: bottom side
	 * red team: left side
	 * green team: right side
	 */
	public ChessBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				//inactive tile creation
				if(((0 <= i && i <= 2) || (11 <= i && i <= 13)) && ((0 <= j && j <= 2) || (11 <= j && j <= 13))) {
					board[i][j] = new Tile(false);
				}
				else {	//active tile creation
					board[i][j] = new Tile(true);
				}
			}
		}
		
		//initializing BLACK team
		for(int i = 0; i <= 1; i++) {
			for(int j = 3; j <= 10; j++) {
				board[i][j].onPiece = true;
				board[i][j].team = TEAM.BLACK;
			}
		}
		//initializing WHITE team
		for(int i = 12; i <= 13; i++) {
			for(int j = 3; j <= 10; j++) {
				board[i][j].onPiece = true;
				board[i][j].team = TEAM.WHITE;
			}
		}
		//initializing RED team
		for(int i = 3; i <= 10; i++) {
			for(int j = 0; j <= 1; j++) {
				board[i][j].onPiece = true;
				board[i][j].team = TEAM.RED;
			}
		}
		//initializing GREEN team
		for(int i = 3; i <= 10; i++) {
			for(int j = 12; j <= 13; j++) {
				board[i][j].onPiece = true;
				board[i][j].team = TEAM.GREEN;
			}
		}
	}

	/**
	 * checking whether moving is legal
	 * @param x: to-move x coordinate
	 * @param y: to-move y coordinate
	 * @param team: team parameter
	 * @param piece: piece parameter
	 * @return if move is qualified, return true. else, return false.
	 */
	public boolean legalMove(int x, int y, TEAM team, PIECE piece) {
		if(board[x][y].active) {
			if(piece == PIECE.PAWN) {	//pawn's move is much fucking than others.
				switch(team) {
				case BLACK:
					if(x) {
						return true;
					}
					else {
						return false;
					}
				case WHITE:
					if(x) {
						return true;
					}
					else {
						return false;
					}
				case RED:
					if(x) {
							return true;
						}
						else {
							return false;
						}
				case GREEN:
					if(x) {
							return true;
						}
						else {
							return false;
						}
				default:
						break;
				}
			}
			else {	//other's move
				switch(piece){
				case KNIGHT:
					break;
				case ROOK:
					break;
				case BISHOP:
					break;
				case QUEEN:
					break;
				case KING:
					break;
				default:
					break;
				}
			}
		}
		else {	//moving to inactive tile
			System.out.println("illegal move: moving to wrong area");
			return false;
		}
	}
	
	public static Tile[][] getInstance() {
		return board;
	}
}
